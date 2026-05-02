<div align="center">

![alt text](logo.png)


# NexHub Coworking

**Proyecto Intermodular · 1º DAW · Miguel Montes Vicente · 2026**

</div>

---

NexHub es una plataforma de gestión para un coworking tecnológico ficticio en Madrid. Es el proyecto que une todos los módulos del curso: la web muestra lo mismo que gestiona la aplicación Java, y los datos viven en la base de datos MySQL. Eso es lo que lo hace intermodular.

```
Web pública  ←——→  App Java  ←——→  MySQL (nexhub_db)
  (cliente)         (admin)         (datos)
```

---

## Estructura del proyecto

```
nexhub-project/
│
├── 01_Sistemas_Informaticos/
│   ├── capturas/
│   └── README.md
│
├── 02_Lenguaje_de_Marcas/
│   ├── index.html
│   ├── espacios.html
│   ├── tarifas.html
│   ├── comunidad.html
│   ├── contacto.html
│   └── assets/
│       ├── css/style.css
│       ├── images/favicon.svg
│       └── js/main.js
│
├── 03_Programacion_MPO/
│   └── src/
│       ├── Main.java
│       ├── db/Conexion.java
│       ├── dao/
│       ├── model/
│       ├── service/
│       ├── controller/
│       └── utils/
│
├── 04_Base_de_Datos/
│   ├── diagramas/
│   │   ├── diagramas_nexhub.drawio
│   │   └── diagramas_nexhub.png
│   └── sql/
│       ├── nexhub_db.sql
│       ├── 01_crear_tablas.sql
│       ├── 02_insertar_datos.sql
│       └── 03_consultas.sql
│
└── 05_Itinerario_Empleabilidad/
    └── README.md
```

---

## Tecnologías

| Capa | Tecnología | Para qué |
|------|-----------|----------|
| Frontend | HTML5, CSS3, JavaScript | Web pública sin frameworks |
| Backend | Java 24, JDBC | Aplicación de consola con patrón DAO |
| Base de datos | MySQL, XAMPP | 7 tablas relacionales |
| Versiones | Git, GitHub | Control de cambios y entrega |
| IDE | IntelliJ IDEA Community | Desarrollo y ejecución |

---

## Cómo ejecutarlo

**Base de datos**

```
1. XAMPP → Start MySQL
2. phpMyAdmin → crear base de datos: nexhub_db
3. Importar → 04_Base_de_Datos/sql/nexhub_db.sql
```

**Aplicación Java**

```
1. IntelliJ → File → Open → 03_Programacion_MPO/
2. Clic derecho sobre src/ → Mark Directory as → Sources Root
3. File → Project Structure → Libraries → + → mysql-connector-j.jar
4. Clic derecho sobre Main.java → Run
```

**Web**

```
Doble clic en 02_Lenguaje_de_Marcas/index.html
No necesita servidor
```

---

## La empresa

NexHub Coworking — Calle Innovación 42, Madrid 28001. Espacio de trabajo para freelancers, startups y equipos distribuidos. Ofrece escritorios flexibles, oficinas privadas, salas de reuniones y servicios adicionales por suscripción mensual. Es una empresa ficticia creada para el proyecto.

---
