int main(){
    char a,b;
    printf("Introduzca 2 letras minúsculas:");
    scanf("%c %c", &a, &b);
    (a < b) ? printf("%c %c", toupper(a), toupper(b)) : printf("%c %c", toupper(b),toupper(a));
    return 0;
}

int main(){
    char a,b;
    printf("Introduzca 2 letras minúsculas:");
    scanf("%c %c", &a, &b);
    a = toupper(a);
    b = toupper(b);
    (a < b) ? printf("%c %c", a, b) : printf("%c %c", b,a);
    return 0;
}

// Ej1
#include <stdio.h>

int main() {
    double IMC, peso, altura;

   printf("Introduzca su peso y altura:");
   scanf("%d %d", &peso, &altura);
   IMC = peso/altura*altura

    switch(IMC)
    {
        case IMC<16:
            printf("Delgadez severa");
            break;
    }

    return 0;
} 

/* Desarrollar un programa que calcule el índice de masa corporal de una persona. Para ello, se requiere definir el peso de la persona (en kilogramos) y su estatura (en metros). El índice de masa corporal (IMC) se calcula utilizando la siguiente fórmula:
IMC = peso / estatura2
Luego, a partir del IMC obtenido se pueden calcular si una persona tiene un peso normal, inferior o superior al normal */
#include<stdio.h>
int main (){
    float p, e, i;
    scanf("%f %f", &p, &e);
    i=p/(e*e);
    
    if ((i>=0)&&(i<18.5))
        printf("Delgadez severa");
    
    else if ((i>=18.5)&&(i<25))
        printf("Peso normal");
    
    else if((i>=25)&&(i<40))
        printf("Obesidad moderada");
    
    return 0;
}

//Para tributar un determinado impuesto se debe ser mayor de 16 años y tener unos ingresos iguales o superiores a 1000 € mensuales. Escribir un programa que obtenga la edad y los ingresos mensuales y muestre por pantalla si el usuario tiene que tributar o no.

#include<stdio.h>
int main (){
    float edad,ingresos;
    
    
    scanf("%f %f", &edad, &ingresos);
    
    if(edad>16 && ingresos>=1000)
        printf("El usuario tiene que tributar");
     else
         printf("El usuario NO tiene que tributar");
    
    return 0;
}


/* Los tramos impositivos para la declaración de la renta en un determinado país son los siguientes:

Desarrolle un programa que lea la renta de una persona y determine el impuesto a pagar con dos decimales. */

#include <stdio.h>

int main() {
    float renta,impuesto;

    scanf("%f", &renta);
    
    if (renta < 10000) {
        impuesto = (renta*0.05);
        printf("%.2f",impuesto);
    } else if (renta < 20000) {
        impuesto = (renta*0.15);
        printf("%.2f",impuesto);
    } else if (renta < 35000) {
        impuesto = (renta*0.20);
        printf("%.2f",impuesto);
    } else if (renta < 60000) {
        impuesto = (renta*0.30);
        printf("%.2f",impuesto);
    } else {
        impuesto = (renta*0.45);
        printf("%.2f",impuesto);
    }

    return 0;
}




//Escribir un programa en el cual  se lean tres valores numéricos enteros y se imprima su rango de variación (diferencia entre el mayor y el menor).

#include <stdio.h>

int main() {
    int n1,n2,n3,rango ;

    scanf("%d %d %d", &n1, &n2, &n3);
    if((n1 >n2) && (n1>n3)){
        if(n2>n3){
            rango= n1 - n3;
            printf("%d",rango);
        }
        else{
            rango = n1 - n2;
            printf("%d",rango);
        }
    }
      if((n2 >n1) && (n2>n3)){
        if(n1>n3){
            rango= n2 - n3;
            printf("%d",rango);
        }
        else{
            rango = n2 - n1;
            printf("%d",rango);
        }
    }
      if((n3 >n1) && (n3>n2)){
        if(n1>n2){
            rango= n3 - n2;
            printf("%d",rango);
        }
        else{
            rango = n3 - n1;
            printf("%d",rango);
        }
    }
   

    return 0;
}


//Programa que lea un carácter. Si se trata de letra  debe transformela en su equivalente mayúscula o minúscula pero si no es una letra debe mostrar el carácter original.

#include <stdio.h>
#include <ctype.h>

int main() {
    char n;
    scanf("%c",&n);
    
    if (isupper(n)) {
        printf("%c",tolower(n));
    } else {
        printf("%c", toupper(n));
    }
    
    return 0;
}
