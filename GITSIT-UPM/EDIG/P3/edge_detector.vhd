----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:24:49 12/21/2023 
-- Design Name: 
-- Module Name:    edge_detector - Behavioral 
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
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity edge_detector is
 Port ( s1 : in STD_LOGIC;
		clk : in STD_LOGIC;
		rst : in STD_LOGIC;
		s1_edges : out STD_LOGIC);
end edge_detector;

architecture Behavioral of edge_detector is
signal x: std_logic := '0';
signal y: std_logic := '0';
signal x_mas: std_logic := '0';
signal y_mas: std_logic := '0';
begin

		process (x,s1)
		begin
			if(s1 = '1') then
				x_mas <= s1;
				y_mas <= x;
			end if;
		end process;

		process (clk)
		begin
			if (clk'event and clk='1') then
				if(rst = '1') then
					x <= x_mas;
					y <= y_mas;
				else
					x <= x_mas;
					y <= y_mas;
				end if;
			end if;
		end process;
		
	s1_edges <= x xor y;
end Behavioral;

