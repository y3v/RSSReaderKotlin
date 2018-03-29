package com.example.androidlinux.rssreader_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;


class MainActivity : AppCompatActivity(){

    lateinit var urlString : String
    var myDb = DatabaseHelper(this)

    var articles : MutableList<Article> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var url : EditText = editTextRssUrl
        urlString = url.text.toString()
        myDb.insertArticle(url.text.toString())

        viewAll()

        buttonGetRSSFeed.setOnClickListener { parseURL(urlString) }
    }

    fun parseURL(url : String?){

        //println("PARSE URL!!" + urlString)
        if (url != null){
            val parser = Parser()
            parser.execute(url)
            parser.onFinish(object : Parser.OnTaskCompleted {

                override fun onTaskCompleted(list: ArrayList<Article>) {
                    println("INSIDE TASK COMPLETED")
                    for (article : Article in list){
                        println("Author is ${article.author}")
                        println("Category is ${article.categories}")
                        println("description is ${article.description}")
                        println("title is ${article.title}")
                        println("---------------------------------")
                        articles.add(article)
                    }
                    listViewArticle.adapter = (CustomAdapter(applicationContext, R.layout.list_row, articles))
                }

                override fun onError() {
                    //what to do in case of error
                }
            })
        }
    }

    private fun viewAll() {
        val res = myDb.allData
        var url : String? = null
        if (res.getCount() == 0) {
            Log.d("DATABASE::::", "FRESH PAGE")
        } else {
            while(res.moveToNext()){
                url = res.getString(1)
            }
        }
        parseURL(url)
    }
}
