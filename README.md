# NexHub Coworking

Proyecto Intermodular · 1.º DAW · Prometeo by The Power · 2025

---

NexHub es una plataforma de gestión para un coworking tecnológico ficticio en Madrid. El proyecto reúne los cinco módulos del curso en un sistema coherente: una web pública, una aplicación Java para el administrador y la base de datos que los sostiene.

---

## Estructura del repositorio

```
nexhub-project/

- 01_Sistemas_Informaticos/
    - capturas/
    - README.md

- 02_Lenguaje_de_Marcas/
    - index.html
    - espacios.html
    - tarifas.html
    - comunidad.html
    - contacto.html
    - assets/
        - css/style.css
        - images/favicon.svg
        - js/main.js
    - README.md

- 03_Programacion_MPO/
    - src/
        - Main.java
        - db/Conexion.java
        - dao/
            - SocioDAO.java
            - EspacioDAO.java
            - ReservaDAO.java
            - EmpleadoDAO.java
        - model/
            - Persona.java      <-- abstract
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
            - CrudService.java  <-- interface genérica
            - Validador.java
    - README.md

- 04_Base_de_Datos/
    - diagramas/
        - diagrama_ER.drawio
        - modelo_relacional.drawio
    - sql/
        - 01_crear_tablas.sql
        - 02_insertar_datos.sql
        - 03_consultas.sql
    - README.md

- 05_Itinerario_Empleabilidad/
    - README.md

- README.md
```

---

## Tecnologías

| | |
|--|--|
| Web | HTML5, CSS3, JavaScript vanilla |
| Aplicación | Java 24 + JDBC + patrón DAO |
| Base de datos | MySQL con XAMPP |
| Versiones | Git y GitHub |
| IDE | IntelliJ IDEA Community |

---

## Puesta en marcha

**Base de datos** — ver `04_Base_de_Datos/README.md`

**Aplicación Java** — ver `03_Programacion_MPO/README.md`

**Web** — abrir `02_Lenguaje_de_Marcas/index.html` directamente en el navegador

---

## Empresa ficticia

NexHub Coworking — Calle Innovación 42, Madrid. Espacio para freelancers, startups y equipos. Escritorios flexibles, oficinas privadas, salas de reuniones y servicios adicionales por suscripción mensual.

---

## Tutores

Francisco Molpeceres — francisco.molpeceres@thepower.education  
Miguel Ángel Alayón — miguel.alayon@thepower.education
