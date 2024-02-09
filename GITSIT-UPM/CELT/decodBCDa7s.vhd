----------------------------------------------------------------------------------
--
-- Decodificador de BCD a 7 segmentos 
--
-- Para valores BCD superiores a 9 representa la cifra hexadecimal ABCDEF
--
-- Departamento de Ingenier�a Electr�nica 2023
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity decodBCDa7s is
    Port ( BCD : in  STD_LOGIC_VECTOR (3 downto 0);    -- Entrada del valor BCD
           SEGMENTOS : out  STD_LOGIC_VECTOR (0 to 6));  -- Salidas al display
end decodBCDa7s;

architecture a_decodBCDa7s of decodBCDa7s is

begin
   with BCD select SEGMENTOS<=      -- abcdefg
"0000001" when "0000",
"1001111" when "0001",           --        a  
"0010010" when "0010",           --       ----
"0000110" when "0011",           --    f |    | b
"1001100" when "0100",           --      | g  |
"0100100" when "0101",           --       ----
"0100000" when "0110",           --    e |    | c 
"0001111" when "0111",           --      |    |
"0000000" when "1000",           --       ----
"0001100" when "1001",           --        d 
"0001000" when "1010",
"1100000" when "1011",     --     Los segmentos se encienden con un '0'  
"0110001" when "1100",
"1000010" when "1101",
"0110000" when "1110",
"0111000" when "1111",
"1111111" when others;


end a_decodBCDa7s;

