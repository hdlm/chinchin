package com.chinchin

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()

    }
}