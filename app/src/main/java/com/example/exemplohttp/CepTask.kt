package com.example.exemplohttp

import android.os.AsyncTask
import android.widget.TextView
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.Buffer

class CepTask (var txtResultado: TextView) : AsyncTask<String, Void, String>() {



    override fun doInBackground(vararg str: String?): String {
        var stringUrl = str[0]
        var inputStream : InputStream? = null
        var inputStreamReader : InputStreamReader? = null
        var buffer : StringBuffer? = null

        try {
            var url : URL = URL(stringUrl)
            var connection : HttpURLConnection = url.openConnection() as HttpURLConnection
            inputStream = connection.inputStream
            inputStreamReader = InputStreamReader(inputStream)
            var reader = BufferedReader(inputStreamReader)
            buffer = StringBuffer()
            var linha : String? = null

            while(true){
                linha = reader.readLine()

                if(linha == null) {
                    break
                }

                buffer.append(linha)

            }

        } catch (e : MalformedURLException) {
            e.printStackTrace()
        } catch (e : IOException) {
            e.printStackTrace()
        }
        return buffer.toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        var logradouro : String? = null
        var bairro : String? = null
        try {
            var jsonObj : JSONObject = JSONObject(result)
            logradouro = jsonObj.getString("logradouro")
            bairro = jsonObj.getString("bairro")
            txtResultado.text = "$logradouro - $bairro"
        } catch (e : JSONException) {
            e.printStackTrace()
        }
    }
}