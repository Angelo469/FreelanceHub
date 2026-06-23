CREATE DATABASE IF NOT EXISTS freelancehub;
USE freelancehub;

CREATE TABLE IF NOT EXISTS freelancer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    especialidade VARCHAR(255) NOT NULL,
    pais VARCHAR(255) NOT NULL,
    taxa_por_hora DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS projeto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cliente VARCHAR(255) NOT NULL,
    prazo DATE NOT NULL,
    valor_dolar DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS atribuicao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    freelancer_id INT NOT NULL,
    projeto_id INT NOT NULL,
    horas_trabalhadas INT NOT NULL,
    FOREIGN KEY (freelancer_id) REFERENCES freelancer(id) ON DELETE CASCADE,
    FOREIGN KEY (projeto_id) REFERENCES projeto(id) ON DELETE CASCADE
);
