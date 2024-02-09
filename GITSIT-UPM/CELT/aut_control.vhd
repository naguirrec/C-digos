----------------------------------------------------------------------------------
-- 
-- Autmata de control del receptor
-- Establece la comunicacin SPI con el MCP3201 y obtiene la muestra leda
--
----------------------------------------------------------------------------------

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity aut_control is
    Port ( CLK        : in  STD_LOGIC;                         -- reloj del sistema
           SPI_DATA   : in  STD_LOGIC;                         -- entrada de datos del puerto SPI
			  SPI_CLK    : out  STD_LOGIC;                        -- Salida de reloj del puerto SPI
           SPI_CS     : out  STD_LOGIC;                        -- Chip Select del puerto SPI
			  FIN_CONV   : out  STD_LOGIC;                        -- Fin de conversin A/D
			  DATOS_ADC  : out  STD_LOGIC_VECTOR (15 downto 0));  -- Dato ledo del ADC
end aut_control;

architecture a_aut_control  of aut_control is

type STATE_TYPE is (ESPERA_1S,ACTIVAR_CS,CLK1,CLK0,DESACT_CS);--el automata tiene los siguientes estados

signal ST : STATE_TYPE := ESPERA_1S;
signal cont : unsigned (15 downto 0):=(others=>'0');
signal data : unsigned (15 downto 0):=(others=>'0');

begin

process (CLK)
  begin
  if (CLK'event and CLK='1') then
    case ST is
	 
	 --vemos como se comporta el automata en funcion del estado en el que esta
	 
      when ACTIVAR_CS =>
		  cont<=(others=>'0'); --el contador pasa a 0
		  ST<=CLK0;					-- cambia el estado a clk0 siempre
		
		-- OTROS CASOS
		when CLK0 =>
		  --cont<=(others=>'0');
		  data<=data(14 downto 0) & SPI_DATA; --se desplazan y anaden los datos
		  cont<=cont+1; --se suma 1 al contador
		  ST<=CLK1;		--pasa de estadoi a clk1
		  
		when CLK1 =>
		  if (cont>=15) then -- cuando el contador llega a 15
			ST<=DESACT_CS;		--el estado pasa a ser desac
		  else 
			ST<=CLK0;		--y sino vuelve a clk0
		  end if;
		 
		when DESACT_CS =>
		  cont<=(others=>'0'); --el contador pasa a 0 y
		  ST<=ESPERA_1S;			--el estado pasa a espera
		  
		when ESPERA_1S =>
		  cont<=cont+1;	--en este estado se suma 1 al contador
		  data<=(others=>'0');	--y se vacian los datos
		  if(cont>=1000) then--si el contador es mayor de 1000
			ST<=ACTIVAR_CS;	--el estado pasa a activar
		  else
			ST<=ESPERA_1S;--y si no llega a 1000 se mantiene en espera
		  end if;
		 		
    end case;
  end if;
  end process;
  
-- PARTE COMBINACIONAL

SPI_CS <= '0' when (ST=ACTIVAR_CS or ST=CLK0 or ST=CLK1)--la salida de spi vale 0 en estos estados
				else '1'; --y 1 en el resto
DATOS_ADC <= STD_LOGIC_VECTOR  (data) ;--salen los datos provenientes de data
FIN_CONV<= '1' when ST=DESACT_CS --solo vale 1 durante el estado de desact
					else '0';  --y vale 0 en el resto de estados
SPI_CLK<= '1' when (ST=CLK1 or ST=DESACT_CS)--la salida es 1 en los estados de clk1 y desact
					else '0';            --y 0 en el resto 
  
end a_aut_control;
