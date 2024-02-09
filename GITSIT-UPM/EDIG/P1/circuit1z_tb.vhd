--------------------------------------------------------------------------------
-- 
-- Modificar el cdigo de alumno en la lnea 32
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.numeric_std.ALL;
 
ENTITY circuit1_tb IS
END circuit1_tb;
 
ARCHITECTURE behavior OF circuit1_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT circuito1
    PORT(
         IN0 : IN  std_logic;
         IN1 : IN  std_logic;
         IN2 : IN  std_logic;
         A1 : OUT  std_logic);
    END COMPONENT;
    

   --Inputs
	signal entrada : std_logic_vector (2 downto 0):= "000";

 	--Outputs
   signal A1 : std_logic;
	
   
  constant codigo : integer := 103;
  
  
  constant sout0: std_logic_vector :="01010101";
  constant sout1: std_logic_vector :="10101010";
  signal rest : integer :=0;
  signal sout : std_logic_vector (7 downto 0) :="00000000";
  signal cont,flag : integer range 0 to 7 :=0;
    
BEGIN
 
 
   rest <= codigo mod 2;
   sout <= sout0 when rest=0  else
	        sout1;
			  
	-- Instantiate the Unit Under Test (UUT)
   uut: circuito1 PORT MAP (
          IN0 => entrada(0),
          IN1 => entrada(1),
          IN2 => entrada(2),
          A1 => A1);

   
   -- Stimulus process
stim_proc: process
   begin		
      wait for 20 ns;	
		cont <= cont +1;
		entrada <= std_logic_vector(unsigned(entrada) +1 );
	  
		if A1 /= sout(cont) then 
			  flag<=flag+1;
		end if;
		 
		if entrada >= "111" then 
		 if flag > 0 then report "Mdulo Circuto1.vhd con Errores";
		  else report "Mdulo Circuto1.vhd Correcto";
		 end if;
      wait;
		end if;
		
   end process;

END;
