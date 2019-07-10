package com.example.myapplication.firstScreen

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.compagnionObjectClass.Lists
import com.example.myapplication.dataClass.News
import com.example.myapplication.secondScreen.SecondActivity
import com.example.myapplication.secondScreen.SecondScreenFragment
import com.example.myapplication.sqlite.DB_news
import com.example.myapplication.sqlite.dbNews
import kotlinx.android.synthetic.main.fragment_news.*
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

/**
 * Created by Jiangning LIN on 08/07/2019.
 */
class FirstScreenFragment: Fragment(){

    private val dB_news = DB_news()

    interface Listener{
        fun onNewsSelection(id:Long)
    }
    var mListener:Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            mListener = context as Listener
        }catch (e: ClassCastException){
            throw java.lang.ClassCastException(context.toString()+"must implement FirstScreenFragment.Listener")
        }
    }

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
        if (Lists.searchNews.isNotEmpty()) Lists.searchNews.clear()

        Lists.listNews.addAll(dB_news.getAllNews(activity.dbNews))
        Lists.searchNews.addAll(Lists.listNews)


        rv_list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_list_news.adapter = NewsRecyclerAdapter(Lists.searchNews){
            showNewsDetail(it.id)
        }
    }
    //load all news selected with same type
    fun loadListNewsWithType(type: String) {
        if (Lists.listNews.isNotEmpty()) Lists.listNews.clear()
        if (Lists.searchNews.isNotEmpty()) Lists.searchNews.clear()

        Lists.listNews.addAll(dB_news.getAllNewsSammeType(activity.dbNews, type))
        Lists.searchNews.addAll(Lists.listNews)

        rv_list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_list_news.adapter = NewsRecyclerAdapter(Lists.searchNews){
            showNewsDetail(it.id)
        }
    }
    //load all news and sort by date
    fun loadListNewsSortByDate() {
        if (Lists.listNews.isNotEmpty()) Lists.listNews.clear()
        if (Lists.searchNews.isNotEmpty()) Lists.searchNews.clear()

        Lists.listNews.addAll(dB_news.getAllNews(activity.dbNews))
        // sort
        Lists.listNews.sortWith(compareByDescending { it.date })
        Lists.listNews.forEach { println("id: "+it.id + ",date:" + it.date) }
        Lists.searchNews.addAll(Lists.listNews)

        rv_list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_list_news.adapter = NewsRecyclerAdapter(Lists.searchNews){
            showNewsDetail(it.id)
        }
    }

    // load all news and sort by title
    fun loadListNewsSortByTitle() {
        if (Lists.listNews.isNotEmpty()) Lists.listNews.clear()
        if (Lists.searchNews.isNotEmpty()) Lists.searchNews.clear()

        Lists.listNews.addAll(dB_news.getAllNews(activity.dbNews))
        // sort
        Lists.listNews.sortWith(compareBy { it.title.toLowerCase() })
        Lists.listNews.forEach { println("id: "+it.id + ",title:" + it.title) }
        Lists.searchNews.addAll(Lists.listNews)

        rv_list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_list_news.adapter = NewsRecyclerAdapter(Lists.searchNews){
            showNewsDetail(it.id)
        }
    }

    //show detail
    fun showNewsDetail(id: Long) {
        if (mListener?.onNewsSelection(id) == null){
            val fragDetail = fragmentManager.findFragmentById(R.id.frag_news_detail) as SecondScreenFragment?
            if (fragDetail != null){
                fragDetail.showDetail(id)
            }else{
                startActivityForResult<SecondActivity>(1, SecondActivity.EXTRA_NEWS_ID to id)
            }
        }
    }
}