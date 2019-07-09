package com.example.myapplication.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

/**
 * Created by Jiangning LIN on 09/07/2019.
 */
val Context.dbNews : DBNews
    get() = DBNews.getInstance(applicationContext)
class DBNews(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DATABASE_NAME_NEWS, null, DATABASE_VERSION_NEWS) {
    companion object {
        const val DATABASE_NAME_NEWS    = "news.db"
        const val DATABASE_VERSION_NEWS = 11

        const val TABLE_NEWS                = "news"
        const val COLUMN_NEWS_ID            = "id"
        const val COLUMN_NEWS_TYPE          = "type"
        const val COLUMN_NEWS_DATE          = "date"
        const val COLUMN_NEWS_TITLE         = "title"
        const val COLUMN_NEWS_PICTURE       = "picture"
        const val COLUMN_NEWS_CONTENT       = "content"
        const val COLUMN_NEWS_DATEFORMATED  = "dateformated"

        private var instance: DBNews? = null

        @Synchronized
        fun getInstance(ctx: Context): DBNews {
            if (instance == null){
                instance = DBNews(ctx)
            }
            return instance!!
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(TABLE_NEWS, true,
            COLUMN_NEWS_ID to INTEGER,
            COLUMN_NEWS_TYPE to TEXT,
            COLUMN_NEWS_DATE to TEXT,
            COLUMN_NEWS_TITLE to TEXT,
            COLUMN_NEWS_PICTURE to TEXT,
            COLUMN_NEWS_CONTENT to TEXT,
            COLUMN_NEWS_DATEFORMATED to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 11) {
            db.execSQL("ALTER TABLE $TABLE_NEWS")
        }
    }

}