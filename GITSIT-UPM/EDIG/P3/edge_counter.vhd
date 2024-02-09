----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:17:12 12/14/2023 
-- Design Name: 
-- Module Name:    edge_counter - Behavioral 
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
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity edge_counter is
 Port ( s1_edges : in STD_LOGIC;
 start : in STD_LOGIC;
 pulse : in STD_LOGIC;
 clk : in STD_LOGIC;
 rst : in STD_LOGIC;
 edges : out STD_LOGIC_VECTOR (7 downto 0));
end edge_counter;

architecture Behavioral of edge_counter is
signal x: unsigned (7 downto 0) :=(others=>'0');
signal y: unsigned (7 downto 0) :=(others=>'0');
signal z: unsigned (7 downto 0) :=(others=>'0');

begin

	process(pulse,start,s1_edges,z)
	begin
		if(pulse='0' and start='1') then
			x <= edges;
		elsif (pulse='1' and s1_edges='1') then
			y <= edges + 1;
		else
			edges <= edges;
		end if;
	end process;
	
	process(clk)
	begin
		if (clk'event and clk='1') then
			if (rst='1') then
				z <= (others => '0');
			else
				z <= y;
			end if;
		end if;
	end process;
	
edges<= std_logic_vector(z);

end Behavioral;

