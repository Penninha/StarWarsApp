package com.fernandopenna.starwarsapp

import android.graphics.drawable.Drawable
import java.io.Serializable

data class ListItem(
    val name: String,
    val description: String,
    val image: Int
) : Serializable