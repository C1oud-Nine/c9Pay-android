package com.example.c9pay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.c9pay.module.createQRCode


class PayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        val imgQR = findViewById<ImageView>(R.id.imgQR)

        // 서버 통신 : QR 생성 요청
        // QR 출력
        val QRstring = "sample string"
        createQRCode(QRstring, imgQR)



        /*
        서버 통신(thread, 동기적)
        //실시간 QR 유효시간 출력
        //인식 여부 확인 후 Activity 전환
         */
    }

    override fun onBackPressed() {
        Toast.makeText(this@PayActivity, "결제를 취소합니다.",
            Toast.LENGTH_LONG).show()
        finish()
    }
}