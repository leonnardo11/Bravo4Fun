package com.ossmurfy.bravo4fun.model

data class PedidoResponse(
    var status: Int,
    var message: String,
    var data: List<Pedido>
)
