package com.chinchin.ui.exchange

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Window
import android.widget.ImageView
import com.chinchin.MainActivity
import com.chinchin.R
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class QrPrintDialog {

    fun showDialog(context: Context, msg: String) {
        Log.d(MainActivity.TAG, "view created: " + this.javaClass.simpleName )

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.qr_dialog_layout)

        val qr = dialog.findViewById<ImageView>(R.id.qrImageView)
        generateBarcode(msg, qr)

        qr.setOnClickListener { _ ->
            dialog.dismiss()
        }

        dialog.show()

    }

    /**
     * El metodo se encarga de generar el codigo QR con la cadena de texto recibida
     * @param msg cadena de texto con el que se generara el codigo QR
     * @return nada, pero muestra el la imagen del QR en pantalla
     */
    private fun generateBarcode(msg:String, barcodeImageView: ImageView):Unit {
        try {
            val encoder = BarcodeEncoder()
            val bitmap = encoder.encodeBitmap(msg, BarcodeFormat.QR_CODE, 500,500)
            barcodeImageView.setImageBitmap(bitmap)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * El metodo prepara la cadena de texto que se utilizara para elaborar el QR
     * @param fiatOrig FIAT de origen
     * @param fiatDest FIAT a convertir
     * @param monto  a convertir
     */
    fun prepareMessage(fiatOrig: String, fiatDest: String, monto: String ):String {
        val msg = "FIAT_ORG:" + fiatOrig + ";FIAT_DST:" + fiatDest + ";MONTO:" + monto + ";;"
        return msg

    }

}