package com.example.myapplication.contact

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.myapplication.R
import com.google.android.gms.maps.GoogleMap
import kotlinx.android.synthetic.main.activity_contact.*
import org.jetbrains.anko.makeCall

/**
 * Created by Jiangning LIN on 10/07/2019.
 */
class ContactActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        //call us
        contact_call.setOnClickListener {
            checkPermissionCall()
            val number = "+33 1 76 61 65 10"
            makeCall(number)
        }
        //send mail
        contact_mail.setOnClickListener {
            val mail = "contact@airweb.fr"
            sendMail(arrayOf(mail))
        }

        //map
        contact_map.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("map", 3)
            startActivity(intent)
        }
    }

    private fun sendMail(to:Array<String>) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, to)
        if (intent.resolveActivity(this.packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun checkPermissionCall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + packageName))
            startActivity(intent)
            finish()
            Toast.makeText(this, "Enable Call Permission..!!", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun makeCall(number: String) {
        val intent = Intent(Intent.ACTION_CALL)
        try {
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }catch (e: Exception){
            reminds(this@ContactActivity, R.string.alert_title_reminds, R.string.alert_message_call)
        }
    }
    //remind can't call
    fun reminds(activity: Activity, title_remind: Int, message: Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title_remind)
        builder.setIcon(R.drawable.ic_warning_red_24dp)
        builder.setMessage(message)
        builder.setPositiveButton("Oui"){_,_ ->}

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

}