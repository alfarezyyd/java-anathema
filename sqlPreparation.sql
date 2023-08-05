DROP DATABASE test;
CREATE DATABASE test;
USE test;
CREATE TABLE IF NOT EXISTS domain
(
    GlobalRank     varchar(255),
    TldRank        varchar(255),
    Domain         varchar(255),
    TLD            varchar(255),
    RefSubNets     varchar(255),
    RefIPs         varchar(255),
    IDN_Domain     varchar(255),
    IDN_TLD        varchar(255),
    PrevGlobalRank varchar(255),
    PrevTldRank    varchar(255),
    PrevRefSubNets varchar(255),
    PrevRefIPs     varchar(255)
);

SELECT *
FROM domain;
SELECT COUNT(*)
FROM domain;