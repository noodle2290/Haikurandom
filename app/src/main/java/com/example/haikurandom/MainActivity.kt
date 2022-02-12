package com.example.haikurandom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.haikurandom.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Firebase.firestore
        val random = Random

        binding.createButton.setOnClickListener{
            db.collection("tasks")
                .get()
                .addOnSuccessListener { tasks ->
                    val haikuList = ArrayList<HaikuClass>()
                    tasks.forEach { haikuList.add(it.toObject(HaikuClass::class.java)) }
                    val haikuSize:Int = haikuList.size
                    Log.d("size",haikuSize.toString())

                    binding.kamiText.text = haikuList[random.nextInt(haikuSize)].kami
                    binding.nakaText.text = haikuList[random.nextInt(haikuSize)].naka
                    binding.shimoText.text = haikuList[random.nextInt(haikuSize)].shimo
                }
                .addOnFailureListener { exception ->
                    Log.d("READ_TAG", "Error getting documents: ", exception)
                }
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, PlusActivity::class.java)
            startActivity(intent)
        }
    }
}