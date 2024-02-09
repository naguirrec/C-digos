----------------------------------------------------------------------------------
--
-- Divisor de la frecuencia del reloj a 1 KHz.
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity div_reloj is
    Port ( CLK_50MHz : in  STD_LOGIC;       -- Entrada reloj de la FPGA 50 MHz
           CLK       : out  STD_LOGIC);     -- Salida reloj a 1 KHz
end div_reloj;

architecture a_div_reloj of div_reloj is

signal contador : unsigned (31 downto 0);
signal frec_div : STD_LOGIC;

begin

process(CLK_50MHz)
  begin
  if (CLK_50MHz'event and CLK_50MHz='1') then --En cada flanco de subida
    contador<=contador+1;                     --Incrementar el contador
	 if (contador=25000) then                   -- Asignamos el valor de contador tras realizar las operaciones pertinentes
	   contador<=(others=>'0');                 -- Poner el contador a 0
		frec_div<=not frec_div;                   --e intercambiar el valor de frec_div
	 end if;
  end if;
  
  end process;
  
CLK<=frec_div;

end a_div_reloj;

