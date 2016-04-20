# coding=utf-8
import socket
import random

print "Generador de datos de cotizacion"

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
s.bind(("localhost", 6969))
s.listen(1)


#Se envía la cotización mientras pregunten
precio = 1.28 


while True:
        precio = precio + random.random() - random.random() #Se hace un random para el precio de cotización
        sc, addr = s.accept()
	#Precio base de cotización 
	recibido = sc.recv(1024)
	if recibido == "close": break
	print str(addr[0]) + " dice: ", recibido.decode('utf-8') #Se imprime la solicitud recibida
	sc.send(str(precio).encode('utf-8')) #Envíamos las cotizaciones del día
	sc.close()

#Cerramos las instancias de socket cliente y servidor

s.close()
