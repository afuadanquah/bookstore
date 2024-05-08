CREATE TABLE books
    (
        id bigint NOT NULL,
        title varchar(255),
        author varchar(255),
        publicationYear int,
        quantity int,
        originalPrice double,
        sellingPrice double,
        salesTaxPrice double,
        PRIMARY KEY (id)

    ) ENGINE = InnoDB;