-- ============================================================
-- TECHSPACE COWORKING — Script 03: Consultas útiles
-- ============================================================

USE nexhub_db;

-- ------------------------------------------------------------
-- 1. Listado de todos los socios
-- ------------------------------------------------------------
SELECT id_socio, nombre, apellidos, email, tipo_membresia
FROM socio
ORDER BY apellidos, nombre;

-- ------------------------------------------------------------
-- 2. Socios con membresía premium
-- ------------------------------------------------------------
SELECT nombre, apellidos, email, fecha_alta
FROM socio
WHERE tipo_membresia = 'premium'
ORDER BY fecha_alta;

-- ------------------------------------------------------------
-- 3. Espacios disponibles ahora mismo
-- ------------------------------------------------------------
SELECT id_espacio, nombre_espacio, tipo, capacidad, precio_hora
FROM espacio
WHERE disponible = 1
ORDER BY tipo, precio_hora;

-- ------------------------------------------------------------
-- 4. Reservas con datos del socio y del espacio (JOIN)
-- ------------------------------------------------------------
SELECT 
    r.id_reserva,
    CONCAT(s.nombre, ' ', s.apellidos) AS socio,
    e.nombre_espacio,
    e.tipo,
    r.fecha_inicio,
    r.fecha_fin,
    r.estado,
    r.precio_total
FROM reserva r
JOIN socio   s ON r.id_socio   = s.id_socio
JOIN espacio e ON r.id_espacio = e.id_espacio
ORDER BY r.fecha_inicio DESC;

-- ------------------------------------------------------------
-- 5. Reservas confirmadas (pendientes de completar)
-- ------------------------------------------------------------
SELECT 
    CONCAT(s.nombre, ' ', s.apellidos) AS socio,
    e.nombre_espacio,
    r.fecha_inicio,
    r.fecha_fin,
    r.precio_total
FROM reserva r
JOIN socio   s ON r.id_socio   = s.id_socio
JOIN espacio e ON r.id_espacio = e.id_espacio
WHERE r.estado = 'confirmada';

-- ------------------------------------------------------------
-- 6. Total de ingresos por reservas completadas
-- ------------------------------------------------------------
SELECT 
    SUM(p.importe) AS total_ingresos,
    COUNT(p.id_pago) AS num_pagos
FROM pago p
JOIN reserva r ON p.id_reserva = r.id_reserva
WHERE r.estado = 'completada';

-- ------------------------------------------------------------
-- 7. Espacio más reservado
-- ------------------------------------------------------------
SELECT 
    e.nombre_espacio,
    e.tipo,
    COUNT(r.id_reserva) AS total_reservas
FROM espacio e
LEFT JOIN reserva r ON e.id_espacio = r.id_espacio
GROUP BY e.id_espacio, e.nombre_espacio, e.tipo
ORDER BY total_reservas DESC
LIMIT 5;

-- ------------------------------------------------------------
-- 8. Servicios que tiene contratado cada socio (JOIN triple)
-- ------------------------------------------------------------
SELECT 
    CONCAT(s.nombre, ' ', s.apellidos) AS socio,
    srv.nombre_servicio,
    ss.fecha_contratacion
FROM socio_servicio ss
JOIN socio   s   ON ss.id_socio    = s.id_socio
JOIN servicio srv ON ss.id_servicio = srv.id_servicio
ORDER BY socio, srv.nombre_servicio;

-- ------------------------------------------------------------
-- 9. Ingresos por tipo de espacio
-- ------------------------------------------------------------
SELECT 
    e.tipo,
    COUNT(r.id_reserva) AS reservas,
    SUM(r.precio_total) AS ingresos_totales
FROM reserva r
JOIN espacio e ON r.id_espacio = e.id_espacio
GROUP BY e.tipo
ORDER BY ingresos_totales DESC;

-- ------------------------------------------------------------
-- 10. Pagos con método tarjeta y su reserva asociada
-- ------------------------------------------------------------
SELECT 
    p.id_pago,
    p.fecha_pago,
    p.importe,
    p.metodo_pago,
    CONCAT(s.nombre, ' ', s.apellidos) AS socio,
    e.nombre_espacio
FROM pago p
JOIN reserva r ON p.id_reserva = r.id_reserva
JOIN socio   s ON r.id_socio   = s.id_socio
JOIN espacio e ON r.id_espacio = e.id_espacio
WHERE p.metodo_pago = 'tarjeta'
ORDER BY p.fecha_pago;

-- ------------------------------------------------------------
-- 11. Socio con más reservas
-- ------------------------------------------------------------
SELECT 
    CONCAT(s.nombre, ' ', s.apellidos) AS socio,
    COUNT(r.id_reserva) AS total_reservas,
    SUM(r.precio_total) AS gasto_total
FROM socio s
LEFT JOIN reserva r ON s.id_socio = r.id_socio
GROUP BY s.id_socio
ORDER BY total_reservas DESC, gasto_total DESC;
