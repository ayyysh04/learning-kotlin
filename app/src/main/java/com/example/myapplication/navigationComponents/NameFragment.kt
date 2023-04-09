package com.example.myapplication.navigationComponents

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNameBinding

class NameFragment : Fragment() {
    private lateinit var binding: FragmentNameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNameBinding.inflate(inflater, container, false)
        binding.btnName.setOnClickListener {
            //passing data from one fragment to another fragment
            if (!TextUtils.isEmpty(binding.etName.text)) {
                // A bundle is a collection of key-value pairs, where the key is a string, and the value can be of any data type. It is used to pass data between different components of an app, such as activities, fragments, and services.
                val bundle = bundleOf("user_name" to binding.etName.text.toString())
                //passing bundle to email
                it.findNavController().navigate(R.id.action_nameFragment_to_emailFragment, bundle)
            } else {
                Toast.makeText(activity, "Please enter a name", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

}