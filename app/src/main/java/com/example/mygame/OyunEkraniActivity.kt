package com.example.mygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.mygame.databinding.ActivityMainBinding
import com.example.mygame.databinding.ActivityOyunEkraniBinding
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.math.floor

private lateinit var binding: ActivityOyunEkraniBinding


class OyunEkraniActivity : AppCompatActivity() {

    //POZİSYONLAR
    private var anakarakterX = 0.0f
    private var anakarakterY = 0.0f
    private var siyahkareX = 0.0f
    private var siyahkareY = 0.0f
    private var saridaireX = 0.0f
    private var saridaireY = 0.0f
    private var kirmiziucgenX = 0.0f
    private var kirmiziucgenY = 0.0f

    //Boyutlar
    private var ekranGenisligi = 0
    private var ekranYuksekligi = 0
    private var anakarakterGenisligi = 0
    private var anakarakterYuksekligi = 0

    //Kontroller
    private var dokunmaKontrol =false
    private var baslangicKontrol =false
    private val timer = Timer()

    private var skor =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOyunEkraniBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.siyahkare.x=-800.0f
        binding.siyahkare.y=-800.0f
        binding.saridaire.x=-800.0f
        binding.saridaire.y=-800.0f
        binding.kirmiziucgen.x=-800.0f
        binding.kirmiziucgen.y=-800.0f

        binding.cl.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                if (baslangicKontrol){
                    if (event?.action==MotionEvent.ACTION_DOWN){
                        Log.e("MotionEvent","ACTION_DOWN : Ekrana dokundu")
                        dokunmaKontrol=true
                    }
                    if (event?.action==MotionEvent.ACTION_UP){
                        Log.e("MotionEvent","ACTION_UP : Ekranı bıraktı")
                        dokunmaKontrol=false

                    }

                }else{
                    baslangicKontrol=true
                    binding.textViewOyunBasla.visibility=View.INVISIBLE
                    anakarakterX = binding.anakarakter.x
                    anakarakterX = binding.anakarakter.y

                    anakarakterGenisligi = binding.anakarakter.width
                    anakarakterYuksekligi = binding.anakarakter.height
                    ekranGenisligi= binding.cl.width
                    ekranYuksekligi= binding.cl.height

                    timer.schedule(0,20){
                        Handler(Looper.getMainLooper()).post{
                            anakarakterHareketEttirme()
                            cisimleriHareketEttirme()
                            carpismaKontrol()
                        }
                    }
                }
                return true
            }
        })
    }
    fun anakarakterHareketEttirme(){

        //anakarakterHiz yerine normal şartlar 20.0f yazıyordu. Ama bu uygulama belki daha büyük veya daha küçük cihazlarda açıldığında
        // hızı hızlı veya yavaş kalabilir diye. Böyle bir oran orantı yapıldı.

        val anakarakterHiz=ekranYuksekligi/60.0f

        if (dokunmaKontrol){
            anakarakterY-=anakarakterHiz
        }else{
            anakarakterY+=anakarakterHiz
        }

        if (anakarakterY<=0.0f){
            anakarakterY=0.0f
        }
        if (anakarakterY >=ekranYuksekligi-anakarakterYuksekligi){
            anakarakterY=(ekranYuksekligi-anakarakterYuksekligi).toFloat()
        }

        binding.anakarakter.y=anakarakterY
    }

    fun cisimleriHareketEttirme(){
        val siyahCisimHiz=ekranGenisligi/44.0f
        val sariCisimHiz=ekranGenisligi/54.0f
        val kirmiziCisimHiz=ekranGenisligi/36.0f

        siyahkareX-=siyahCisimHiz
        saridaireX-=sariCisimHiz
        kirmiziucgenX-=kirmiziCisimHiz

        if (siyahkareX < 0.0f){
            siyahkareX=ekranGenisligi+20.0f
            siyahkareY= floor(Math.random()*ekranYuksekligi).toFloat()
        }

        binding.siyahkare.x =siyahkareX
        binding.siyahkare.y =siyahkareY


        if (kirmiziucgenX < 0.0f){
            kirmiziucgenX=ekranGenisligi+20.0f
            kirmiziucgenY= floor(Math.random()*ekranYuksekligi).toFloat()
        }

        binding.kirmiziucgen.x =kirmiziucgenX
        binding.kirmiziucgen.y =kirmiziucgenY


        if (saridaireX < 0.0f){
            saridaireX=ekranGenisligi+20.0f
            saridaireY= floor(Math.random()*ekranYuksekligi).toFloat()
        }

        binding.saridaire.x =saridaireX
        binding.saridaire.y =saridaireY
    }

    fun carpismaKontrol(){
        val saridaireMerkezX=saridaireX + binding.saridaire.width/2.0f
        val saridaireMerkezY=saridaireY + binding.saridaire.height/2.0f

        if (0.0f <= saridaireMerkezX && saridaireMerkezX <= anakarakterGenisligi
            && anakarakterY <= saridaireMerkezY && saridaireMerkezY <= anakarakterY+anakarakterYuksekligi){
            skor+=20
            saridaireX=-10.0f
        }

        val kirmiziucgenMerkezX=kirmiziucgenX + binding.kirmiziucgen.width/2.0f
        val kirmiziucgenMerkezY=kirmiziucgenY + binding.kirmiziucgen.height/2.0f

        if (0.0f <= kirmiziucgenMerkezX && kirmiziucgenMerkezX <= anakarakterGenisligi
            && anakarakterY <= kirmiziucgenMerkezY && kirmiziucgenMerkezY <= anakarakterY+anakarakterYuksekligi){
            skor+=50
            kirmiziucgenX=-10.0f
        }

        val siyahkareMerkezX=siyahkareX + binding.siyahkare.width/2.0f
        val siyahkareMerkezY=siyahkareY + binding.siyahkare.height/2.0f

        if (0.0f <= siyahkareMerkezX && siyahkareMerkezX <= anakarakterGenisligi
            && anakarakterY <= siyahkareMerkezY && siyahkareMerkezY <= anakarakterY+anakarakterYuksekligi){

            siyahkareX=-10.0f
            timer.cancel()

            val intent = Intent(this@OyunEkraniActivity,SonucEkraniActivity::class.java)
            intent.putExtra("skor",skor)
            startActivity(intent)
            finish()
        }
        binding.textViewSkor.text=skor.toString()
    }
}