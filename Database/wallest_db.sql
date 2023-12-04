create database wallets_;     

\c wallets_;
   CREATE TABLE IF NOT EXISTS Account (
        id SERIAL PRIMARY KEY,
        pay VARCHAR(255),
        solde DECIMAL(10, 2),
        account_type VARCHAR(50),
        currency VARCHAR(50),
        create_date date
    );


    CREATE Table IF NOT Exists Currency(
        id serial PRIMARY KEY,
        code varchar (50),
        name VARCHAR (100),
        exchange_rate int
    );

    CREATE Table if NOT exists Transaction(
        id serial PRIMARY KEY,
        id_account int ,
        rising FLOAT,
        transaction_type VARCHAR (100),
        currency VARCHAR (100),
        date DATE,
        constraint fk_transaction_account FOREIGN KEY(id_account) REFERENCES Account (id)
    );