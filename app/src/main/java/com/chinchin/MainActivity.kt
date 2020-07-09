package com.chinchin

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.Volley
import com.chinchin.model.Divisa
import com.chinchin.network.WebService
import com.chinchin.ui.exchange.ExchangeFragment
import com.chinchin.ui.qr.QrFragment
import com.chinchin.ui.logout.LogoutFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "mychinchin"
    }

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

                //TODO aqui va el logout
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

//        val ws = WebService.newInstance(this)
//        ws.getAll(object: WebService.ICallback {
//            override fun done(divisa: Divisa) {
//                // do nothing
//            }
//        })
        Divisa.getInstance().addFakeData()

        Log.d(TAG, "MainActivity created")

    }

    private fun replaceFragment(fragment: Fragment) {
        Log.d(TAG, "MainActivity -> replaceFregment() invoked to ${fragment.toString()}")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()

    }
}