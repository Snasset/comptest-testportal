package com.dheril.intern_test.screen.firstscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.dheril.intern_test.screen.secondscreen.SecondScreenActivity
import com.dheril.intern_test.screen.secondscreen.SecondScreenActivity.Companion.EXTRA_NAME
import com.dheril.intern_test.databinding.ActivityFirstscreenBinding

class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews(){
        binding.btnNext.setOnClickListener {
            val name = binding.edName.text.toString()
            val moveToHome = Intent(this, SecondScreenActivity::class.java)
            moveToHome.putExtra(EXTRA_NAME, name)
            startActivity(moveToHome)
        }

        binding.btnCheck.setOnClickListener {
            val input = binding.edPalindrome.text.toString()
            checkPalindrome(input)
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun checkPalindrome(input: String){
        val reversedInput = input.lowercase().reversed()

        val result = if (input.lowercase() == reversedInput) {
            "$input is a palindrome."
        } else {
            "$input is not a palindrome."
        }

        showDialog(result)

    }

    private fun showDialog(result: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(result)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }



}