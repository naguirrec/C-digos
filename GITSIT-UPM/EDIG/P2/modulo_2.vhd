----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:56:55 11/04/2023 
-- Design Name: 
-- Module Name:    modulo_2 - Behavioral 
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

entity modulo_2 is
	Port(C :in STD_LOGIC_VECTOR(3 downto 0);
		  D: out STD_LOGIC_VECTOR(3 downto 0));
end modulo_2;

architecture Behavioral of modulo_2 is
signal S0,S1,S2,S3 : STD_LOGIC;
component FA is
	Port ( 
		sum_a : in STD_LOGIC;
		sum_b : in STD_LOGIC;
		cin : in STD_LOGIC;
		sum : out STD_LOGIC;
		cout : out STD_LOGIC
	);
end component;
signal Sum1, Cout1, Cout2: STD_LOGIC;

begin --Se declaran 3 FA, cadad uno sumará el nº de aciertos en la señal que recibe y los pasa al siguiente FA, además de sacarlo como D
	FA1 : FA port map ( 
			sum_a => C(1),
			sum_b => '0',
			cin => C(0),
			sum => Sum1,
			cout => Cout1 );
	
	FA2 : FA port map (
			sum_a => Sum1,
			sum_b => C(2),
			cin => C(3),
			sum => D(0),
			cout => Cout2 );
			
	FA3 : FA port map (
			sum_a => Cout1,
			sum_b => Cout2,
			cin => '0',
			sum => D(1),
			cout => D(2) );
			
			D(3) <= '0';
			

end Behavioral;

