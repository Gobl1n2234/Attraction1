create table category (
                          id bigint not null auto_increment,
                          name varchar(255) not null,
                          primary key (id)
) engine=InnoDB
GO
alter table category
    add constraint UK_46ccwnsi9409t36lurvtyljak unique (name)
GO