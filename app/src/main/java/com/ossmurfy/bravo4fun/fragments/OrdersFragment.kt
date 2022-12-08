package com.ossmurfy.bravo4fun.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.FragmentCartBinding
import com.ossmurfy.bravo4fun.databinding.FragmentOrdersBinding
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

        //atualizaProdutos()
        return (binding.root)
    }

    /*fun atualizaProdutos(){
        val callback = object : Callback<PedidoResponse> {

            override fun onResponse(
                call: Call<PedidoResponse>,
                response: Response<PedidoItem>
            )
            {
                if (activity == null) {
                    return
                }

                if (response.isSuccessful){
                    var responseBody = response.body()
                    //atualizarUI(responseBody?.PRODUTO)
                }
            }

            override fun onFailure(call: Call<PedidoResponse>, t: Throwable) {

            }
        }
        API().pedido.listPedidos(45).enqueue(callback)
    }*/


}