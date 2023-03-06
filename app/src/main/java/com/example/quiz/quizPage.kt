package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.quiz.databinding.ActivityQuizPageBinding
import com.example.quiz.databinding.ActivitySignUpBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class quizPage : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    private lateinit var database2 : DatabaseReference
    lateinit var quizquestion : ArrayList<data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityQuizPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var code = intent.getStringExtra("CODE").toString()
        var path = buildString {
            append("Code/")
            append(code)
            append("/Questions")
    }
        Log.d("MAIN", "$path")

        quizquestion = arrayListOf()
        database2 = FirebaseDatabase.getInstance().getReference(path)
        database2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (snapshot in snapshot.children){
                        val datasc = snapshot.getValue(data::class.java)
                        if (datasc != null) {
                            quizquestion.add(datasc)
                            Log.d("MAIN", "$datasc")
                        }
                    }
                    Log.d("MAIN", "${quizquestion.size}")
                    binding.question.text = quizquestion[0].question
                }


                /*        if (snapshot.exists()) {
                            for (dataSnapshot in snapshot.children) {
                                val data = dataSnapshot.getValue(data::class.java)
                                if (data != null) {
                                    quizquestion?.add(data)
                                }

                            }
                        }*/

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@quizPage,"error",Toast.LENGTH_SHORT)
            }

        })



    }

     fun getdata() {
        database = FirebaseDatabase.getInstance().getReference("Code/123/Questions")
        //var data2 = data("ok","ok","ok","what??","ok2")
       // database.child("Question2").setValue(data2).addOnSuccessListener {
       //     Toast.makeText(this@quizPage,"Success",Toast.LENGTH_SHORT)
       // }

    }
}