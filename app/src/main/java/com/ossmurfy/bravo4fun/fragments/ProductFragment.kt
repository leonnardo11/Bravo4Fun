package com.ossmurfy.bravo4fun.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossmurfy.bravo4fun.model.Produto
import com.ossmurfy.bravo4fun.BottomNavigationActivity
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.CardProductBinding
import com.ossmurfy.bravo4fun.databinding.EventCardBinding
import com.ossmurfy.bravo4fun.databinding.FragmentEventsBinding
import com.ossmurfy.bravo4fun.databinding.FragmentProductBinding
import com.ossmurfy.bravo4fun.model.ProdutoResponse
import com.ossmurfy.bravo4fun.model.VerProdutoResponse
import com.ossmurfy.bravo4fun.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductFragment (val idProdutoo: Int) : Fragment() {

    lateinit var binding: FragmentProductBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater)

        atualizaProduto(inflater)

        return binding.root
    }

    fun atualizaProduto(inflater: LayoutInflater) {

        //Callback acionado quando a execução da API concluir
        val callback = object : Callback<VerProdutoResponse> {

            //Chamada quando o endpoint responder
            override fun onResponse(call: Call<VerProdutoResponse>, response: Response<VerProdutoResponse>) {

                //desabilitarCarregamento()

                if (response.isSuccessful) {
                    val produtoResponse  = response.body()
                    atualizarUI(produtoResponse?.produto, inflater)
                }
                else {
                    //val error = response.errorBody().toString()
                    //Snackbar.make(binding.container, "Não é possível autualizar os produtos",
                    //Snackbar.LENGTH_LONG).show()

                    Log.e("ERROR", response.errorBody().toString())
                }
            }

            //Chamada caso aconteça algum problema e não seja possível bater no endpoint
            //Ou a resposta seja incompatível
            override fun onFailure(call: Call<VerProdutoResponse>, t: Throwable) {
                //desabilitarCarregamento()

                //Snackbar.make(binding.container, "Não foi possível se conectar ao servidor",
                //Snackbar.LENGTH_LONG).show()

                Log.e("ERROR", "Falha ao executar serviço", t)
            }
        }

        //Faz a chamada a API
        API().produto.list(idProdutoo).enqueue(callback)

        //Chama uma função para habilitar o carregamento
        //habilitarCarregamento()
    }

    fun atualizarUI(produto: Produto?, inflater: LayoutInflater) {
        //Limpa a lista de itens


        //Itera pela lista de respostas

            //ELEMENTOS DINÂMICOS
            //Cria um cartão dinamicamente
            produto?.let{
                binding.titleTextView.text = it.PRODUTO_NOME
                binding.descriptionTextView.text = it.PRODUTO_DESC
                binding.priceTextView.text = it.PRODUTO_PRECO
            }
    }
}