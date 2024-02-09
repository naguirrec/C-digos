----------------------------------------------------------------------------------
-- 
-- Registro que almacena los datos de DECENAS, UNIDADES y DCIMAS
-- cada vez que se activa el ENABLE
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity registro is
    Port ( CLK     : in   STD_LOGIC;                       -- entrada de reloj
	        ENABLE  : in   STD_LOGIC;                       -- enable 
           E0      : in   STD_LOGIC_VECTOR (3 downto 0);   -- entrada E0
           E1      : in   STD_LOGIC_VECTOR (3 downto 0);   -- entrada E1
           E2      : in   STD_LOGIC_VECTOR (3 downto 0);   -- entrada E2
           E3      : in   STD_LOGIC_VECTOR (3 downto 0);   -- entrada E3
           Q0      : out  STD_LOGIC_VECTOR (3 downto 0);   -- salida Q0
           Q1      : out  STD_LOGIC_VECTOR (3 downto 0);   -- salida Q1
           Q2      : out  STD_LOGIC_VECTOR (3 downto 0);   -- salida Q2
           Q3      : out  STD_LOGIC_VECTOR (3 downto 0));  -- salida Q3
end registro;

architecture a_registro of registro is

signal QS0 : STD_LOGIC_VECTOR (3 downto 0):="0000"; -- seal que almacena el valor de Q0
signal QS1 : STD_LOGIC_VECTOR (3 downto 0):="0000"; -- seal que almacena el valor de Q1
signal QS2 : STD_LOGIC_VECTOR (3 downto 0):="0000"; -- seal que almacena el valor de Q2
signal QS3 : STD_LOGIC_VECTOR (3 downto 0):="0000"; -- seal que almacena el valor de Q3

begin
  process (CLK)
    begin
      if (CLK'event and CLK='1') then   -- con cada flanco activo
		   if(ENABLE='1') then
			
			-- Realizar el proceso de captura
			QS0<=E0;
			QS1<=E1;
			QS2<=E2;
			QS3<=E3;
			
			end if;
		end if;
  end process;
  -- CABLEADO DE LAS SALIDAS

	Q0<=QS0;
	Q1<=QS1;
	Q2<=QS2;
	Q3<=QS3;

end a_registro;

