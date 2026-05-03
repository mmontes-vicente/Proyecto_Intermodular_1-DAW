-- ============================================================
-- NEXHUB COWORKING — Script 01: Creación de tablas
-- Base de datos: nexhub_db
-- ============================================================

-- IMPORTANTE: Selecciona nexhub_db en phpMyAdmin antes de importar
-- o ejecuta esta línea primero:
-- USE nexhub_db;

-- ============================================================
-- TABLA: SOCIO
-- ============================================================
CREATE TABLE socio (
    id_socio       INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(100)  NOT NULL,
    apellidos      VARCHAR(150)  NOT NULL,
    email          VARCHAR(150)  NOT NULL UNIQUE,
    telefono       VARCHAR(15),
    fecha_alta     DATE          NOT NULL,
    tipo_membresia ENUM('basica','premium','empresa') NOT NULL DEFAULT 'basica'
);

-- ============================================================
-- TABLA: ESPACIO
-- ============================================================
CREATE TABLE espacio (
    id_espacio     INT AUTO_INCREMENT PRIMARY KEY,
    nombre_espacio VARCHAR(100)  NOT NULL,
    tipo           ENUM('escritorio','sala_reunion','sala_evento','cabina') NOT NULL,
    capacidad      INT           NOT NULL,
    precio_hora    DECIMAL(8,2)  NOT NULL,
    disponible     TINYINT(1)    NOT NULL DEFAULT 1
);

-- ============================================================
-- TABLA: EMPLEADO
-- ============================================================
CREATE TABLE empleado (
    id_empleado        INT AUTO_INCREMENT PRIMARY KEY,
    nombre             VARCHAR(100)  NOT NULL,
    apellidos          VARCHAR(150)  NOT NULL,
    cargo              VARCHAR(100)  NOT NULL,
    email              VARCHAR(150)  UNIQUE,
    salario            DECIMAL(10,2),
    fecha_contratacion DATE
);

-- ============================================================
-- TABLA: SERVICIO
-- ============================================================
CREATE TABLE servicio (
    id_servicio     INT AUTO_INCREMENT PRIMARY KEY,
    nombre_servicio VARCHAR(150) NOT NULL,
    descripcion     TEXT,
    precio          DECIMAL(8,2) NOT NULL
);

-- ============================================================
-- TABLA: RESERVA
-- Depende de: socio, espacio
-- ============================================================
CREATE TABLE reserva (
    id_reserva   INT AUTO_INCREMENT PRIMARY KEY,
    id_socio     INT           NOT NULL,
    id_espacio   INT           NOT NULL,
    fecha_inicio DATETIME      NOT NULL,
    fecha_fin    DATETIME      NOT NULL,
    estado       ENUM('pendiente','confirmada','cancelada','completada') NOT NULL DEFAULT 'pendiente',
    precio_total DECIMAL(10,2),
    FOREIGN KEY (id_socio)   REFERENCES socio(id_socio),
    FOREIGN KEY (id_espacio) REFERENCES espacio(id_espacio)
);

-- ============================================================
-- TABLA: PAGO
-- Depende de: reserva
-- ============================================================
CREATE TABLE pago (
    id_pago     INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva  INT           NOT NULL UNIQUE,
    fecha_pago  DATE          NOT NULL,
    importe     DECIMAL(10,2) NOT NULL,
    metodo_pago ENUM('tarjeta','transferencia','efectivo') NOT NULL,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);

-- ============================================================
-- TABLA: SOCIO_SERVICIO
-- Tabla intermedia relación N:M entre socio y servicio
-- ============================================================
CREATE TABLE socio_servicio (
    id_socio           INT  NOT NULL,
    id_servicio        INT  NOT NULL,
    fecha_contratacion DATE NOT NULL,
    PRIMARY KEY (id_socio, id_servicio),
    FOREIGN KEY (id_socio)    REFERENCES socio(id_socio),
    FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio)
);
