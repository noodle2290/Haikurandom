package com.example.haikurandom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.haikurandom.databinding.ActivityMainBinding
import com.example.haikurandom.databinding.ActivityPlusBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class PlusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlusBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Firebase.firestore

        binding.submitButton.setOnClickListener {
            val haiku = HaikuClass(
                kami = binding.kamiEditText.text.toString(),
                naka = binding.nakaEditText.text.toString(),
                shimo = binding.shimoEditText.text.toString(),
            )

            db.collection("tasks")
                .add(haiku)
                .addOnSuccessListener { documentReference ->
                    Log.d("ADD_TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("ADD_TAG", "Error adding document", e)
                }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}