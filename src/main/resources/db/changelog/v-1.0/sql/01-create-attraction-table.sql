create table attraction (
                            id bigint not null auto_increment,
                            latitude double precision not null,
                            longitude double precision not null,
                            name varchar(30) not null,
                            category_id bigint,
                            city_id bigint,
                            primary key (id)
) engine=InnoDB
GO
alter table attraction
    add constraint UK_pkl6ep8pexyl88g9wmle0w4nk unique (name)
GO
