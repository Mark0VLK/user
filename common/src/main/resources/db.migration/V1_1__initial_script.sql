create table if not exists data.user_info
(
    id         bigint auto_increment
    primary key,
    surname    varchar(40)          not null,
    name       varchar(20)          not null,
    patronymic varchar(40)          not null,
    email      varchar(50)          not null,
    password   varchar(50)          not null,
    created    timestamp(6)         not null,
    changed    timestamp(6)         not null,
    is_deleted tinyint(1) default 0 not null,
    constraint email
    unique (email),
    constraint id
    unique (id)
    );

create table if not exists data.role
(
    id        bigint auto_increment
    primary key,
    role_name varchar(20)  not null,
    id_user   bigint       not null,
    changed   timestamp(6) not null,
    created   timestamp(6) not null,
    constraint id
    unique (id),
    constraint role_user_info_id_fk
    foreign key (id_user) references data.user_info (id)
    on update cascade on delete cascade
    );

create index user_email_index
    on data.user_info (email);

