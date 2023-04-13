package com.example.myapplication.navigationComponents.dataBinding


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEmail2Binding

/**
 * A simple [Fragment] subclass.
 */
class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmail2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email2, container, false)
        val name = arguments!!.getString("input_text")
        binding.submitButton.setOnClickListener {
            if (!TextUtils.isEmpty(binding.emailEditText.text.toString())) {
                val bundle = bundleOf(
                    "input_email" to binding.emailEditText.text.toString(),
                    "input_name" to name
                )
                it.findNavController()
                    .navigate(R.id.action_emailFragment2_to_welcomeFragment2, bundle)

            } else {
                Toast.makeText(activity, " Please, enter your email ", Toast.LENGTH_LONG).show()
            }
        }


        return binding.root
    }
}
