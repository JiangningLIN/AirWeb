package com.example.myapplication.firstScreen

import android.app.DownloadManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.myapplication.R
import com.example.myapplication.compagnionObjectClass.Lists
import com.example.myapplication.webService.Net
import com.example.myapplication.webService.NewRequest

class MainActivity : AppCompatActivity() {

    private val newsRequest = NewRequest()
    private val net         = Net()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (net.connect(this))
            newsRequest.run("https://airweb-demo.airweb.fr/psg/psg.json")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //val fragList = fragmentManager.findFragmentById(R.id.list_news_fragment) as FirstScreenFragment
        //fragList.loadListNews()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.first_screen, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
