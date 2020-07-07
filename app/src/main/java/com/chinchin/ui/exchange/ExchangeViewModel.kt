package com.chinchin.ui.exchange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExchangeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Exchange Fragment"
    }
    val text: LiveData<String> = _text

    private var result: MutableLiveData<Double> = MutableLiveData()
    private var monto = 0.0
    private var tipoRate = "usd"
    private var valorRate = 205000.0
    private var resultRate = 0.0

    fun setMonto(monto: String):Unit {
        this.monto = monto.toDouble()
        result.value = monto.toDouble() * valorRate
    }

    fun getResultado(): MutableLiveData<Double> {
        return result
    }

}