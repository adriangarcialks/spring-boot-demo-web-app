# Spring Boot Web App Demo (STUDENT LIST)

Esta es una aplicación web desarrollada en Spring Boot. Gestiona una lista de estudiantes pudiendo realizar las típicas acciones CRUD.

## Características
- El frontend es una interfaz de usuario creada con las plantillas Thymeleaf. Una general contiene los fragmentos del menú y del footer que son usados por el resto de plantillas.

- Dos usuarios ('admin' y 'user') guardados en memoria disponen de diferentes privilegios dentro de la aplicación (roles). Pudiendo el usuario 'admin' ver la lista de estudiantes y crear, editar y eliminar un estudiante, mientras que el usuario 'user' solamente puede ver la lista de estudiantes.

- Cuando se arranca la aplicación se realiza una llamada a una API externa que provee 5 usuarios generados aleatoriamente que posteriormente son guardados en la base de datos (PostgreSQL) para popular de datos la aplicación.

## Especificaciones
- Java version: 17
- Maven version: 3.6.3
- Spring Boot version: 3.0.1
