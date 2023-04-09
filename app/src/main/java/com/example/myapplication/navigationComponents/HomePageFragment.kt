package com.example.myapplication.navigationComponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomepageBinding

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomepageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomepageBinding.inflate(inflater, container, false)
        binding.btnSignUp.setOnClickListener {
//            The findNavController() method is a utility method provided by the Navigation component in Android. This method returns the NavController associated with the View that was clicked. The NavController is responsible for handling navigation between destinations in the app.
//            navigate() is a method provided by the Android Navigation Component, which is used for navigating from one destination to another within an Android app.
//            The navigate() method takes an argument, which is the ID of the action that defines the navigation path between the source and destination fragments. In this case, R.id.action_homeFragment_to_nameFragment is the ID of the action that takes us from HomeFragment to NameFragment
            it.findNavController().navigate(R.id.action_homeFragment_to_nameFragment)
        }


        binding.btnTerms.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_termsFragment)
        }
        return binding.root
    }

}