package com.ossmurfy.bravo4fun.model

data class Produto(
    var PRODUTO_ID: Int,
    var PRODUTO_NOME: String,
    var PRODUTO_DESC: String,
    var PRODUTO_DESCONTO: Double,
    var CATEGORIA_ID: Int,
    var PRODUTO_ATIVO: Int,
    var PRODUTO_PRECO: String,
    var PRODUTO_IMAGEM: List<String>,
)
