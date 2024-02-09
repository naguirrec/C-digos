----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    10:46:02 10/09/2023 
-- Design Name: 
-- Module Name:    full_system - Behavioral 
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

entity full_system is
    Port ( IN0 : in  STD_LOGIC;
           IN1 : in  STD_LOGIC;
           IN2 : in  STD_LOGIC;
           S : in  STD_LOGIC;
           Z : out  STD_LOGIC);
end full_system;

architecture Behavioral of full_system is

component circuito1
	port (IN0 : in STD_LOGIC;
			IN1 : in STD_LOGIC;
			IN2 : in STD_LOGIC;
			A1 : out STD_LOGIC);
end component;

component circuito2
	port( IN0 : in STD_LOGIC;
			IN1 : in STD_LOGIC;
			IN2 : in STD_LOGIC;
			A2 : out STD_LOGIC);
end component;

component multiplexor
	port (A1 : in STD_LOGIC;
			A2 : in STD_LOGIC;
			S : in STD_LOGIC;
			Z: out STD_LOGIC);
end component;

signal A1,A2 :STD_LOGIC;
begin 

U0 : circuito1 
	port map(IN0 => IN0, IN1 => IN1, IN2 => IN2, A1 => A1);
	
U1 : circuito2
	port map(IN0 => IN0, IN1=>IN1, IN2=>IN2, A2=>A2);
	
U2 : multiplexor
	port map(A1=>A1, A2=>A2, S=>S, Z=>Z);

end Behavioral;

