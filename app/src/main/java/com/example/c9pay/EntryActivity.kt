package com.example.c9pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button

class EntryActivity : AppCompatActivity() {
    companion object {
        var entryActivity : EntryActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        MainActivity.mainActivity?.finish()

        entryActivity = this
        val btnSignin = findViewById<Button>(R.id.btnSignin)
        btnSignin.setOnClickListener {
            /* 서버 통신 : 아이디 비밀번호 유효한지 확인
            ~~~~
            ~~~~
            */

            // 유효하면, Main 액티비티 실행
            val toMainIntent = Intent(this@EntryActivity, MainActivity::class.java)
            startActivity(toMainIntent)

            // 유효하지 않으면, 토스트 메시지
            // Toast.makeText(this@EntryActivity, "올바르지 않은 ID 및 비밀번호 입니다.", Toast.LENGTH_SHORT).show()
        }
    }
}