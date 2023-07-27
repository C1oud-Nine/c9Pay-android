package com.example.c9pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val btnSignupsubmit = findViewById<Button>(R.id.btnSignupsubmit)
        btnSignupsubmit.setOnClickListener {
            /*
            서버 통신 : 회원가입 절차
             */

            /*
            통신 완료 확인 후
             */
            Toast.makeText(this, "회원가입을 완료했습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onBackPressed() {
        Toast.makeText(this, "회원가입을 취소합니다.", Toast.LENGTH_SHORT).show()
        finish()
    }
}