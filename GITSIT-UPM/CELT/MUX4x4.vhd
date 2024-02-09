----------------------------------------------------------------------------------
-- MUX4x4

--  Multiplexor de 4 entradas de 4 bits
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity MUX4x4 is
    Port ( E0 : in  STD_LOGIC_VECTOR (3 downto 0); -- Entrada 0
           E1 : in  STD_LOGIC_VECTOR (3 downto 0); -- Entrada 1
           E2 : in  STD_LOGIC_VECTOR (3 downto 0); -- Entrada 2
           E3 : in  STD_LOGIC_VECTOR (3 downto 0); -- Entrada 3
           S : in  STD_LOGIC_VECTOR (1 downto 0);  -- Seal de control
           Y : out  STD_LOGIC_VECTOR (3 downto 0)); -- Salida
end MUX4x4;

architecture a_MUX4x4 of MUX4x4 is

begin
    with S select Y<=
	 
			E0 when "00",-- Selecciona la entrada 0 cuando el bit de control es 0
			E1 when "01", -- Selecciona la entrada 1
			E2 when "10", -- Selecciona la entrada 2
			E3 when others; -- Selecciona la entrada 3 por defecto

end a_MUX4x4;

