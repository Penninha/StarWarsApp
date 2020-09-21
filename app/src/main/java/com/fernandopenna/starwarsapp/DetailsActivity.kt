package com.fernandopenna.starwarsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        description_name.text = intent.getStringExtra("name")
        description_image.setImageResource(intent.getIntExtra("imageId", 0))
        description_text.text = intent.getStringExtra("description")
    }
}
