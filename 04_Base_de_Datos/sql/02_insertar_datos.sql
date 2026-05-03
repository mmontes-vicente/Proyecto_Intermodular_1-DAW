-- ============================================================
-- TECHSPACE COWORKING — Script 02: Inserción de datos
-- ============================================================

USE nexhub_db;

-- ============================================================
-- SOCIOS
-- ============================================================
INSERT INTO socio (nombre, apellidos, email, telefono, fecha_alta, tipo_membresia) VALUES
('Ana',      'García López',     'ana.garcia@email.com',      '612345678', '2024-01-10', 'premium'),
('Carlos',   'Martínez Ruiz',    'carlos.martinez@email.com', '623456789', '2024-01-15', 'basica'),
('Laura',    'Sánchez Pérez',    'laura.sanchez@email.com',   '634567890', '2024-02-01', 'empresa'),
('Pedro',    'González Torres',  'pedro.gonzalez@email.com',  '645678901', '2024-02-14', 'basica'),
('Marta',    'Fernández Gil',    'marta.fernandez@email.com', '656789012', '2024-03-01', 'premium'),
('Javier',   'López Moreno',     'javier.lopez@email.com',    '667890123', '2024-03-10', 'basica'),
('Sofía',    'Ramírez Castro',   'sofia.ramirez@email.com',   '678901234', '2024-03-20', 'empresa'),
('Alejandro','Díaz Vargas',      'alejandro.diaz@email.com',  '689012345', '2024-04-05', 'premium');

-- ============================================================
-- ESPACIOS
-- ============================================================
INSERT INTO espacio (nombre_espacio, tipo, capacidad, precio_hora, disponible) VALUES
('Escritorio A1',     'escritorio',    1, 5.00,  1),
('Escritorio A2',     'escritorio',    1, 5.00,  1),
('Escritorio B1',     'escritorio',    1, 5.00,  0),
('Sala Innovación',   'sala_reunion',  8, 20.00, 1),
('Sala Colaboración', 'sala_reunion',  6, 15.00, 1),
('Sala Presentación', 'sala_evento',  20, 40.00, 1),
('Cabina Podcast',    'cabina',        2, 10.00, 1),
('Escritorio C1',     'escritorio',    1, 5.00,  1),
('Escritorio C2',     'escritorio',    1, 5.00,  1),
('Sala Dirección',    'sala_reunion',  4, 25.00, 1);

-- ============================================================
-- EMPLEADOS
-- ============================================================
INSERT INTO empleado (nombre, apellidos, cargo, email, salario, fecha_contratacion) VALUES
('Roberto',  'Herrera Vega',    'Gerente',           'roberto.herrera@techspace.com', 2800.00, '2023-06-01'),
('Isabel',   'Morales Pino',    'Recepcionista',     'isabel.morales@techspace.com',  1600.00, '2023-06-15'),
('Diego',    'Castro Fuentes',  'Técnico IT',        'diego.castro@techspace.com',    2000.00, '2023-09-01'),
('Natalia',  'Blanco Ruiz',     'Coordinadora',     'natalia.blanco@techspace.com',  1900.00, '2024-01-05');

-- ============================================================
-- SERVICIOS
-- ============================================================
INSERT INTO servicio (nombre_servicio, descripcion, precio) VALUES
('Internet Fibra 1Gbps',  'Conexión de fibra óptica simétrica de alta velocidad', 30.00),
('Taquilla Personal',     'Taquilla privada con llave para guardar material',      15.00),
('Impresión/Escaneado',   'Acceso ilimitado a impresoras y escáners',              10.00),
('Café y Snacks',         'Acceso ilimitado a zona de café, té y snacks',          20.00),
('Sala de Reuniones Extra','5 horas adicionales en salas de reuniones al mes',     25.00),
('Parking',               'Plaza de parking cubierta en el edificio',              50.00);

-- ============================================================
-- RESERVAS
-- ============================================================
INSERT INTO reserva (id_socio, id_espacio, fecha_inicio, fecha_fin, estado, precio_total) VALUES
(1, 1, '2024-04-01 09:00:00', '2024-04-01 17:00:00', 'completada', 40.00),
(2, 4, '2024-04-02 10:00:00', '2024-04-02 12:00:00', 'completada', 40.00),
(3, 6, '2024-04-03 09:00:00', '2024-04-03 13:00:00', 'completada', 160.00),
(1, 5, '2024-04-05 14:00:00', '2024-04-05 16:00:00', 'completada', 30.00),
(4, 2, '2024-04-08 09:00:00', '2024-04-08 17:00:00', 'confirmada', 40.00),
(5, 7, '2024-04-10 11:00:00', '2024-04-10 13:00:00', 'confirmada', 20.00),
(6, 4, '2024-04-12 09:00:00', '2024-04-12 11:00:00', 'pendiente',  40.00),
(7, 1, '2024-04-15 09:00:00', '2024-04-15 17:00:00', 'confirmada', 40.00);

-- ============================================================
-- PAGOS
-- ============================================================
INSERT INTO pago (id_reserva, fecha_pago, importe, metodo_pago) VALUES
(1, '2024-04-01', 40.00,  'tarjeta'),
(2, '2024-04-02', 40.00,  'tarjeta'),
(3, '2024-04-03', 160.00, 'transferencia'),
(4, '2024-04-05', 30.00,  'tarjeta'),
(5, '2024-04-08', 40.00,  'efectivo'),
(6, '2024-04-10', 20.00,  'tarjeta');

-- ============================================================
-- SOCIO_SERVICIO (socios que contratan servicios)
-- ============================================================
INSERT INTO socio_servicio (id_socio, id_servicio, fecha_contratacion) VALUES
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
