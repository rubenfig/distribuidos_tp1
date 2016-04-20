# coding=utf-8
import socket
import json
import time
import random
import base64
import threading

#Se calculan las variables y se utiliza json para representar los datos
def enviarS(identificador):
	s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
	UDP_IP = "localhost"
	UDP_PORT = 21337
	id = identificador
	coord_x = -25.5
	coord_y = -16.5
	dep = int(random.random()*100)
	dis = int(random.random()*100)
	for j in range(5):
		tiempo = time.time()
		cod = int(random.random()*100)
		encoded = str(base64.b64encode(open("filename.png", "rb").read()))
		mensaje = json.dumps({"id":id, "posX":coord_x, "posY":coord_y, "imagen":encoded, "codigo_cultivo":cod, "departamento":dep, "distrito":dis})
		#Se envía por un socket UDP

		s.sendto(mensaje.encode('utf-8'), (UDP_IP,UDP_PORT))
		time.sleep(1)

def main():
        print "Generador de datos satelitales"
	x = int(input("Ingrese la cantidad de Satelites: "))
	for i in range(x):
		t = threading.Thread(target=enviarS, args=(i,))
		t.start()

main()

