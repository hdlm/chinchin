package com.chinchin.model

import android.annotation.SuppressLint
import android.util.Log
import com.chinchin.MainActivity

class Divisa
{
    protected var divisas: MutableList<DiferencialCambiario>? = null

    init {
        divisas = ArrayList<DiferencialCambiario>()
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        private var instancia: Divisa? = null

        public fun getInstance(): Divisa {
            if (instancia == null) {
                instancia = Divisa()
            }
            return instancia as Divisa
        }
    }


    /**
     * La funcion agrega un nuevo diferencial cambiario a la collection de divisas
     */
    fun add(orig:String, dest:String, rate:String):Unit {
        Log.d(MainActivity.TAG, "Divisa -> add moneda ${orig} > ${dest} = ${rate}")
        var moneda = DiferencialCambiario(orig, dest, rate.toDouble())
        divisas!!.add(moneda)

    }

    /**
     * El metodo tiene el proposito de cargar data de prueba
     */
    fun addFakeData() {
        Log.d(MainActivity.TAG, "Divisa -> load fake data for testing")
        divisas!!.add(DiferencialCambiario("PTR","USD", 60.0))
        divisas!!.add(DiferencialCambiario("PTR","ETH", 3.0))
        divisas!!.add(DiferencialCambiario("PTR","BTC", 2.0))
        divisas!!.add(DiferencialCambiario("PTR","PTR", 1.0))
        divisas!!.add(DiferencialCambiario("PTR","BS", 100000.0))
        divisas!!.add(DiferencialCambiario("PTR","EURO", 50.0))

    }

    /**
     * La funcion retorna el rate correspondiente al tipo de divisa especificado
     * @param tipoDivisa (USD,VE,BTC,...)
     * @return valor double correspondiente a la tasa de cambio, en caso de no encontrarse retornara null
     */
    fun getRate(tipoDivisa: String): Double? {

        divisas!!.forEach { tpoCambiario ->
            if (tpoCambiario.dest.equals(tipoDivisa,true)) {
                Log.d(MainActivity.TAG, "Divisa -> se encontro la divisa: ${tipoDivisa}")
                return tpoCambiario.rate
            }
        }
        return null

    }

    /**
     * La clase representa un diferencial cambio, contiene la moneda de origen, moneda destino, y
     * el diferencial cambiario de esa conversion.
     * Por ejm, para cambiar de Bolivares(origen) a USD(destino), el factor de conversion sera 220.000
     */
    data class DiferencialCambiario(val orig:String, val dest: String, var rate: Double)
}
