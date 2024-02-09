----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    18:14:47 11/04/2023 
-- Design Name: 
-- Module Name:    modulo_1 - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
------
----------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity modulo_1 is
    Port ( A, B : in STD_LOGIC_VECTOR(3 downto 0);
				E : in STD_LOGIC;
           C: out STD_LOGIC_VECTOR (3 downto 0));
end modulo_1;

architecture Behavioral of modulo_1 is
begin
	process (A,B,E)
	begin
			
		if E = '0' then 
			C <= (others => '0');
		else 
			C(3) <=  (A(3) and B(3)) or ((not A(3)) and (not B(3)));
			C(2) <=  (A(2) and B(2)) or ((not A(2)) and (not B(2)));
			C(1) <=  (A(1) and B(1)) or ((not A(1)) and (not B(1)));
			C(0) <=  A(0) xnor B(0);
		end if;
	end process;
end Behavioral;


	










