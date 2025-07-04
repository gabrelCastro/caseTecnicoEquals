DROP TABLE IF EXISTS header;
DROP TABLE IF EXISTS detalhe;
DROP TABLE IF EXISTS trailer;

CREATE TABLE header (
    id SERIAL PRIMARY KEY,
    tipo_registro CHAR(1) NOT NULL CHECK (tipo_registro = '0'),
    estabelecimento CHAR(10) NOT NULL,
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


CREATE TABLE detalhe (
    id SERIAL PRIMARY KEY,
    tipo_registro CHAR(1) NOT NULL CHECK (tipo_registro = '1'),
    estabelecimento CHAR(10) NOT NULL,
    data_transacao DATE NOT NULL,
    data_evento DATE NOT NULL,
    hora_evento TIME NOT NULL,
    tipo_evento CHAR(2),
    tipo_transacao CHAR(2),
    numero_serie_leitor VARCHAR(8),
    codigo_transacao VARCHAR(32),
    codigo_pedido VARCHAR(21),
    valor_total NUMERIC(18,2),
    valor_liquido NUMERIC(18,2),
    pagamento CHAR(1),
    plano CHAR(2),
    parcela CHAR(2),
    quantidade_parcelas CHAR(2),
    data_prevista_pagamento DATE,
    taxa_parcelamento_comprador NUMERIC(18,2),
    tarifa_boleto_comprador NUMERIC(18,2),
    valor_original_transacao NUMERIC(18,2),
    taxa_parcelamento_vendedor NUMERIC(18,2),
    taxa_intermediacao NUMERIC(18,2),
    tarifa_boleto_vendedor NUMERIC(18,2),
    repasse_aplicacao NUMERIC(18,2),
    valor_liquido_transacao NUMERIC(18,2),
    status_pagamento CHAR(2),
    filler CHAR(2),
    meio_pagamento CHAR(2),
    instituicao_financeira VARCHAR(30),
    canal_entrada CHAR(2),
    leitor CHAR(2),
    meio_captura CHAR(2),
    numero_logico VARCHAR(32),
    nsu VARCHAR(11),
    filler2 CHAR(3),
    cartao_bin VARCHAR(6),
    cartao_holder VARCHAR(4),
    codigo_autorizacao VARCHAR(6),
    codigo_cv VARCHAR(32),
    reservado CHAR(139)
);

CREATE TABLE trailer (
    id SERIAL PRIMARY KEY,
    tipo_registro CHAR(1) NOT NULL CHECK (tipo_registro = '9'),
    total_registro INT NOT NULL,
    reservado CHAR(518)
);