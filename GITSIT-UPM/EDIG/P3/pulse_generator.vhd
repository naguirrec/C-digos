----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:16:13 12/14/2023 
-- Design Name: 
-- Module Name:    pulse_generator - Behavioral 
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

entity pulse_generator is
 Port ( start : in STD_LOGIC;
 clk : in STD_LOGIC;
 rst : in STD_LOGIC;
 pulse : out STD_LOGIC);
end pulse_generator;

architecture Behavioral of pulse_generator is
signal y: unsigned (4 downto 0) :=(others=>'0');
signal z: unsigned (4 downto 0) :=(others=>'0');
begin
	process (z,start)
	begin
		if ((z=0 and start/='1') or (z=25)) then
			y <= "00000";
		elsif (z=0 and start = '1') then
			y <= "00001";
		else
			y <= z + 1;
		end if;
	end process;
	
	process(clk)
	begin	
		if ( clk'event and clk='1') then
			if(rst= '1') then
				z <= (others => '0');
			else
				z <= y;
			end if;
		end if;
	end process;

	process(z)
	begin
		if (z>0) then
			pulse <= '1';
		else
			pulse <= '0';
		end if;
	end process;
		
end Behavioral;

