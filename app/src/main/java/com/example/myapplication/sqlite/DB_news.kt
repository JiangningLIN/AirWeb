package com.example.myapplication.sqlite

import com.example.myapplication.dataClass.News
import org.jetbrains.anko.db.asMapSequence
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by Jiangning LIN on 09/07/2019.
 */
class DB_news {
    //insert to dbNews from list
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
        cursor.moveToLast()
        println("db exist: " + cursor.getInt(0))
        return cursor.getInt(0) > 0
    }

    //delete table
    fun delete(db: DBNews){
        db.use {
            delete(DBNews.TABLE_NEWS)
        }
    }

    //get all news
    fun getAllNews(db: DBNews): ArrayList<News>{
        val list = arrayListOf<News>()
        db.use {
            select(DBNews.TABLE_NEWS,
                DBNews.COLUMN_NEWS_ID,
                DBNews.COLUMN_NEWS_TYPE,
                DBNews.COLUMN_NEWS_DATE,
                DBNews.COLUMN_NEWS_TITLE,
                DBNews.COLUMN_NEWS_PICTURE,
                DBNews.COLUMN_NEWS_CONTENT,
                DBNews.COLUMN_NEWS_DATEFORMATED)
                .exec {
                    for (row in asMapSequence()){
                        list.add(News(
                            row[DBNews.COLUMN_NEWS_ID] as Long,
                            row[DBNews.COLUMN_NEWS_TYPE] as String,
                            row[DBNews.COLUMN_NEWS_DATE] as String,
                            row[DBNews.COLUMN_NEWS_TITLE] as String,
                            row[DBNews.COLUMN_NEWS_PICTURE] as String,
                            row[DBNews.COLUMN_NEWS_CONTENT] as String,
                            row[DBNews.COLUMN_NEWS_DATEFORMATED] as String
                        ))
                    }
                }
        }
        return list
    }

    // to get all news with the same type
    fun getAllNewsSammeType(db: DBNews, type: String): ArrayList<News>{
        val list = arrayListOf<News>()
        db.use {
            select(DBNews.TABLE_NEWS,
                DBNews.COLUMN_NEWS_ID,
                DBNews.COLUMN_NEWS_TYPE,
                DBNews.COLUMN_NEWS_DATE,
                DBNews.COLUMN_NEWS_TITLE,
                DBNews.COLUMN_NEWS_PICTURE,
                DBNews.COLUMN_NEWS_CONTENT,
                DBNews.COLUMN_NEWS_DATEFORMATED)
                .whereArgs(
                    "${DBNews.COLUMN_NEWS_TYPE} = {type}",
                    "type" to type
                )
                .exec {
                    for (row in asMapSequence()){
                        list.add(News(
                            row[DBNews.COLUMN_NEWS_ID] as Long,
                            row[DBNews.COLUMN_NEWS_TYPE] as String,
                            row[DBNews.COLUMN_NEWS_DATE] as String,
                            row[DBNews.COLUMN_NEWS_TITLE] as String,
                            row[DBNews.COLUMN_NEWS_PICTURE] as String,
                            row[DBNews.COLUMN_NEWS_CONTENT] as String,
                            row[DBNews.COLUMN_NEWS_DATEFORMATED] as String
                        ))
                    }
                }
        }
        return list
    }
}