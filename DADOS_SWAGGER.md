# 📋 Dados Prontos para Copiar e Colar no Swagger

## 🍕 ITEM_CARDAPIO

### INSERT 1 - Pizza
```json
{
  "nome": "Pizza Teste 1",
  "descricao": "Teste INSERT 1 - Pizza Margherita",
  "preco": 35,
  "categoria": "PRATO",
  "disponivel": true
}
```

### INSERT 2 - Bebida
```json
{
  "nome": "Suco Teste 2",
  "descricao": "Teste INSERT 2 - Suco de Laranja",
  "preco": 8.5,
  "categoria": "BEBIDA",
  "disponivel": true
}
```

### UPDATE 1 - Pizza
```json
{
  "nome": "Pizza Teste 1 UPDATED",
  "descricao": "Teste UPDATE 1 - Pizza modificada",
  "preco": 38,
  "categoria": "PRATO",
  "disponivel": true
}
```

### UPDATE 2 - Bebida
```json
{
  "nome": "Suco Teste 2 UPDATED",
  "descricao": "Teste UPDATE 2 - Suco modificado",
  "preco": 9,
  "categoria": "BEBIDA",
  "disponivel": false
}
```

---

## 🛒 PEDIDO

### INSERT 1
```json
{
  "itens": [
    {
      "itemCardapioId": 1,
      "quantidade": 2
    }
  ],
  "observacao": "Teste PEDIDO INSERT 1"
}
```

### INSERT 2
```json
{
  "itens": [
    {
      "itemCardapioId": 2,
      "quantidade": 1
    }
  ],
  "observacao": "Teste PEDIDO INSERT 2"
}
```

### UPDATE 1 (Status)
- Use o endpoint: `PUT /api/pedido/{id}/status`
- Parâmetro **id**: coloque um ID existente (ex: `1`)
- Parâmetro **status**: `PRONTO`

### UPDATE 2 (Status)
- Parâmetro **id**: outro ID existente
- Parâmetro **status**: `ENTREGUE`

---

## 📦 PEDIDO_ITEM

### INSERT 1
```json
{
  "pedidoId": 25,
  "itemCardapioId": 3,
  "quantidade": 2,
  "precoUnitario": 7
}
```

### INSERT 2
```json
{
  "pedidoId": 26,
  "itemCardapioId": 4,
  "quantidade": 3,
  "precoUnitario": 5
}
```

### UPDATE 1
```json
{
  "pedidoId": 25,
  "itemCardapioId": 3,
  "quantidade": 5,
  "precoUnitario": 7.5
}
```

### UPDATE 2
```json
{
  "pedidoId": 26,
  "itemCardapioId": 4,
  "quantidade": 4,
  "precoUnitario": 5.5
}
```

---

## ⚠️ IMPORTANTE

1. **IDs dinâmicos**: anote os IDs retornados nos INSERTs
2. **Comandas**: use IDs de comandas existentes (verifique no banco)
3. **Itens**: use IDs de itens de cardápio existentes (1, 2, 3, etc.)

---

## 🎬 Ordem de Gravação

1. **ITEM_CARDAPIO**: INSERT 1 → INSERT 2 → UPDATE 1 → UPDATE 2 → DELETE 1 → DELETE 2
2. **PEDIDO**: INSERT 1 → INSERT 2 → UPDATE 1 → UPDATE 2 → DELETE 1 → DELETE 2
3. **PEDIDO_ITEM**: INSERT 1 → INSERT 2 → UPDATE 1 → UPDATE 2 → DELETE 1 → DELETE 2

