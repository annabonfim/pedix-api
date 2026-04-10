-- ====================================================
-- RESET DO BANCO - Remove tabelas existentes (seguro)
-- ====================================================
BEGIN
  FOR t IN (SELECT table_name FROM user_tables) LOOP
    EXECUTE IMMEDIATE 'DROP TABLE "' || t.table_name || '" CASCADE CONSTRAINTS';
  END LOOP;
END;
/
-- ====================================================
-- SEQUENCES
-- ====================================================

BEGIN
  EXECUTE IMMEDIATE 'DROP SEQUENCE ITEM_CARDAPIO_SEQ';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP SEQUENCE PEDIDO_SEQ';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP SEQUENCE PEDIDO_ITEM_SEQ';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE ITEM_CARDAPIO_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE PEDIDO_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE PEDIDO_ITEM_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- ====================================================
-- TABELAS
-- ====================================================

CREATE TABLE ITEM_CARDAPIO (
  ID_ITEM         NUMBER PRIMARY KEY,
  NOME            VARCHAR2(120) NOT NULL,
  DESCRICAO       VARCHAR2(500),
  PRECO           NUMBER(12,2) NOT NULL,
  CATEGORIA       VARCHAR2(30),
  DISPONIVEL      CHAR(1) DEFAULT 'S',
  IMAGEM_URL      VARCHAR2(500)
);

CREATE TABLE PEDIDO (
  ID_PEDIDO       NUMBER PRIMARY KEY,
  ID_COMANDA      NUMBER NOT NULL,
  STATUS          VARCHAR2(50) DEFAULT 'EM_PREPARO',
  OBSERVACAO      VARCHAR2(500),
  DATA_HORA       TIMESTAMP DEFAULT SYSTIMESTAMP,
  TOTAL           NUMBER(12,2) DEFAULT 0
);

CREATE TABLE PEDIDO_ITEM (
  ID_PEDIDO_ITEM     NUMBER PRIMARY KEY,
  PEDIDO_ID          NUMBER NOT NULL,
  ITEM_CARDAPIO_ID   NUMBER NOT NULL,
  QUANTIDADE         NUMBER(5) NOT NULL,
  PRECO_UNITARIO     NUMBER(10,2) NOT NULL,
  SUBTOTAL           NUMBER(12,2) NOT NULL,
  CONSTRAINT FK_PEDIDO FOREIGN KEY (PEDIDO_ID) REFERENCES PEDIDO(ID_PEDIDO) ON DELETE CASCADE,
  CONSTRAINT FK_ITEM FOREIGN KEY (ITEM_CARDAPIO_ID) REFERENCES ITEM_CARDAPIO(ID_ITEM)
);

-- ===================================================
-- TRIGGERS (auto incremento via sequence)
-- ====================================================

CREATE OR REPLACE TRIGGER TRG_ITEM_CARDAPIO_ID
BEFORE INSERT ON ITEM_CARDAPIO
FOR EACH ROW
WHEN (NEW.ID_ITEM IS NULL)
BEGIN
  SELECT ITEM_CARDAPIO_SEQ.NEXTVAL INTO :NEW.ID_ITEM FROM dual;
END;
/

CREATE OR REPLACE TRIGGER TRG_PEDIDO_ID
BEFORE INSERT ON PEDIDO
FOR EACH ROW
WHEN (NEW.ID_PEDIDO IS NULL)
BEGIN
  SELECT PEDIDO_SEQ.NEXTVAL INTO :NEW.ID_PEDIDO FROM dual;
END;
/

CREATE OR REPLACE TRIGGER TRG_PEDIDO_ITEM_ID
BEFORE INSERT ON PEDIDO_ITEM
FOR EACH ROW
WHEN (NEW.ID_PEDIDO_ITEM IS NULL)
BEGIN
  SELECT PEDIDO_ITEM_SEQ.NEXTVAL INTO :NEW.ID_PEDIDO_ITEM FROM dual;
END;
/

-- ====================================================
-- DADOS DE EXEMPLO
-- ====================================================

-- Itens do cardápio (restaurante italiano)
INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Água', 'Água mineral sem gás, garrafa 500ml', 4.50, 'bebida', 'S', 'https://images.unsplash.com/photo-1548839140-29a749e1cf4d?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Refrigerante', 'Refrigerante gelado, lata 350ml', 6.00, 'bebida', 'S', 'https://images.unsplash.com/photo-1622483767028-3f66f32aef97?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Suco', 'Suco natural da fruta, copo 300ml', 8.00, 'bebida', 'S', 'https://images.unsplash.com/photo-1600271886742-f049cd451bba?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Espresso Italiano', 'Café espresso encorpado, servido na xícara tradicional', 7.00, 'bebida', 'S', 'https://images.unsplash.com/photo-1510707577719-ae7c14805e3a?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Hambúrguer', 'Hambúrguer artesanal com queijo, alface, tomate e molho especial', 25.00, 'prato', 'S', 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Batata Frita', 'Porção média de batata frita crocante com sal e orégano', 15.00, 'prato', 'S', 'https://images.unsplash.com/photo-1573080496219-bb080dd4f877?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Pizza Margherita', 'Massa fina com molho de tomate San Marzano, mussarela e manjericão fresco', 35.00, 'prato', 'S', 'https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Insalata Caprese', 'Tomate, mussarela de búfala, manjericão e azeite extravirgem', 28.00, 'prato', 'S', 'https://images.unsplash.com/photo-1592417817098-8fd3d9eb14a5?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Sorvete', 'Sorvete artesanal italiano, 2 bolas à escolha', 16.00, 'sobremesa', 'S', 'https://images.unsplash.com/photo-1497034825429-c343d7c6a68f?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Panna Cotta', 'Creme italiano com calda de frutas vermelhas', 18.00, 'sobremesa', 'S', 'https://images.unsplash.com/photo-1488477181946-6428a0291777?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Bruschetta Caprese', 'Pão italiano tostado com tomate, mussarela de búfala e manjericão', 22.00, 'prato', 'S', 'https://images.unsplash.com/photo-1572695157366-5e585ab2b69f?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Arancini', 'Bolinhos crocantes de risoto recheados com mussarela', 28.00, 'prato', 'S', 'https://images.unsplash.com/photo-1595295333158-4742f28fbd85?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Spaghetti Carbonara', 'Massa al dente com molho cremoso, bacon crocante e parmesão', 42.00, 'prato', 'S', 'https://images.unsplash.com/photo-1612874742237-6526221588e3?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Tiramisù', 'Clássico italiano com camadas de biscoito, café e mascarpone', 24.00, 'sobremesa', 'S', 'https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Limonada Siciliana', 'Limonada refrescante com hortelã e limão siciliano', 14.00, 'bebida', 'S', 'https://images.unsplash.com/photo-1621263764928-df1444c5e859?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Lasagna alla Bolognese', 'Camadas de massa fresca, ragu de carne, bechamel e parmesão gratinado', 48.00, 'prato', 'S', 'https://images.unsplash.com/photo-1574894709920-11b28e7367e3?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Risotto ai Funghi', 'Arroz arbóreo cremoso com mix de cogumelos e parmesão', 45.00, 'prato', 'S', 'https://images.unsplash.com/photo-1476124369491-e7addf5db371?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Fettuccine Alfredo', 'Massa fresca ao molho cremoso de parmesão e manteiga', 38.00, 'prato', 'S', 'https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=300');

INSERT INTO ITEM_CARDAPIO (NOME, DESCRICAO, PRECO, CATEGORIA, DISPONIVEL, IMAGEM_URL)
VALUES ('Soda Italiana', 'Nossas sodas italianas são refrescantes, artesanais e feitas na hora. Escolha o seu sabor favorito e se surpreenda com cada gole. Maçã Verde — leve, refrescante e com aquele toque cítrico irresistível. Frutas Vermelhas — intensa, doce e cheia de personalidade. Lichia — delicada, exótica e surpreendentemente refrescante.', 12.00, 'bebida', 'S', 'https://images.unsplash.com/photo-1512482017241-ccce0181a7fd?w=800&q=80');

-- Pedidos
INSERT INTO PEDIDO (ID_COMANDA, STATUS, OBSERVACAO, TOTAL)
VALUES (1001, 'EM_PREPARO', 'Sem queijo ralado', 35.00);

INSERT INTO PEDIDO (ID_COMANDA, STATUS, OBSERVACAO, TOTAL)
VALUES (1002, 'PRONTO', 'Um com gelo, outro sem', 17.00);

INSERT INTO PEDIDO (ID_COMANDA, STATUS, OBSERVACAO, TOTAL)
VALUES (1003, 'EM_PREPARO', 'Sem cebola', 45.00);

-- Itens dos pedidos
INSERT INTO PEDIDO_ITEM (PEDIDO_ID, ITEM_CARDAPIO_ID, QUANTIDADE, PRECO_UNITARIO, SUBTOTAL)
VALUES (1, 1, 1, 35.00, 35.00);

INSERT INTO PEDIDO_ITEM (PEDIDO_ID, ITEM_CARDAPIO_ID, QUANTIDADE, PRECO_UNITARIO, SUBTOTAL)
VALUES (2, 2, 2, 8.50, 17.00);

INSERT INTO PEDIDO_ITEM (PEDIDO_ID, ITEM_CARDAPIO_ID, QUANTIDADE, PRECO_UNITARIO, SUBTOTAL)
VALUES (3, 5, 1, 40.00, 40.00);

COMMIT;
