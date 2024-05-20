package com.bahercoding.smiledetector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {
    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`start_activity`)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener{
            val intent = Intent(this, com.bahercoding.smiledetectorlib.MainActivity::class.java)
            startActivity(intent)

        }




    }
}