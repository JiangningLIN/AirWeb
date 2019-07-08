package com.example.myapplication.webService

import android.os.AsyncTask
import java.io.*
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Jiangning LIN on 08/07/2019.
 */
val TIMEOUT = 3*1000
class HttpTask(callback: (String?)->Unit) : AsyncTask<String, Unit, String>(){
    val callback = callback
    override fun doInBackground(vararg params: String?): String? {
        val url = URL(params[1])
        val httpClient = url.openConnection() as HttpURLConnection
        httpClient.readTimeout = TIMEOUT
        httpClient.connectTimeout = TIMEOUT
        httpClient.requestMethod = params[0]
        if (params[0] == "POST"){
            httpClient.instanceFollowRedirects = false
            httpClient.doOutput = true
            httpClient.doInput = true
            httpClient.useCaches = false
            httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8")
        }

        try{
            if (params[0] == "POST"){
                httpClient.connect()
                val os = httpClient.outputStream
                val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                //writer.write(params[2])
                writer.flush()
                writer.close()
                os.close()
            }
            if (httpClient.responseCode == HttpURLConnection.HTTP_OK){
                val stream = BufferedInputStream(httpClient.inputStream)
                val data:String = readStream(inputSteam = stream)
                return data
            }else{
                println("ERROR ${httpClient.responseCode}")
            }
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            httpClient.disconnect()
        }
        return null
    }

    private fun readStream(inputSteam: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputSteam))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        callback(result)
    }

}