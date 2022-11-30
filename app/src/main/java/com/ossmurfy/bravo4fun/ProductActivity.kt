package com.ossmurfy.bravo4fun

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ossmurfy.bravo4fun.databinding.ActivityProductBinding
import com.ossmurfy.bravo4fun.service.ProdutoService
import com.ossmurfy.bravo4fun.model.Produto
import com.ossmurfy.bravo4fun.model.ProdutoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun chamarApiListProd() {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://oficinacordova.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofit.create(ProdutoService::class.java)


        val chamada = service.listAll()


        val callback = object : Callback<ProdutoResponse> {
            override fun onResponse(call: Call<ProdutoResponse>, response: Response<ProdutoResponse>) {
                if (response.isSuccessful) {
                    val listaProduto = response.body()

                    val nomeProduto = listaProduto?.data?.first()?.PRODUTO_NOME

                    alert("Sucesso", "Nome do Primeiro Produto: $nomeProduto")
                } else {
                    alert("Erro", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ProdutoResponse>, t: Throwable) {
               alert("Erro", t.message.toString())
            }
        }
        chamada.enqueue(callback)
    }

    fun alert(titulo: String, msg: String){
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(msg)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
}
