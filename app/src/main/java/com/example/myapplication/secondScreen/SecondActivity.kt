package com.example.myapplication.secondScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.myapplication.R

/**
 * Created by Jiangning LIN on 10/07/2019.
 */
class SecondActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_NEWS_ID = "SecondActivity.EXTRA_NEWS_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val fragDetail = fragmentManager.findFragmentById(R.id.frag_news_detail) as SecondScreenFragment
        fragDetail.showDetail(intent.getLongExtra(EXTRA_NEWS_ID, 0))
    }
}