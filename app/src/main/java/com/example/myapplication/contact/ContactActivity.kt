package com.example.myapplication.contact

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.myapplication.R
import com.google.android.gms.maps.GoogleMap
import kotlinx.android.synthetic.main.activity_contact.*

/**
 * Created by Jiangning LIN on 10/07/2019.
 */
class ContactActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        //todo call us

        //todo send mail

        //todo map
        contact_map.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("map", 3)
            startActivity(intent)
        }
    }

}