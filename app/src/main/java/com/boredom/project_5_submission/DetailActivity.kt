package com.boredom.project_5_submission

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity: AppCompatActivity() {
    var gambarHeroDetail: ImageView? = null
    var namaHeroDetail: TextView? = null
    var contentDetailHero: TextView? = null

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
        private get() {
            if (intent.hasExtra("img_hero") && intent.hasExtra("nama_hero") && intent.hasExtra("info_hero")) {
                val fotoHero = intent.getIntExtra("img_hero", 0)
                val namaHero = intent.getStringExtra("nama_hero")
                val infoHero = intent.getStringExtra("info_hero")
                setDataActivity(fotoHero, namaHero, infoHero)
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