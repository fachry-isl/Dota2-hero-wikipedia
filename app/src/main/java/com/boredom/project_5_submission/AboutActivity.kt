package com.boredom.project_5_submission

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun contactMe(view: View) {
        val message = "Hi "
        val intentContactEmail = Intent(Intent.ACTION_SENDTO)
        intentContactEmail.data = Uri.parse("mailto:")
        intentContactEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf("fachryikhsal890@gmail.com"))
        intentContactEmail.putExtra(Intent.EXTRA_TEXT, message)
        if (intentContactEmail.resolveActivity(packageManager) != null) {
            startActivity(intentContactEmail)
        } else {
            Toast.makeText(
                this,
                "NO GMAIL Detected. you can contact fachryikhsal890@gmail.com",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}