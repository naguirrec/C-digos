eta = 1;
sigma = 1;
SNR = eta^2 / sigma^2;

% a) Simule el transmisor:
N = 10000;
trx = round(rand(1, N));

% b) Simule el canal de comunicaciones:
ruido = sigma * randn(1, N);
VA_x = zeros(1, N);
%
i = 1;
while i <= N
    if trx(i) == 1
        VA_x(i) = eta + ruido(i);
    else
        VA_x(i) = -eta + ruido(i);
    end
    i = i + 1;
end



% c) Simule el receptor:
alfa = 0;
decisiones = zeros(1, N);
%
for i = 1:N
    if VA_x(i) > alfa
        decisiones(i) = 1;
    else
        decisiones(i) = 0;
    end
end

% Crear histogramas de Xi cuando Si=0 y Xi cuando Si=1
figure;

% Histograma de Xi cuando Si=0
subplot(1, 2, 1);
histogram(VA_x(trx == 0), 'BinWidth', 0.2);
title('Histograma de Xi cuando Si=0');
xlabel('Valores de Xi');
ylabel('Frecuencia');

% Histograma de Xi cuando Si=1
subplot(1, 2, 2);
histogram(VA_x(trx == 1), 'BinWidth', 0.2);
title('Histograma de Xi cuando Si=1');
xlabel('Valores de Xi');
ylabel('Frecuencia');

% Calcule las frecuencias relativas de E0, E1 y E
E0 = sum(decisiones(trx == 0) ~= trx(trx == 0));%
E1 = sum(decisiones(trx == 1) ~= trx(trx == 1));
E = E0 + E1;
Etotal = E;%

frec_relativa_E0 = E0  / sum(trx == 0);
frec_relativa_E1 = E1 / sum(trx == 1);
frec_relativa_E  = E / length(trx);

fprintf('Frecuencia relativa de E0: %f\n', frec_relativa_E0);
fprintf('Frecuencia relativa de E1: %f\n', frec_relativa_E1);
fprintf('Frecuencia relativa de E: %f\n', frec_relativa_E);
