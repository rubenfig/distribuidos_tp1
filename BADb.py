import socket
import threading
import json
import psycopg2
import time
import dataset
db = dataset.connect('sqlite:///BADB.db')
db = dataset.connect('postgresql://postgres:postgres@localhost:5432/sockets')
log = open ("log.txt","a")

def recibir(i):
	if i != 0:
		UDP_IP = "127.0.0.1"
		if i == 1:
			UDP_PORT = 11337
		if i == 2:
			UDP_PORT = 21337
		if i == 3:
			UDP_PORT = 31337

		sock = socket.socket(socket.AF_INET, # Internet
						 socket.SOCK_DGRAM) # UDP
		sock.bind((UDP_IP, UDP_PORT))

		while True:
			data, addr = sock.recvfrom(100000000) # buffer size is 1024 bytes
			parsed_json= json.loads(data.decode('UTF-8'))
			log = open ("log.txt","a")
			if i == 1:
				log.write("Recibido de tractor "+str(parsed_json['id'])+"\n")
				table = db['tractor']
				table.insert(dict(tractor_id = parsed_json['id'], coord_x = parsed_json['posX'], coord_y = parsed_json ['posY'], altura = parsed_json ['altura'], humedad = parsed_json['humedad'], peso = parsed_json['peso'], temperatura = parsed_json['temperatura'] ))
				
			if i == 2:
				log.write("Recibido de satelite "+str(parsed_json['id'])+"\n")
				table = db['satelite']
				table.insert(dict(satelite_id = parsed_json['id'], coord_x = parsed_json['posX'], coord_y = parsed_json ['posY'], imagen = parsed_json['imagen'], cultivo_id = parsed_json ['codigo_cultivo'], departamento = parsed_json['departamento'], distrito = parsed_json['distrito']))

			if i == 3:
				log.write("Recibido de sensor "+str(parsed_json['id'])+"\n")
				table = db['sensor']
				table.insert(dict(sensor_id = parsed_json['id'], zona = parsed_json['zona'], departamento = parsed_json['departamento'], distrito = parsed_json['distrito'], humedad = parsed_json['humedad'], viento = parsed_json['temperatura']))
			log.close()
			
			
	else:
		#time.sleep(2)
		log = open ("log.txt","a")
		TCP_IP = '127.0.0.1'
		TCP_PORT = 6969
		BUFFER_SIZE = 1024
		s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		s.connect((TCP_IP, TCP_PORT))
		print "La lista de productos es: \n1-Soja\n2-Tomate\n3-Locote"
		pregunta = raw_input("Desea consultar una cotizacion? responda con si o no: ")
		while pregunta=='si':
			mensaje= raw_input("Ingrese el numero del producto para consultar su cotizacion por Kg: ")
			s.send(mensaje)
			data = s.recv(BUFFER_SIZE)
			print "La cotizacion actual es: " + data
			pregunta = raw_input("Seguir consultado cotizacion? responda con si o no: ")
			log.write("Recibido de cotizador: "+ data + "\n")
		s.close()
		log.close()

for i in range(4):
	t = threading.Thread(target=recibir, args=(i,))
	t.start()

#comando para usar en cotizador select imagen from satelite where id=(select MAX(id) from Satelite)