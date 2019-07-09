package com.example.myapplication.sqlite

import com.example.myapplication.dataClass.News
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

/**
 * Created by Jiangning LIN on 09/07/2019.
 */
class DB_news {
    //todo insert to dbNews from list
    fun insert(db: DBNews, list: List<News>){
        for (i in 0 until list.size){
            db.use {
                insert(DBNews.TABLE_NEWS,
                    DBNews.COLUMN_NEWS_ID to list[i].id,
                    DBNews.COLUMN_NEWS_TYPE to list[i].type,
                    DBNews.COLUMN_NEWS_DATE to list[i].date,
                    DBNews.COLUMN_NEWS_TITLE to list[i].title,
                    DBNews.COLUMN_NEWS_PICTURE to list[i].picture,
                    DBNews.COLUMN_NEWS_CONTENT to list[i].content,
                    DBNews.COLUMN_NEWS_DATEFORMATED to list[i].dateformated)
            }
        }
    }

    //if exist table DBNews
    fun isExist(db: DBNews): Boolean {
        val mDB = db.readableDatabase
        val cursor = mDB.rawQuery("SELECT COUNT(*) FROM news", null)
        cursor.moveToFirst()
        return cursor.getInt(0) == 0
    }

    //todo delete table
    fun delete(db: DBNews){
        db.use {
            delete(DBNews.TABLE_NEWS)
        }
    }
}