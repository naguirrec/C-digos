
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.numeric_std.ALL;
 
ENTITY modulo3_tb IS
END modulo3_tb;
 
ARCHITECTURE behavior OF modulo3_tb IS 
     
    COMPONENT modulo_3
    PORT(
         D : IN  std_logic_vector(3 downto 0);
         salida : OUT  std_logic_vector(6 downto 0)
        );
    END COMPONENT;
    
   signal D : std_logic_vector(3 downto 0) := (others => '0');
   signal salida : std_logic_vector(6 downto 0);
   
  constant values : std_logic_vector (112 downto 1) :=X"00000000003DFFE17EDB3F3B587E";
  signal contador : unsigned (4 downto 0) :="00000";
  signal contador2 : integer range 0 to 15 :=0;
  
BEGIN
 
	 uut: modulo_3 PORT MAP (
          D => D,
          salida => salida
        );
stim_proc: process
	variable top,bot : integer range 0 to 128;
	variable fallo : integer range 0 to 16;
	
   begin		
     	top := 7 * (contador2 +1);
		bot := (7 * contador2) + 1;
		contador <= contador +1;
		contador2 <= contador2 +1;
		d <= std_logic_vector(contador(3 downto 0));
		 wait for 20 ns;
		 if salida/= values(top downto bot) then
--		   report "Error";
			fallo := fallo +1;
--		 else report "Ok";
		 end if;
		 if contador > "01111" then 
		 if fallo > 0 then report "Módulo con ERRORES";
		 else
		  report "Módulo CORRECTO";
		 end if;
		 wait;
		 end if;
	end process;

END;
