create table rating (
                        id bigint not null auto_increment,
                        date datetime(6) not null,
                        rating integer not null,
                        user_id bigint not null,
                        attraction_id bigint,
                        primary key (id)
) engine=InnoDB
GO
alter table rating
    add constraint UK_8dfu35xwik8uwlrdloci4ok2i unique (user_id)
GO
alter table rating
    add constraint FKp488n4cinbc5ajf6caq7mnf1l
    foreign key (attraction_id)
    references attraction (id)
GO