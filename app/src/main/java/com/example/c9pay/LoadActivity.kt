package com.example.c9pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class LoadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        val btnCharge = findViewById<Button>(R.id.btnCharge)
        btnCharge.setOnClickListener {
            /*
            입력받은 충전액 유효한지 확인
             */
            /*
            서버 통신 : 잔액 충전
             */
            /*
            통신 완료 확인 절차
             */
            Toast.makeText(this@LoadActivity, "충전 성공",
                Toast.LENGTH_LONG).show()

            finish()
        }
    }

    override fun onBackPressed() {
        Toast.makeText(this@LoadActivity, "잔액 충전을 취소합니다.",
            Toast.LENGTH_LONG).show()
        finish()
    }
}