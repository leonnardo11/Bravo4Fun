package com.ossmurfy.bravo4fun.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.ossmurfy.bravo4fun.databinding.FragmentEventsBinding
import com.ossmurfy.bravo4fun.databinding.ItemCarrinhoBinding
import com.ossmurfy.bravo4fun.model.Cart
import com.ossmurfy.bravo4fun.model.CartResponse
import com.ossmurfy.bravo4fun.model.Produto
import com.ossmurfy.bravo4fun.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EventsFragment : Fragment() {
    lateinit var binding: FragmentEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater)

        atualizarCarrinho()

        return binding.root
    }

    fun atualizarCarrinho() {

        //Callback acionadoo quando a execução da API concluir
        val callback = object : Callback<CartResponse> {

            //Chamada quando o endpoint responder
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {

                //desabilitarCarregamento()

                if (response.isSuccessful) {
                    val cartResponse = response.body()
                    atualizarUI(cartResponse?.Carrinho)
                }
                else {
                    //val error = response.errorBody().toString()
                    Snackbar.make(binding.container, "Não é possível autualizar o carrinho",
                        Snackbar.LENGTH_LONG).show()

                    val erro = response.errorBody()?.string()
                    erro?.let {
                        Log.e("ERROR", it)
                    }
                }
            }

            //Chamada caso aconteça algum problema e não seja possível bater no endpoint
            //Ou a resposta seja incompatível
            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                //desabilitarCarregamento()

                Snackbar.make(binding.container, "Não foi possível se conectar ao servidor",
                    Snackbar.LENGTH_LONG).show()

                Log.e("ERROR", "Falha ao executar serviço", t)
            }
        }

        //Faz a chamada a API
        API().cart.listar().enqueue(callback)

        //Chama uma função para habilitar o carregamento
        //habilitarCarregamento()
    }

    fun atualizarUI(lista: List<Cart>?) {
        //Limpa a lista de itens
        binding.container.removeAllViews()

        //Itera pela lista de respostas
        lista?.forEach {
            //ELEMENTOS DINÂMICOS
            //Cria um cartão dinamicamente
            val cardBinding = ItemCarrinhoBinding.inflate(layoutInflater)

            //Configura os itens do cartão com os valores do
            //item do array
            cardBinding.textNome.text = it.PRODUTO_ID.toString()

            //Solicita o carregamento da imagem
//            Picasso.get().load(
//                "https://oficinacordova.azurewebsites.net/android/rest/produto/image/${it.}"
//            ).into(cardBinding.productImageView1)

//            cardBinding.root.setOnClickListener { cartao ->
//                val frag = DetalheProdutoFragment(it.idProduto)
//                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, frag)?.addToBackStack("Detalhe do Produto")?.commit()
//            }

            //Adiciona o cartão no container para que apareça na tela
            binding.container.addView(cardBinding.root)
        }
    }


}