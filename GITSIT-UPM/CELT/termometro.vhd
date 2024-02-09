----------------------------------------------------------------------------------
-- Termmetro basado en el LM35 y ADC MCP3201
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity termometro is
    Port ( CLK_50MHz : in  STD_LOGIC;               -- Reloj del sistema
           SPI_DATA : in  STD_LOGIC;                -- Entrada de datos del puerto SPI
			  SPI_CLK : out  STD_LOGIC;                -- Salida de reloj del puerto SPI
           SPI_CS : out  STD_LOGIC;                 -- Salida chip select del puerto SPI
           AN : out  STD_LOGIC_VECTOR (3 downto 0); -- Salida de seleccin de los displays
           SEG7 : out  STD_LOGIC_VECTOR (0 to 6);   -- Salida para los segmentos de los displays
           DP : out  STD_LOGIC);                    -- Salida para el punto decimal de los displays
end termometro;

architecture a_termometro of termometro is

component div_reloj is
    Port ( CLK_50MHz : in  STD_LOGIC;       -- Entrada reloj de la FPGA 50 MHz
           CLK       : out  STD_LOGIC);     -- Salida reloj a 1 KHz
end component;

   -- OTROS COMPONENTES
	
component aut_control is
    Port ( CLK        : in  STD_LOGIC;                         -- reloj del sistema
           SPI_DATA   : in  STD_LOGIC;                         -- entrada de datos del puerto SPI
			  SPI_CLK    : out  STD_LOGIC;                        -- Salida de reloj del puerto SPI
           SPI_CS     : out  STD_LOGIC;                        -- Chip Select del puerto SPI
			  FIN_CONV   : out  STD_LOGIC;                        -- Fin de conversin A/D
			  DATOS_ADC  : out  STD_LOGIC_VECTOR (15 downto 0));  -- Dato ledo del ADC
end component;

component ADC_a_TEMP is
    Port ( DATOS_ADC : in  STD_LOGIC_VECTOR (15 downto 0);     -- Datos del convertidor A/D
           TEMP      : out  STD_LOGIC_VECTOR (15 downto 0));   -- Salida temperatura en punto fijo con 6 bits decimales
end component;

component TEMP_a_BCD is
    Port ( TEMP     : in  STD_LOGIC_VECTOR (15 downto 0);  -- Temperatura con 6 bits decimales
           DECENAS  : out  STD_LOGIC_VECTOR (3 downto 0);  -- Decenas en BCD
           UNIDADES : out  STD_LOGIC_VECTOR (3 downto 0);  -- Unidades en BCD
           DECIMAS  : out  STD_LOGIC_VECTOR (3 downto 0)); -- Dcimas en BCD 
end component;

component visualizacion is
    Port ( ENABLE : in STD_LOGIC;                         -- Entrada de ENABLE
	        DECENAS : in  STD_LOGIC_VECTOR (3 downto 0);   -- Entrada decenas
	        UNIDADES : in  STD_LOGIC_VECTOR (3 downto 0);   -- Entrada unidades
	        DECIMAS : in  STD_LOGIC_VECTOR (3 downto 0);   -- Entrada dcimas
           CLK     : in  STD_LOGIC;                   -- Entrada de reloj
           SEG7 : out  STD_LOGIC_VECTOR (0 to 6);      -- Salida para los displays 
			  DP   : out STD_LOGIC;                       -- Salida punto decimal
           AN   : out  STD_LOGIC_VECTOR (3 downto 0)); -- Activacin individual
end component;
	
	-- POSIBLES SEALES NECESARIAS
signal C0 : STD_LOGIC;
signal T0 : STD_LOGIC_VECTOR(15 downto 0);--señal que conecta ADC_a_TEMP con TEMP_a_BCD
signal T1 : STD_LOGIC_VECTOR(3 downto 0);--señal que conecta DECENAS con E3
signal T2 : STD_LOGIC_VECTOR(3 downto 0);--Señal que conecta UNIDADES con E2
signal T3 : STD_LOGIC_VECTOR(3 downto 0);--Señal que conecta DECIMAS con E1
signal A2 : STD_LOGIC_VECTOR(15 downto 0);--Señal que conecta DATOS_ADC del autómata con ADC_a_TEMP
signal A1 : STD_LOGIC;--FIN_CONV con ENABLE

begin

U1 : div_reloj
	port map (CLK_50MHz => CLK_50MHz,
				 CLK => C0);
	
U2 : aut_control
	port map(SPI_CS => SPI_CS,
				SPI_CLK => SPI_CLK,
				SPI_DATA => SPI_DATA,
				FIN_CONV => A1,
				DATOS_ADC => A2,
				CLK => C0);
	
U3 : ADC_a_TEMP 
	port map (DATOS_ADC =>A2 ,
				 TEMP => T0);
	
U4 : TEMP_a_BCD
	port map (TEMP => T0,
				 DECENAS => T1,
				 UNIDADES => T2,
				 DECIMAS => T3);
				 
U5 : Visualizacion 
	port map (ENABLE => A1,
				 DECENAS => T1,
				 UNIDADES => T2,
				 DECIMAS => T3, 
				 CLK => C0,
				 SEG7 => SEG7,
				 AN => AN,
				 DP => DP);
				 
           
   -- OTRAS INTERCONEXIONES

end a_termometro;

