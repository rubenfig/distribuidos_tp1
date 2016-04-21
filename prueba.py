import dataset
#db = dataset.connect('sqlite:///BADB.db')
db = dataset.connect('postgresql://postgres:postgres@localhost:5432/sockets')
elegido='Soja'
result = db.query("select precio from "+elegido+"  where id=(select MAX(id) from "+elegido+")")
for row in result:
    print (row ['precio'])
