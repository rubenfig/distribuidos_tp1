# coding=utf-8
import socket
import json
import time
import random
import threading

#Se calculan las variables y se utiliza json para representar los datos
def enviarC(identificador,UDP_IP,UDP_PORT):
	id = identificador
	hum = 50.5
	vie = 1.5
	tem = 25.5
	dep = int(random.random()*100)
	dis = int(random.random()*100)
	zon = int(random.random()*100)
	for j in range(5):
		tiempo = time.time()
		hum = hum + random.random() - random.random()
		tem = tem + random.random() - random.random()
		vie = vie + random.random() - random.random()
		mensaje = json.dumps({"tiempo":tiempo, "id":id, "humedad":hum, "viento":vie, "temperatura":tem, "departamento":dep, "distrito":dis, "zona":zon})

		#Se envía a traves de un socket UDP
		
		s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
		s.sendto(mensaje.encode('utf-8'), (UDP_IP,UDP_PORT))
		time.sleep(1)

def main():
        print("Generador de Datos de Clima")
        UDP_IP = raw_input("Ingrese IP de Destino: ")
        UDP_PORT = 31337
	x = int(input("Ingrese la cantidad de Sensores de clima: "))
	for i in range(x):
		t = threading.Thread(target=enviarC, args=(i,UDP_IP,UDP_PORT,))
		t.start()

main()
