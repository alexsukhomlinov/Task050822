package net.virtmarket.task050822.data


import androidx.annotation.DrawableRes





    data class Friend(
        val id: Long,
        val name: String,
        @DrawableRes
        val image: Int?,
        val description: String
    )

