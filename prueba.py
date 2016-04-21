import dataset
#db = dataset.connect('sqlite:///BADB.db')
db = dataset.connect('postgresql://postgres:postgres@localhost:5432/sockets')
elegido=0
result = db.query("select * from satelite where id = (select MAX(id) from sensor where sensor_id = "+str(elegido)+")")
for row in result:
    respuesta = json.dumps(
        {"timestamp": row['tiempo'], "id": row['sensor_id'], "humedad": row['humedad'], "viento": row['viento'],
         "temperatura": row['temperatura'],
         "departamento": row['departamento'], "distrito": row['distrito'], "zona": row['zona']})
