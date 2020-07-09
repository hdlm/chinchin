package com.chinchin.ui.exchange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chinchin.model.Divisa

class ExchangeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Exchange Fragment"
    }
    val text: LiveData<String> = _text

    private var result: MutableLiveData<Double> = MutableLiveData()
    private var monto = 0.0
    private var tipoRate = "usd"
    private var valorRate = 205000.0


    fun setMonto(monto: String):Unit {
        this.monto = monto.toDouble()
        result.value = monto.toDouble() * valorRate
    }

    /**
     * La funcion cambia el rate correspondiente al tipo de divisa especificado
     * @param tipoDivisa  (VE,USD,BTC,..)
     * @return nada pero en caso de no encontrarse la divisa indicada el rate valdra 0.0
     */
    fun setRate(tipoDivisa: String): Unit {

        tipoRate = tipoDivisa

        val rate = Divisa.getInstance().getRate(tipoDivisa)
        rate ?.let {
            valorRate = rate
        } ?: run {
            valorRate = 0.0
        }

    }

    fun getResultado(): MutableLiveData<Double> {
        return result
    }

}