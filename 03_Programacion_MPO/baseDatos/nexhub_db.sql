-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 23-04-2026 a las 02:21:13
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nexhub_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `cargo` varchar(100) NOT NULL,
  `email` varchar(150) DEFAULT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  `fecha_contratacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `nombre`, `apellidos`, `cargo`, `email`, `salario`, `fecha_contratacion`) VALUES
(1, 'Roberto', 'Herrera Vega', 'Gerente', 'roberto.herrera@nexhub.com', 2800.00, '2023-06-01'),
(2, 'Isabel', 'Morales Pino', 'Recepcionista', 'isabel.morales@nexhub.com', 1600.00, '2023-06-15'),
(3, 'Diego', 'Castro Fuentes', 'Técnico IT', 'diego.castro@nexhub.com', 2000.00, '2023-09-01'),
(4, 'Natalia', 'Blanco Ruiz', 'Coordinadora', 'natalia.blanco@nexhub.com', 1900.00, '2024-01-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacio`
--

CREATE TABLE `espacio` (
  `id_espacio` int(11) NOT NULL,
  `nombre_espacio` varchar(100) NOT NULL,
  `tipo` enum('escritorio','sala_reunion','sala_evento','cabina') NOT NULL,
  `capacidad` int(11) NOT NULL,
  `precio_hora` decimal(8,2) NOT NULL,
  `disponible` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `espacio`
--

INSERT INTO `espacio` (`id_espacio`, `nombre_espacio`, `tipo`, `capacidad`, `precio_hora`, `disponible`) VALUES
(1, 'Escritorio A1', 'escritorio', 1, 5.00, 1),
(2, 'Escritorio A2', 'escritorio', 1, 5.00, 1),
(3, 'Escritorio B1', 'escritorio', 1, 5.00, 0),
(4, 'Sala Innovación', 'sala_reunion', 8, 20.00, 1),
(5, 'Sala Colaboración', 'sala_reunion', 6, 15.00, 1),
(6, 'Sala Presentación', 'sala_evento', 20, 40.00, 1),
(7, 'Cabina Podcast', 'cabina', 2, 10.00, 1),
(8, 'Escritorio C1', 'escritorio', 1, 5.00, 1),
(9, 'Escritorio C2', 'escritorio', 1, 5.00, 1),
(10, 'Sala Dirección', 'sala_reunion', 4, 25.00, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE `pago` (
  `id_pago` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL,
  `fecha_pago` date NOT NULL,
  `importe` decimal(10,2) NOT NULL,
  `metodo_pago` enum('tarjeta','transferencia','efectivo') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`id_pago`, `id_reserva`, `fecha_pago`, `importe`, `metodo_pago`) VALUES
(1, 1, '2024-04-01', 40.00, 'tarjeta'),
(2, 2, '2024-04-02', 40.00, 'tarjeta'),
(3, 3, '2024-04-03', 160.00, 'transferencia'),
(4, 4, '2024-04-05', 30.00, 'tarjeta'),
(5, 5, '2024-04-08', 40.00, 'efectivo'),
(6, 6, '2024-04-10', 20.00, 'tarjeta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id_reserva` int(11) NOT NULL,
  `id_socio` int(11) NOT NULL,
  `id_espacio` int(11) NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime NOT NULL,
  `estado` enum('pendiente','confirmada','cancelada','completada') NOT NULL DEFAULT 'pendiente',
  `precio_total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id_reserva`, `id_socio`, `id_espacio`, `fecha_inicio`, `fecha_fin`, `estado`, `precio_total`) VALUES
(1, 1, 1, '2024-04-01 09:00:00', '2024-04-01 17:00:00', 'completada', 40.00),
(2, 2, 4, '2024-04-02 10:00:00', '2024-04-02 12:00:00', 'completada', 40.00),
(3, 3, 6, '2024-04-03 09:00:00', '2024-04-03 13:00:00', 'completada', 160.00),
(4, 1, 5, '2024-04-05 14:00:00', '2024-04-05 16:00:00', 'completada', 30.00),
(5, 4, 2, '2024-04-08 09:00:00', '2024-04-08 17:00:00', 'confirmada', 40.00),
(6, 5, 7, '2024-04-10 11:00:00', '2024-04-10 13:00:00', 'confirmada', 20.00),
(7, 6, 4, '2024-04-12 09:00:00', '2024-04-12 11:00:00', 'pendiente', 40.00),
(8, 7, 1, '2024-04-15 09:00:00', '2024-04-15 17:00:00', 'confirmada', 40.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id_servicio` int(11) NOT NULL,
  `nombre_servicio` varchar(150) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id_servicio`, `nombre_servicio`, `descripcion`, `precio`) VALUES
(1, 'Internet Fibra 1Gbps', 'Conexión de fibra óptica simétrica de alta velocidad', 30.00),
(2, 'Taquilla Personal', 'Taquilla privada con llave para guardar material', 15.00),
(3, 'Impresión/Escaneado', 'Acceso ilimitado a impresoras y escáners', 10.00),
(4, 'Café y Snacks', 'Acceso ilimitado a zona de café, té y snacks', 20.00),
(5, 'Sala de Reuniones Extra', '5 horas adicionales en salas de reuniones al mes', 25.00),
(6, 'Parking', 'Plaza de parking cubierta en el edificio', 50.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socio`
--

CREATE TABLE `socio` (
  `id_socio` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `tipo_membresia` enum('basica','premium','empresa') NOT NULL DEFAULT 'basica'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `socio`
--

INSERT INTO `socio` (`id_socio`, `nombre`, `apellidos`, `email`, `telefono`, `fecha_alta`, `tipo_membresia`) VALUES
(1, 'Ana', 'García López', 'ana.garcia@email.com', '612345678', '2024-01-10', 'premium'),
(2, 'Carlos', 'Martínez Ruiz', 'carlos.martinez@email.com', '623456789', '2024-01-15', 'basica'),
(3, 'Laura', 'Sánchez Pérez', 'laura.sanchez@email.com', '634567890', '2024-02-01', 'empresa'),
(4, 'Pedro', 'González Torres', 'pedro.gonzalez@email.com', '645678901', '2024-02-14', 'basica'),
(5, 'Marta', 'Fernández Gil', 'marta.fernandez@email.com', '656789012', '2024-03-01', 'premium'),
(6, 'Javier', 'López Moreno', 'javier.lopez@email.com', '667890123', '2024-03-10', 'basica'),
(7, 'Sofía', 'Ramírez Castro', 'sofia.ramirez@email.com', '678901234', '2024-03-20', 'empresa'),
(8, 'Alejandro', 'Díaz Vargas', 'alejandro.diaz@email.com', '689012345', '2024-04-05', 'premium');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socio_servicio`
--

CREATE TABLE `socio_servicio` (
  `id_socio` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `fecha_contratacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `socio_servicio`
--

INSERT INTO `socio_servicio` (`id_socio`, `id_servicio`, `fecha_contratacion`) VALUES
(1, 1, '2024-01-10'),
(1, 2, '2024-01-10'),
(1, 4, '2024-01-10'),
(2, 1, '2024-01-15'),
(3, 1, '2024-02-01'),
(3, 2, '2024-02-01'),
(3, 5, '2024-02-01'),
(3, 6, '2024-02-01'),
(5, 1, '2024-03-01'),
(5, 4, '2024-03-01'),
(7, 1, '2024-03-20'),
(7, 6, '2024-03-20');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `espacio`
--
ALTER TABLE `espacio`
  ADD PRIMARY KEY (`id_espacio`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`id_pago`),
  ADD UNIQUE KEY `id_reserva` (`id_reserva`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `id_socio` (`id_socio`),
  ADD KEY `id_espacio` (`id_espacio`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id_servicio`);

--
-- Indices de la tabla `socio`
--
ALTER TABLE `socio`
  ADD PRIMARY KEY (`id_socio`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `socio_servicio`
--
ALTER TABLE `socio_servicio`
  ADD PRIMARY KEY (`id_socio`,`id_servicio`),
  ADD KEY `id_servicio` (`id_servicio`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `espacio`
--
ALTER TABLE `espacio`
  MODIFY `id_espacio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `id_pago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `socio`
--
ALTER TABLE `socio`
  MODIFY `id_socio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_socio`) REFERENCES `socio` (`id_socio`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_espacio`) REFERENCES `espacio` (`id_espacio`);

--
-- Filtros para la tabla `socio_servicio`
--
ALTER TABLE `socio_servicio`
  ADD CONSTRAINT `socio_servicio_ibfk_1` FOREIGN KEY (`id_socio`) REFERENCES `socio` (`id_socio`),
  ADD CONSTRAINT `socio_servicio_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id_servicio`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
