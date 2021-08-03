--liquibase formatted sql

--changeset raulmontemayor:20210725223442_1
CREATE TABLE hero ( 
   id BIGINT NOT NULL AUTO_INCREMENT, 
   full_name VARCHAR(200) NOT NULL,
   alter_ego VARCHAR(200) NOT NULL,
   species VARCHAR(200) NOT NULL,
   universe VARCHAR(200) NOT NULL
);