----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:17:40 12/14/2023 
-- Design Name: 
-- Module Name:    act_sensor - Behavioral 
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

entity act_sensor is
 Port ( s1 : in STD_LOGIC;
 start : in STD_LOGIC;
 clk : in STD_LOGIC;
 rst : in STD_LOGIC;
 edges : out STD_LOGIC_VECTOR(7 downto 0);
 ready : out STD_LOGIC);
end act_sensor;

architecture Behavioral of act_sensor is
component pulse_generator is
 Port ( start : in STD_LOGIC;
 clk : in STD_LOGIC;
 rst : in STD_LOGIC;
 pulse : out STD_LOGIC);
end component;

component edge_detector is
 Port ( s1 : in STD_LOGIC;
		clk : in STD_LOGIC;
		rst : in STD_LOGIC;
		s1_edges : out STD_LOGIC);
end component;

component edge_counter is
 Port ( s1_edges : in STD_LOGIC;
 start : in STD_LOGIC;
 pulse : in STD_LOGIC;
 clk : in STD_LOGIC;
 rst : in STD_LOGIC;
 edges : out STD_LOGIC_VECTOR (7 downto 0));
end component;

begin
	U1: pulse_generator
		port map(start => start,
					clk => clk,
					rst => rst,
					pulse => x);
					
	U2: edge_detector
		port map(s1 => s1,
					clk => clk,
					rst => rst,
					s1_edges => y);
					
	U3: edge_counter
		port map(pulse => x,
					start => start,
					s1_edges => y,
					clk => clk,
					rst => rst,
					edges => edges);

	ready <= not(x);
end Behavioral;

