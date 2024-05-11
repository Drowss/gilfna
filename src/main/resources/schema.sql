create table clase9test.autor(
	id int auto_increment primary key,
	name varchar(255) not null,
	lastname varchar(255) not null,
	email varchar(255) not null,
	username varchar(255) not null
);

create table clase9test.anuncio(
	id int auto_increment primary key,
	title varchar(255) not null,
	content text not null,
	published timestamp not null,
	updated timestamp,
	autorid int not null,
	foreign key (autorid) references clase9test.autor(id)
);

ALTER TABLE clase9test.anuncio ADD autorid int NOT NULL;
SET FOREIGN_KEY_CHECKS=0;
ALTER TABLE clase9test.anuncio ADD CONSTRAINT fk_autorid FOREIGN KEY (autorid) REFERENCES clase9test.autor(id);
SET GLOBAL FOREIGN_KEY_CHECKS=1;

ALTER TABLE clase9test.autor
RENAME COLUMN lastaname TO lastname;

DELETE FROM clase9test.anuncio WHERE id=1;

create table clase9test.comentario(
	anuncio int not null,
	name varchar(100) not null,
	content text not null,
	published timestamp not null,
	updated timestamp,
	foreign key (anuncio) references clase9test.anuncio(id)
);

create table clase9test.evento(
	id int auto_increment primary key,
	title varchar(255) not null,
	descripcion text not null,
	estado varchar(120) not null,
	tipo varchar(120) not null,
	datecreated timestamp not null,
	dateupdated timestamp
);

create table clase9test.evento_autor(
	autor int not null,
	evento int not null,
	name varchar(255),
	primary key (autor,evento)
);



