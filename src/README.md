# Cuna Inteligente - Backend

Este repositorio contiene el backend del proyecto **Cuna Inteligente**, desarrollado con Spring Boot. El backend es responsable de gestionar la lógica de negocio, procesar los datos de los sensores, y proporcionar una API RESTful para que el frontend y los nodos IoT interactúen con el sistema.

## Problemática

El monitoreo del entorno de un bebé y el control de dispositivos asociados requieren una gestión eficiente y segura de datos. El backend se encarga de centralizar esta información, asegurando que los datos sean procesados en tiempo real y almacenados de manera estructurada para análisis y reportes futuros.

## Objetivos del Backend

- Recibir datos de los sensores IoT (temperatura, humedad, movimiento, audio).
- Procesar y almacenar los datos en una base de datos PostgreSQL.
- Exponer una API RESTful para el acceso y control de los datos.
- Gestionar comandos hacia los dispositivos IoT, como reproducir sonidos o mover el servomotor.
- Implementar reglas de negocio para generar alertas basadas en condiciones predefinidas.

## Funcionalidades

1. **Gestión de Datos de Sensores**:  
   Almacena y procesa registros de temperatura, humedad, movimiento y audio en tiempo real.

2. **API RESTful**:  
   Proporciona endpoints para que el frontend pueda consultar datos y enviar comandos.

3. **Validación y Seguridad**:  
   Implementa validaciones para garantizar la integridad de los datos y el acceso seguro mediante tokens.

4. **Notificaciones y Alertas**:  
   Genera alertas cuando se detectan condiciones críticas en los datos.

## Estructura del Proyecto

```plaintext
src/
├── main/
│   ├── java/com/cunainteligente/
│   │   ├── controller/    # Controladores REST
│   │   ├── service/       # Servicios de lógica de negocio
│   │   ├── repository/    # Repositorios JPA para la base de datos
│   │   └── model/         # Entidades JPA
│   └── resources/
│       └── application.properties  # Configuración del proyecto
