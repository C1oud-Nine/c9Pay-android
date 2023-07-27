package com.example.c9pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.Toast

class EntryActivity : AppCompatActivity() {
    var backPressedTime: Long = 0

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

            // 유효하면, Activity 전환
            val toMainIntent = Intent(this@EntryActivity, MainActivity::class.java)
            startActivity(toMainIntent)

            // 유효하지 않으면, 알림
            // Toast.makeText(this@EntryActivity, "올바르지 않은 ID 및 비밀번호 입니다.", Toast.LENGTH_SHORT).show()
        }

        val btnSignup = findViewById<Button>(R.id.btnSignup)
        btnSignup.setOnClickListener {
            val toSignupIntent = Intent(this@EntryActivity, SignupActivity::class.java)
            startActivity(toSignupIntent)
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        }
        else {
            Toast.makeText(this@EntryActivity, "한 번 더 뒤로가기 버튼을 누르면 앱을 종료합니다.",
                Toast.LENGTH_LONG).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}