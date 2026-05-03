<div align="center">

![alt text](demo/logo.png)


# NexHub Coworking

**Programación — Módulo 0485 + MPO · Miguel Montes Vicente · 1º DAW · 2026**

</div>

---

Aplicación de consola en Java 24 que gestiona socios, espacios, reservas y empleados conectándose a MySQL con JDBC. El código usa el patrón DAO para separar el SQL del resto de la lógica.

---

## Estructura

```
03_Programacion_MPO/src/

- Main.java
- db/
    - Conexion.java             Singleton JDBC
- dao/
    - SocioDAO.java             todo el SQL de socios
    - EspacioDAO.java           todo el SQL de espacios
    - ReservaDAO.java           todo el SQL de reservas
    - EmpleadoDAO.java          todo el SQL de empleados
- model/
    - Persona.java              clase abstracta
    - Socio.java                extiende Persona
    - Empleado.java             extiende Persona
    - Espacio.java
    - Reserva.java              calcularPrecio(), esValida()
- service/
    - SocioService.java
    - EspacioService.java
    - ReservaService.java
    - EmpleadoService.java
- controller/
    - MenuController.java
- utils/
    - CrudService.java          interfaz genérica <T, ID>
    - Validador.java            validaciones centralizadas
```

---

## Cómo funciona

```
MenuController  →  Service  →  DAO  →  MySQL
   (input)         (lógica)   (SQL)
```

El controlador recibe lo que escribe el usuario y llama al servicio. El servicio valida los datos y llama al DAO. El DAO ejecuta el SQL y devuelve objetos. Cada clase tiene una sola cosa que hacer.

---

## Decisiones de diseño — MPO

**`Persona` es abstracta.** `Socio` y `Empleado` comparten `id`, `nombre`, `apellidos` y `email`. Sin la clase abstracta, esos campos estarían copiados en dos sitios. Si cambia algo, se cambia en un solo lugar.

**`CrudService<T, ID>` es una interfaz.** Define los métodos que tiene que tener cualquier servicio: `guardar`, `listarTodos`, `buscarPorId` y `eliminar`. Añadir un servicio nuevo es implementar esa interfaz y rellenarla.

**`Validador` con métodos estáticos.** Toda la lógica de validación está en un único lugar. Si cambia el formato de email permitido, se edita un método en un archivo.

**`Reserva` tiene lógica propia.** `calcularPrecio()` y `esValida()` están en el modelo porque pertenecen al negocio, no al controlador ni a la base de datos.

**`EmpleadoService` como módulo extra.** Se añadió sin tocar el código que ya existía. Eso demuestra que la arquitectura en capas permite ampliar la aplicación sin romper lo que ya funciona.

---

## Antes de ejecutar

**1. Base de datos**

```
1. XAMPP → Start MySQL
2. phpMyAdmin → crear base de datos: nexhub_db
3. Importar → 04_Base_de_Datos/sql/nexhub_db.sql → Continuar
```

**2. Driver JDBC**

Descargar desde https://dev.mysql.com/downloads/connector/j (Platform Independent), extraer el `.jar` y añadirlo en IntelliJ:

```
File → Project Structure → Libraries → + → Java → seleccionar el .jar
```

**3. Credenciales**

Editar `src/db/Conexion.java`:

```java
private static final String URL      = "jdbc:mysql://localhost:3306/nexhub_db";
private static final String USUARIO  = "root";
private static final String PASSWORD = "";   // vacío en XAMPP por defecto
```

---

## Ejecutar

```
1. XAMPP con MySQL activo
2. IntelliJ → File → Open → 03_Programacion_MPO/
3. Clic derecho sobre src/ → Mark Directory as → Sources Root
4. File → Project Structure → Libraries → + → mysql-connector-j.jar
5. File → Project Structure → Project → SDK: Java 24
6. Clic derecho sobre Main.java → Run
```
