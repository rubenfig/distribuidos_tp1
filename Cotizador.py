# coding=utf-8
import socket
import random

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
s.bind(("", 6969))
s.listen(1)
sc, addr = s.accept()

precio = 1.28 #se establece un numero cualquiera para el calculo del random
print "\t\t\t\tCotizador central:\n\n\n"

while True:
	#Precio base de cotización
	recibido = sc.recv(1024)
	if recibido == "1":
		recibido = "Soja"
	elif recibido == "2":
		recibido = "Tomate"
	elif recibido == "3":
		recibido = "Locote"
	elif recibido == "no": break

	print str("La direccion: " + addr[0]) + " consulto la cotizacion de: ", recibido #Se imprime la solicitud recivida
	precio = abs(precio + random.random() - random.random()) #Se hace un random para el precio de cotización
	sc.send(str(precio)) #Envíamos las cotizaciones del día

#Cerramos las instancias de socket cliente y servidor
sc.close()
s.close()