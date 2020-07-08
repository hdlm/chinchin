package com.chinchin.ui.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chinchin.R

class QrFragment : Fragment() {

    private lateinit var qrViewModel: QrViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        qrViewModel =
                ViewModelProvider(this).get(QrViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_qr, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        qrViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    companion object {
        fun newInstance(): QrFragment {
            return QrFragment()
        }
    }
}