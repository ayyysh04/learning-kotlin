package com.example.myapplication.navigationComponents

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEmailBinding

class EmailFragment : Fragment() {
    private lateinit var binding: FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmailBinding.inflate(inflater, container, false)

//getting text passed from Name fragment
        val inputName = requireArguments().getString("user_name")
        binding.btnSubmit.setOnClickListener {
            if (!TextUtils.isEmpty(binding.etEmail.text)) {
                Log.i("MYAPP", binding.etEmail.text.toString())
//                Email Validation using Patterns
                if (Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text).matches()) {
                    //passing two values to another fragment
//                    not a efficient way of passing large data -> for large data to fetch we should use viewModel -> will use in next project
                    val bundle = bundleOf(
                        "user_name" to inputName,
                        "user_email" to binding.etEmail.text.toString()
                    )
                    it.findNavController()
                        .navigate(R.id.action_emailFragment_to_welcomeFragment, bundle)
                } else {
                    Toast.makeText(
                        activity,
                        "Please enter a correct email address",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(activity, "Email field is empty", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

}