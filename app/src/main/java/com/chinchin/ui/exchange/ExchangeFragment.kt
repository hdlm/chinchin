package com.chinchin.ui.exchange

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.chinchin.MainActivity
import com.chinchin.R
import kotlinx.android.synthetic.main.fragment_exchange.*
import java.text.DecimalFormat
import java.text.NumberFormat

class ExchangeFragment : Fragment() {

    //private lateinit var viewModel: ExchangeViewModel
    private val viewModel by viewModels<ExchangeViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: " + this.javaClass.simpleName )

        resultadoTextView.text = getString(R.string.exchange_fragment_resultado_textview_no_value)

        val resultadoObserver = Observer<Double> { result ->
            val formatter: DecimalFormat = DecimalFormat("#,##0.00")
            resultadoTextView.text = formatter.format(result)
        }
        viewModel.getResultado().observe(viewLifecycleOwner, resultadoObserver)

        // Setting Adapter to Spinners
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.divisas_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            divisaSpinner.adapter = adapter
        }

        viewModel.setRate("usd")
        divisaSpinner.setSelection(0)  // seleccion por default
        divisaSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {

            override fun onItemSelected( parent: AdapterView<*>?, view: View?, position: Int, id: Long ) {
                var tipoDivisa = divisaSpinner.selectedItem.toString()
                viewModel.setRate(tipoDivisa)
                Log.d(MainActivity.TAG, "ExchangeFragment -> divisaSpinner change to: ${tipoDivisa}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }

        }

        convertButton.setOnClickListener {
            if (montoText.text.isNotEmpty()) {
                viewModel.setMonto(montoText.text.toString())
            } else {
                resultadoTextView.text = getString(R.string.exchange_fragment_resultado_textview_no_value)
            }
        }

    }

    companion object {
        fun newInstance(): ExchangeFragment {
            return ExchangeFragment()
        }
    }
}