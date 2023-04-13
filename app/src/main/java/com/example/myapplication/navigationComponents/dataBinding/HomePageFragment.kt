package com.example.myapplication.navigationComponents.dataBinding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomepage2Binding

/**
 * A simple [Fragment] subclass.
 */
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomepage2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homepage2, container, false)
        binding.signUpButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homePageFragment_to_nameFragment2)
        }
        binding.termsButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homePageFragment_to_termsFragment2)
        }

        return binding.root
    }
}
