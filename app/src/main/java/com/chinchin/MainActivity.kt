package com.chinchin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.chinchin.model.Divisa
import com.chinchin.network.WebService
import com.chinchin.ui.exchange.ExchangeFragment
import com.chinchin.ui.qr.QrFragment
import com.chinchin.ui.logout.LogoutFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.navigation_exchange -> {
                replaceFragment(ExchangeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_qr -> {
                replaceFragment(QrFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_logout -> {
                replaceFragment(LogoutFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(ExchangeFragment.newInstance())
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



        val queue = Volley.newRequestQueue(this)
        val url = "https://www.binance.com/exchange-api/v1/public/asset-service/product/get-products"
        //val url = "https://www.google.com"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//        Response.Listener<String> { response ->
//            Toast.makeText(this, "Response is: ${response.substring(0,500)}", Toast.LENGTH_LONG).show()
//            Log.d("mychinchin", "Response is: ${response.substring(0,500)}")
//
//        },
//        Response.ErrorListener {
//            Toast.makeText(this, "Response is: ERROR", Toast.LENGTH_LONG).show()
//            Log.d("mychinchin", "Response is: ERROR")
//
//        })

//        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
//        Response.Listener { response ->
//            Toast.makeText(this, "Response is: ${response.toString().substring(0,500)}", Toast.LENGTH_LONG).show()
//            Log.d("mychinchin", "Response is: ${response.toString().substring(0,500)}")
//        },
//        Response.ErrorListener { error ->
//            Log.d("mychinchin", "Response is: ${error.message}")
//        })
//
//
//        queue.add(jsonObjectRequest)

        val ws = WebService.newInstance(this)
        ws.getAll(object: WebService.ICallback {
            override fun data(divisas: List<Divisa>) {
                val valor = divisas.size
            }
        })


    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()

    }
}