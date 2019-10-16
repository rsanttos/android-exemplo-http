package com.example.exemplohttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cep = edtCep.text.toString()
        var url = "https://viacep.com.br/ws/$cep/json"
        var task = CepTask(tvEndereco)

        task.execute(url)
    }
}
