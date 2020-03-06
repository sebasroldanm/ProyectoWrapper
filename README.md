# Proyecto1

Trabajo 1

localhost:8080/personas/listar  			=> GET

localhost:8080/personas/listarPorNombre/Camilo  	=> GET
localhost:8080/personas/listarPorNombre/Juan  		=> GET

localhost:8080/personas/guardar				=> POST
{
    "nombre": "Fernanda",
    "cedula": 987654,
    "email": "fernanda@app.com",
    "edad": 22,
    "direccion": "Calle 4 No 6 70",
    "estadoCivil": "Soltera"
}

localhost:8080/personas/editar				=> PUT
{
    "nombre": "Luisa",
    "cedula": 987654,
    "email": "Luisa@app.com",
    "edad": 29,
    "direccion": "Calle 13 No 2 32",
    "estadoCivil": "Casada"
}

localhost:8080/personas/eliminar/987654
localhost:8080/personas/eliminar/123456
