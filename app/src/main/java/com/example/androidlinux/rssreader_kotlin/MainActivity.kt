package com.example.androidlinux.rssreader_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_main.*
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;


class MainActivity : AppCompatActivity(){

    var urlString : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlString = urlInput.text.toString()
        RSS_Get_Feed.setOnClickListener {  }

        val parser = Parser()
        parser.execute(urlString)
        parser.onFinish(object : Parser.OnTaskCompleted {

            override fun onTaskCompleted(list: ArrayList<Article>) {
                for (article : Article in list){
                    println("Author is ${article.author}")
                    println("Category is ${article.categories}")
                    println("description is ${article.description}")
                    println("title is ${article.title}")
                }
            }

            override fun onError() {
                //what to do in case of error
            }
        })

    }
}
