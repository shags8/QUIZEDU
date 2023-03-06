package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import com.example.quiz.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class Login : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.Login.setOnClickListener {

            val email = binding.email.text.toString()
            val passcode = binding.passcode.text.toString()

            if (email.isNotBlank()&&passcode.isNotBlank()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,passcode).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this,quizPage::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Signin Failed" , Toast.LENGTH_SHORT).show()
                    }
                }
            }




        }
        binding.signup.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
            finish()
        }
    }
}