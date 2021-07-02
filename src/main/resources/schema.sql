CREATE TABLE IF NOT EXISTS Ingredient
(
    id   varchar(4)   NOT NULL UNIQUE,
    name varchar(256) NOT NULL,
    type varchar(10)  NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco
(
    id   identity,
    name varchar(50) NOT NULL,
    type timestamp   NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco_Ingredients
(
    taco       bigint     NOT NULL,
    ingredient varchar(4) NOT NULL,
    FOREIGN KEY (taco) REFERENCES Taco (id),
    FOREIGN KEY (ingredient) REFERENCES Ingredient (id)
);

CREATE TABLE IF NOT EXISTS Taco_Order
(
    id             identity,
    deliveryName   varchar(50) NOT NULL,
    deliveryStreet varchar(50) NOT NULL,
    deliveryCity   varchar(50) NOT NULL,
    deliveryState  varchar(2)  NOT NULL,
    deliveryZip    varchar(10) NOT NULL,
    ccNumber       varchar(16) NOT NULL,
    ccExpiration   varchar(5)  NOT NULL,
    ccCVV          varchar(3)  NOT NULL,
    placedAt       timestamp   NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco_Order_Tacos
(
    taco_order bigint NOT NULL,
    taco       bigint NOT NULL,
    FOREIGN KEY (taco_order) REFERENCES Taco_Order (id),
    FOREIGN KEY (taco) REFERENCES Taco (id)
);