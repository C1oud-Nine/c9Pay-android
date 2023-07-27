package com.example.c9pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var backPressedTime: Long = 0
    companion object {
        var mainActivity : MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EntryActivity.entryActivity?.finish()
        mainActivity = this

        /*
        서버통신 : 잔액 조회
        */
        //잔액 출력

        val btnSignout = findViewById<Button>(R.id.btnSignout)
        btnSignout.setOnClickListener {
            /*
            서버통신 : 로그아웃 절차
             */

            /*
            통신 완료 확인 절차
             */
            Toast.makeText(this@MainActivity, "로그아웃 했습니다.",
                Toast.LENGTH_LONG).show()
            val toEntryIntent = Intent(this@MainActivity, EntryActivity::class.java)
            startActivity(toEntryIntent)
        }

        val btnLoad = findViewById<Button>(R.id.btnLoad)
        btnLoad.setOnClickListener {
            val toLoadIntent = Intent(this@MainActivity,LoadActivity::class.java)
            startActivity(toLoadIntent)
        }

        val btnPay = findViewById<Button>(R.id.btnPay)
        btnPay.setOnClickListener {
            val toPayIntent = Intent(this@MainActivity,PayActivity::class.java)
            startActivity(toPayIntent)
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        }
        else {
            Toast.makeText(this@MainActivity, "한 번 더 뒤로가기 버튼을 누르면 앱을 종료합니다.",
                Toast.LENGTH_LONG).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}