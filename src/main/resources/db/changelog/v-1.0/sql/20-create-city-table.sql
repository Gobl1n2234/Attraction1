create table city (
                      id bigint not null auto_increment,
                      name varchar(255) not null,
                      primary key (id)
) engine=InnoDB
GO
alter table city
    add constraint UK_qsstlki7ni5ovaariyy9u8y79 unique (name)
GO
alter table attraction
    add constraint FKhwoch55y40g4w67kgl9ummiee
        foreign key (city_id)
            references city (id)
GO