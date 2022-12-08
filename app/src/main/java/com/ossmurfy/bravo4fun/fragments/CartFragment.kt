package com.ossmurfy.bravo4fun.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.FragmentCartBinding
import com.ossmurfy.bravo4fun.databinding.FragmentEventsBinding
import com.ossmurfy.bravo4fun.databinding.ItemCartBinding
import com.ossmurfy.bravo4fun.model.Cart
import com.ossmurfy.bravo4fun.model.CartResponse
import com.ossmurfy.bravo4fun.model.PedidoResponse
import com.ossmurfy.bravo4fun.service.API
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater)

        binding.finalizar.setOnClickListener {
            finalizaCompra()
        }
        atualizarProdutos()


        return binding.root
    }

    fun atualizarProdutos() {


        val callback = object : Callback<CartResponse> {


            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {

                if (activity == null) {
                    return
                }
                desabilitarCarregamento()

                if (response.isSuccessful) {
                    val listaProdutos = response.body()
                    atualizarUI(listaProdutos?.data)
                }
                else {


                    Log.e("ERROR", response.errorBody().toString())
                }
            }


            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                desabilitarCarregamento()

                Log.e("ERROR", "Falha ao executar serviço", t)
            }
        }

        //Faz a chamada a API
        API().cart.listCart(61).enqueue(callback)


        //Chama uma função para habilitar o carregamento
        habilitarCarregamento()
    }

    private fun desabilitarCarregamento() {
        binding.swipe.isRefreshing = false

    }

    fun finalizaCompra(){
        val callback = object : Callback<PedidoResponse>{
            override fun onResponse(
                call: Call<PedidoResponse>,
                response: Response<PedidoResponse>
            ) {
                if (activity == null){
                    return
                }
                if( response.isSuccessful){
                    AlertaSucesso()
                }
            }

            override fun onFailure(call: Call<PedidoResponse>, t: Throwable) {
                AlertaFalha()
            }
        }
        API().pedido.inserirPedido(61).enqueue(callback)
    }


    private fun habilitarCarregamento() {
        binding.swipe.isRefreshing = true
    }

    fun atualizarUI(lista: List<Cart>?) {
        //Limpa a lista de itens
        binding.listView.removeAllViews()

        //Itera pela lista de respostas
        lista?.forEach {
            //ELEMENTOS DINÂMICOS
            //Cria um cartão dinamicamente
            val cardBinding = ItemCartBinding.inflate(layoutInflater)

            //Configura os itens do cartão com os valores do
            //item do array
            cardBinding.ProdutoTitulo.text = it.PRODUTO_NOME
            cardBinding.ProdutoPreco.text = "Valor total: R$ " + it.PRODUTO_PRECO_TOTAL
            cardBinding.ProdutoQuant.text = "Quantidade: " + it.ITEM_QTD.toString()

            /*Picasso.get().load(
            ""
            ).placeholder(R.drawable.no_image).error(R.drawable.no_image).into(cardBinding.image)*/

            cardBinding.root.setOnClickListener { cartao ->
                val frag = ProductFragment(it.PRODUTO_ID)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, frag)?.addToBackStack("Detalhe do Produto")?.commit()
            }

            //Adiciona o cartão no container para que apareça na tela
            binding.listView.addView(cardBinding.root)

        }
    }

    fun AlertaSucesso(){
        AlertDialog.Builder(context)
            .setTitle("Sucesso!")
            .setMessage("Compra realizada com sucesso.")
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
    fun AlertaFalha(){
        AlertDialog.Builder(context)
            .setTitle("Falha!")
            .setMessage("Algo de errado aconteceu no caminho. :(")
            .setPositiveButton("OK", null)
            .create()
            .show()
    }



}