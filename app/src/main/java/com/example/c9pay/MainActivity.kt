package com.example.c9pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    companion object {
        var mainActivity : MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EntryActivity.entryActivity?.finish()

        mainActivity = this
        val btnSignout = findViewById<Button>(R.id.btnSignout)
        btnSignout.setOnClickListener {
            val toEntryIntent = Intent(this@MainActivity, EntryActivity::class.java)
            startActivity(toEntryIntent)
        }
    }
}