package com.chinchin.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.chinchin.model.Divisa

class WebService {
    private val url = "https://www.binance.com/exchange-api/v1/public/asset-service/product/get-products"
    private lateinit var queue: RequestQueue

    companion object {
        fun newInstance(context: Context): WebService {
            val ws = WebService()
            ws.queue = Volley.newRequestQueue(context)

            return ws
        }
    }

    fun getAll(callback: ICallback): Unit {

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //Log.d("mychinchin", "Response is: ${response.toString().substring(0,500)}")
                var divisas: MutableList<Divisa> = ArrayList<Divisa>()

                val data = response.getJSONArray("data")
                for(idx in 0..data.length()-1) {
                    val dataObj = data.getJSONObject(idx)

                    var b = dataObj.getString("b")
                    var q = dataObj.getString("q")
                    var i = dataObj.getString("i")
                    divisas.add(Divisa(b, q, i))
                }
                callback.data(divisas)

            },
            Response.ErrorListener { error ->
                Log.d("mychinchin", "Response is: ${error.message}")
            })

        queue.add(jsonObjectRequest)

    }


    interface ICallback {
        fun data(divisas: List<Divisa>): Unit
    }


}