package com.ossmurfy.bravo4fun.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.EventCardBinding
import com.ossmurfy.bravo4fun.databinding.FragmentEventsBinding
import com.ossmurfy.bravo4fun.model.Produto
import com.ossmurfy.bravo4fun.model.ProdutoResponse
import com.ossmurfy.bravo4fun.service.API
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EventsFragment : Fragment() {

    lateinit var binding: FragmentEventsBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventsBinding.inflate(inflater)

        atualizarProdutos()

        binding.swipe.setOnRefreshListener {
            atualizarProdutos()
        }

        return binding.root
    }

    fun atualizarProdutos() {

        //Callback acionado quando a execução da API concluir
        val callback = object : Callback<ProdutoResponse> {

            //Chamada quando o endpoint responder
            override fun onResponse(call: Call<ProdutoResponse>, response: Response<ProdutoResponse>) {

                if (activity == null) {
                    return
                }

                desabilitarCarregamento()

                if (response.isSuccessful) {
                    val produtosResponse  = response.body()
                    atualizarUI(produtosResponse?.data)
                    desabilitarCarregamento()
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
            override fun onFailure(call: Call<ProdutoResponse>, t: Throwable) {
                desabilitarCarregamento()

                //Snackbar.make(binding.container, "Não foi possível se conectar ao servidor",
                    //Snackbar.LENGTH_LONG).show()

                Log.e("ERROR", "Falha ao executar serviço", t)
            }
        }

        //Faz a chamada a API
        API().produto.listAll().enqueue(callback)

        //Chama uma função para habilitar o carregamento
        habilitarCarregamento()
    }

    private fun desabilitarCarregamento() {
        binding.swipe.isRefreshing = false

    }


    private fun habilitarCarregamento() {
        binding.swipe.isRefreshing = true
    }

    fun atualizarUI(lista: List<Produto>?) {
        //Limpa a lista de itens
        binding.container.removeAllViews()

        //Itera pela lista de respostas
        lista?.forEach {
            //ELEMENTOS DINÂMICOS
            //Cria um cartão dinamicamente
            val cardBinding = EventCardBinding.inflate(layoutInflater)

            //Configura os itens do cartão com os valores do
            //item do array
            cardBinding.textTitulo.text = it.PRODUTO_NOME
            cardBinding.textDesc.text = it.PRODUTO_DESC
            if(it.PRODUTO_IMAGEM != null && it.PRODUTO_IMAGEM.size > 0) {


                var imagem = it.PRODUTO_IMAGEM[0]

                //Solicita o carregamento da imagem
                Picasso.get().load(
                    "$imagem"
                ).placeholder(R.drawable.no_image).error(R.drawable.no_image)
                    .into(cardBinding.imagem)
            }

            cardBinding.root.setOnClickListener { cartao ->
                val frag = ProductFragment(it.PRODUTO_ID)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, frag)?.addToBackStack("Detalhe do Produto")?.commit()
            }

            //Adiciona o cartão no container para que apareça na tela
            binding.container.addView(cardBinding.root)
        }
    }


}