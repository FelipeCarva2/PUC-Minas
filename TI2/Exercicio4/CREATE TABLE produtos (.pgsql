CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    estoque INTEGER DEFAULT 0
);

INSERT INTO produtos (nome, preco, estoque) VALUES
    ('Caneta Esferográfica Azul', 2.50, 100),
    ('Caderno Universitário 200 folhas', 25.90, 50),
    ('Borracha Branca Pequena', 1.20, 200),
    ('Lápis Preto HB', 1.80, 150);

    SELECT * from produtos