----------------------------------------------------------------------------------
-- 
-- Mdulo de visualizacin, presenta los datos 
-- en los displays capturndolos con la seal ENABLE. 
--
----------------------------------------------------------------------------------

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity visualizacion is
    Port ( ENABLE : in STD_LOGIC;                         -- Entrada de ENABLE
	        DECENAS : in  STD_LOGIC_VECTOR (3 downto 0);   -- Entrada decenas
	        UNIDADES : in  STD_LOGIC_VECTOR (3 downto 0);   -- Entrada unidades
	        DECIMAS : in  STD_LOGIC_VECTOR (3 downto 0);   -- Entrada dcimas
           CLK     : in  STD_LOGIC;                   -- Entrada de reloj
           SEG7 : out  STD_LOGIC_VECTOR (0 to 6);      -- Salida para los displays 
			  DP   : out STD_LOGIC;                       -- Salida punto decimal
           AN   : out  STD_LOGIC_VECTOR (3 downto 0)); -- Activacin individual
end visualizacion;

architecture a_visualizacion of visualizacion is

-- COMPONENTES

component decodBCDa7s
  Port ( BCD : in  STD_LOGIC_VECTOR (3 downto 0);    -- Entrada del valor BCD
         SEGMENTOS : out  STD_LOGIC_VECTOR (0 to 6));  -- Salidas al display
end component;

component registro
  Port (   CLK     : in   STD_LOGIC;                       -- entrada de reloj
	        ENABLE  : in   STD_LOGIC;                       -- enable 
           E0      : in   STD_LOGIC_VECTOR (3 downto 0);   -- entrada E0
           E1      : in   STD_LOGIC_VECTOR (3 downto 0);   -- entrada E1
           E2      : in   STD_LOGIC_VECTOR (3 downto 0);   -- entrada E2
           E3      : in   STD_LOGIC_VECTOR (3 downto 0);   -- entrada E3
           Q0      : out  STD_LOGIC_VECTOR (3 downto 0);   -- salida Q0
           Q1      : out  STD_LOGIC_VECTOR (3 downto 0);   -- salida Q1
           Q2      : out  STD_LOGIC_VECTOR (3 downto 0);   -- salida Q2
           Q3      : out  STD_LOGIC_VECTOR (3 downto 0));  -- salida Q3
end component;	
		
component MUX4x4
  Port (   E0 : in  STD_LOGIC_VECTOR (3 downto 0); -- Entrada 0
           E1 : in  STD_LOGIC_VECTOR (3 downto 0); -- Entrada 1
           E2 : in  STD_LOGIC_VECTOR (3 downto 0); -- Entrada 2
           E3 : in  STD_LOGIC_VECTOR (3 downto 0); -- Entrada 3
           S : in  STD_LOGIC_VECTOR (1 downto 0);  -- Senal de control
           Y : out  STD_LOGIC_VECTOR (3 downto 0)); -- Salida
end component;

component refresco
  Port (   CLK : in  STD_LOGIC;                  -- reloj de refresco
           S : out  STD_LOGIC_VECTOR (1 downto 0);   -- Control para el mux
			  DP : out STD_LOGIC;                       -- Control del punto decimal
           AN : out  STD_LOGIC_VECTOR (3 downto 0)); -- Control displays individuales
end component;

-- SEALES

--   Definir posibles seales necesarias
		signal S1 : STD_LOGIC_VECTOR (3 downto 0):="1100"; --senal de e0 que muestra la c
		--signal S2 : STD_LOGIC_VECTOR (3 downto 0); --senal decimas e1
		--signal S3 : STD_LOGIC_VECTOR (3 downto 0); --senal unidades e2
	--	signal S4 : STD_LOGIC_VECTOR (3 downto 0); --senal decenas e3
		signal S5 : STD_LOGIC_VECTOR (3 downto 0); --senal entre q0 y e0mux
		signal S6 : STD_LOGIC_VECTOR (3 downto 0); --senal entre q1 y e1mux
		signal S7 : STD_LOGIC_VECTOR (3 downto 0); --senal entre q2 y e2mux
		signal S8 : STD_LOGIC_VECTOR (3 downto 0); --senal entre q3 y e3mux
		signal S9 : STD_LOGIC_VECTOR (3 downto 0); --senal Y y BCD
		signal S10 : STD_LOGIC_VECTOR (1 downto 0); --senal S
		--signal S11 : STD_LOGIC_VECTOR (0 to 6); --senal de seg7
		--signal S12 : STD_LOGIC_VECTOR (3 downto 0); --senal de an
		--signal S13 : STD_LOGIC; --senal de dp
		--signal S14 : STD_LOGIC; --senal enable
		--signal S15 : STD_LOGIC; --senal clk 
		
		
		
begin --se conectan las diferentes senales a las entradas
		--segun lo establecido antes

U1 : decodBCDa7s
			port map ( 
					BCD=>S9,
					SEGMENTOS=>SEG7
				);
				
U2 : registro
			port map ( 
				E0=>S1,
				E1=>DECIMAS,
				E2=>UNIDADES,
				E3=>DECENAS,
				Q0=>S5,
				Q1=>S6,
				Q2=>S7,
				Q3=>S8,
				ENABLE=>ENABLE,
				CLK=>CLK
				);

U3 : MUX4x4
			port map ( 
				E0=>S5,
				E1=>S6,
				E2=>S7,
				E3=>S8,
				S=>S10,
				Y=>S9				
				);
				
U4 : refresco
			port map ( 
				CLK=>CLK,
				DP=>DP,
				S=>S10,
				AN=>AN
				);				

				
end a_visualizacion;


