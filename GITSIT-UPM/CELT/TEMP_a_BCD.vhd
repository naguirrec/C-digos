----------------------------------------------------------------------------------
-- Convierte 16 bits en punto fijo con 10 enteros y 6 decimales en BCD
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity TEMP_a_BCD is
    Port ( TEMP     : in  STD_LOGIC_VECTOR (15 downto 0);  -- Temperatura con 6 bits decimales
           DECENAS  : out  STD_LOGIC_VECTOR (3 downto 0);  -- Decenas en BCD
           UNIDADES : out  STD_LOGIC_VECTOR (3 downto 0);  -- Unidades en BCD
           DECIMAS  : out  STD_LOGIC_VECTOR (3 downto 0)); -- Dcimas en BCD 
end TEMP_a_BCD;

architecture a_TEMP_a_BCD of TEMP_a_BCD is

signal ENT : unsigned (9 downto 0):="0000000000";  -- Parte entera de la temperatura
signal DEC : unsigned (5 downto 0):="000000";     -- Parte decimal de la temperatura
signal s_unid : unsigned (9 downto 0):="0000000000";        -- unidades de la temperatura

begin

-- Separamos parte entera y parte decimal
 ENT <= unsigned(STD_LOGIC_VECTOR(TEMP(15 downto 6)));--Cogemos los bits que nos interesan de la señal TEMP y la transformamos en tipo unsigned
 DEC <= unsigned(STD_LOGIC_VECTOR(TEMP(5 downto 0)));																	
--Para asignar los valores que nos interesan seguimos las tablas proporcionadas en el enunciado para determinar los intervalos 
--requeridos, para definir las memorias ROM usamos la estructura when else estipulada en el enunciado, siguiendo la misma metodologia para las demás ROM
				
	DECENAS <=
				"1001" when ENT >= 90 else
				"1000" when ENT >= 80 else
				"0111" when ENT >= 70 else
				"0110" when ENT >= 60 else
				"0101" when ENT >= 50 else
				"0100" when ENT >= 40 else
				"0011" when ENT >= 30 else
				"0010" when ENT >= 20 else
				"0001" when ENT >= 10 else 
			    "0000"; -- Valor por defecto en caso de rango no esperado /// others => 0 ;
			
-- Las unidades se determinan restando las decenas al valor de la temperatura.
			
	s_unid <=
			ENT - 90 when ENT >= 90 else
			ENT -	80 when ENT >= 80 else
			ENT -	70 when ENT >= 70 else
			ENT -	60 when ENT >= 60 else
			ENT -	50 when ENT >= 50 else
			ENT -	40 when ENT >= 40 else
			ENT -	30 when ENT >= 30 else
			ENT -	20 when ENT >= 20 else
			ENT -	10 when ENT >= 10 else 
			ENT;
	
							  
	DECIMAS <= 
				"0000" when DEC < 6 else
				"0001" when DEC < 12 else
				"0010" when DEC < 18 else
				"0011" when DEC < 24 else
				"0100" when DEC < 30 else
				"0101" when DEC < 36 else
				"0110" when DEC < 42 else
				"0111" when DEC < 48 else
				"1000" when DEC < 54 else 
			    "1001" ;
         
UNIDADES<= STD_LOGIC_VECTOR(s_unid( 3 downto 0));  --Transformamos la señal y nos quedamos con los bits que nos interesan, en este caso, los 4 bits menos significativos de s_unid

end a_TEMP_a_BCD;