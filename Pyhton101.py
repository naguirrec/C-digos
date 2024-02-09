print("Hello python 101")
print("Hello\nWorld!")
# print('Hello World!') 
print(bool(1)) #el bool está asociado a 0 ó 1, asiq lo podemos representar asi tmbn
print(int(False))
float(2.0)
int(2.0)     #convierte el float a int
                #// integer division
type(int(2.0))   #nos dice el tipo de dato
myvariable = 56
print("myvariable:" , myvariable)    #como en c
print("michael jackson \n hehe ")       #\n es un intro
print("michael jackson \t hehe ")       #\t es una tabulación
palabras = "abecedario"
print("Me gusta decir el", palabras)
print(palabras)
B=palabras.upper()   #este método coge el str y lo pone todo en mayúsculas
print(B)
frase1 = "Hola que tal estás"
C=frase1.replace("Hola","Ey")       #este método reemplaza la palabra que quiras por otra
print(frase1)
print(C)
name = "Lizz"
print(len(name))        #len te da la longitud
print(name[0:2])        #coge de la posición 0 a la 2
print(name[0])      #[]señalas la posición del str
var = "01234567"
print(var[::2])         #coge las posiciones de 2 en 2
a = "1"
b = "2"
print( a + b)           #concatenar strings
a = "Thriller is the sixth studio album"
print("before upper:", a)
b = a.upper()
print("After upper:", b)        #otro ejemplo del upper

a = "Michael Jackson is the best"   #otro ejemplo del replace 
print(a)
b = a.replace('Michael', 'Janet')
print(b)

name = "Michael Jackson"
print(name.find('el'))      #nos dice en qué posición empieza el string que queremos buscar
L = ["Micharl Jackson" ,3 , 69 ,(7,9,5), "MJ"] #Lista, se pueden cambiar los valores, añadir distintos elementos, concatenar
print(L)
print(L[3])
print(L[0:3])
L.extend(["Pop", 90305])        #Método para añadir elementos a la lista
print(L)
L[0]= "Messi"       #Se pueden cambiar los valores de la lista
print(L)
del(L[0])       #Elimina el elemento
print(L)
B=["a","b","c"]
print(B[1:])        #del elemento 1 hasta el final

Set1 = {"MJ", "CR7" , "Messi"}      #Un set es como una lista, pero no mantiene el orden
print(Set1)

Set2 = set(L)           #Se puede convertir una lista a set usando esto
Set2.add("HEHE")        #Se puede añadir elementos a un set usando nombredelset.add()
print(Set2)
Set2.remove("MJ")       #Se usa para eliminar un elemento del set
print(Set2)

albumset1 = {"AC/DC", "Nirvana", "Linkin Park", "Guns n Roses"}
albumset2 = {"AC/DC", "NYSC", "Linkin Park", }
albumset3 = albumset1 & albumset2       #Se puede intersecar 
print(albumset3)
print(albumset3.issubset(albumset1))        #Se puede comprobar si un set es un subset de otro
print(albumset1.union(albumset2))           #Se puede hacer la unión de 2 sets
print( "AC/DC" in albumset1)                #Se puede comprobar si un elemento está en un set
print(albumset1.difference(albumset2))      #Método para ver la diferencia entre elementos de sets


diccionario1 = {"año": 2023, "mes": "febrero",  "día": 28, "EJ": (3,3,4,4,)}#Se crea un diccionario así, la key entre comillas, :valor asignado
print(diccionario1)         #Las keys pueden ser cualquier tipo, int,str, listas, tupples
print(diccionario1["año"])      
diccionario1["haha"] = 3280     #Se puede añadir una key con su valor definido al diccionario 
print(diccionario1)
del(diccionario1["haha"])       #Se usa esto para eliminar una key y elemento del diccionario
print(diccionario1)
print("año" in diccionario1)        #Sirve para comprobar si una key está en el dicionario
print(28 in diccionario1)       #Con el valor asignado no sirve o no sé aún 
print(diccionario1.keys())      #Nos saca todas las keys del diccionario
print(diccionario1.values())    #Nos saca todos los valores del diccionario

LA = ['a','b','c']
LA.append(['a','b'])        #añades el elemneto a la lista
print(LA)

age=18          #if, else y elif (else if) 
if(age>18):
    print("you can enter")
elif(age == 18):
    print("Go see Pinkfloid")
else:
    print("go see Meat Loaf")
print("move on")

#Comparador or (||) y comparador and (&&)

albumyear = 1990
if(albumyear < 1980) or (albumyear>1989):       
    print("The album was made in the 70's or 90's")

else:
    print("The album was made in the 1980's")

albumyear2 = 1983
if(albumyear2 > 1979) and (albumyear2<1990):       
    print("The album was made in the 1980's")

    #Elementos comparadores:
    
#equal: ==
#not equal: !=
#greater than: >
#less than: <
#greater than or equal to: >=
#less than or equal to: <=

print("A" > "B")    #Las letras tienen asignados valores ASCII asiq se pueden comparar de esta forma

A=[3,4,5]           #Bucle for:
for a in A:
    print(a)

print("Siguiente bucle")

dates = [1982,1980,1973]    #Otro bucle for
N = len(dates)

for i in range(N):
    print(dates[i])

print("Siguiente bucle")
        
x=3                 #Bucle while:
y=-1

while(y!=x):
    print(y)
    y=y+1

#Cuidado con como se cierran aquí los bucles

#Inicio de funciones

notasalbum = [10.0 , 9.5 , 7.4 ]
L=len(notasalbum)       #La función len nos da la longitud
S=sum(notasalbum)       #La función sum nos da la suma de todos los elementos   
notasalbum = sorted(notasalbum)     #La función sortedd nos ordena los elementos
notasalbum.sort     #El método sort cambia la lista por la que le hemos pasado pero ordenada
print("Función 1")
def f1(a):
    b = a + 1
    return b

c = f1(5)
print(c)
print("Función 2")
def Mult(x,y):
    g = x*y
    return g

Mult(2,3)


class Circle1 (object):          #Definición de clase y objetos
    def __init__(self, radius, color):
        self.radius = radius,
        self.color = color; 
    def add_radius(self,r):
        self.radius = self.radius + r


c1 = Circle1(3,"red") 


# Create a class Circle
# Import the library,  importamos esto para dibujar los objetos
#import matplotlib.pyplot as plt        
#%matplotlib inline  ----> No sé pq pero no funciona

class Circle(object):
    
    # Constructor
    def __init__(self, radius=3, color='blue'):
        self.radius = radius
        self.color = color 
    
    # Method
    def add_radius(self, r):
        self.radius = self.radius + r
        return(self.radius)
    
    # Method
    def drawCircle(self):
        plt.gca().add_patch(plt.Circle((0, 0), radius=self.radius, fc=self.color))
        plt.axis('scaled')
        plt.show()  

RedCircle = Circle(10, "red")
dir(RedCircle)

print('Radius of object:',RedCircle.radius)
RedCircle.add_radius(2)
print('Radius of object of after applying the method add_radius(2):',RedCircle.radius)
RedCircle.add_radius(5)
print('Radius of object of after applying the method add_radius(5):',RedCircle.radius)

BlueCircle = Circle(radius=100)
BlueCircle.radius
BlueCircle.color
BlueCircle.drawCircle()
          

#https://docs.python.org/3/library/exceptions.html --> Buitl in python exceptions
# potential code before try catch

    #try:
    # code to try to execute
    #except:
    # code to execute if there is an exception
    
# code that will still execute if there is an exception

    # potential code before try catch
#try:
    # code to try to execute
#except ZeroDivisionError:
    # code to execute if there is a ZeroDivisionError       ZeroDivisionError occurs when you try to divide by zero.
#except NameError:
    # code to execute if there is a NameError               NameError -- in this case, it means that you tried to use the variable a when it was not defined.
#except:
    # code to execute if ther is any exception              IndexError -- in this case, it occured because you tried to access data from a list using an index that does not exist for this list.
#else:
    # code to execute if there is no exception
#finally:
    # code to execute at the end of the try except no matter what
    
# code that will execute if there is no exception or a one that we are handling


try:
    b = int(input("Please enter a number to divide a: "))
    a = a/b
except ZeroDivisionError:
    print("The number you provided cant divide 1 because it is 0")
except ValueError:
    print("You did not provide a number")
except:
    print("Something went wrong")
else:
    print("success a=",a)
finally:
    print("Processing Complete")








