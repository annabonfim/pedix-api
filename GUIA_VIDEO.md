# 🎬 Guia para Gravação do Vídeo - Testes CRUD API Pedix

## 📋 Objetivo
Demonstrar o funcionamento dos CRUDs nas tabelas **ITEM_CARDAPIO**, **PEDIDO** e **PEDIDO_ITEM** através da API REST Java.

---

## 🎯 Como Usar o Swagger UI

### Passo a Passo:

1. **Acesse:** http://localhost:8080/swagger-ui.html
2. **Expanda** a seção do endpoint desejado
3. **Clique em "Try it out"**
4. **Preencha** os dados no formato JSON
5. **Clique em "Execute"**
6. **Veja** a resposta

---

## 🎯 Script para o Vídeo (Swagger UI)

### 1️⃣ Introdução (10-15 segundos)
- Mostrar que a API está rodando
- Acessar http://localhost:8080/swagger-ui.html no navegador
- Mostrar a interface do Swagger

### 2️⃣ Testar ITEM_CARDAPIO (1-2 minutos)

#### INSERT 1
```bash
curl -s -X POST http://localhost:8080/api/item-cardapio \
  -H "Content-Type: application/json" \
  -d '{
    "nome":"Pizza Teste 1",
    "descricao":"Teste INSERT 1",
    "preco":35.00,
    "categoria":"PRATO",
    "disponivel":true
  }' | python3 -m json.tool
```

#### INSERT 2
```bash
curl -s -X POST http://localhost:8080/api/item-cardapio \
  -H "Content-Type: application/json" \
  -d '{
    "nome":"Suco Teste 2",
    "descricao":"Teste INSERT 2",
    "preco":8.50,
    "categoria":"BEBIDA",
    "disponivel":true
  }' | python3 -m json.tool
```

#### UPDATE 1
```bash
curl -s -X PUT http://localhost:8080/api/item-cardapio/23 \
  -H "Content-Type: application/json" \
  -d '{
    "nome":"Pizza Teste 1 UPDATED",
    "descricao":"Teste UPDATE 1",
    "preco":38.00,
    "categoria":"PRATO",
    "disponivel":true
  }' | python3 -m json.tool
```

#### UPDATE 2
```bash
curl -s -X PUT http://localhost:8080/api/item-cardapio/24 \
  -H "Content-Type: application/json" \
  -d '{
    "nome":"Suco Teste 2 UPDATED",
    "descricao":"Teste UPDATE 2",
    "preco":9.00,
    "categoria":"BEBIDA",
    "disponivel":false
  }' | python3 -m json.tool
```

#### DELETE 1
```bash
curl -s -X DELETE http://localhost:8080/api/item-cardapio/23 | python3 -m json.tool
```

#### DELETE 2
```bash
curl -s -X DELETE http://localhost:8080/api/item-cardapio/24 | python3 -m json.tool
```

### 3️⃣ Testar PEDIDO (1-2 minutos)

#### INSERT 1
```bash
curl -s -X POST "http://localhost:8080/api/pedido/comanda/1" \
  -H "Content-Type: application/json" \
  -d '{
    "itens":[{"itemCardapioId":1,"quantidade":2}],
    "observacao":"Teste PEDIDO INSERT 1"
  }' | python3 -m json.tool
```

#### INSERT 2
```bash
curl -s -X POST "http://localhost:8080/api/pedido/comanda/2" \
  -H "Content-Type: application/json" \
  -d '{
    "itens":[{"itemCardapioId":2,"quantidade":1}],
    "observacao":"Teste PEDIDO INSERT 2"
  }' | python3 -m json.tool
```

#### UPDATE 1 (Status)
```bash
curl -s -X PUT "http://localhost:8080/api/pedido/23/status?status=PRONTO" | python3 -m json.tool
```

#### UPDATE 2 (Status)
```bash
curl -s -X PUT "http://localhost:8080/api/pedido/24/status?status=ENTREGUE" | python3 -m json.tool
```

#### DELETE 1
```bash
curl -s -X DELETE http://localhost:8080/api/pedido/23 | python3 -m json.tool
```

#### DELETE 2
```bash
curl -s -X DELETE http://localhost:8080/api/pedido/24 | python3 -m json.tool
```

### 4️⃣ Testar PEDIDO_ITEM (1-2 minutos)

#### INSERT 1
```bash
curl -s -X POST http://localhost:8080/api/pedido-item \
  -H "Content-Type: application/json" \
  -d '{
    "pedidoId":25,
    "itemCardapioId":3,
    "quantidade":2,
    "precoUnitario":7.00
  }' | python3 -m json.tool
```

#### INSERT 2
```bash
curl -s -X POST http://localhost:8080/api/pedido-item \
  -H "Content-Type: application/json" \
  -d '{
    "pedidoId":26,
    "itemCardapioId":4,
    "quantidade":3,
    "precoUnitario":5.00
  }' | python3 -m json.tool
```

#### UPDATE 1
```bash
curl -s -X PUT http://localhost:8080/api/pedido-item/27 \
  -H "Content-Type: application/json" \
  -d '{
    "pedidoId":25,
    "itemCardapioId":3,
    "quantidade":5,
    "precoUnitario":7.50
  }' | python3 -m json.tool
```

#### UPDATE 2
```bash
curl -s -X PUT http://localhost:8080/api/pedido-item/28 \
  -H "Content-Type: application/json" \
  -d '{
    "pedidoId":26,
    "itemCardapioId":4,
    "quantidade":4,
    "precoUnitario":5.50
  }' | python3 -m json.tool
```

#### DELETE 1
```bash
curl -s -X DELETE http://localhost:8080/api/pedido-item/27 | python3 -m json.tool
```

#### DELETE 2
```bash
curl -s -X DELETE http://localhost:8080/api/pedido-item/28 | python3 -m json.tool
```

---

## 🎬 Dicas para Gravação

1. **Terminal**: Ajustar fonte e cores para visibilidade
2. **Ordenação**: Seguir a ordem acima
3. **Pausas**: Pausar 1-2 s entre comandos
4. **Demonstração**: Mostrar apenas comandos e resultados
5. **Tempo**: Aproximadamente 5 minutos

---

## 🌐 Alternativa: Swagger UI

Se preferir, abra:
```
http://localhost:8080/swagger-ui.html
```

Use a interface para demonstrar os endpoints.

---

## ✅ Checklist Final

- [ ] API rodando em http://localhost:8080
- [ ] Terminal com fonte legível
- [ ] 2 INSERTs por tabela
- [ ] 2 UPDATEs por tabela
- [ ] 2 DELETEs por tabela
- [ ] Comandos e respostas JSON visíveis
- [ ] Resultados positivos

---

## 🎥 Estrutura do Vídeo Recomendada

1. **Introdução** (15s): "API Pedix para testar CRUDs"
2. **ITEM_CARDAPIO** (90s): demonstrar INSERT, UPDATE, DELETE
3. **PEDIDO** (90s): demonstrar INSERT, UPDATE, DELETE
4. **PEDIDO_ITEM** (90s): demonstrar INSERT, UPDATE, DELETE
5. **Conclusão** (15s): "CRUDs testados com sucesso"

**Tempo total: ~5 minutos**

