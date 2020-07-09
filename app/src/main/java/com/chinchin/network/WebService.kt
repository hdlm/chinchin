package com.chinchin.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.chinchin.MainActivity
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
                Log.d(MainActivity.TAG, "WebService -> getAll(), response is: ${response.toString().substring(0,500)}")
                val divisa = Divisa.getInstance()

                val data = response.getJSONArray("data")
                for(idx in 0..data.length()-1) {
                    val dataObj = data.getJSONObject(idx)

                    var b = dataObj.getString("b")
                    var q = dataObj.getString("q")
                    var i = dataObj.getString("i")
                    divisa.add(b, q, i)
                }
                callback.done(divisa)

            },
            Response.ErrorListener { error ->
                Log.d(MainActivity.TAG, "WebService -> Response is: ${error.message}")
            })

        queue.add(jsonObjectRequest)

    }


    interface ICallback {
        fun done(divisa:Divisa): Unit
    }


}