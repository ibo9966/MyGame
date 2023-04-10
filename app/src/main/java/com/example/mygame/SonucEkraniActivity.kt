package com.example.mygame

import android.content.Context
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

        val skor = intent.getIntExtra("skor",0)
        binding.textViewToplamSkor.text = skor.toString()



        val sp= getSharedPreferences("sonuc", Context.MODE_PRIVATE)
        val enYuksekSkor=sp.getInt("enYuksekSkor",0)

        if (skor >enYuksekSkor){
            val editor= sp.edit()
            editor.putInt("enYuksekSkor",skor)
            editor.commit()

            binding.textViewEnYuksekSkor.text=skor.toString()

        }else{
            binding.textViewEnYuksekSkor.text=enYuksekSkor.toString()

        }


        binding.buttonTekrarDene.setOnClickListener {
            val intent= Intent(this@SonucEkraniActivity,MainActivity::class.java)
            startActivity(intent)

        }
    }
}