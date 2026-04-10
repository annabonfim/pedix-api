# 🎬 Guia Rápido - Testando pelo Swagger UI

## 📍 Acesse:
```
http://localhost:8080/swagger-ui.html
```

---

## 🎯 TESTE ITEM_CARDAPIO

### ✅ POST - Criar Item 1
1. Expandir `POST /api/item-cardapio`
2. Clicar em **"Try it out"**
3. Preencher com:
```json
{
  "nome": "Pizza Teste 1",
  "descricao": "Teste INSERT 1",
  "preco": 35.00,
  "categoria": "PRATO",
  "disponivel": true
}
```
4. Clicar em **"Execute"**

### ✅ POST - Criar Item 2
Mesmo processo, preencher com:
```json
{
  "nome": "Suco Teste 2",
  "descricao": "Teste INSERT 2",
  "preco": 8.50,
  "categoria": "BEBIDA",
  "disponivel": true
}
```

### ✏️ PUT - Atualizar Item 1
1. Expandir `PUT /api/item-cardapio/{id}`
2. Clicar em **"Try it out"**
3. No campo **id**, colocar: `23`
4. Preencher Body com:
```json
{
  "nome": "Pizza Teste 1 UPDATED",
  "descricao": "Teste UPDATE 1",
  "preco": 38.00,
  "categoria": "PRATO",
  "disponivel": true
}
```
5. **"Execute"**

### ✏️ PUT - Atualizar Item 2
Mesmo processo, id: `24`, body:
```json
{
  "nome": "Suco Teste 2 UPDATED",
  "descricao": "Teste UPDATE 2",
  "preco": 9.00,
  "categoria": "BEBIDA",
  "disponivel": false
}
```

### 🗑️ DELETE - Remover Item 1
1. Expandir `DELETE /api/item-cardapio/{id}`
2. Clicar em **"Try it out"**
3. No campo **id**, colocar: `23`
4. **"Execute"**

### 🗑️ DELETE - Remover Item 2
Mesmo processo, id: `24`

---

## 🎯 TESTE PEDIDO

### ✅ POST - Criar Pedido 1
1. Expandir `POST /api/pedido/comanda/{comandaId}`
2. Clicar em **"Try it out"**
3. No campo **comandaId**, colocar: `1`
4. Preencher Body com:
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
5. **"Execute"**

### ✅ POST - Criar Pedido 2
Mesmo processo, comandaId: `2`, body:
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

### ✏️ PUT - Atualizar Status 1
1. Expandir `PUT /api/pedido/{id}/status`
2. Clicar em **"Try it out"**
3. No campo **id**, colocar: `23`
4. No campo **status**, colocar: `PRONTO`
5. **"Execute"**

### ✏️ PUT - Atualizar Status 2
Mesmo processo, id: `24`, status: `ENTREGUE`

### 🗑️ DELETE - Remover Pedido 1
1. Expandir `DELETE /api/pedido/{id}`
2. Clicar em **"Try it out"**
3. No campo **id**, colocar: `23`
4. **"Execute"**

### 🗑️ DELETE - Remover Pedido 2
Mesmo processo, id: `24`

---

## 🎯 TESTE PEDIDO_ITEM

### ✅ POST - Criar Item de Pedido 1
1. Expandir `POST /api/pedido-item`
2. Clicar em **"Try it out"**
3. Preencher Body com:
```json
{
  "pedidoId": 25,
  "itemCardapioId": 3,
  "quantidade": 2,
  "precoUnitario": 7.00
}
```
4. **"Execute"**

### ✅ POST - Criar Item de Pedido 2
Mesmo processo, body:
```json
{
  "pedidoId": 26,
  "itemCardapioId": 4,
  "quantidade": 3,
  "precoUnitario": 5.00
}
```

### ✏️ PUT - Atualizar Item de Pedido 1
1. Expandir `PUT /api/pedido-item/{id}`
2. Clicar em **"Try it out"**
3. No campo **id**, colocar: `27`
4. Preencher Body com:
```json
{
  "pedidoId": 25,
  "itemCardapioId": 3,
  "quantidade": 5,
  "precoUnitario": 7.50
}
```
5. **"Execute"**

### ✏️ PUT - Atualizar Item de Pedido 2
Mesmo processo, id: `28`, body:
```json
{
  "pedidoId": 26,
  "itemCardapioId": 4,
  "quantidade": 4,
  "precoUnitario": 5.50
}
```

### 🗑️ DELETE - Remover Item de Pedido 1
1. Expandir `DELETE /api/pedido-item/{id}`
2. Clicar em **"Try it out"**
3. No campo **id**, colocar: `27`
4. **"Execute"**

### 🗑️ DELETE - Remover Item de Pedido 2
Mesmo processo, id: `28`

---

## ✅ Checklist
- [ ] ITEM_CARDAPIO: 2 INSERTs, 2 UPDATEs, 2 DELETEs
- [ ] PEDIDO: 2 INSERTs, 2 UPDATEs, 2 DELETEs
- [ ] PEDIDO_ITEM: 2 INSERTs, 2 UPDATEs, 2 DELETEs

---

## 💡 Dicas
1. **Anotar IDs**: ao criar, anote o ID retornado para UPDATE/DELETE
2. **Pausar**: pause 1–2 segundos entre requisições
3. **Mostrar resposta**: exiba o JSON completo
4. **Timing total**: ~5 minutos

