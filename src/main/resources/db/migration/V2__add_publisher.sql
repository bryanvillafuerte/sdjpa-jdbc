drop table if exists publisher;

create table publisher
(
    id         bigint not null auto_increment primary key,
    name varchar(255),
    address  varchar(255)
) engine = InnoDB;