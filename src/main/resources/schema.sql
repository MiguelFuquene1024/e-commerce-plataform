CREATE TABLE IF NOT EXISTS producto  (
                                     id INT auto_increment NOT NULL,
                                     name varchar(100) NOT NULL,
                                     description varchar(100) NULL,
                                     price FLOAT NULL,
                                     amount INT NOT NULL,
                                     CONSTRAINT producto_pk PRIMARY KEY (id),
                                     CONSTRAINT producto_unique UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;