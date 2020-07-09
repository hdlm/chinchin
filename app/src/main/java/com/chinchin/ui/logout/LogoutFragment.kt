package com.chinchin.ui.logout

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chinchin.MainActivity
import com.chinchin.R
import java.lang.IllegalStateException

class LogoutFragment : DialogFragment() {

    private val DIALOG_FRAGMENT = 1
    private lateinit var logoutViewModel: LogoutViewModel

//    override fun onCreateView(
//            inflater: LayoutInflater,
//            container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        logoutViewModel =
//                ViewModelProvider(this).get(LogoutViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_logout, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        logoutViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root
//    }

    companion object {
        fun newInstance(): LogoutFragment {
            return LogoutFragment()
        }
    }


    var mCallback: InterfaceCommunicator? = null

    interface InterfaceCommunicator {
        fun sendRequestCode(resultCode: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(MainActivity.TAG, "view created: " + this.javaClass.simpleName )

        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            //var mAuth = FirebaseAuth.getInstance()

            builder.setMessage(R.string.logout_dialog_message)
                .setPositiveButton(R.string.logout_dialog_button_yes,
                    DialogInterface.OnClickListener { _, _ ->
                        // User accepted
                        mCallback?.sendRequestCode(Activity.RESULT_OK)
                        System.exit(0)
                        //dismiss()
                    })
                .setNegativeButton(R.string.logout_dialog_button_no,
                    DialogInterface.OnClickListener { _, _ ->
                        // User cancelled the dialog
                        mCallback?.sendRequestCode(Activity.RESULT_CANCELED)
                        dismiss()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }


}