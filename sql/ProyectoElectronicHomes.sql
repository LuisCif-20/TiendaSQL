/*--------------------Creamos la Base de Datos--------------------*/

CREATE DATABASE electronichomes;
\c electronichomes

/*Creamos los Esquemas*/

CREATE SCHEMA ControlInmuebles;
CREATE SCHEMA ControlPersonal;
CREATE SCHEMA ControlTienda;

/*Creamos Tablas para las entidades presentes*/

/*Para el Esquema ControlInmuebles*/
CREATE TABLE ControlInmuebles.Instalacion (
    idSucursal SERIAL,
    nombreInmueble VARCHAR(20) NOT NULL UNIQUE,
    descripcion VARCHAR(200) NOT NULL,
    PRIMARY KEY (idSucursal)
);

/*Para el Esquema ControlPersonal*/
CREATE TABLE ControlPersonal.Rol (
    idRol Serial,
    nombreRol VARCHAR(20) NOT NULL UNIQUE,
    descripcion VARCHAR(500) NOT NULL,
    PRIMARY KEY (idRol)
);

CREATE TABLE ControlPersonal.Empleado (
    idUsuario BIGINT NOT NULL,
    contrasenia VARCHAR(64) NOT NULL,
    nombreEmpleado VARCHAR(50) NOT NULL,
    sucursal INT NOT NULL REFERENCES ControlInmuebles.Instalacion(idSucursal),
    rol INT NOT NULL REFERENCES ControlPersonal.Rol(idRol),
    salario DECIMAL NOT NULL,
    PRIMARY KEY (idUsuario)
);

/*Para el Esquema de Tienda*/
CREATE TABLE ControlTienda.Inventario (
    idInventario SERIAL,
    inmueble INT NOT NULL,
    FOREIGN KEY (inmueble) REFERENCES ControlInmuebles.Instalacion(idSucursal),
    PRIMARY KEY (idInventario)
);

CREATE TABLE ControlTienda.Producto (
    codigoPdt VARCHAR(12) NOT NULL,
    inventario INT NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL,
    precioUnitario DECIMAL NOT NULL,
    FOREIGN KEY (inventario) REFERENCES ControlTienda.Inventario (idInventario),
    PRIMARY KEY (codigoPdt, inventario)
);

CREATE TABLE ControlTienda.Cliente (
    idCli SERIAL,
    nit BIGINT NOT NULL UNIQUE,
    apellidoCliente VARCHAR(25) NOT NULL,
    nombreCliente VARCHAR(25) NOT NULL,
    PRIMARY KEY (idCli)
);

CREATE TABLE ControlTienda.Venta (
    idVenta SERIAL,
    sucursal INT NOT NULL REFERENCES ControlInmuebles.Instalacion (idSucursal),
    fechaVenta DATE NOT NULL,
    codigoEmpleado BIGINT NOT NULL REFERENCES ControlPersonal.Empleado (idUsuario),
    nitCli BIGINT REFERENCES ControlTienda.Cliente (nit),
    cf BOOLEAN NOT NULL,
    total DECIMAL NOT NULL, 
    PRIMARY KEY (idVenta)
);

CREATE TABLE ControlTienda.DetalleVenta (
    idDtl SERIAL,
    idVenta INT NOT NULL,
    codigoPdt VARCHAR(12) NOT NULL,
    inventario INT NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL,
    subTotal DECIMAL NOT NULL,
    FOREIGN KEY (codigoPdt, inventario) REFERENCES ControlTienda.Producto(codigoPdt, inventario),
    FOREIGN KEY (idVenta) REFERENCES ControlTienda.Venta (idVenta),
    PRIMARY KEY (idDtl)
);

/*--------------------Inserciones--------------------*/

/*En la Tabla de Instalacion*/
INSERT INTO ControlInmuebles.Instalacion (nombreInmueble, descripcion) VALUES ('Electronic-Homes', 'Es toda la empresa en general'),
('Bodega', 'Inmueble que almacena todos los productos que pueden ser distribuidos en las demas sucursales'),
('Sucursal Central', 'Instalacion ubicada en la region central'),
('Sucursal Norte', 'Instalacion ubicada en la region norte'),
('Sucursal Sur', 'Instalacion ubicada en la region sur');

/*En la Tabla de Rol*/
INSERT INTO ControlPersonal.Rol (nombreRol, descripcion) VALUES ('Vendedor', 'Tendrá acceso a la venta de productos dentro de una sucursal, tomando en cuenta que el mismo solo podrá realizar ventas en la sucursal en la que se encuentre trabajando actualmente. Además, los vendedores tendrán la capacidad de registrar y modificar clientes en el sistema.'),
('Inventario', 'Tendrá la capacidad de realizar ingreso de productos a una sucursal en específico, tomando en cuenta que todo producto que se ingrese en una sucursal destino debe ser removido de su origen. También se debe tomar en cuenta que solamente podrá ingresar productos en la sucursal en la que se encuentra trabajando actualmente.'),
('Bodeguero', 'Únicamente tendrá la capacidad de ingresar y modificar productos en bodega.'),
('Administrador', 'Tendrá la capacidad de generar reportes y registrar trabajadores nuevos.');

/*En la Tabla Empleados*/
/*Insercion de Empleados Sucursal Central*/
INSERT INTO ControlPersonal.Empleado VALUES (5732480196, 'ab767ca65ab7a7d3d2c1469ca5ab9bfa3cb677e44efc83b1b7f911faec34f353', 'Jose Javier Tzicap Lopez', 3, 1, 3500.00),
(2194650378, '542ea6a7baef1db275441a9fe12726585220f5f04be3017ca26a0856cfc7b179', 'Gerardo Alejando Lopez Guzman', 3, 1, 3500.00),
(6850392741, 'fdf81ce43112f6d1e7f44a84c5464a34f7fa40fcc6136d58900adb31ad4c2e27', 'Simon Asturios Guerrero Guerrero', 3, 1, 3500.00),
(9108275634, 'e9424a3ddab411d2bc49f0047b9e7d60b516e7ac57a605960c96d9250489ebeb', 'Pedro Ricardo Morales Barrios', 3, 2, 3200.00);
/*Insercion de Empleados Sucursal Norte*/
INSERT INTO ControlPersonal.Empleado VALUES (3762951840, '0d71158e772d35ac6972d0efcfa2dc0d669d1afeb1c142522cb01e9ea6f57026', 'Angel Eduardo Mejia Reyes', 4, 1, 3500.00),
(1029384756, '9c52968674f2c7508ae2d3f9b8178b8efe7b347869ac28102230b8baa62e297b', 'Juan Antonio Tzic Vasquez', 4, 1, 3500.00),
(4987320165, '949ab0cf8c8d7633fadbe0b62d1d24abad0c8d4f0de67409bbbe999405648be8', 'Ana Gabriela Rodas Sagastume', 4, 1, 3500.00),
(8217369054, '4c318bcdd60fcf495b5d594f876cbd57889bfe533ce410120ed2c26a89900e92', 'Adriana Emilia Santos Quiroa', 4, 2, 3200.00);
/*Insercion de Empleados Sucursal Sur*/
INSERT INTO ControlPersonal.Empleado VALUES (3079584612, '403952df2a2fe4cf9be710f5deb22f1d81fc78605534224c501c7536a56272a5', 'Maria Alejandra Monterroso Montenegro', 5, 1, 3500.00),
(5649382071, 'a385af9c08c581a6c6f3c8063da466995dbdf370a1687edebec1b766f073d759', 'Silvia Julieta Reyes Reyes', 5, 1, 3500.00),
(8730952461, '8f3e935c3e69a6c0df82a5c0827a2f79b257cb730432c870fe9959a1ffd5e8a9', 'Ana Elisa Rodas Cifuentes', 5, 1, 3500.00),
(3458796210, 'c4ce2996097eb950e0378e93313d11061c2ca7d3261610de6681fbf03f70bb50', 'Sandra Noemi Sanchez Sanchez', 5, 2, 3200.00);
/*Insercion de Empleados en Bodega*/
INSERT INTO ControlPersonal.Empleado VALUES (6570281493, '8a93f5eedabf2e82b167792264e2d340c0595fd4974a2b378688e2087a057fff', 'Roberto Gilberto Rodriguez de Leon', 2, 3, 3800.00),
(1205498736, '77285a04bbbe79d2c7d7ba103d27f1aa0930f3a51b2c08e75b72972fb3ed3d92', 'Jose Alfredo Garcia Maldonado', 2, 3, 3800.00),
(8763452190, '6ec5044eb8f246200259f4101784f9c7fc8cd6f64e20e7654e1117eb167016b3', 'Daniel Leandro Giron Giron', 2, 3, 3800.00),
(4629580371, 'ce17f8cf83f04da7a4b9a98dd531687912d8c201a74a5b894c567535351a1eb1', 'Pablo David Minera Ortiz', 2, 3, 3800.00);
/*Insercion de Empleado Administrador*/
INSERT INTO ControlPersonal.Empleado VALUES (2020304820, '169e0f9f69c296f0c699835bf917cd73a1bb02b7ba14f7b3c6472051908fa5df', 'Luis Nery Cifuentes Rodas', 1, 4, 4000.00);

/*En la Tabla Inventario*/
INSERT INTO ControlTienda.Inventario (inmueble) VALUES (2),
(3),
(4),
(5);

/*En la Tabla Producto*/
/*Insercion de Productos En Bodega*/
INSERT INTO ControlTienda.Producto VALUES ('01234567H890', 1, 'Refrigerador Oster', 15, 5000.00),
('1234I5678901', 1, 'Estufa Hoover', 10, 4700.00),
('2345678J9012', 1, 'Horno Electrico Hoover', 10, 5000.00),
('3456K7890123', 1, 'Microondas Black and Decker', 10, 3900.00),
('456L8901234K', 1, 'Lavadora Whirlpool', 15, 3500.00),
('56789M012345', 1, 'Secadora Whirlpool', 15, 3000.00),
('678N90123456', 1, 'Aspiradora Samsung', 10, 850.00),
('789O1234567P', 1, 'Batidora Oster', 20, 350.00),
('890P23456789', 1, 'Licuadora Oster', 20, 400.00),
('90120Q345678', 1, 'Tostadora Black and Decker', 20, 250.00),
('1234R5678901', 1, 'Cafetera Philips', 15, 300.00),
('23456S789012', 1, 'Plancha de Ropa Nordika', 25, 150.00),
('345T6789012U', 1, 'Ventilador Philips', 20, 350.00),
('45678U012345', 1, 'Calefactor Samsung', 10, 650.00),
('56789V123456', 1, 'Extractor de Jugos Oster', 30, 450.00),
('W8901234567X', 1, 'Televisor 50 pulgadas LG', 25, 2400.00),
('X9012345678Y', 1, 'Equipo Stereo Sony', 10, 1000.00),
('1234Z5678901', 1, 'Telefono de Casa Nordika', 30, 200.00),
('2345678A012B', 1, 'Videocasetera Sony', 10, 200.00),
('3456C7890123', 1, 'Triturador de Alimentos Oster', 30, 225.00),
('456D8901234E', 1, 'Televisor 30 pulgadas Samsung', 15, 1600.00),
('56789F012345', 1, 'Lavadora Philips', 15, 2600.00),
('678G90123456', 1, 'Refrigerador Dyson', 15, 3500.00),
('789H1234567I', 1, 'Licuadora KitchenAid',20, 350.00),
('890I23456789', 1, 'Microondas Panasonic', 10, 2500.00),
('9012J3456780', 1, 'Equipo Stereo Panasonic', 5, 950.00),
('1234K5678901', 1, 'Repetidor de Wifi Steren', 30, 500.00),
('23456L789012', 1, 'Dispensador de Agua Whirlpool',20, 330.00),
('345M6789012N', 1, 'Lampara de Mesa Nordika', 35, 100.00),
('45678N012345', 1, 'Plancha de Ropa Oster', 30, 250.00);
/*Incersion de Productos En Sucursal Central*/
INSERT INTO ControlTienda.Producto VALUES ('01234567H890', 2, 'Refrigerador Oster', 11, 5000.00),
('1234I5678901', 2, 'Estufa Hoover', 8, 4700.00),
('2345678J9012', 2, 'Horno Electrico Hoover', 8, 5000.00),
('3456K7890123', 2, 'Microondas Black and Decker', 8, 3900.00),
('456L8901234K', 2, 'Lavadora Whirlpool', 11, 3500.00),
('56789M012345', 2, 'Secadora Whirlpool', 11, 3000.00),
('678N90123456', 2, 'Aspiradora Samsung', 8, 850.00),
('789O1234567P', 2, 'Batidora Oster', 16, 350.00),
('890P23456789', 2, 'Licuadora Oster', 16, 400.00),
('90120Q345678', 2, 'Tostadora Black and Decker', 16, 250.00),
('1234R5678901', 2, 'Cafetera Philips', 11, 300.00),
('23456S789012', 2, 'Plancha de Ropa Nordika', 25, 150.00),
('345T6789012U', 2, 'Ventilador Philips', 16, 350.00),
('45678U012345', 2, 'Calefactor Samsung', 8, 650.00),
('56789V123456', 2, 'Extractor de Jugos Oster', 21, 450.00),
('W8901234567X', 2, 'Televisor 50 pulgadas LG', 17, 2400.00),
('X9012345678Y', 2, 'Equipo Stereo Sony', 8, 1000.00),
('1234Z5678901', 2, 'Telefono de Casa Nordika', 21, 200.00),
('2345678A012B', 2, 'Videocasetera Sony', 8, 200.00),
('3456C7890123', 2, 'Triturador de Alimentos Oster', 21, 225.00),
('456D8901234E', 2, 'Televisor 30 pulgadas Samsung', 11, 1600.00),
('56789F012345', 2, 'Lavadora Philips', 11, 2600.00),
('678G90123456', 2, 'Refrigerador Dyson', 11, 3500.00),
('789H1234567I', 2, 'Licuadora KitchenAid',16, 350.00),
('890I23456789', 2, 'Microondas Panasonic', 8, 2500.00),
('9012J3456780', 2, 'Equipo Stereo Panasonic', 5, 950.00),
('1234K5678901', 2, 'Repetidor de Wifi Steren', 21, 500.00),
('23456L789012', 2, 'Dispensador de Agua Whirlpool',16, 330.00),
('345M6789012N', 2, 'Lampara de Mesa Nordika', 24, 100.00),
('45678N012345', 2, 'Plancha de Ropa Oster', 21, 250.00);
/*Insercion de Productos En Sucursal Norte*/
INSERT INTO ControlTienda.Producto VALUES ('01234567H890', 3, 'Refrigerador Oster', 7, 5000.00),
('1234I5678901', 3, 'Estufa Hoover', 6, 4700.00),
('2345678J9012', 3, 'Horno Electrico Hoover', 6, 5000.00),
('3456K7890123', 3, 'Microondas Black and Decker', 6, 3900.00),
('456L8901234K', 3, 'Lavadora Whirlpool', 7, 3500.00),
('56789M012345', 3, 'Secadora Whirlpool', 7, 3000.00),
('678N90123456', 3, 'Aspiradora Samsung', 6, 850.00),
('789O1234567P', 3, 'Batidora Oster', 10, 350.00),
('890P23456789', 3, 'Licuadora Oster', 10, 400.00),
('90120Q345678', 3, 'Tostadora Black and Decker', 10, 250.00),
('1234R5678901', 3, 'Cafetera Philips', 7, 300.00),
('23456S789012', 3, 'Plancha de Ropa Nordika', 25, 150.00),
('345T6789012U', 3, 'Ventilador Philips', 10, 350.00),
('45678U012345', 3, 'Calefactor Samsung', 6, 650.00),
('56789V123456', 3, 'Extractor de Jugos Oster', 14, 450.00),
('W8901234567X', 3, 'Televisor 50 pulgadas LG', 12, 2400.00),
('X9012345678Y', 3, 'Equipo Stereo Sony', 6, 1000.00),
('1234Z5678901', 3, 'Telefono de Casa Nordika', 14, 200.00),
('2345678A012B', 3, 'Videocasetera Sony', 6, 200.00),
('3456C7890123', 3, 'Triturador de Alimentos Oster', 14, 225.00),
('456D8901234E', 3, 'Televisor 30 pulgadas Samsung', 7, 1600.00),
('56789F012345', 3, 'Lavadora Philips', 7, 2600.00),
('23456L789012', 3, 'Dispensador de Agua Whirlpool', 10, 330.00),
('789H1234567I', 3, 'Licuadora KitchenAid', 10, 350.00),
('890I23456789', 3, 'Microondas Panasonic', 6, 2500.00);
/*Insercion de Productos En Sucursal Sur*/
INSERT INTO ControlTienda.Producto VALUES ('01234567H890', 4, 'Refrigerador Oster', 4, 5000.00),
('1234I5678901', 4, 'Estufa Hoover', 3, 4700.00),
('2345678J9012', 4, 'Horno Electrico Hoover', 3, 5000.00),
('3456K7890123', 4, 'Microondas Black and Decker', 3, 3900.00),
('456L8901234K', 4, 'Lavadora Whirlpool', 4, 3500.00),
('678N90123456', 4, 'Aspiradora Samsung', 6, 850.00),
('W8901234567X', 4, 'Televisor 50 pulgadas LG', 6, 2400.00),
('789O1234567P', 4, 'Batidora Oster', 5, 350.00),
('890P23456789', 4, 'Licuadora Oster', 5, 400.00),
('90120Q345678', 4, 'Tostadora Black and Decker', 5, 250.00),
('1234R5678901', 4, 'Cafetera Philips', 4, 300.00),
('23456S789012', 4, 'Plancha de Ropa Nordika', 12, 150.00),
('345T6789012U', 4, 'Ventilador Philips', 5, 350.00),
('45678U012345', 4, 'Calefactor Samsung', 3, 650.00),
('56789V123456', 4, 'Extractor de Jugos Oster', 7, 450.00);

/*En la Tabla Cliente*/
INSERT INTO ControlTienda.Cliente (nit, apellidoCliente, nombreCliente) VALUES (73921548, 'Martinez Gamarro', 'Esna Alexandra'),
(68230917, 'Tebalan Garrido', 'Oscar Miguel'),
(45082731, 'Jimenez Alvarado', 'Rudy Napoleon'),
(89217653, 'Argueta Itzep', 'Marlin Casimira'),
(21549783, 'Cuc Rodriguez', 'Londra Natasha'),
(67310824, 'Fuentes Cifuentes', 'David Robelo'),
(98123546, 'Barrios Barrios', 'Joel Abdiel'),
(37689012, 'Salanic Estrada', 'Angela Paulina');
/*En la Tabla Ventas y Detalle de Ventas*/
/*Insercion Ventas Sucursal-Central*/
INSERT INTO ControlTienda.Venta (sucursal, fechaVenta, codigoEmpleado, nitcli, cf, total) VALUES (3, '2020/05/08', 5732480196, 73921548, false, 5000.00), 
(3, '2020/09/09', 2194650378, null, true, 350.00),
(3, '2021/01/13', 5732480196, 89217653, false, 200.00),
(3, '2021/06/30', 6850392741, null, true, 2600.00),
(3, '2022/12/20', 5732480196, 37689012, false, 500.00); 
INSERT INTO ControlTienda.DetalleVenta (idVenta, codigoPdt, inventario, descripcion, cantidad, subTotal) VALUES (1, '01234567H890', 2, 'Refrigerador Oster', 1, 5000.00),
(2, '789O1234567P', 2, 'Batidora Oster', 1, 350.00), 
(3, '2345678A012B', 2, 'Videocasetera Sony', 1, 200.00), 
(4, '56789F012345', 2, 'Lavadora Philips', 1, 2600.00),  
(5, '45678N012345', 2, 'Plancha de Ropa Oster', 2, 500.00);

/*Insercion Ventas Sucursal-Norte*/
INSERT INTO ControlTienda.Venta (sucursal, fechaVenta, codigoEmpleado, nitcli, cf, total) VALUES (4, '2019/03/23', 3762951840, null, true, 2830.00), 
(4, '2019/12/01', 1029384756, 68230917, false, 225.00),
(4, '2020/01/18', 3762951840, null, true, 650.00),
(4, '2021/02/15', 4987320165, 21549783, false, 1000.00),
(4, '2022/04/03', 3762951840, null, true, 500.00); 
INSERT INTO ControlTienda.DetalleVenta (idVenta, codigoPdt, inventario, descripcion, cantidad, subTotal) VALUES (6, '890I23456789', 3, 'Microondas Panasonic', 1, 2500.00),
(6, '23456L789012', 3, 'Dispensador de Agua Whirlpool', 1, 330.00),
(7, '3456C7890123', 3, 'Triturador de Alimentos Oster', 1, 225.00), 
(8, '45678U012345', 3, 'Calefactor Samsung', 1, 650.00), 
(9, 'X9012345678Y', 3, 'Equipo Stereo Sony', 1, 1000.00), 
(10, '90120Q345678', 3, 'Tostadora Black and Decker', 2, 500.00);

/*Insercion Ventas Sucursal-Sur*/
INSERT INTO ControlTienda.Venta (sucursal, fechaVenta, codigoEmpleado, nitcli, cf, total) VALUES (5, '2022/04/19', 3079584612, 45082731, false, 2400.00), 
(5, '2022/06/24', 5649382071, null, true, 4700.00),
(5, '2022/09/17', 3079584612, 67310824, false, 850.00),
(5, '2022/10/22', 8730952461, null, true, 300.00),
(5, '2022/12/31', 3079584612, 98123546, false, 350.00); 
INSERT INTO ControlTienda.DetalleVenta (idVenta, codigoPdt, inventario, descripcion, cantidad, subTotal) VALUES (11, 'W8901234567X', 4, 'Televisor 50 pulgadas LG', 1, 2400.00),
(12, '1234I5678901', 4, 'Estufa Hoover', 1, 4700.00), 
(13, '678N90123456', 4, 'Aspiradora Samsung', 1, 850.00), 
(14, '1234R5678901', 4, 'Cafetera Philips', 1, 300.00),  
(15, '345T6789012U', 4, 'Ventilador Philips', 2, 350.00);