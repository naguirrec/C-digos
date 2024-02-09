----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:57:50 11/04/2023 
-- Design Name: 
-- Module Name:    master_mind - Behavioral 
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

entity master_mind is
 Port ( A,B : in STD_LOGIC_VECTOR (3 downto 0);
 E : in STD_LOGIC;
 salida: out STD_LOGIC_VECTOR (6 downto 0);
 F : out STD_LOGIC);
end master_mind;

architecture Behavioral of master_mind is

component modulo_1
		port( A : in STD_LOGIC_VECTOR (3 downto 0); 
				B : in STD_LOGIC_VECTOR (3 downto 0);
				E : in STD_LOGIC;
				C : out STD_LOGIC_VECTOR (3 downto 0));
end component;
	
component modulo_2
		port( C : in STD_LOGIC_VECtOR(3 downto 0); 
				D : out STD_LOGIC_VECtOR(3 downto 0));
end component;
	
component modulo_3
		port( D : in STD_LOGIC_VECTOR (3 downto 0); 
				salida : out STD_LOGIC_VECTOR (6 downto 0));
end component;
	
signal Cc, Dc : STD_LOGIC_VECTOR (3 downto 0);
	
begin
	C1: modulo_1
		port map ( A(0) => A(0),
						A(1) => A(1),
						A(2) => A(2),
						A(3) => A(3),
						B(0) => B(0),
						B(1) => B(1),
						B(2) => B(2),
						B(3) => B(3),
						E => E,
						C(0) => Cc(0),
						C(1) => Cc(1),
						C(2) => Cc(2),
						C(3) => Cc(3) );
		
	C2: modulo_2
		port map ( C(0) => Cc(0),
						C(1) => Cc(1),
						C(2) => Cc(2),
						C(3) => Cc(3),
						D(0) => Dc(0),
						D(1) => Dc(1),
						D(2) => Dc(2),
						D(3) => Dc(3) );
						
	C3: modulo_3
		port map ( D(0) => Dc(0),
						D(1) => Dc(1),
						D(2) => Dc(2),
						D(3) => Dc(3),
						salida(0)=> salida(0),
						salida(1)=> salida(1),
						salida(2)=> salida(2),
						salida(3)=> salida(3),
						salida(4)=> salida(4),
						salida(5)=> salida(5),
						salida(6)=> salida(6));
						
F <= Dc(2);
	
	
end Behavioral;

