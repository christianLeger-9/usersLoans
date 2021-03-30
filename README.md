Challenge - Users Loans

Descargando el repositorio:

-git clone git@github.com:christianLeger-9/usersLoans.git


Para ejecutar la aplicacion utilizando Maven desde STS:

clean install


Workflow:

- Descargar repositorio.

- Validar funcionamiento del proyecto descargado ejecutando la aplicacion via Maven.

- Desde el Eclipse o STS, importar el proyecto (importar como proyecto Maven existente).


Ambientes disponibles:

Los mismos se encuentran configurados en el archivo "application.yml"
- dev
- qa



End-Points:

-  /loan: en donde se encuentra la operatoria tanto para buscar loans por page y size obligatorios
-  como filtrar por idUser. Tambien se puede guardar nuevos loans.

- /users: en donde se puede guardar un nuevo usuario con sus respectivos valores, eliminar un usuario
- en el cual tambien se eliminan sus loans asociados, y buscar un usuario por id.


Aclaraciones: 
Al momento de realizar el metodo saveUser, deben enviar el json con el loans vacios(corchetes vacios).
Luego dar de alta loans para el usuario creado anteriormente, y luego correr el get de usuario por id.

