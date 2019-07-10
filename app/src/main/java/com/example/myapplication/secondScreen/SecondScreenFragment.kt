package com.example.myapplication.secondScreen

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.firstScreen.NewsRecyclerAdapter
import com.example.myapplication.sqlite.DB_news
import com.example.myapplication.sqlite.dbNews
import com.example.myapplication.webService.Net
import kotlinx.android.synthetic.main.fragment_news_detail.*
import kotlinx.android.synthetic.main.item_news.*

/**
 * Created by Jiangning LIN on 10/07/2019.
 */
class SecondScreenFragment: Fragment() {
    private val dB_news = DB_news()
    private val net     = Net()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }


    //Show detail of news
    fun showDetail(id: Long) {
        val news = dB_news.getNewsToShow(activity.dbNews, id)
        title_detail.setText(news.title)
        try{
            if (net.connect(activity))
                NewsRecyclerAdapter.GetImageFromURL(image_detail).execute(news.picture)
        }catch (e: Exception){}
        content_detail.setText(news.content)
    }
}