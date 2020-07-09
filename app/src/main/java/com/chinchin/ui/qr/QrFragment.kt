package com.chinchin.ui.qr

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chinchin.MainActivity
import com.chinchin.R
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.fragment_qr.*

class QrFragment : Fragment() {

    private val viewModel by viewModels<QrViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_qr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: " + this.javaClass.simpleName )

        generateButton.setOnClickListener {
            try {
                val encoder = BarcodeEncoder()
                val bitmap = encoder.encodeBitmap(qrText.text.toString(), BarcodeFormat.QR_CODE, 500,500)
                barcodeImageView.setImageBitmap(bitmap)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    companion object {
        fun newInstance(): QrFragment {
            return QrFragment()
        }
    }
}