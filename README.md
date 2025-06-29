# 🚀 Kasumi CRUD - API REST con Spring Boot

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-green.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-blue.svg)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org/)

## 📋 Descripción

**Kasumi CRUD** es una aplicación web desarrollada con Spring Boot que implementa operaciones CRUD (Create, Read, Update, Delete) para la gestión de clientes. El proyecto está diseñado para demostrar la integración entre Spring Boot y PostgreSQL, proporcionando una API REST completa para el manejo de datos de clientes.

🎥 Demostración

Puedes ver una demostración del funcionamiento del proyecto en el siguiente enlace:
👉 [Visita el proyecto Kasumi](https://youtu.be/RUQfBsnZ6II)

## ✨ Características

- 🔧 **Spring Boot 3.5.3** - Framework principal
- 🗄️ **PostgreSQL** - Base de datos relacional
- 📊 **Spring Data JPA** - Persistencia de datos
- 🌐 **REST API** - Endpoints HTTP para operaciones CRUD
- 🔍 **Validación de datos** - Prevención de duplicados
- 📅 **Cálculo automático de antigüedad** - Basado en fecha de registro
- 🏗️ **Arquitectura en capas** - Controller, Service, Repository

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 3.5.3 | Framework de desarrollo |
| Spring Data JPA | 3.5.3 | Persistencia de datos |
| PostgreSQL | 13+ | Base de datos |
| Maven | 3.6+ | Gestión de dependencias |
| Hibernate | 6.x | ORM |

## 📁 Estructura del Proyecto

```
crud-kasumi/
├── src/
│   ├── main/
│   │   ├── java/com/kasumi/crud_kasumi/
│   │   │   ├── CrudKasumiApplication.java
│   │   │   └── customer/
│   │   │       ├── Customer.java          # Entidad JPA
│   │   │       ├── CustomerController.java # Controlador REST
│   │   │       ├── CustomerService.java   # Lógica de negocio
│   │   │       └── CustomerRepository.java # Repositorio de datos
│   │   └── resources/
│   │       └── application.properties     # Configuración
│   └── test/
└── pom.xml
```

## 🚀 Instalación y Configuración

### Prerrequisitos

- Java 17 o superior
- Maven 3.6+
- PostgreSQL 13+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### 1. Clonar el Repositorio

```bash
git clone https://github.com/jcardonamde/crud-kasumi-api.git
cd crud-kasumi
```

### 2. Configurar Base de Datos

1. **Crear base de datos PostgreSQL:**
```sql
CREATE DATABASE kasumi;
```

2. **Configurar credenciales** en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/kasumi
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### 3. Ejecutar la Aplicación

```bash
# Compilar y ejecutar
mvn spring-boot:run

# O alternativamente
mvn clean install
java -jar target/crud-kasumi-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: `http://localhost:8080`

## 📚 API Endpoints

### Base URL: `http://localhost:8080/api/v1/customers`

| Método | Endpoint | Descripción | Cuerpo de la Petición |
|--------|----------|-------------|----------------------|
| `GET` | `/api/v1/customers` | Obtener todos los clientes | - |
| `POST` | `/api/v1/customers` | Crear nuevo cliente | JSON del cliente |
| `PUT` | `/api/v1/customers` | Actualizar cliente existente | JSON del cliente |
| `DELETE` | `/api/v1/customers/{id}` | Eliminar cliente por ID | - |

### Modelo de Datos

```json
{
  "id": 1,
  "name": "Juan",
  "lastname": "Pérez",
  "email": "juan.perez@email.com",
  "phonenumber": "+1234567890",
  "fecha": "2020-01-15",
  "antiguedad": 4
}
```

### Ejemplos de Uso

#### 1. Crear un Cliente
```bash
curl -X POST http://localhost:8080/api/v1/customers \
  -H "Content-Type: application/json" \
  -d '{
    "name": "María",
    "lastname": "García",
    "email": "maria.garcia@email.com",
    "phonenumber": "+1234567891",
    "fecha": "2019-06-20"
  }'
```

#### 2. Obtener Todos los Clientes
```bash
curl -X GET http://localhost:8080/api/v1/customers
```

#### 3. Actualizar un Cliente
```bash
curl -X PUT http://localhost:8080/api/v1/customers \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "name": "María",
    "lastname": "García López",
    "email": "maria.garcia.lopez@email.com",
    "phonenumber": "+1234567891",
    "fecha": "2019-06-20"
  }'
```

#### 4. Eliminar un Cliente
```bash
curl -X DELETE http://localhost:8080/api/v1/customers/1
```

## 🔧 Configuración

### Propiedades de la Aplicación

```properties
# Configuración de la aplicación
spring.application.name=crud-kasumi

# Configuración de la base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/kasumi
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña

# Configuración de JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

### Opciones de `ddl-auto`:

- `create-drop`: Crea las tablas al inicio y las elimina al final
- `create`: Crea las tablas al inicio
- `update`: Actualiza el esquema si es necesario
- `validate`: Valida el esquema sin modificarlo
- `none`: No realiza ninguna acción

## 🧪 Pruebas

Para ejecutar las pruebas unitarias:

```bash
mvn test
```

## 📊 Características Especiales

### Cálculo Automático de Antigüedad
La entidad `Customer` incluye un campo calculado `antiguedad` que determina automáticamente los años transcurridos desde la fecha de registro:

```java
public int getAntiguedad() {
    return Period.between(this.fecha, LocalDate.now()).getYears();
}
```

### Validación de Duplicados
El sistema previene la creación de clientes con nombres duplicados:

```java
Optional<Customer> res = customerRepository.findCustomerByName(customer.getName());
if(res.isPresent() && customer.getId()==null) {
    // Retorna error de conflicto
}
```

## 🚨 Respuestas de Error

### Cliente Duplicado (409 Conflict)
```json
{
  "error": true,
  "message": "Ya existe un cliente con ese nombre"
}
```

### Cliente No Encontrado (409 Conflict)
```json
{
  "error": true,
  "message": "No existe un cliente con ese id"
}
```

## 👨‍💻 Autores

**Desarrollado por:**

👥 Daniel Alejandro Vargas Uzuriaga
<br>
👥 Daniela López Chica <br>
👥 Jonathan Cardona Calderon <br>
👥 Luz Eleidis Baldovino González

:computer: Programa Tecnólogo en Análisis y Desarrollo de Software
Ficha Técnica 2977435

![Logo SENA](https://docs.google.com/drawings/d/e/2PACX-1vRHtXZUAI_yYltgXtZnIChIn1CDQyMCtZJLQ8R-5TiVO_IjaDVPsQnYlPEotP63Jz_I06loshw4yA1X/pub?w=50&h=50)

---

⭐ Si este proyecto te ha sido útil, ¡no olvides darle una estrella! 
