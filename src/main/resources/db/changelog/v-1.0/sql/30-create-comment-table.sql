create table comment (
                         id bigint not null auto_increment,
                         date datetime(6) not null,
                         text varchar(255) not null,
                         user_id bigint not null,
                         primary key (id)
) engine=InnoDB
GO
alter table comment
    add constraint UK_mxoojfj9tmy8088avf57mpm02 unique (user_id)
GO