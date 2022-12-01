package com.ossmurfy.bravo4fun.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.ossmurfy.bravo4fun.model.Produto
import com.ossmurfy.bravo4fun.BottomNavigationActivity
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.CardProductBinding
import com.ossmurfy.bravo4fun.databinding.EventCardBinding
import com.ossmurfy.bravo4fun.databinding.FragmentEventsBinding
import com.ossmurfy.bravo4fun.databinding.FragmentProductBinding
import com.ossmurfy.bravo4fun.model.CartResponse
import com.ossmurfy.bravo4fun.model.ProdutoResponse
import com.ossmurfy.bravo4fun.model.VerProdutoResponse
import com.ossmurfy.bravo4fun.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat

@Suppress("UNREACHABLE_CODE")
class ProductFragment (val idProdutoo: Int) : Fragment() {

    lateinit var binding: FragmentProductBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductBinding.inflate(inflater)

        binding.buyButton.setOnClickListener {
            addCarrinho()
        }

        atualizaProduto(inflater)



        return binding.root


    }

    fun atualizaProduto(inflater: LayoutInflater) {

        //Callback acionado quando a execução da API concluir
        val callback = object : Callback<VerProdutoResponse> {

            //Chamada quando o endpoint responder
            override fun onResponse(call: Call<VerProdutoResponse>, response: Response<VerProdutoResponse>) {
                if (activity == null) {
                    return
                }
                desabilitarCarregamento()

                if (response.isSuccessful) {
                    val produtoResponse  = response.body()
                    atualizarUI(produtoResponse?.data, inflater)
                }
                else {
                    val error = response.errorBody().toString()
                    //Snackbar.make(binding.container, "Não é possível atualizar os produtos",
                    //Snackbar.LENGTH_LONG).show()

                    Log.e("ERROR", response.errorBody().toString())
                }
            }


            override fun onFailure(call: Call<VerProdutoResponse>, t: Throwable) {
                desabilitarCarregamento()

                //Snackbar.make(binding.container, "Não foi possível se conectar ao servidor",
                //Snackbar.LENGTH_LONG).show()

                Log.e("ERROR", "Falha ao executar serviço", t)
            }
        }

        //Faz a chamada a API
        API().produto.list(idProdutoo).enqueue(callback)

        //Chama uma função para habilitar o carregamento
        habilitarCarregamento()
    }

    fun addCarrinho(){

        val callback = object : Callback<CartResponse> {

            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if( response.isSuccessful ) {
                    AlertaSucesso()
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                AlertaFalha()
            }
        }

        API().cart.inserir(idProdutoo, 1, 30).enqueue(callback)

    }

    private fun desabilitarCarregamento() {
        binding.swipe.isRefreshing = false

    }


    private fun habilitarCarregamento() {
        binding.swipe.isRefreshing = true
    }

    fun atualizarUI(produto: Produto?, inflater: LayoutInflater) {
            produto?.let{
                binding.titleTextView.text = it.PRODUTO_NOME
                binding.descriptionTextView.text = it.PRODUTO_DESC
                binding.priceTextView.text = "R$ " + it.PRODUTO_PRECO

            }

    }

    fun AlertaSucesso(){
            AlertDialog.Builder(context)
                .setTitle("Sucesso!")
                .setMessage("Produto adicionado ao carrinho.")
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