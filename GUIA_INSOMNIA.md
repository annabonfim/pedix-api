# 🎬 Guia para Testar no Insomnia

## ⚙️ Configuração Básica

1. **Método**: Selecione `POST`, `PUT`, `DELETE` ou `GET`
2. **URL**: `http://localhost:8080/api/item-cardapio` (ou o endpoint desejado)
3. **Headers**: 
   - Adicione: `Content-Type: application/json`
4. **Body**: **IMPORTANTE** - Clique na aba **"Body"** (não "Params"!)
   - Selecione **"JSON"** como tipo
   - Cole o JSON dos dados

---

## 🍕 ITEM_CARDAPIO

### ✅ POST - Criar Item 1

**Configuração:**
- Método: `POST`
- URL: `http://localhost:8080/api/item-cardapio`
- Headers: `Content-Type: application/json`
- Body (JSON):
```json
{
  "nome": "Pizza Teste 1",
  "descricao": "Teste INSERT 1 - Pizza Margherita",
  "preco": 35,
  "categoria": "PRATO",
  "disponivel": true
}
```

### ✅ POST - Criar Item 2

**Body (JSON):**
```json
{
  "nome": "Suco Teste 2",
  "descricao": "Teste INSERT 2 - Suco de Laranja",
  "preco": 8.5,
  "categoria": "BEBIDA",
  "disponivel": true
}
```

### ✏️ PUT - Atualizar Item 1

**Configuração:**
- Método: `PUT`
- URL: `http://localhost:8080/api/item-cardapio/23` (substitua 23 pelo ID retornado)
- Headers: `Content-Type: application/json`
- Body (JSON):
```json
{
  "nome": "Pizza Teste 1 UPDATED",
  "descricao": "Teste UPDATE 1 - Pizza modificada",
  "preco": 38,
  "categoria": "PRATO",
  "disponivel": true
}
```

### ✏️ PUT - Atualizar Item 2

**URL**: `http://localhost:8080/api/item-cardapio/24` (substitua pelo ID)
**Body (JSON):**
```json
{
  "nome": "Suco Teste 2 UPDATED",
  "descricao": "Teste UPDATE 2 - Suco modificado",
  "preco": 9,
  "categoria": "BEBIDA",
  "disponivel": false
}
```

### 🗑️ DELETE - Remover Item 1

**Configuração:**
- Método: `DELETE`
- URL: `http://localhost:8080/api/item-cardapio/23` (sem Body necessário)

### 🗑️ DELETE - Remover Item 2

**URL**: `http://localhost:8080/api/item-cardapio/24`

---

## 🛒 PEDIDO

### ✅ POST - Criar Pedido 1

**Configuração:**
- Método: `POST`
- URL: `http://localhost:8080/api/pedido/comanda/1`
- Headers: `Content-Type: application/json`
- Body (JSON):
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

### ✅ POST - Criar Pedido 2

**URL**: `http://localhost:8080/api/pedido/comanda/2`
**Body (JSON):**
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

### ✏️ PUT - Atualizar Pedido 1 (com status)

**Configuração:**
- Método: `PUT`
- URL: `http://localhost:8080/api/pedido/23` (substitua 23 pelo ID retornado)
- Headers: `Content-Type: application/json`
- Body (JSON):
```json
{
  "itens": [
    {
      "itemCardapioId": 1,
      "quantidade": 2
    }
  ],
  "observacao": "Sem cebola, por favor",
  "status": "PRONTO"
}
```

### ✏️ PUT - Atualizar Pedido 2 (só observação)

**URL**: `http://localhost:8080/api/pedido/24` (substitua pelo ID)
**Body (JSON):**
```json
{
  "itens": [
    {
      "itemCardapioId": 2,
      "quantidade": 1
    }
  ],
  "observacao": "Teste UPDATE 2 - Observação modificada"
}
```

**💡 Dica:** O campo `status` é opcional. Se não enviar, o status permanece o mesmo. Pode atualizar itens, observação e/ou status tudo de uma vez.

### 🗑️ DELETE - Remover Pedido 1

**Configuração:**
- Método: `DELETE`
- URL: `http://localhost:8080/api/pedido/23`

### 🗑️ DELETE - Remover Pedido 2

**URL**: `http://localhost:8080/api/pedido/24`

---

## 📦 PEDIDO_ITEM

### ✅ POST - Criar Item de Pedido 1

**Configuração:**
- Método: `POST`
- URL: `http://localhost:8080/api/pedido-item`
- Headers: `Content-Type: application/json`
- Body (JSON):
```json
{
  "pedidoId": 25,
  "itemCardapioId": 3,
  "quantidade": 2,
  "precoUnitario": 7
}
```

### ✅ POST - Criar Item de Pedido 2

**Body (JSON):**
```json
{
  "pedidoId": 26,
  "itemCardapioId": 4,
  "quantidade": 3,
  "precoUnitario": 5
}
```

### ✏️ PUT - Atualizar Item de Pedido 1

**Configuração:**
- Método: `PUT`
- URL: `http://localhost:8080/api/pedido-item/27` (substitua pelo ID retornado)
- Headers: `Content-Type: application/json`
- Body (JSON):
```json
{
  "pedidoId": 25,
  "itemCardapioId": 3,
  "quantidade": 5,
  "precoUnitario": 7.5
}
```

### ✏️ PUT - Atualizar Item de Pedido 2

**URL**: `http://localhost:8080/api/pedido-item/28`
**Body (JSON):**
```json
{
  "pedidoId": 26,
  "itemCardapioId": 4,
  "quantidade": 4,
  "precoUnitario": 5.5
}
```

### 🗑️ DELETE - Remover Item de Pedido 1

**Configuração:**
- Método: `DELETE`
- URL: `http://localhost:8080/api/pedido-item/27`

### 🗑️ DELETE - Remover Item de Pedido 2

**URL**: `http://localhost:8080/api/pedido-item/28`

---

## ⚠️ CHECKLIST ANTES DE ENVIAR

- [ ] Aba **"Body"** selecionada (não "Params"!)
- [ ] Tipo selecionado: **"JSON"**
- [ ] Header `Content-Type: application/json` adicionado
- [ ] JSON válido (sem vírgulas nos decimais, use ponto: `35.5` ou `35`)
- [ ] URL correta com o método HTTP certo
- [ ] IDs existentes (anote os IDs retornados nos INSERTs)

---

## 💡 Dica Rápida

Se aparecer erro "Required request body is missing":
1. Clique na aba **"Body"**
2. Selecione **"JSON"**
3. Cole o JSON correto
4. Envie novamente

