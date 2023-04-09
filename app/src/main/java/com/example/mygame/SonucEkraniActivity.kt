package com.example.mygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mygame.databinding.ActivityOyunEkraniBinding
import com.example.mygame.databinding.ActivitySonucEkraniBinding

private lateinit var binding: ActivitySonucEkraniBinding


class SonucEkraniActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySonucEkraniBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonTekrarDene.setOnClickListener {
            val intent= Intent(this@SonucEkraniActivity,MainActivity::class.java)
            startActivity(intent)

        }
    }
}