-- Remover as tabelas existentes, se existirem
DROP TABLE IF EXISTS tg_bairro;
DROP TABLE IF EXISTS tg_cidade;
DROP TABLE IF EXISTS tg_contato_usuario;
DROP TABLE IF EXISTS tg_endereco_usuario;
DROP TABLE IF EXISTS tg_estado;
DROP TABLE IF EXISTS tg_log;
DROP TABLE IF EXISTS tg_logradouro;
DROP TABLE IF EXISTS tg_noticia;
DROP TABLE IF EXISTS tg_noticia_acessada;
DROP TABLE IF EXISTS tg_telefone_usuario;
DROP TABLE IF EXISTS tg_tipo_contato;
DROP TABLE IF EXISTS tg_usuario;

-- Criar a tabela tg_estado
CREATE TABLE tg_estado (
    id_estado   INT NOT NULL AUTO_INCREMENT,
    sg_estado   CHAR(2) NOT NULL,
    nm_estado   VARCHAR(30) NOT NULL,
    dt_cadastro DATE NOT NULL,
    PRIMARY KEY (id_estado)
);

-- Criar a tabela tg_cidade
CREATE TABLE tg_cidade (
    id_cidade   INT NOT NULL AUTO_INCREMENT,
    id_estado   INT NOT NULL,
    sg_cidade   CHAR(2) NOT NULL,
    cd_ibge     INT NOT NULL,
    nr_ddd      INT NOT NULL,
    nm_cidade   VARCHAR(30) NOT NULL,
    dt_cadastro DATE NOT NULL,
    PRIMARY KEY (id_cidade),
    CONSTRAINT fk_estado_cidade FOREIGN KEY (id_estado) REFERENCES tg_estado (id_estado)
);

-- Criar a tabela tg_bairro
CREATE TABLE tg_bairro (
    id_bairro   INT NOT NULL AUTO_INCREMENT,
    id_cidade   INT NOT NULL,
    nm_bairro   VARCHAR(30) NOT NULL,
    nm_zona     VARCHAR(15) NOT NULL,
    dt_cadastro DATE NOT NULL,
    PRIMARY KEY (id_bairro),
    CONSTRAINT fk_cidade_bairro FOREIGN KEY (id_cidade) REFERENCES tg_cidade (id_cidade)
);

ALTER TABLE tg_bairro
    ADD CHECK ( nm_zona IN ( 'Zona Leste', 'Centro', 'Leste', 'Norte', 'Oeste',
                             'Sul', 'Zona Norte', 'Zona Oeste', 'Zona Sul' ) );

-- Criar a tabela tg_logradouro
CREATE TABLE tg_logradouro (
    id_logradouro INT NOT NULL AUTO_INCREMENT,
    id_bairro     INT NOT NULL,
    nm_logradouro VARCHAR(30) NOT NULL,
    nr_cep        INT NOT NULL,
    dt_cadastro   DATE NOT NULL,
    PRIMARY KEY (id_logradouro),
    CONSTRAINT fk_bairro_logradouro FOREIGN KEY (id_bairro) REFERENCES tg_bairro (id_bairro)
);

-- Criar a tabela tg_usuario
CREATE TABLE tg_usuario (
    id_usuario    INT NOT NULL AUTO_INCREMENT,
    nm_usuario    VARCHAR(30) NOT NULL,
    nr_cpf        BIGINT NOT NULL,
    nm_rg         VARCHAR(15),
    dt_nascimento DATE,
    nm_email      VARCHAR(100) NOT NULL,
    nm_senha      VARCHAR(100) NOT NULL,
    dt_cadastro   DATE,
    PRIMARY KEY (id_usuario),
    UNIQUE (nr_cpf),
    UNIQUE (nm_rg)
);

-- Criar a tabela tg_tipo_contato
CREATE TABLE tg_tipo_contato (
    id_tipo     INT NOT NULL AUTO_INCREMENT,
    nm_tipo     VARCHAR(10) NOT NULL,
    dt_inico    DATE NOT NULL,
    dt_fim      DATE,
    dt_cadastro DATE NOT NULL,
    PRIMARY KEY (id_tipo)
);

-- Criar a tabela tg_contato_usuario
CREATE TABLE tg_contato_usuario (
    id_contato  INT NOT NULL AUTO_INCREMENT,
    id_usuario  INT NOT NULL,
    id_tipo     INT NOT NULL,
    nm_contato  VARCHAR(30) NOT NULL,
    nr_ddi      INT NOT NULL,
    nr_ddd      INT,
    nr_telefone INT,
    dt_cadastro DATE NOT NULL,
    PRIMARY KEY (id_contato),
    CONSTRAINT fk_tipo_contato FOREIGN KEY (id_tipo) REFERENCES tg_tipo_contato (id_tipo),
    CONSTRAINT fk_usuario_contato FOREIGN KEY (id_usuario) REFERENCES tg_usuario (id_usuario)
);

-- Criar a tabela tg_telefone_usuario
CREATE TABLE tg_telefone_usuario (
    id_telefone INT NOT NULL AUTO_INCREMENT,
    id_usuario  INT NOT NULL,
    nr_ddi      INT NOT NULL,
    nr_ddd      INT NOT NULL,
    nr_telefone INT NOT NULL,
    tp_telefone VARCHAR(20) NOT NULL,
    st_telefone CHAR(1) NOT NULL,
    dt_cadastro DATE NOT NULL,
    PRIMARY KEY (id_telefone),
    CONSTRAINT fk_usuario_telefone FOREIGN KEY (id_usuario) REFERENCES tg_usuario (id_usuario)
);

-- Criar a tabela tg_endereco_usuario
CREATE TABLE tg_endereco_usuario (
    id_endereco    INT NOT NULL AUTO_INCREMENT,
    id_logradouro  INT NOT NULL,
    id_usuario     INT NOT NULL,
    nr_logradouro  INT,
    ds_complemento VARCHAR(30),
    ds_ponto_ref   VARCHAR(30),
    dt_inicio      DATE NOT NULL,
    dt_fim         DATE,
    dt_cadastro    DATE NOT NULL,
    PRIMARY KEY (id_endereco),
    CONSTRAINT fk_logradouro_endereco FOREIGN KEY (id_logradouro) REFERENCES tg_logradouro (id_logradouro),
    CONSTRAINT fk_usuario_endereco FOREIGN KEY (id_usuario) REFERENCES tg_usuario (id_usuario)
);

-- Criar a tabela tg_log
CREATE TABLE tg_log (
    id_log       INT NOT NULL AUTO_INCREMENT,
    id_usuario   INT NOT NULL,
    nm_procedure VARCHAR(100) NOT NULL,
    dt_erro      TIMESTAMP NOT NULL,
    cd_erro      INT NOT NULL,
    nm_erro      VARCHAR(4000) NOT NULL,
    PRIMARY KEY (id_log),
    CONSTRAINT fk_usuario_log FOREIGN KEY (id_usuario) REFERENCES tg_usuario (id_usuario)
);

-- Criar a tabela tg_noticia
CREATE TABLE tg_noticia (
    id_noticia   INT NOT NULL AUTO_INCREMENT,
    nm_titulo    VARCHAR(200) NOT NULL,
    nm_subtitulo VARCHAR(200) NOT NULL,
    dt_noticia   DATE NOT NULL,
    ds_imagem    VARCHAR(400) NOT NULL,
    ds_link      VARCHAR(400) NOT NULL,
    PRIMARY KEY (id_noticia)
);

-- Criar a tabela tg_noticia_acessada
CREATE TABLE tg_noticia_acessada (
    id_noticia_acess INT NOT NULL AUTO_INCREMENT,
    id_usuario       INT NOT NULL,
    id_noticia       INT NOT NULL,
    dt_acesso        DATE NOT NULL,
    PRIMARY KEY (id_noticia_acess),
    CONSTRAINT fk_noticia_acessada FOREIGN KEY (id_noticia) REFERENCES tg_noticia (id_noticia),
    CONSTRAINT fk_usuario_noticia_acessada FOREIGN KEY (id_usuario) REFERENCES tg_usuario (id_usuario)
);
