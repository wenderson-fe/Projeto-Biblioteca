CREATE TABLE IF NOT EXISTS livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    num_copias INT NOT NULL,
    emprestado BOOLEAN DEFAULT FALSE,

    PRIMARY KEY (id)
    );
