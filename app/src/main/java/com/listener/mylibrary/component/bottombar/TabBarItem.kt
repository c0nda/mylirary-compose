package com.listener.mylibrary.component.bottombar

import androidx.annotation.DrawableRes

data class TabBarItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String
)
