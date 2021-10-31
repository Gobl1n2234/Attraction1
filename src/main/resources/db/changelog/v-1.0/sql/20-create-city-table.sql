create table city (
                      id bigint not null auto_increment,
                      name varchar(255) not null,
                      primary key (id)
) engine=InnoDB
GO
alter table city
    add constraint UK_qsstlki7ni5ovaariyy9u8y79 unique (name)
GO
