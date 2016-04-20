# coding=utf-8
import socket
import json
import time
import random
import threading

def enviarT(identificador):
	#Se calculan las variables y se utiliza json para representar los datos
	id = identificador
	coord_x = -25.5
	coord_y = -16.5
	alt = 67.5
	hum = 50.5
	pes = 0.00000001
	tem = 25.5
	for j in range(5): #Para probar la concurrencia, cada idTractor envía 5 datos distintos
		tiempo = time.time()
		coord_x = coord_x + random.random() - random.random() 
		coord_y = coord_y + random.random() - random.random()
		alt = alt + random.random() - random.random()
		hum = hum + random.random() - random.random()
		pes = pes + random.random()
		tem = tem + random.random() - random.random()
		mensaje = json.dumps({"timestamp":tiempo, "id":id, "posX":coord_x, "posY":coord_y, "altura":alt, "humedad":hum, "peso":pes, "temperatura":tem})


		#Se envía por un socket UDP
		UDP_IP = "localhost"
		UDP_PORT = 11337
		s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
		s.sendto(mensaje, (UDP_IP,UDP_PORT))
		time.sleep(1)

#Definimos la cantidad de hilos que se van a ejecutar para enviar los mensajes de manera concurrente
def main():
	x = int(input("Ingrese la cantidad de tractores: "))
	for i in range(x):
		t = threading.Thread(target=enviarT, args=(i,))
		t.start()

main()	
	