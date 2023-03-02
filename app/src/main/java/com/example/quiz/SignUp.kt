package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.quiz.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class SignUp : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Signup.setOnClickListener {

            val email = binding.email.text.toString()
            val passcode = binding.passcode.text.toString()
            val rollno = binding.rollno.text.toString()
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (passcode.isNotEmpty()&&rollno.isNotEmpty()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, passcode).addOnCompleteListener {
                        val intent = Intent(this , CODE::class.java)
                        startActivity(intent)
                        finish()

                    }.addOnFailureListener {
                        Toast.makeText(this,"Sign up Failed", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this,"Enter All details", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Enter Correct Mail", Toast.LENGTH_SHORT).show()
            }



        }
        binding.login.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }


    }
}