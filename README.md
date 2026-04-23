# NexHub Coworking

Proyecto Intermodular · 1.º DAW · Prometeo by The Power · 2025

---

NexHub es una plataforma de gestión para un coworking tecnológico ficticio en Madrid. El proyecto reúne los módulos del curso en algo que funciona como un todo: una web pública, una aplicación Java para el administrador y la base de datos que los conecta.

---

## Estructura

```
nexhub-project/

- web/                          # Módulo Lenguajes de Marcas (0373)
    - index.html
    - espacios.html
    - tarifas.html
    - comunidad.html
    - contacto.html
    - assets/
        - css/style.css
        - images/favicon.svg
        - js/main.js

- src/                          # Módulo Programación (0485) + MPO
    - Main.java
    - db/
        - Conexion.java
    - dao/
        - SocioDAO.java         <-- acceso a datos socios
        - EspacioDAO.java       <-- acceso a datos espacios
        - ReservaDAO.java       <-- acceso a datos reservas
        - EmpleadoDAO.java      <-- acceso a datos empleados
    - model/
        - Persona.java          <-- abstract
        - Socio.java
        - Empleado.java
        - Espacio.java
        - Reserva.java
    - service/
        - SocioService.java
        - EspacioService.java
        - ReservaService.java
        - EmpleadoService.java
    - controller/
        - MenuController.java
    - utils/
        - CrudService.java      <-- interface genérica
        - Validador.java

- bbdd/                         # Módulo Bases de Datos (0484)
    - sql/
        - 01_crear_tablas.sql
        - 02_insertar_datos.sql
        - 03_consultas.sql
    - diagrama_ER.drawio        <-- añadir manualmente

- SISTEMAS.md                   # Módulo Sistemas Informáticos (0483)
- EMPLEABILIDAD.md              # Módulo Empleabilidad (1709)
- README.md
```

---

## Tecnologías

|--|--|
| Web | HTML5, CSS3, JavaScript vanilla |
| Aplicación | Java 24 + JDBC |
| Patrón acceso a datos | DAO (Data Access Object) |
| Base de datos | MySQL con XAMPP |
| Versiones | Git y GitHub |
| IDE | IntelliJ IDEA Community |

---

## Puesta en marcha

**Base de datos**

1. Abre XAMPP y arranca MySQL.
2. En phpMyAdmin crea una base de datos llamada `nexhub_db`.
3. Importa `bbdd/sql/01_crear_tablas.sql` y después `bbdd/sql/02_insertar_datos.sql`.

**Aplicación Java**

1. Abre IntelliJ y carga la carpeta `src/`.
2. Añade el driver JDBC: File > Project Structure > Libraries > + > selecciona `mysql-connector-j.jar`.
3. Edita `src/db/Conexion.java` con tu usuario y contraseña de MySQL.
4. Ejecuta `Main.java`.

**Web**

Doble clic en `web/index.html`. No necesita servidor.

---

## Empresa ficticia

NexHub Coworking — Calle Innovación 42, Madrid. Espacio para freelancers, startups y equipos. Escritorios flexibles, oficinas privadas, salas de reuniones y servicios por suscripción mensual.

---

## Tutores

Francisco Molpeceres — francisco.molpeceres@thepower.education  
Miguel Ángel Alayón — miguel.alayon@thepower.education
