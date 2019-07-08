package com.example.myapplication.firstScreen

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.compagnionObjectClass.Lists
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Jiangning LIN on 08/07/2019.
 */
class FirstScreenFragment: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadListNews()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    fun loadListNews() {
        rv_list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_list_news.adapter = NewsRecyclerAdapter(Lists.listNews)
    }
}