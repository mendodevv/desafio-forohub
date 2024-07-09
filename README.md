# Desafío Forohub
<img src="/readme-images/forohub-banner.png">

Este proyecto, llamado "Desafío Forohub", fue realizado como parte de la formación en el programa Oracle Next Education (ONE). Es una API rest que nos permite crear, leer, actualizar y eliminar tópicos (Se aplican las operaciones CRUD). Tiene un sistema de autenticación que implementa las tecnologías de Spring Security y tokens JWT.

## Descripción del proyecto
Este proyecto utiliza Java y Spring Boot como tecnologías principales, con Maven para la gestión de dependencias. La arquitectura del proyecto sigue las mejores prácticas y patrones de diseño, organizándose en paquetes específicos para modelos, repositorios, servicios y configuraciones de seguridad.

Se implementa autenticación JWT para proteger las rutas y asegurar que solo los usuarios autorizados puedan acceder a ciertas funcionalidades. Además, se integra con Spring Security para una gestión de seguridad robusta y flexible.

La documentación de la API se realiza mediante SpringDoc, que proporciona una interfaz de usuario de Swagger para explorar y probar los endpoints de la API de manera interactiva.

## Organización del código
El proyecto sigue la estructura estándar de Maven y se organiza en varios paquetes:

- `com.aluracursos.forohub.controller`: Controladores para manejar las solicitudes HTTP.
- `com.aluracursos.forohub.dominio`: Contiene a nuestros objetos (Topico y usuario) y todo lo que se relacione con ellos, por ejemplo en el caso del tópico, su clase, repositorio, DTOS, etc...
- `com.aluracursos.forohub.infra.security`: Configuraciones de seguridad, autenticación, manejo de errores y configuración de springdoc.

Asimismo, contamos con:

- Las dependencias necesarias:
  - Lombok
  - Spring Web
  - Spring Boot DevTools
  - Spring Data JPA
  - Flyway Migration
  - MySQL Driver
  - Validation
  - Spring Security
  - Java JWT
  - Springdoc

 - La declaración y configuración de la base de datos en el archivo `application.properties`

## Organización del código de manera gráfica




