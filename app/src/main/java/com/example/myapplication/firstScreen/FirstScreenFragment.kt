package com.example.myapplication.firstScreen

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.compagnionObjectClass.Lists
import com.example.myapplication.sqlite.DB_news
import com.example.myapplication.sqlite.dbNews
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Jiangning LIN on 08/07/2019.
 */
class FirstScreenFragment: Fragment() {

    private val dB_news = DB_news()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadListNews()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    //load all news
    fun loadListNews() {
        if (Lists.listNews.isNotEmpty()) Lists.listNews.clear()
        Lists.listNews.addAll(dB_news.getAllNews(activity.dbNews))
        println("list fragment: " + Lists.listNews)

        rv_list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_list_news.adapter = NewsRecyclerAdapter(Lists.listNews)
    }
    //load all news selected with same type
    fun loadListNewsWithType(type: String) {
        if (Lists.listNews.isNotEmpty()) Lists.listNews.clear()
        Lists.listNews.addAll(dB_news.getAllNewsSammeType(activity.dbNews, type))

        rv_list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_list_news.adapter = NewsRecyclerAdapter(Lists.listNews)
    }
}