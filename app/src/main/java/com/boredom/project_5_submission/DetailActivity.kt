package com.boredom.project_5_submission

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {
    var gambarHeroDetail: ImageView? = null
    var namaHeroDetail: TextView? = null
    var contentDetailHero: TextView? = null

    lateinit var btn_share: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        gambarHeroDetail = findViewById(R.id.img_detail)
        namaHeroDetail = findViewById(R.id.tv_detail_name)
        contentDetailHero = findViewById(R.id.tv_content_detail)

        // Mengambil data dari explicit intent untuk ditampil pada halaman ini
        incomingExtra

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private val incomingExtra: Unit
     get() {
            if (intent.hasExtra("img_hero") && intent.hasExtra("nama_hero") && intent.hasExtra("info_hero")) {
                val fotoHero = intent.getIntExtra("img_hero", 0)
                val namaHero = intent.getStringExtra("nama_hero")
                val infoHero = intent.getStringExtra("info_hero")
                setDataActivity(fotoHero, namaHero, infoHero)

                btn_share = findViewById(R.id.btn_share)
                btn_share.setOnClickListener{
                    val shareIntent = Intent()
                    shareIntent.setAction(Intent.ACTION_SEND)
                    shareIntent.setType("text/plain")
                    //Target Whatsapp
                    shareIntent.setPackage("com.whatsapp")
                    //Add Text
                    val formatText= "Lore $namaHero from Dota Wikipedia : $infoHero"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, formatText)
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    try {
                        startActivity(shareIntent)
                    } catch (ex: ActivityNotFoundException) {
                        Toast.makeText(this,"Whatsapp have not been installed",Toast.LENGTH_LONG).show()
                        //ToastHelper.MakeShortText("Whatsapp have not been installed.")
                    }
                }
            }
        }


    private fun setDataActivity(fotoHero: Int, namaHero: String?, infoHero: String?) {
        Glide.with(this).load(fotoHero).apply(
            RequestOptions()
                .override(175, 325)
        )
            .into(gambarHeroDetail!!)
        namaHeroDetail!!.text = namaHero
        contentDetailHero!!.text = infoHero
    }

}