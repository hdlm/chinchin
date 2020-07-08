package com.chinchin.ui.exchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chinchin.R
import kotlinx.android.synthetic.main.fragment_exchange.*

class ExchangeFragment : Fragment() {

    //private lateinit var viewModel: ExchangeViewModel
    private val viewModel by viewModels<ExchangeViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //viewModel =
        //        ViewModelProvider(this).get(ExchangeViewModel::class.java)
        //val root = inflater.inflate(R.layout.fragment_exchange, container, false)
        //val textView: TextView = root.findViewById(R.id.tasaTextView)
        //viewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this).get(ExchangeViewModel::class.java)

        resultadoTextView.text = getString(R.string.exchange_fragment_resultado_textview_no_value)

        val resultadoObserver = Observer<Double> { result ->
            resultadoTextView.text = result.toString()
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
        divisaSpinner.setSelection(0)  // seleccion por default
        divisaSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }

            override fun onItemSelected( parent: AdapterView<*>?, view: View?, position: Int, id: Long ) {
                //TODO consultar el endpoint para obtener el valor correspondiente a la divisa seleccionada
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