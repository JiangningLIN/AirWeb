package com.example.myapplication.webService

import com.example.myapplication.compagnionObjectClass.Lists
import com.example.myapplication.dataClass.News
import com.example.myapplication.sqlite.DBNews
import com.example.myapplication.sqlite.DB_news
import org.json.JSONObject

/**
 * Created by Jiangning LIN on 08/07/2019.
 */
class NewRequest {
    private val dB_news = DB_news()

    //Get information of new
    private fun getAll(response: String?): List<News>{
        val json =  arrayListOf<News>()

        val jsonObject = JSONObject(response)
        val jsonData = jsonObject.getJSONArray("news")
        for (i in 0..jsonData.length() - 1){
            val mjsonObject = JSONObject(jsonData[i].toString())
            json.add(News(
                mjsonObject.getInt("nid"),
                mjsonObject.getString("type"),
                mjsonObject.getString("date"),
                mjsonObject.getString("title"),
                mjsonObject.getString("picture"),
                mjsonObject.getString("content"),
                mjsonObject.getString("dateformated")
                )
            )
        }
        return json
    }

    // run to get
    fun run(db: DBNews, url: String) {
        HttpTask{
            if(it == null) return@HttpTask
            else{
                println("it:"+ it)
                val news = getAll(it)
                dB_news.insert(db, news)
                Lists.listNews.addAll(news)
                println("list: "+ Lists.listNews)
            }
        }.execute("POST", url)
    }
}