create table if not exists user
(
	id bigint auto_increment
		primary key,
	username varchar(50) not null,
	email varchar(100) not null,
	crate_time timestamp(6) null,
	update_time timestamp(6) null
);

