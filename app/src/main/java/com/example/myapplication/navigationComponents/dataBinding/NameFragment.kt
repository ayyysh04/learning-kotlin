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
import com.example.myapplication.databinding.FragmentName2Binding

/**
 * A simple [Fragment] subclass.
 */
class NameFragment : Fragment() {
    private lateinit var binding: FragmentName2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name2, container, false)

        binding.nextButton.setOnClickListener {
            if (!TextUtils.isEmpty(binding.nameEditText.text.toString())) {
                val bundle = bundleOf(
                    "input_text" to binding.nameEditText.text.toString()
                )
                it.findNavController().navigate(R.id.action_nameFragment2_to_emailFragment2, bundle)
            } else {
                Toast.makeText(activity, "User Name cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}
