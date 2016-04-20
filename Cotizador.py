# coding=utf-8
import socket
import random

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
s.bind(("", 6969))
s.listen(1)
sc, addr = s.accept()

#Se envía la cotización mientras pregunten
precio = 1.28 
precio = precio + random.random() - random.random() #Se hace un random para el precio de cotización

while True:
	#Precio base de cotización 
	recibido = sc.recv(1024)
	if recibido == "close": break
	print str(addr[0]) + " dice: ", recibido #Se imprime la solicitud recivida
	sc.send(str(precio)) #Envíamos las cotizaciones del día

#Cerramos las instancias de socket cliente y servidor
sc.close()
s.close()