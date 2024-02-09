----------------------------------------------------------------------------------
-- Conversin de datos del ADC en temperatura
-- El sensor proporciona una tensin de 100 mV por C
----------------------------------------------------------------------------------

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity ADC_a_TEMP is
    Port ( DATOS_ADC : in  STD_LOGIC_VECTOR (15 downto 0);     -- Datos del convertidor A/D
           TEMP      : out  STD_LOGIC_VECTOR (15 downto 0));   -- Salida temperatura en punto fijo con 6 bits decimales
end ADC_a_TEMP;

architecture a_ADC_a_TEMP of ADC_a_TEMP is

constant FILTRO  : unsigned (15 downto 0):="0000111111111000";--Tras realizar la operación pertinente determinamos este valor del filtro
signal datos     : unsigned (15 downto 0):=(others=>'0'); -- Datos ledos del conversor y filtrados
signal datosx32  : unsigned (31 downto 0):=(others=>'0'); -- Datos multiplicados por 32
signal datosx16  : unsigned (31 downto 0):=(others=>'0'); -- Datos multiplicados por 16
signal datosx2   : unsigned (31 downto 0):=(others=>'0'); -- Datos multiplicados por 2
signal datosx50  : unsigned (31 downto 0):=(others=>'0'); -- Datos multiplicados por 50

begin

datos<= unsigned(DATOS_ADC)  and FILTRO;  -- datos de entrada filtrados

-- Para pasar a C se trata de multiplicar por 50 y dividir entre 4096

-- Para multiplicar datos*50, hacemos: datos*32+datos*16+datos*2

datosx32 <= "00000000000" & datos & "00000" ; -- Usamos esta operación para concatenar el nº de ceros que necesitemos por cada lado, en este caso 5 por la derecha (2^5 = 32) y 11 por la izquierda para completar los 16
datosx16 <= "000000000000" & datos & "0000" ; --                                                                                                    
datosx2  <= "000000000000000" & datos & "0" ;

datosx50<= datosx32 + datosx16 + datosx2;--Realizamos la operación que nos indica el enunciado

-- Ahora tomamos los bits correspondientes a 10 enteros y 6 decimales

TEMP<=STD_LOGIC_VECTOR(datosx50(21 downto 6));--Convertimos la señal y cogemos los bits que nos interesan, siguiendo lo que nos dice el enunciado

end a_ADC_a_TEMP;

