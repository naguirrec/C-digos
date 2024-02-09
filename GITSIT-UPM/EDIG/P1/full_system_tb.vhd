--------------------------------------------------------------------------------
-- 
-- Modificar el cdigo de alumno en la lnea 35
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.numeric_std.ALL;
 
ENTITY full_system_tb IS
END full_system_tb;
 
ARCHITECTURE behavior OF full_system_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT full_system
    PORT(
         IN0 : IN  std_logic;
         IN1 : IN  std_logic;
         IN2 : IN  std_logic;
         S : IN  std_logic;
         Z : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs

	signal entrada : std_logic_vector (3 downto 0) :="0000";
 	--Outputs
   signal Z : std_logic;
	signal rest : integer :=0;
	signal cont,flag : integer range 0 to 15 :=0;
	
	constant codigo : integer := 103; 
	
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: full_system PORT MAP (
          IN0 => entrada(0),
          IN1 => entrada(1),
          IN2 => entrada(2),
          S => entrada(3),
          Z => Z
        );

 rest <= codigo mod 2;
 

   -- Stimulus process
   stim_proc: process
	variable sol : std_logic;
   begin		
      -- hold reset state for 100 ns.
      wait for 20 ns;	
		cont <= cont +1;
		entrada <= std_logic_vector(unsigned(entrada) +1 );
	
		if rest=0 then 
		     sol := not(entrada(0)) or ( entrada(3) and (not(entrada(1)) or not(entrada(2))));
		   if Z /= sol then
				  flag <= flag +1;
			 end if;
		  else
		    sol := (not(entrada(3)) and entrada(0)) or 
			         ( entrada(3) and not(entrada(2)) and not(entrada(1)) and not(entrada(0)));
          if Z /= sol then
              flag<= flag +1;
		     end if;
		 end if;
		 
		 if entrada >= "1111" then 
		   if flag > 0 then report "Mdulo Full_system.vhd con Errores";
		     else report "Mdulo Full_system.vhd Correcto";
		   end if;
         wait;
		 end if;
		 
   end process;

END;
