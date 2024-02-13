CREATE TABLE CLIENT (
    ID serial primary key,
    BALANCE integer NOT NULL,
    BALANCE_LIMIT integer NOT NULL
);

CREATE TABLE CLIENT_TRANSACTION (
    ID serial primary key,
    TRANSACTION_VALUE integer NOT NULL,
    DESCRIPTION varchar(10) NOT NULL,
    TYPE varchar(1) NOT NULL,
    CLIENT_ID serial NOT NULL,
    DATE_TIME timestamp NOT NULL
);
