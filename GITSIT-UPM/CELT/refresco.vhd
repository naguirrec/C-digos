----------------------------------------------------------------------------------
-- refresco

-- Circuito que refresca los displays peridicamente
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;


entity refresco is
    Port ( CLK : in  STD_LOGIC;                  -- reloj de refresco
           S : out  STD_LOGIC_VECTOR (1 downto 0);   -- Control para el mux
			  DP : out STD_LOGIC;                       -- Control del punto decimal
           AN : out  STD_LOGIC_VECTOR (3 downto 0)); -- Control displays individuales
end refresco;

architecture a_refresco of refresco is

		signal QS : unsigned (1 downto 0):="00"; --la que va con s
		--signal PAN : unsigned (3 downto 0):="0000";
		--signal DPS : STD_LOGIC:='1';

begin
  
  process (CLK)
    begin
	 if (CLK'event and CLK='1') then
			QS <= QS+1;
			
	 -- if (QS="01") then
		--	DPS <='0';
		 --end if;
	 end if;

	 end process;
	 
-- Completar cableado de:

		S <= STD_LOGIC_VECTOR(QS);
			--se activan con un 0 en funcion de la senal qs
		AN<= "1110" when QS="00" else--cuando se recibe 00 se activa el panel de la derecha
			 "1101" when QS="01" else--con un 01 el segundo de la drcha
			 "1011" when QS="10" else--con 10 el segundo de la izqda
			 "0111" when QS="11"; --con un 11 el de la izda
		
		DP <= '1' when QS="00" else
			 '1' when QS="01" else
			 '0' when QS="10" else
			 --cuando se activa el segundo panel se activa el 
			 --dot point (con un 0)
			 '1' when QS="11";

 
 end a_refresco;

