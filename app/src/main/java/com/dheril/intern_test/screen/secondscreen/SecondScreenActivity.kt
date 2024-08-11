package com.dheril.intern_test.screen.secondscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.dheril.intern_test.screen.thirdscreen.ThirdScreenActivity
import com.dheril.intern_test.databinding.ActivitySecondscreenBinding

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupviews()
    }

    private fun setupviews(){
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.tvName.text = intent.getStringExtra(EXTRA_NAME)
        binding.btnChoose.setOnClickListener {
            startActivity(Intent(this, ThirdScreenActivity::class.java))
        }

        if (intent.getStringExtra(EXTRA_USERNAME) != null){
            binding.tvUsername.text = intent.getStringExtra(EXTRA_USERNAME)
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

    }


    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_USERNAME = "extra_username"
    }
}