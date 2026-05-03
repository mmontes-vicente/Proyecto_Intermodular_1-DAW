<div align="center">

![alt text](capturas/logo.png)


# NexHub Coworking

**Bases de Datos — Módulo 0484 · Miguel Montes Vicente · 1º DAW · 2026**

</div>

---

Base de datos relacional para NexHub Coworking. Siete tablas que modelan el funcionamiento real del negocio: socios, espacios, reservas, pagos, empleados y servicios.

---

## Estructura

```
04_Base_de_Datos/

- diagramas/
    - diagramas_nexhub.drawio
    - diagramas_nexhub.png
- sql/
    - nexhub_db.sql             exportación completa: estructura + datos
    - 01_crear_tablas.sql       DDL: las 7 tablas con claves primarias y foráneas
    - 02_insertar_datos.sql     DML: datos de ejemplo realistas
    - 03_consultas.sql          11 consultas con SELECT, JOIN, GROUP BY y ORDER BY
- README.md
```

Los archivos `.drawio` se abren con https://app.diagrams.net, sin instalar nada.

---

## Tablas

| Tabla | Qué guarda |
|-------|-----------|
| `socio` | Clientes del coworking con su tipo de membresía |
| `espacio` | Escritorios, oficinas, salas y cabinas |
| `empleado` | Personal que gestiona las reservas |
| `reserva` | Cada reserva de un espacio por un socio |
| `pago` | El pago que genera cada reserva (1:1) |
| `servicio` | Servicios adicionales: parking, taquilla, impresión |
| `socio_servicio` | Qué servicios tiene contratados cada socio (N:M) |

---

## Relaciones

```
| Entidades | Cardinalidad |
|-----------|:---:|
| SOCIO — RESERVA | 1:N |
| ESPACIO — RESERVA | 1:N |
| EMPLEADO — RESERVA | 1:N |
| RESERVA — PAGO | 1:1 |
| SOCIO — SERVICIO | N:M |

```
La relación N:M entre SOCIO y SERVICIO se resuelve con `socio_servicio`, cuya clave primaria es compuesta: `(id_socio, id_servicio)`.

---

## Scripts

**`nexhub_db.sql`** — exportación completa generada desde phpMyAdmin. Incluye la estructura y los datos en un solo archivo. Es lo más cómodo para que el profesor lo importe de una vez.

**`01_crear_tablas.sql`** — DDL. Crea las 7 tablas con sus claves primarias, foráneas y restricciones. Ejecutar primero.

**`02_insertar_datos.sql`** — DML. Inserta los datos de ejemplo. Están pensados para que las consultas del siguiente script devuelvan resultados con sentido.

**`03_consultas.sql`** — 11 consultas útiles: socios por membresía, reservas activas con JOIN, ingresos por mes, espacios disponibles y más.

---

## Importar en phpMyAdmin

### Opción recomendada

```
1. XAMPP → Start MySQL
2. http://localhost/phpmyadmin
3. Nueva base de datos: nexhub_db
4. Seleccionar nexhub_db → Importar → nexhub_db.sql → Continuar
```

### Opción paso a paso

```
1. Crear nexhub_db
2. Importar 01_crear_tablas.sql
3. Importar 02_insertar_datos.sql
4. Para probar consultas: pestaña SQL → pegar 03_consultas.sql
```
