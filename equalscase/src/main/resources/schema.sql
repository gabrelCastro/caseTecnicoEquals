CREATE TABLE header (
    id SERIAL PRIMARY KEY,
    tipo_registro CHAR(1) NOT NULL CHECK (tipo_registro = '0'),
    estabelecimento BIGINT NOT NULL,
    data_geracao DATE NOT NULL,
    periodo_inicial DATE NOT NULL,
    periodo_final DATE NOT NULL,
    sequencia INT NOT NULL,
    empresa_adquirente VARCHAR(5) NOT NULL DEFAULT 'FICTI',
    tipo_extrato CHAR(3) NOT NULL,
    filler CHAR(21) DEFAULT 'A',
    versao_layout CHAR(3) DEFAULT '002',
    versao_release CHAR(5) DEFAULT '.002c',
    reservado CHAR(453)
);