import socket
import threading
import json
import time
import dataset
db = dataset.connect('sqlite:///BADB.db')
log = open ("log.txt","a")
IP="localhost"

print "Base de datos"

def tractor():
        sock = socket.socket(socket.AF_INET, # Internet
					 socket.SOCK_DGRAM) # UDP
	sock.bind((IP, 11337))
	while True:
                data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
                parsed_json= json.loads(data.decode('UTF-8'))
                log = open ("log.txt","a")
                log.write("Recibido de tractor "+str(parsed_json['id'])+"\n")
                table = db['tractor']
                table.insert(dict(tractor_id = parsed_json['id'], coord_x = parsed_json['posX'], coord_y = parsed_json ['posY'], altura = parsed_json ['altura'], humedad = parsed_json['humedad'], peso = parsed_json['peso'], temperatura = parsed_json['temperatura'] ))
                log.close()

def satelite():
        sock = socket.socket(socket.AF_INET, # Internet
					 socket.SOCK_DGRAM) # UDP
	sock.bind((IP, 21337))
	while True:
                data, addr = sock.recvfrom(100000000) # buffer size is 100000000 bytes
                parsed_json= json.loads(data.decode('UTF-8'))
                log = open ("log.txt","a")
                log.write("Recibido de satelite "+str(parsed_json['id'])+"\n")
                table = db['satelite']
                table.insert(dict(satelite_id = parsed_json['id'], coord_x = parsed_json['posX'], coord_y = parsed_json ['posY'], imagen = parsed_json['imagen'], cultivo_id = parsed_json ['codigo_cultivo'], departamento = parsed_json['departamento'], distrito = parsed_json['distrito']))
                log.close()

def sensor():
	sock = socket.socket(socket.AF_INET, # Internet
					 socket.SOCK_DGRAM) # UDP
	sock.bind((IP, 31337))
	while True:
		data, addr = sock.recvfrom(100000000) # buffer size is 1024 bytes
		parsed_json= json.loads(data.decode('UTF-8'))
		log = open ("log.txt","a")
		log.write("Recibido de sensor "+str(parsed_json['id'])+"\n")
		table = db['sensor']
		table.insert(dict(sensor_id = parsed_json['id'], zona = parsed_json['zona'], departamento = parsed_json['departamento'], distrito = parsed_json['distrito'], humedad = parsed_json['humedad'], viento = parsed_json['temperatura']))
		log.close()

def cotizar():
        time.sleep(5)
	BUFFER_SIZE = 1024
	while True:
                s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                mensaje = "SOJA"
                s.connect((IP, 6969))
                s.send(mensaje.encode('utf-8'))
                data = s.recv(BUFFER_SIZE)
                print ("Cotizacion recibida:", data.decode('UTF-8'))
                time.sleep(5)
                s.close()

def analizer():
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind((IP, 4444))
        s.listen(1)
	BUFFER_SIZE = 1024
	
	while True:
                sc, addr = s.accept()
                recibido = sc.recv(1024)
                print str(addr[0]) + " dice: ", recibido.decode('utf-8') #Se imprime la solicitud recibida
                parsed_json= json.loads(recibido.decode('UTF-8'))
                if parsed_json['data'] == "1":
                        respuesta = "clima" #hay que hacer el query y convertir a json
                        sc.send(respuesta.encode("utf-8"))
                if parsed_json['data'] == "2":
                        respuesta = "tractor" #hay que hacer el query y convertir a json
                        sc.send(respuesta.encode("utf-8"))
                if parsed_json['data'] == "3":
                        respuesta = "satelite" #hay que hacer el query y convertir a json
                        sc.send(respuesta.encode("utf-8"))
                if parsed_json['data'] == "4":
                        respuesta = "cotizador" #hay que hacer el query y convertir a json
                        sc.send(respuesta.encode("utf-8"))
                print ("se envio:", respuesta.decode('UTF-8'))
                sc.close()
        s.close()

t = threading.Thread(target=tractor, args=())
t.start()

t = threading.Thread(target=satelite, args=())
t.start()

t = threading.Thread(target=sensor, args=())
t.start()

t = threading.Thread(target=cotizar, args=())
t.start()

t = threading.Thread(target=analizer, args=())
t.start()
