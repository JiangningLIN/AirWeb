package com.example.myapplication.dataClass

/**
 * Created by Jiangning LIN on 08/07/2019.
 */
data class News(
    val id: Int,
    val type: String,
    val date: String,
    val title: String,
    val picture: String,
    val content: String,
    val dateformated: String
)