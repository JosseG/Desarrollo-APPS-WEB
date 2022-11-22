drop database if exists ControlAlmacen;
-- creamos la bd
create database ControlAlmacen;
-- activamos la bd
use ControlAlmacen;


create table if not exists tb_tipoproducto
(
    id_tipoprod int auto_increment primary key,
    nombre_tipoprod varchar(20) unique  not null,
    estado boolean not null
);

create table if not exists tb_producto
(
	id_producto char(10) primary key,
    id_tipoprod int not null,
	codigobar_producto varchar(14) not null unique,
	descripcion_producto varchar(240) not null,
	marca_producto varchar(12) not null,
    stock_producto int not null,
	estado boolean not null,
    foreign key(id_tipoprod) references tb_tipoproducto(id_tipoprod)
);


create table if not exists tb_tipoinventario
(
    id_tipoinventario char(10) primary key,
    nombre_tipoinventario varchar(10) not null,
    estado boolean not null
);



create table if not exists tb_sucursal
(
	id_sucursal char(10) primary key,
	sector_sucursal varchar(30) not null ,  
	estado boolean not null
);

create table if not exists tb_almacen
(
	id_almacen char(10) primary key,
	id_sucursal char(10) not null,
	desc_almacen varchar(120) not null,
	direccion_almacen varchar(120) not null,
	estado boolean not null,
	foreign key (id_sucursal) references tb_sucursal(id_sucursal)
);

create table if not exists tb_empleado
(
	id_empleado char(10) primary key,
	id_almacen char(10) not null,
	nombre_empleado varchar(90) not null,
	apellido_empleado varchar(90) not null,
	correo_empleado varchar(90) not null,
	telefono_empleado varchar(90) not null,
	estado boolean not null,
	foreign key (id_almacen) references tb_almacen(id_almacen)
);



create table if not exists tb_cargo
(
	id_cargo int auto_increment primary key,
	nombre_cargo varchar(60) not null,
	estado boolean not null
);

create table if not exists tb_usuario
(
	id_usuario int auto_increment primary key, 
	id_empleado char(10) not null,
	alias_usuario varchar(50) not null unique,
	contrasena_usuario varchar(350) not null,
    estado boolean not null,
	foreign key (id_empleado) references tb_empleado(id_empleado)
);


create table if not exists tb_menu
(
    id_menu    int auto_increment primary key,
    des_menu   varchar(55)  null,
    icono_menu varchar(150) null,
    estado boolean not null
);



create table if not exists tb_acceso
(
	id_acceso char(10) primary key,
	id_menu  int not null,
    nombre_acceso  varchar(60) not null,
	url_acceso varchar(100) not null,
	estado boolean not null,
    foreign key(id_menu) references tb_menu(id_menu)
);


create table if not exists tb_acceso_cargo
(
	id_acceso char(10),
	id_cargo int,
    estado boolean not null,
	primary key (id_acceso,id_cargo),
	foreign key (id_acceso) references tb_acceso(id_acceso),
	foreign key (id_cargo) references tb_cargo(id_cargo)
);

create table if not exists tb_proveedor 
(
	id_proveedor int auto_increment primary key,
	razon_social_proveedor varchar(50) unique not null,
	nombre_comercial_proveedor varchar(60) not null,
	numero_ruc_proveedor varchar(15) unique not null,
	email_proveedor varchar(50) unique null,
	direccion_proveedor varchar(100) null,
	departamento_proveedor varchar(35)not null,
	telefono_proveedor varchar(15) not null,
    estado boolean not null
);



create table if not exists tb_ordencompra 
(
	id_orden_compra int auto_increment primary key,
    id_proveedor int not null,
	nro_orden_compra char(10) unique not null,
	fechaorden_compra date not null,
	fechaentrega date not null,
	condicionespago varchar(25) not null,
	transporte char(20) null,
	valortotal_orden float not null,
    estado boolean not null,
	foreign key (id_proveedor) references tb_proveedor(id_proveedor)
);

select * from  tb_ordencompra;


create table if not exists tb_detalleordencompra 
(
	id_detalle_orden int auto_increment primary key,
	id_orden_compra int not null,
	id_producto char(10) not null,
	cantidad int not null,
	precio_unitario float not null,
    estado boolean not null,
	foreign key(id_orden_compra) references tb_ordencompra(id_orden_compra),
	foreign key(id_producto) references tb_producto(id_producto)
);


create table if not exists tb_inventario
(
    id_inventario int auto_increment primary key,
    id_orden_compra int not null,
    id_tipoinventario char(10) not null,
    id_almacen char(10) not null,
    id_empleado char(10) not null,
    cantidad_inventario int not null,   
    descripcion_inventario varchar(120) not null,
    estado char(10) not null,
    foreign key (id_orden_compra) references tb_ordencompra (id_orden_compra),
    foreign key (id_tipoinventario) references tb_tipoinventario (id_tipoinventario),
    foreign key (id_almacen) references tb_almacen (id_almacen),
    foreign key (id_empleado) references tb_empleado (id_empleado)
);



create table tb_cargo_usuario (
	id_cargo int not null,
    id_usuario int not null,
    primary key(id_cargo,id_usuario),
    foreign key(id_cargo) references tb_cargo(id_cargo),
    foreign key(id_usuario) references tb_usuario(id_usuario)
);

create table if not exists persistent_logins (
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);



/* INSERT MENU */

INSERT INTO  tb_menu (id_menu,des_menu,icono_menu,estado)
VALUES (null,'Mantenimiento','fas fa-boxes text-light me-3',1),
       (null,'Reportes','fas fa-barcode text-light me-3',1),
	   (null,'Transacciones','fas fa-truck-loading text-light me-3',1),
       (null,'Consultas','fas fa-dna text-light me-3',1),
       (null,'Configuración','fa-solid fa-screwdriver-wrench me-3',1);

/* INSERT MENU */




/* INSERT MENU */

INSERT INTO tb_acceso (id_acceso,id_menu,nombre_acceso,url_acceso,estado)
VALUES('ACS0000001',1,'Orden de Compra','',1),
('ACS0000002',1,'Producto','',1),
('ACS0000003',1,'Usuarios','',1),
('ACS0000004',2,'Inventario','',1),
('ACS0000005',2,'Orden de Compra','',1);

/* INSERT MENU */




/* INSERT TIPOINVENTARIO */

insert into tb_proveedor values(1,'Compunet S.A.C','Compunet','12345678984','Compunet@gmail.com','los portales 2003 Cercado','Lima','123456253',1);
insert into tb_proveedor values(2,'Systems S.A.C','Systems Perú','12333678984','systemsperu@gmail.com','los lirios 213 Breña','Lima','123556253',1);
insert into tb_proveedor values(3,'Core S.A.C','Core Latam','12333118984','corelatam@gmail.com','Cáceda 123 Surco','Lima','123550053',1);
insert into tb_proveedor values(4,'Vision S.A.C','Visión TEC','12333678000','visiontec@gmail.com','Luces 444 La Molina','Lima','121156253',1);

/* INSERT TIPOINVENTARIO */





/* INSERT TIPOINVENTARIO */

insert into tb_tipoinventario values('TI00000001','INGRESO',true);
insert into tb_tipoinventario values('TI00000002','SALIDA',true);

/* INSERT TIPOINVENTARIO */




/* INSERT SUCURSAL, ALMACEN, EMPLEADO */

insert into tb_sucursal values ('SC00000001','Selva Central',true);

insert into tb_almacen values ('AL00000001','SC00000001','Fundado en 2018','Av Las Palmas, Lurin',true);

insert into tb_empleado values ('EM00000001','AL00000001','Mauricio','Jimenez','mau@gmail.com','994545661',true);
insert into tb_empleado values ('EM00000002','AL00000001','Raul','Suarez','raus@gmail.com','994545000',true);

/* INSERT SUCURSAL, ALMACEN, EMPLEADO */




/* INSERT USUARIO, CARGO, USUARIO-CARGO */

insert into tb_usuario (id_empleado,alias_usuario,contrasena_usuario,estado) values('EM00000001','admin@email.com','$2a$04$uMOWE.EgIiwwEFZXiD3WQehFS.jFMllDpqWQtJ/1Am5jh/MaMuP2a',true);
insert into tb_usuario (id_empleado,alias_usuario,contrasena_usuario,estado) values('EM00000002','vendedor@email.com','$2a$04$4O9PBqLSYtvd5gaA9TyH9uc4IdyvjqSKVXGY/G62wKCsv8gUUVZZ.',true);


INSERT INTO tb_cargo (nombre_cargo,estado) VALUES ("ROLE_ADMINISTRADOR",true);
INSERT INTO tb_cargo (nombre_cargo,estado) VALUES ("ROLE_VENDEDOR",true);
INSERT INTO tb_cargo (nombre_cargo,estado) VALUES ("ROLE_ASISTENTE",true);

INSERT INTO tb_cargo_usuario VALUES (1,1);
INSERT INTO tb_cargo_usuario VALUES (2,1);
INSERT INTO tb_cargo_usuario VALUES (2,2);

/* INSERT USUARIO, CARGO, USUARIO-CARGO */




/* INSERT TIPOPRODUCTO, PRODUCTO */

insert into tb_tipoproducto (nombre_tipoprod, estado) values ('LINEA BLANCA', true);
insert into tb_tipoproducto (nombre_tipoprod, estado) values ('LINEA GRIS', true);
insert into tb_tipoproducto (nombre_tipoprod, estado) values ('LINEA MARRON', true);

insert into tb_producto values('PROD000001','1','3001234567890',"Refrigeradora Side by Side Family Hub 685 L, con mayor espacio en el interior, el mismo tamaño por fuera.",'SAMSUNG',8,true);
insert into tb_producto values('PROD000002','1','3000982736764',"Refrigeradora French Door 427L, Linear Cooling con HygieneFresh+ y conectividad Wi-Fi, plateado.",'LG',12,true);
insert into tb_producto values('PROD000003','1','3007873920009',"Microondas Black Stainless, contiene ceramica Enamel, contiene un dorador para la comida.",'SAMSUNG',21,true);
insert into tb_producto values('PROD000004','1','3009539826276',"Horno Microondas NeoChef 25 Litros con EasyClean, luz interior y base estable.",'LG',23,true);
insert into tb_producto values('PROD000005','1','3008098329798',"Lavadora de 14kg con la tecnologia Al control, SmartThings, EcoBubble, QuickDrive y Hygiene Steam.",'SAMSUNG',17,true);
insert into tb_producto values('PROD000006','1','3008900002222',"Lavadora de 11 kg de Lavado de alta tecnologia, carga frontal con AI DD, Steam+ y conectividad Wi-Fi.",'LG',19,true);
insert into tb_producto values('PROD000007','1','3009878283672',"Secadora SensorDry 9 kg con tecnologia de SensorDry, Funcion Esterilizar y Funcion Antiarrugas.",'SAMSUNG',20,true);
insert into tb_producto values('PROD000008','1','3009087326876',"Secadora 9 Kg, carga frontal con Dual Inverter Heat Pump y conectividad Wi-Fi, Blanco.",'LG',24,true);
insert into tb_producto values('PROD000009','1','3003498797974',"Campana Extractora Decorativa Acero Inoxidable, disfruta de un funcionamiento más sencillo gracias al control táctil integrado.",'SAMSUNG',42,true);
insert into tb_producto values('PROD000010','1','3002937383887',"Campana Extractora LG con Filtro de grasa lavable, 3 velocidades e Iluminación LED.",'LG',36,true);
insert into tb_producto values('PROD000011','1','3008979907828',"Horno Eléctrico Dual Cook, Black 60cm.",'SAMSUNG',31,true);
insert into tb_producto values('PROD000012','1','3008732862766',"Horno empotrable LG Studio | 4.7 pies cúbicos | Smart ThinQ.",'LG',38,true);
insert into tb_producto values('PROD000013','1','3002888888822',"Lavaplatos con StormWash, desjando todos los platos de cualquier tamaño relucientes.",'SAMSUNG',39,true);
insert into tb_producto values('PROD000014','1','3001983866733',"Lavaplatos LG QuadWash™ TrueSteam™ con Smart ThinQ™ WiFi.",'LG',32,true);
insert into tb_producto values('PROD000015','1','3006366363637',"Congeladora tiene diseño horizontal y capacidad de 260 litros, perfecta para conservar todos tus alimentos.",'SAMSUNG',23,true);
insert into tb_producto values('PROD000016','1','3005972976387',"Congeladora tiene su diseño horizontal de 400 litros de capacidad, para proteger bien tus alimentos.",'LG',22,true);

insert into tb_producto values('PROD000017','2','600161618484',"Samsung S22 ULTRA, el celular actualmente mas potente de la marca samsung, con procesador, camara, pantalla de ultima generación.",'SAMSUNG',48,true);
insert into tb_producto values('PROD000018','2','600161896523',"Iphone 14 PRO MAX, el ultimo iphone que a salido al mercado, un smartphone de alta calidad.",'APPLE',41,true);
insert into tb_producto values('PROD000019','2','600963296692',"Laptop Samsung Galaxy Book Pro 13.3 Mystic Blue, una laptop de excelente calidad, ligera y pantalla tactil.",'SAMSUNG',43,true);
insert into tb_producto values('PROD000020','2','600322883920',"Laptop Apple MacBook Air 13.3 pulgadas, es la gama de entrada de apple, pero de excelente calidad.",'APPLE',42,true);
insert into tb_producto values('PROD000021','2','600239872936',"Teclado Samsung Trio 500, es un producto de buena calidad, liviano y con conectividad bluetooth.",'SAMSUNG',122,true);
insert into tb_producto values('PROD000022','2','600291298882',"Teclado Apple Magic Keyboard 2 con conectividad bluetooth, de alta calidad y liviano.",'APPLE',142,true);
insert into tb_producto values('PROD000023','2','600366276727',"Samsung Galaxy Tab S8 Ultra, es un producto de ultima generacion con 14.6 pulgadas y materiales de primera.",'SAMSUNG',102,true);
insert into tb_producto values('PROD000024','2','600692379723',"Ipad Pro de 12.9 pulgadas, es un producto de alta calidad y de ultima generación",'APPLE',113,true);
insert into tb_producto values('PROD000025','2','600529378829',"ALL-IN-ONE PC HP de 24 pulgadas, buen producto de bajo costo.",'HP',63,true);
insert into tb_producto values('PROD000026','2','600290788711',"iMAC con pantalla retina 4,5k de 24 pulgadas, un producto de alto costo, pero de buena calidad.",'APPLE',73,true);
insert into tb_producto values('PROD000027','2','600091279787',"IMPRESORA MULTIFUNCIONAL INALÁMRICA EPSON ECOTANK L4260, una de las mejores impresoras del mercado.",'EPSON',87,true);
insert into tb_producto values('PROD000028','2','600239273927',"CANON IMPRESORA MULTIFUNCIONAL G3160, buen producto con un costo menor y sin sacrificar la calidad.",'CANON',94,true);

insert into tb_producto values('PROD000029','3','900237628533',"Televisor Samsung Smart TV 65 pulgadas Neo QLED 4K Mini LED, es un producto de alta gama, con buena calidad de materiales.",'SAMSUNG',25,true);
insert into tb_producto values('PROD000030','3','900126362622',"Televisor LG LED 4K MINI LED ThinQ AI 65, con buena resolucion de colores y materiales.",'LG',24,true);
insert into tb_producto values('PROD000031','3','900198747654',"Monitor Samsung 24 pulgadas LED, 1920x1080, IPS, HDMI / VGA, un producto bueno, pero con bajo costo.",'SAMSUNG',23,true);
insert into tb_producto values('PROD000032','3','900108367264',"LG Monitor Gamer UltraGear 27” Full HD IPS compatible con G-Sync, un producto excelente para jugar videojuegos.",'LG',22,true);
insert into tb_producto values('PROD000033','3','900328732722',"JBL Charge 5 parlante bluetooth, un producto de alta calidad de sonido, materiales y de larga duracion de bateria.",'JBL',121,true);
insert into tb_producto values('PROD000034','3','900793787824',"Parlante bluetooth portátil Sony Extra Bass XB43 led, IP67, con larga duracionde bateria y buena calidad.",'SONY',125,true);
insert into tb_producto values('PROD000035','3','900197466333',"Cámara digital Samsung WB2100 16.4MP CMOS con zoom óptico de 35x, con buena calidad de imagen y de video.",'SAMSUNG',89,true);
insert into tb_producto values('PROD000036','3','900486748681',"Cámara profesional Alpha a7 III Full Frame Mirrorless, una super camara, de alta calidad de fotos y video.",'SONY',19,true);
insert into tb_producto values('PROD000037','3','900987376363',"Audifonos Noise Cancelling Sony WH-1000XM4 con Bluetooth, alta calidad de sonido y cancelacion de ruido",'SONY',65,true);
insert into tb_producto values('PROD000038','3','900153521909',"Audifonos Beats Studio3 Wireless Shadow Gray, excelente calidad de sonido con acústica refinada y Pure ANC",'BEATS',75,true);
insert into tb_producto values('PROD000039','1','900153521910',"Prueba",'PRUEBA',21,true);
insert into tb_producto values('PROD000040',2,'900153521911',"Prueba2",'PRUEBA2',11,true);
/* INSERT USUARIO, CARGO, USUARIO-CARGO */




/* INSERT ORDENCOMPRA */

insert into  tb_ordencompra VALUES(null,1,'OC00000001','2022/10/16','2022/10/16','CONTADO','con transporte',1000.00,1);
insert into  tb_ordencompra VALUES(null,1,'OC00000002','2022/10/16','2022/10/16','CONTADO','con transporte',1120.00,1);
insert into  tb_ordencompra VALUES(null,1,'OC00000003','2022/10/16','2022/10/16','CONTADO','con transporte',1645.00,1);
insert into  tb_ordencompra VALUES(null,1,'OC00000004','2022/10/16','2022/10/16','CONTADO','con transporte',1647.00,1);
insert into  tb_ordencompra VALUES(null,1,'OC00000005','2022/10/16','2022/10/16','CONTADO','con transporte',6845.00,1);
insert into  tb_ordencompra VALUES(null,2,'OC00000006','2022/10/16','2022/10/16','CONTADO','con transporte',9784.00,1);
insert into  tb_ordencompra VALUES(null,2,'OC00000007','2022/10/16','2022/10/16','CONTADO','con transporte',1649.00,1);
insert into  tb_ordencompra VALUES(null,2,'OC00000008','2022/10/16','2022/10/16','CONTADO','con transporte',1112.00,1);
insert into  tb_ordencompra VALUES(null,2,'OC00000009','2022/10/16','2022/10/16','CONTADO','con transporte',4578.00,1);
insert into  tb_ordencompra VALUES(null,2,'OC00000010','2022/10/16','2022/10/16','CONTADO','con transporte',1021.00,1);
insert into  tb_ordencompra VALUES(null,2,'OC00000011','2022/10/16','2022/10/16','CONTADO','con transporte',1547.00,1);
insert into  tb_ordencompra VALUES(null,3,'OC00000012','2022/10/16','2022/10/16','CONTADO','con transporte',1321.00,1);


insert into  tb_ordencompra VALUES(null,3,'OC00000013','2021/10/16','2021/10/22','CONTADO','con transporte',1250.00,1);
insert into  tb_ordencompra VALUES(null,3,'OC00000014','2021/10/16','2021/10/22','CONTADO','con transporte',1154.00,1);
insert into  tb_ordencompra VALUES(null,3,'OC00000015','2021/10/16','2021/10/22','CONTADO','con transporte',1877.00,1);
insert into  tb_ordencompra VALUES(null,3,'OC00000016','2021/10/15','2021/10/20','CONTADO','con transporte',1952.00,1);
insert into  tb_ordencompra VALUES(null,4,'OC00000017','2020/10/16','2020/10/22','CONTADO','con transporte',1120.00,1);
insert into  tb_ordencompra VALUES(null,4,'OC00000018','2020/10/17','2020/10/25','CONTADO','con transporte',1645.00,1);
insert into  tb_ordencompra VALUES(null,4,'OC00000019','2020/10/18','2020/10/25','CONTADO','con transporte',1784.00,1);
insert into  tb_ordencompra VALUES(null,4,'OC00000020','2020/10/13','2020/10/19','CONTADO','con transporte',1111.00,1);
insert into  tb_ordencompra VALUES(null,4,'OC00000021','2019/10/11','2019/10/16','CONTADO','con transporte',1321.00,1);
insert into  tb_ordencompra VALUES(null,4,'OC00000022','2019/10/10','2019/10/15','CONTADO','con transporte',1011.00,1);
insert into  tb_ordencompra VALUES(null,4,'OC00000023','2019/10/13','2019/10/19','CONTADO','con transporte',1841.00,1);
insert into  tb_ordencompra VALUES(null,4,'OC00000024','2019/10/14','2019/10/19','CONTADO','con transporte',1642.00,1);

/* INSERT ORDENCOMPRA */

select * from tb_ordencompra;

select o1_0.id_orden_compra,o1_0.nro_orden_compra,o1_0.condicionespago,o1_0.estado,o1_0.fechaorden_compra,o1_0.fechaentrega,o1_0.id_proveedor,o1_0.transporte,o1_0.valortotal_orden from tb_ordencompra o1_0 where o1_0.fechaentrega='2021-10-22' order by o1_0.fechaentrega asc limit 1,5;



/* INSERT INVENTARIO */

insert into tb_inventario values(1,1,'TI00000001','AL00000001','EM00000001',2,'descripcion','activo');

/* INSERT INVENTARIO */






select * from tb_ordencompra;
/*oc.nro_orden_compra,prov.nombre_comercial_proveedor,oc.valortotal_orden*/
select prov.nombre_comercial_proveedor,max(oc.valortotal_orden) as total from tb_ordencompra oc join tb_proveedor prov on oc.id_proveedor = prov.id_proveedor group by prov.nombre_comercial_proveedor ;

select * from tb_producto;

select * from tb_usuario;
select * from tb_cargo_usuario;
select * from persistent_logins;

select * from tb_sucursal;
select * from tb_almacen;
select * from tb_empleado;
select * from tb_inventario;
select * from tb_proveedor;

