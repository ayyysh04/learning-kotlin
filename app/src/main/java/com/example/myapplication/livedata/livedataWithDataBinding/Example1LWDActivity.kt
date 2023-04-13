package com.example.myapplication.livedata.livedataWithDataBinding
//LWD : livedata with DataBinding
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityExample1LwdactivityBinding
import com.example.myapplication.livedata.livedataWithDataBinding.challenge.LwdChallengeActivity

class Example1LWDActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExample1LwdactivityBinding
    private lateinit var viewModel: Example1ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_example1_lwdactivity)
        viewModel = ViewModelProvider(this).get(Example1ViewModel::class.java)
        binding.lifecycleOwner =
            this /* live data is always attached with the lifecycle of the activity or fragment or a service ,so have to provide actual lifecycle owner to liveData*/
        binding.myViewModel = viewModel

        binding.goToLwdChallenge.setOnClickListener {
            val intent = Intent(this, LwdChallengeActivity::class.java)
            startActivity(intent)
        }
    }
}

