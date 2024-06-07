CREATE TABLE Usuario (
    Id_Usuario INT PRIMARY KEY AUTO_INCREMENT,
    Nombre_Usuario VARCHAR(50),
    Apellido_1 VARCHAR(50),
    Apellido_2 VARCHAR(50),
    Email VARCHAR(100),
    Telefono VARCHAR(20),
    Username VARCHAR(50),
    Password VARCHAR(50)
);

CREATE TABLE Producto (
    Id_Producto INT PRIMARY KEY AUTO_INCREMENT,
    Nombre_Producto VARCHAR(100),
    Precio_Producto DECIMAL(10, 2),
    Marca_Producto VARCHAR(50),
    Fecha_Subida_Producto DATE,
    Descripcion_Producto TEXT,
    Imagen_Producto VARCHAR(255),
    Id_Usuario INT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario)
);

CREATE TABLE Favorito (
    Id_Favorito INT PRIMARY KEY AUTO_INCREMENT,
    Id_Producto INT,
    Id_Usuario INT,
    FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto),
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario)
);

CREATE TABLE Categoria (
    Id_Categoria INT PRIMARY KEY AUTO_INCREMENT,
    Nombre_Categoria VARCHAR(50)
);

CREATE TABLE Producto_Categoria (
    Id_Producto_Categoria INT PRIMARY KEY AUTO_INCREMENT,
    Id_Producto INT,
    Id_Categoria INT,
    FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto),
    FOREIGN KEY (Id_Categoria) REFERENCES Categoria(Id_Categoria)
);

CREATE TABLE Direccion_Cliente (
    Id_Direccion_Cliente INT PRIMARY KEY AUTO_INCREMENT,
    Id_Usuario INT,
    Pais VARCHAR(50),
    Provincia VARCHAR(50),
    Ciudad VARCHAR(50),
    CP VARCHAR(10),
    Portal INT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario)
);

CREATE TABLE Venta (
    Id_Venta INT PRIMARY KEY AUTO_INCREMENT,
    Fecha_Venta DATE,
    Hora_Venta TIME,
    Id_Usuario INT,
    Id_Producto INT,
    Id_Direccion_Cliente INT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario),
    FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto),
    FOREIGN KEY (Id_Direccion_Cliente) REFERENCES Direccion_Cliente(Id_Direccion_Cliente)
);

CREATE TABLE Direccion_Vendedor (
    Id_Direccion_Vendedor INT PRIMARY KEY AUTO_INCREMENT,
    Id_Usuario INT,
    Pais VARCHAR(50),
    Provincia VARCHAR(50),
    Ciudad VARCHAR(50),
    CP VARCHAR(10),
    Portal INT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario)
);

CREATE TABLE Compra (
    Id_Compra INT PRIMARY KEY AUTO_INCREMENT,
    Fecha_Compra DATE,
    Hora_Compra TIME,
    Id_Usuario INT,
    Id_Producto INT,
    Id_Direccion_Vendedor INT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario),
    FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto),
    FOREIGN KEY (Id_Direccion_Vendedor) REFERENCES Direccion_Vendedor(Id_Direccion_Vendedor)
);

CREATE TABLE Valoracion (
    Id_Valoracion INT PRIMARY KEY AUTO_INCREMENT,
    Id_Usuario INT,
    Id_Producto INT,
    Estrellas DECIMAL(2,1),
    Resena TEXT,
    FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario),
    FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto)
);


INSERT INTO Usuario (Nombre_Usuario, Apellido_1, Apellido_2, Email, Telefono, Username, Password)
VALUES 
('Juan', 'Perez', 'Gomez', 'juan@example.com', '123456789', 'juanito', 'contrasena123'),
('Maria', 'Lopez', 'Garcia', 'maria@example.com', '987654321', 'marita', 'password456'),
('Pedro', 'Rodriguez', 'Martinez', 'pedro@example.com', '456789012', 'pedrito', 'secret789'),
('Ana', 'Gomez', 'Lopez', 'ana@example.com', '111222333', 'anita', 'clave123'),
('Carlos', 'Fernandez', 'Diaz', 'carlos@example.com', '444555666', 'carlitos', 'password789'),
('Laura', 'Martinez', 'Lopez', 'laura@example.com', '777888999', 'laurita', 'secreto456'),
('Javier', 'Garcia', 'Perez', 'javier@example.com', '123789456', 'javi', 'contrasena789'),
('Elena', 'Lopez', 'Gutierrez', 'elena@example.com', '456123789', 'elenita', 'clave456'),
('Miguel', 'Rodriguez', 'Fernandez', 'miguel@example.com', '789456123', 'migue', 'password123'),
('Sofia', 'Perez', 'Fernandez', 'sofia@example.com', '321654987', 'sofi', 'secreto123'),
('Adrian', 'Gutierrez', 'Martinez', 'adrian@example.com', '654987321', 'adri', 'contrasena456'),
('Carmen', 'Fernandez', 'Gomez', 'carmen@example.com', '987321654', 'carmencita', 'clave789'),
('Alberto', 'Gomez', 'Fernandez', 'alberto@example.com', '369258147', 'alber', 'password789'),
('Natalia', 'Martinez', 'Rodriguez', 'natalia@example.com', '147258369', 'nati', 'secreto789'),
('Diego', 'Gutierrez', 'Lopez', 'diego@example.com', '258369147', 'dieguito', 'contrasena123');


INSERT INTO Producto (Nombre_Producto, Precio_Producto, Marca_Producto, Fecha_Subida_Producto, Descripcion_Producto, Imagen_Producto, Id_Usuario)
VALUES 
('Camisa de Vestir', 50.00, 'H&M', '2023-01-15', 'Camisa elegante para ocasiones especiales.', 'camisa_vestir.jpg', 1),
('Jeans Skinny', 60.00, 'Zara', '2023-02-20', 'Jeans ajustados de moda para un look moderno.', 'jeans_skinny.jpg', 2),
('Zapatillas Deportivas', 80.00, 'Nike', '2023-03-10', 'Zapatillas ideales para actividades deportivas.', 'zapatillas_deportivas.jpg', 3),
('Vestido de Noche', 120.00, 'Forever 21', '2023-07-05', 'Vestido elegante para eventos nocturnos.', 'vestido_noche.jpg', 4),
('Abrigo de Invierno', 150.00, 'Hollister', '2023-08-10', 'Abrigo abrigado para los días fríos de invierno.', 'abrigo_invierno.jpg', 5),
('Bufanda de Lana', 25.00, 'Gap', '2023-09-15', 'Bufanda suave y cálida para días frescos.', 'bufanda_lana.jpg', 6),
('Blusa Floral', 35.00, 'Zara', '2023-10-20', 'Blusa con estampado floral para un look primaveral.', 'blusa_floral.jpg', 7),
('Sombrero de Fieltro', 30.00, 'H&M', '2023-11-25', 'Sombrero elegante para complementar tu estilo.', 'sombrero_fieltro.jpg', 8),
('Pantalones Cortos Casual', 40.00, 'Forever 21', '2023-12-01', 'Pantalones cortos cómodos para un look casual.', 'pantalones_cortos.jpg', 9),
('Chaqueta de Cuero', 90.00, 'Zara', '2024-01-05', 'Chaqueta de cuero para un estilo moderno y atrevido.', 'chaqueta_cuero.jpg', 10),
('Vestido de Verano', 55.00, 'Hollister', '2024-02-10', 'Vestido ligero y fresco para días calurosos.', 'vestido_verano.jpg', 11),
('Gorra Deportiva', 20.00, 'Nike', '2024-03-15', 'Gorra ideal para actividades al aire libre.', 'gorra_deportiva.jpg', 12),
('Calcetines Estampados', 10.00, 'Gap', '2024-04-20', 'Calcetines con divertidos estampados para un toque de estilo.', 'calcetines_estampados.jpg', 1),
('Suéter de Punto', 70.00, 'H&M', '2024-05-25', 'Suéter acogedor de punto para los días fríos.', 'sueter_punto.jpg', 2),
('Pijama Cómodo', 45.00, 'Forever 21', '2024-06-30', 'Pijama suave y cómodo para una buena noche de sueño.', 'pijama_comodo.jpg', 3);



INSERT INTO Favorito (Id_Producto, Id_Usuario)
VALUES
(1, 2),
(3, 1),
(2, 3), 
(4, 2),
(7, 1),
(10, 3),
(2, 6),
(5, 5),
(8, 8),
(11, 11),
(3, 4),
(6, 7),
(9, 10),
(1, 12),
(12, 9);


INSERT INTO Categoria (Nombre_Categoria)
VALUES 
('Hombre'),
('Mujer'),
('Niño'),
('Camisetas'),
('Pantalones'),
('Chaquetas'),
('Vestidos'),
('Zapatos'),
('Accesorios');



INSERT INTO Producto_Categoria (Id_Producto, Id_Categoria)
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(7, 2),
(10, 3),
(2, 4),
(5, 5),
(8, 6),
(11, 1),
(3, 2),
(6, 3),
(9, 4),
(1, 5),
(12, 6);


INSERT INTO Direccion_Cliente (Id_Usuario, Pais, Provincia, Ciudad, CP, Portal)
VALUES 
(1, 'España', 'Madrid', 'Madrid', '28001', 12),
(2, 'México', 'Ciudad de México', 'Ciudad de México', '12345', 34),
(3, 'Argentina', 'Buenos Aires', 'Buenos Aires', 'C1234ABC', 56),
(4, 'Colombia', 'Bogotá', 'Bogotá', '110121', 78),
(7, 'Chile', 'Santiago', 'Santiago', '7500112', 23),
(10, 'Perú', 'Lima', 'Lima', 'LIM01', 45),
(2, 'México', 'Guadalajara', 'Guadalajara', '45678', 67),
(5, 'España', 'Barcelona', 'Barcelona', '08001', 89),
(8, 'Argentina', 'Córdoba', 'Córdoba', 'X5000', 12),
(11, 'Uruguay', 'Montevideo', 'Montevideo', '11200', 34),
(3, 'Argentina', 'Buenos Aires', 'La Plata', 'B1900', 56),
(6, 'México', 'Monterrey', 'Monterrey', '64000', 78),
(9, 'Colombia', 'Medellín', 'Medellín','050013', 90),
(1, 'España', 'Valencia', 'Valencia','46001', 23),
(12, 'Perú', 'Arequipa', 'Arequipa', '04001', 45);


INSERT INTO Venta (Fecha_Venta, Hora_Venta, Id_Usuario, Id_Producto, Id_Direccion_Cliente)
VALUES 
('2023-04-05', '15:30:00', 1, 1, 1),
('2023-05-10', '12:45:00', 2, 2, 2),
('2023-06-20', '18:00:00', 3, 3, 3),
('2024-07-05', '14:30:00', 4, 4, 1),
('2024-08-10', '11:45:00', 7, 7, 2),
('2024-09-15', '17:00:00', 10, 10, 3),
('2024-10-20', '13:15:00', 2, 2, 4),
('2024-11-25', '10:30:00', 5, 5, 5),
('2024-12-01', '19:45:00', 8, 8, 6),
('2025-01-05', '16:00:00', 11, 11, 7),
('2025-02-10', '12:15:00', 3, 3, 8),
('2025-03-15', '18:30:00', 6, 6, 9),
('2025-04-20', '14:45:00', 9, 9, 10),
('2025-05-25', '11:00:00', 1, 1, 11),
('2025-06-30', '17:15:00', 12, 12, 12);


INSERT INTO Valoracion (Id_Usuario, Id_Producto, Estrellas, Resena)
VALUES 
(1, 1, 4, 'Buena experiencia de compra'),
(2, 2, 5, 'Producto de alta calidad'),
(3, 3, 3, 'Entrega rápida pero producto defectuoso'),
(4, 4, 3, 'Buen producto, pero esperaba más funciones'),
(7, 7, 5, 'Excelente calidad de sonido en los altavoces'),
(10, 10, 4, 'Buena bicicleta, fácil de ensamblar'),
(2, 2, 2, 'Impresora lenta, pero cumple su función'),
(5, 5, 5, 'Horno eléctrico fácil de usar y limpiar'),
(8, 8, 4, 'Altavoces Bluetooth funcionan muy bien'),
(11, 11, 5, 'Silla de oficina cómoda y resistente'),
(3, 3, 3, 'Reloj inteligente con buen diseño pero batería corta'),
(6, 6, 4, 'Teclado gaming responde muy bien'),
(9, 9, 2, 'Mochila portátil de baja calidad'),
(1, 1, 5, 'Tablet con excelente rendimiento'),
(12, 12, 3, 'Smart TV con buena resolución pero interfaz lenta');

