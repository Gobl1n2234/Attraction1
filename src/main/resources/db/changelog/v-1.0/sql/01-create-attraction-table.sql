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
alter table attraction
    add constraint FKm5xqx525ip704vr5307yo29ej
    foreign key (category_id)
    references category (id)
GO
alter table attraction
    add constraint FKhwoch55y40g4w67kgl9ummiee
    foreign key (city_id)
    references city (id)
GO