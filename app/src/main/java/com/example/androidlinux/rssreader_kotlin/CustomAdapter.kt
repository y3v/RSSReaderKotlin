package com.example.androidlinux.rssreader_kotlin

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import com.prof.rssparser.Article
import kotlinx.android.synthetic.main.list_row.view.*

class CustomAdapter(context: Context, resource: Int, private val articles: MutableList<Article>) : ArrayAdapter<Article>(context, resource, articles) {

    private val adapter: CustomAdapter
    init {
        this.adapter = this
    }

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.list_row, parent, false)

        rowView.textViewTitle.text = articles[pos].title
        rowView.textViewSource.text = articles[pos].link
        rowView.webViewDescription.loadData(articles[pos].description, "text/html", "UTF-8")


        return rowView
    }
}