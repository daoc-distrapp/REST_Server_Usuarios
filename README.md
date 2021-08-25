# REST_Server_Usuarios
Servidor REST para hacer CRUD sobre una tabla sencilla de "usuarios"

## Notas

Este es un proyecto Spring Boot.

Utilícelo solamente para pruebas, ya que NO es un buen ejemplo de cómo mantener información de usuarios en una base: carece completamente de seguridades y los passwords se guardan en claro.

## Ejecución

- En la línea de comando vaya al directorio raíz (aquel con el pom.xml) y ejecute:
  - Alternativa 1:
    - `mvnw spring-boot:run`
  - Alternativa 2:
    - `mvn clean package`
    - `java -jar target\usrs_rest_srv-0.0.1-SNAPSHOT.jar`

## Cliente de prueba

Utilice: https://github.com/dordonez-ute-moviles/Flutter_HttpRestClient

## Datos

- El servidor mantiene una base de datos embebida H2, con el nombre *usuarios*, la que se creará en el directorio del usuario que ejecuta la aplicación, en el archivo *~/usuarios.mv.db*. Es posible abrir una interfaz web para monitorear la base, en la URL http://localhost:8080/h2-console. En la base existe solamente la tabla *USUARIO*, de la que presentamos la parte relevante:

```java
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String usr;
	private String pwd;
  //...
```

> Hay tres campos: *id*, que es la clave primaria y auto numérico, y *usr* y *pwd*, que son de texto.

## API

- El controlador REST tiene cinco puntos de acceso:
  - `GET /usuarios`
    - Devuelve todos los usuarios registrados en la base en formato JSON
      > Ej: http://localhost:8080/usuarios -->
      `[{"id":1,"usr":"diego","pwd":"1234"},{"id":2,"usr":"ana","pwd":"2345"},...]`

  - `GET /usuarios/{id}`
    - Devuelve solamente el usuario con la id indicada
      > Ej: http://localhost:8080/usuarios/3 -->
      `{"id":3,"usr":"admin","pwd":"3456"}`

  - `POST /usuarios/create`
    - Crea un nuevo usuario en la base, con los datos pasados en el cuerpo de la petición. Si la id del usuario ya existe en la base, la llamada falla y devuelve un error.
    - El cuerpo de la petición debe estar en formato JSON: `{"usr":"guest","pwd":"4567"}` (sin id) o `{"id":0,"usr":"guest","pwd":"4567"}` (la id=0 se ignora).
    - Se debe incluir el header `content-type: application/json`

  - `PUT /usuarios/update`
    - Modifica un usuario existente en la base, con los datos pasados en el cuerpo de la petición. Si la id del usuario no existe en la base, se crea un usuario nuevo (se comporta como POST). El cuerpo de la petición debe estar en formato JSON y se debe incluir el header `content-type: application/json`.

  - `DELETE /usuarios/delete`
      - Elimina un usuario de la base, con los datos pasados en el cuerpo de la petición. El cuerpo de la petición debe estar en formato JSON y el único atributo obligatorio es la id (Ej: `{"id":8}`). Es necesario también incluir el header `content-type: application/json`.
