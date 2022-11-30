package com.ossmurfy.bravo4fun.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.FragmentCartBinding
import com.ossmurfy.bravo4fun.databinding.ItemCartBinding
import com.ossmurfy.bravo4fun.model.Cart
import com.ossmurfy.bravo4fun.model.CartResponse
import com.ossmurfy.bravo4fun.service.API
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding


    fun atualizarProdutos(inflater: LayoutInflater) {

        //Callback acionado quando a execução da API concluir
        val callback = object : Callback<CartResponse> {

            //Chamada quando o endpoint responder
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {

                //desabilitarCarregamento()

                if (response.isSuccessful) {
                    val listaProdutos = response.body()
                    atualizarUI(listaProdutos?.data, inflater)
                }
                else {
                    //val error = response.errorBody().toString()
                    //Snackbar.make(binding.container, "Não é possível autualizar os produtos",
                    //Snackbar.LENGTH_LONG).show()

                    //Log.e("ERROR", response.errorBody().toString())
                }
            }

            //Chamada caso aconteça algum problema e não seja possível bater no endpoint
            //Ou a resposta seja incompatível
            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                //desabilitarCarregamento()

                //Snackbar.make(binding.container, "Não foi possível se conectar ao servidor",
                //Snackbar.LENGTH_LONG).show()

                //Log.e("ERROR", "Falha ao executar serviço", t)
            }
        }

        //Faz a chamada a API
        API().cart.listCart(28).enqueue(callback)

        //Chama uma função para habilitar o carregamento
        //habilitarCarregamento()
    }

    fun atualizarUI(lista: List<Cart>?, inflater: LayoutInflater) {
        //Limpa a lista de itens
        binding.container.removeAllViews()

        //Itera pela lista de respostas
        lista?.forEach {
            //ELEMENTOS DINÂMICOS
            //Cria um cartão dinamicamente
            val cardBinding = ItemCartBinding.inflate(inflater)

            //Configura os itens do cartão com os valores do
            //item do array
            cardBinding.ProdutoTitulo.text = it.PRODUTO_NOME
            cardBinding.ProdutoQuant.text = "Quantidade: " + it.ITEM_QTD.toString()
            cardBinding.ProdutoPreco.text = "R$: " + it.PRODUTO_PRECO.toString()


            Picasso.get().load(
            ""
            ).placeholder(R.drawable.no_image).error(R.drawable.no_image).into(cardBinding.image)

            //Adiciona o cartão no container para que apareça na tela
            binding.container.addView(cardBinding.root))

        }
    }




}