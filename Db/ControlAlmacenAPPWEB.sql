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
	estado boolean not null,
    foreign key(id_tipoprod) references tb_tipoproducto(id_tipoprod)
);
insert into tb_tipoproducto(nombre_tipoprod,estado) values ('comestible',true);
insert into tb_tipoproducto(nombre_tipoprod,estado) values ('no comestible',true);
insert into tb_tipoproducto(nombre_tipoprod,estado) values ('acido',true);

select * from tb_tipoproducto;

insert into tb_producto values ('PR00000001',1,'0000123','Cereal bar','Costa',true);

select * from tb_producto;

create table if not exists tb_tipoinventario
(
    id_tipoinventario int auto_increment primary key,
    nombre_tipoinventario varchar(10) not null,
    estado boolean not null
);

create table if not exists tb_sucursal
(
	id_sucursal char(10) primary key,
	sector_sucursal varchar(30) not null ,  /*Sur, Centro, Norte*/
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

insert into tb_sucursal values ('SC00000001','Selva Central',true);
select * from tb_sucursal;

insert into tb_almacen values ('AL00000001','SC00000001','Fundado en 2018','Av Las Palmas, Lurin',true);
select * from tb_almacen;

insert into tb_empleado values ('EM00000001','AL00000001','Mauricio','Jimenez','mau@gmail.com','994545661',true);
select * from tb_empleado;



create table if not exists tb_inventario
(
    id_inventario int auto_increment primary key,
    id_producto char(10) not null,
    id_tipoinventario int not null,
    id_almacen char(10) not null,
    id_empleado char(10) not null,
    cantidad_inventario int not null,   
    descripcion_inventario varchar(120) not null,
    estado boolean not null,
    foreign key (id_producto) references tb_producto (id_producto),
    foreign key (id_tipoinventario) references tb_tipoinventario (id_tipoinventario),
    foreign key (id_almacen) references tb_almacen (id_almacen),
    foreign key (id_empleado) references tb_empleado (id_empleado)
);

create table if not exists tb_cargo
(
	id_cargo char(10) primary key,
	nombre_cargo varchar(30) not null,
	estado boolean not null
);

create table if not exists tb_usuario
(
	id_usuario int auto_increment primary key, 
	id_empleado char(10) not null,
	id_cargo char(10) not null,
	alias_usuario varchar(30) not null unique,
	contrasena_usuario varchar(50) not null,
    estado boolean not null,
	foreign key (id_empleado) references tb_empleado(id_empleado),
	foreign key (id_cargo) references tb_cargo(id_cargo)  
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
	nombre_acceso varchar(60) not null,
	estado boolean not null,
    foreign key(id_menu) references tb_menu(id_menu)
);


create table if not exists tb_acceso_cargo
(
	id_acceso char(10),
	id_cargo char(10),
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









