Sistema de gestion de inventario

## Descripción

**Inventory** es una aplicación desarrollada con **Spring Boot** que permite gestionar productos, clientes y ventas a través de una API REST. Implementa **Spring Security** para la autenticación de usuarios y utiliza **Hibernate** para la gestión de la base de datos, garantizando un acceso seguro y eficiente a la información.

## Tecnologías Utilizadas

-  -   **Java 17**
    
-   **Spring Boot 3.3.5**
    
-   **Spring Data JPA**
    
-   **Spring Security**
    
-   **JWT (Json Web Tokens) para autenticación**
    
-   **Hibernate**
    
-   **MySQL**
    
-   **Maven**
    
-   **Lombok**
    
-   **Cloudinary (para gestión de imágenes)**
    
-   **Docker** (opcional)
    

## Instalación y Ejecución

### Prerrequisitos

Asegúrate de tener instalado en tu sistema:

-   [Java 17](https://adoptium.net/)
        
-   [Docker](https://www.docker.com/) (si usas contenedores)
    
-   [Base de Datos correspondiente]
    

### Configuración del Proyecto

1.  Clonar el repositorio:
    
   ```
https://github.com/sebasrb08/api-inventory.git
 ```
    
2.  Configurar las variables de entorno necesarias antes de ejecutar el proyecto:
    
```
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/tu_base_datos
export SPRING_DATASOURCE_USERNAME=usuario
export SPRING_DATASOURCE_PASSWORD=contraseña
export CLOUDINARY_NAME=tu_cloud_name
export CLOUDINARY_API_KEY=tu_api_key
export CLOUDINARY_API_SECRET=tu_api_secret
 ```
    

### Ejecución del Proyecto

Para ejecutar el proyecto localmente:

```
mvn spring-boot:run
```

O bien, si usas un JAR generado:

```
mvn clean package
java -jar target/tu-archivo.jar
```



## Endpoints API

### Endpoints Publicos

-   **Gestión de Autenticacion**:
    
    -   `POST /auth/login`:  inicia sesion y genera un token JWT
    -   `POST /auth/register`:  registrarse en la base de datos
  

### Endpoints Privados

-   **Gestión de Productos**:
    
    -   `POST /producto`: Agregar un nuevo producto .
    -   `PUT /producto/{id}`: Actualizar los detalles de un producto.
    -   `DELETE /producto/{id}`: Eliminar un producto.
    -   `GET /producto/{id}`: Obtener detalles de un producto en específico.
    -   `GET /producto`: Obtener una lista de todos los productos.

-   **Gestión de Categorias**:
    
    -   `POST /categoria`: Agregar una nueva categoría.
    -   `PUT /categoria/{id}`: Actualizar los detalles de una categoria.
    -   `DELETE /categoria/{id}`: Eliminar una categoria.
    -   `GET /categoria/{id}`: Obtener detalles de una categoria en específico.
    -   `GET /categoria`: Obtener una lista de todos los categoria.


-   **Gestión de Clientes**:
    
    -   `POST /cliente`: Agregar un nuevo cliente.
    -   `PUT /cliente/{id}`: Actualizar los detalles de un cliente.
    -   `DELETE /cliente/{id}`: Eliminar un cliente.
    -   `GET /cliente/{id}`: Obtener detalles de un cliente en específico.
    -   `GET /cliente`: Obtener una lista de todos los clientes.

-   **Gestión de Detalle_Producto**:
    
    -   `POST /detalle`: Agregar un nuevo detalle_producto.
    -   `PUT /detalle/{id}`: Actualizar los detalles de un detalle_producto.
    -   `DELETE /detalle/{id}`: Eliminar un detalle_producto.
    -   `GET /detalle/{id}`: Obtener detalles de un detalle_producto en específico.
    -   `GET /detalle`: Obtener una lista de todos los detalle_producto.


-   **Gestión de Ventas**:
    
    -   `POST /compra`: Agregar un nuevo compra.
    -   `PUT /compra/{id}`: Actualizar los detalles de una compra.
    -   `DELETE /compra/{id}`: Eliminar una compra.
    -   `GET /compra/{id}`: Obtener detalles de una compra en específico.
    -   `GET /compra`: Obtener una lista de todos las compras.

-   **Gestión de Imagenes**:
    
    -   `PATCH /upload/{id}`: Subir una imagen a Cloudinary


## 🔐 Autenticación y Uso de JWT

Este proyecto utiliza **JSON Web Tokens (JWT)** para la autenticación y autorización de usuarios.

### 🔹 **1. Obtener Token de Acceso**

Para autenticarse, primero debes iniciar sesión con un usuario registrado:


```
`POST /auth/login
Content-Type: application/json

{ "username": "usuario@example.com", "password": "tu_contraseña" }` 
```
🔹 **Respuesta exitosa:**


`{  "token":  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."  }` 

----------

### 🔹 **2. Usar el Token en Peticiones Protegidas**

Una vez obtenido el token, inclúyelo en el **header** `Authorization` en cada petición a los endpoints privados:

```
`GET /producto
Authorization: Bearer <tu_token>`
```

## Estructura del Proyecto

```
📦 src
 ┣ 📂 main
 ┃ ┣ 📂 java/Inventario
 ┃ ┃ ┣ 📂 config        # Configuración general (CORS, Security, etc.)
 ┃ ┃ ┣ 📂 controller    # Controladores REST
 ┃ ┃ ┣ 📂 Dto           # Objetos de transferencia de datos
 ┃ ┃ ┣ 📂 jwt           # Manejo de autenticación con JWT
 ┃ ┃ ┣ 📂 model         # Entidades de la base de datos
 ┃ ┃ ┣ 📂 repository    # Repositorios para acceso a la base de datos
 ┃ ┃ ┣ 📂 service       # Lógica de negocio
 ┃ ┃ ┣ 📂 util          # Utilidades generales
 ┃ ┃ ┗ 📜 Application.java  # Punto de entrada de la aplicación
 ┃ ┗ 📂 resources
 ┃ ┃ ┣ 📂 static        
 ┃ ┃ ┣ 📂 templates     
 ┃ ┃ ┗ 📜 application.properties  # Configuración de la aplicación
```

