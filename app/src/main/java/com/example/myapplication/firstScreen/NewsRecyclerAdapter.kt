package com.example.myapplication.firstScreen

import android.graphics.BitmapFactory
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.dataClass.News
import kotlinx.android.synthetic.main.item_news.view.*
import java.net.URL

/**
 * Created by Jiangning LIN on 08/07/2019.
 */
class NewsRecyclerAdapter(val list: MutableList<News>): RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView     = view.findViewById(R.id.title)
        var image: ImageView    = view.findViewById(R.id.image)
        val content: TextView   = view.findViewById(R.id.content)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NewsRecyclerAdapter.NewsViewHolder, position: Int) {
        holder.title.text = list[position]?.title
        val url = URL(list[position]?.picture)
        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        holder.image.setImageBitmap(bmp)
        holder.content.text = list[position]?.content
    }
}