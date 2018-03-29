package com.example.androidlinux.rssreader_kotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    val allData: Cursor
        get() {
            val db = this.writableDatabase

            return db.rawQuery("select * from ${TABLE_NAME}", null)
        }

    override fun onCreate(db: SQLiteDatabase) {
        val query = "create table $TABLE_NAME(ID integer primary key autoincrement, article text)"
        Log.i("DATABASE:::", "CREATED")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        val query = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(query)
        onCreate(db)
    }

    fun insertArticle(article: String): Boolean {
        var ret = false

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ARTICLE, article)
        val res = db.insert(TABLE_NAME, null, contentValues)

        if (res != -1L) {
            ret = true
        }

        return ret
    }

    /*fun deleteEntry(article: String): Boolean {
        var ret = false

        val db = this.writableDatabase
        val res = db.delete(TABLE_NAME, "article = '$article';", null).toLong()

        if (res != -1L) {
            ret = true
        }

        return ret
    }*/

    companion object {

        val DATABASE_NAME = "articles.db"
        val TABLE_NAME = "articles"
        val ID = "id"
        val ARTICLE = "article"
    }
}