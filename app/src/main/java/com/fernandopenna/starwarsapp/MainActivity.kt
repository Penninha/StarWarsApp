package com.fernandopenna.starwarsapp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener() {
            val intent = Intent(this@MainActivity, InfoActivity::class.java)
            startActivity(intent)
        }

        ObjectAnimator.ofFloat(logo, "translationY", -800f, 0f).apply {
            duration = 1250
            start()
        }
    }
}
