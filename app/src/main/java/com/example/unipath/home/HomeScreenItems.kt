package com.example.unipath.home

import com.example.unipath.R

class HomeScreenItems(var image : Int)
{
companion object {
    fun get():List<HomeScreenItems>{
        return listOf(
            HomeScreenItems(R.drawable.unipath)
        )
    }
}
}