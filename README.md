# Gestión de Negocio de Alimentos

## Descripción del Proyecto

Este proyecto tiene como objetivo desarrollar un sistema de administración para un negocio de venta de alimentos, como una cafetería, restaurante o bar, utilizando una arquitectura de microservicios. El sistema permitirá gestionar diferentes roles dentro del negocio, como host, meseros, cocina, corredores (limpieza), caja y administrador.

## Características del Sistema

- **Roles de Usuario**: El sistema permite el acceso mediante login a 6 roles diferentes:
    - Host
    - Meseros
    - Cocina
    - Corredores (limpieza)
    - Caja
    - Administrador

- **Gestión de Mesas**: El host puede asignar mesas y registrar el nombre del cliente. Las mesas tendrán diferentes estados representados por colores o iconos: libre, asignada, pedido, comiendo y limpieza.

- **Gestión de Ordenes**: Los meseros pueden gestionar ordenes y enviarlas a la cocina. Las ordenes enviadas no pueden modificarse, pero se pueden agregar más ordenes.

- **Gestión de Limpieza**: Los corredores se encargan de la limpieza de las mesas asignadas una vez que el cliente pide la cuenta.

- **Gestión de Cobros**: La caja se encarga de generar el cobro una vez que el cliente valida la cuenta.

- **CRUD de Empleados y Reportes**: El administrador puede realizar un CRUD completo sobre los empleados y generar reportes de ventas por día, producto o empleado.

## Tecnologías Utilizadas

- **Lenguaje de Programación**: Java
- **Framework**: Spring Boot
- **Gestor de Dependencias**: Maven
- **Base de Datos**: MySQL
- **Arquitectura**: Microservicios
- **API Gateway**: Spring Cloud Gateway
- **Service Discovery**: Spring Eureka

## Estructura del Proyecto

El proyecto está dividido en varios módulos, cada uno representando un microservicio específico:

- `orderup-backend`: Módulo principal que contiene los submódulos.
- `orderup-model`: Contiene las entidades y repositorios.
- `orderup-api`: Contiene los microservicios específicos.
    - `orderup-api-microservicios`: Módulo que agrupa los microservicios.
        - `orderup-api-usuarios`
        - `orderup-api-productos`
        - `orderup-api-ordenes`
        - `orderup-api-mesas`

## Configuración del Proyecto

### Prerrequisitos

- Java 17
- Maven 3.6+
- MySQL 8.0+
- Docker (opcional, para contenedores)

### Instalación

1. Clonar el repositorio:
    ```sh
    git clone https://github.com/Pumpinator/orderup-backend.git
    cd orderup-backend
    ```

2. Configurar la base de datos MySQL:
    - Crear una base de datos llamada `orderup_db`.
    - Importar los datos iniciales (productos y empleados).

3. Configurar las propiedades de conexión a la base de datos en los archivos `application.properties` de cada microservicio.

4. Compilar y empaquetar el proyecto:
    ```sh
    mvn clean install
    ```

5. Ejecutar los microservicios:
    ```sh
    mvn spring-boot:run
    ```
