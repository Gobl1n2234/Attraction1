create table category (
                          id bigint not null auto_increment,
                          name varchar(255) not null,
                          primary key (id)
) engine=InnoDB
GO
alter table category
    add constraint UK_46ccwnsi9409t36lurvtyljak unique (name)
GO
alter table attraction
    add constraint FKm5xqx525ip704vr5307yo29ej
        foreign key (category_id)
            references category (id)
GO