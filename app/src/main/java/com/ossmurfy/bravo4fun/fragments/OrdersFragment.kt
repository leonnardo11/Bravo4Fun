package com.ossmurfy.bravo4fun.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.FragmentCartBinding
import com.ossmurfy.bravo4fun.databinding.FragmentOrdersBinding
import com.ossmurfy.bravo4fun.databinding.OrdersCardBinding
import com.ossmurfy.bravo4fun.model.Pedido
import com.ossmurfy.bravo4fun.model.PedidoItem
import com.ossmurfy.bravo4fun.model.PedidoResponse
import com.ossmurfy.bravo4fun.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrdersFragment : Fragment(){

    lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrdersBinding.inflate(inflater)

        atualizaProdutos()
        return (binding.root)
    }

    fun atualizaProdutos(){
        val callback = object : Callback<PedidoResponse> {

            override fun onResponse(call: Call<PedidoResponse>, response: Response<PedidoResponse>)
            {
                if(activity == null){
                    return
                }
                desabilitarCarregamento()
                if (response.isSuccessful){
                    val pedidos = response.body()
                    atualizarUI(pedidos?.data)
                }
            }

            override fun onFailure(call: Call<PedidoResponse>, t: Throwable) {
                alertaFalha()
                desabilitarCarregamento()
            }
        }
        API().pedido.listPedidos(62).enqueue(callback)
        habilitarCarregamento()
    }

    fun atualizarUI(data: List<Pedido>?){
        binding.listView.removeAllViews()

        data?.forEach {
            val cardBinding = OrdersCardBinding.inflate(layoutInflater)

            cardBinding.pedidoid.text = "Pedido: " + it.PEDIDO_ID.toString()
            cardBinding.data.text = "Data: " + it.PEDIDO_DATA

            it.ITEM.forEach {

                cardBinding.total.text = "Total: " + it.ITEM_PRECO
                cardBinding.quant.text = it.ITEM_QTD.toString() + " Unidades"

                it.PRODUTO.forEach {
                    cardBinding.produto.text = it.PRODUTO_NOME
                }
            }
            binding.listView.addView(cardBinding.root)
        }
    }
    private fun desabilitarCarregamento() {
        binding.swipe.isRefreshing = false

    }


    private fun habilitarCarregamento() {
        binding.swipe.isRefreshing = true
    }

    fun alertaFalha(){
        if(activity == null){
            return
        }
        AlertDialog.Builder(context)
            .setTitle("Falha!")
            .setMessage("Algo de errado aconteceu no caminho.")
            .setPositiveButton("OK", null)
            .create()
            .show()
    }


}