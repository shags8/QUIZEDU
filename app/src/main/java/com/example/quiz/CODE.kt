package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quiz.databinding.ActivityCodeBinding
import com.example.quiz.databinding.ActivityQuizPageBinding

class CODE : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
       var code = binding.code.text.toString()
        binding.start.setOnClickListener{
            intent = Intent(this , quizPage::class.java)
            intent.putExtra("CODE",code)
            startActivity(intent)
            finish()
        }

    }
}