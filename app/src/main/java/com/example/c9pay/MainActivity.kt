package com.example.c9pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.c9pay.module.handleErrorCode
import com.example.c9pay.retrofit.RetrofitInterface
import com.example.c9pay.retrofit.UserResponse
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var backPressedTime: Long = 0

    lateinit var txtUserName: TextView
    lateinit var txtCredit: TextView
    companion object {
        var mainActivity : MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EntryActivity.entryActivity?.finish()
        mainActivity = this

        var authorization: String? = intent.getStringExtra("authorization")

        authorization = "Authorization=$authorization"
        Log.d("authorization : ", authorization)

        val retrofitObj = RetrofitInterface.create()
        retrofitObj.getUser(authorization).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                    Log.d("success", "")
                    val responseBody = response.body()!!
                    txtUserName = findViewById(R.id.txtUserName)
                    txtUserName.setText("안녕하세요,\n" + responseBody.username + " 님!")

                    txtCredit = findViewById(R.id.txtCredit)
                    val credit = responseBody.credit
                    val t_dec_up = DecimalFormat("#,###")
                    val credit_decimal = t_dec_up.format(credit)
                    txtCredit.setText("₩ " + credit_decimal)
                }

                else {
                    val responseErrorBody = JSONTokener(response.errorBody()?.string()!!).nextValue() as JSONObject
                    val errorCode = responseErrorBody.getString("errorCode")
                    Log.e("error : ", errorCode)
                    Toast.makeText(this@MainActivity, handleErrorCode(errorCode), Toast.LENGTH_SHORT)
                        .show()

                    val toEntryIntent = Intent(this@MainActivity, EntryActivity::class.java)
                    startActivity(toEntryIntent)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "서버와 연결할 수 없습니다.", Toast.LENGTH_SHORT)
                    .show()
                Log.d("failure :", t.message.toString())

                val toEntryIntent = Intent(this@MainActivity, EntryActivity::class.java)
                startActivity(toEntryIntent)
            }
        })

        val btnSignout = findViewById<Button>(R.id.btnSignout)
        btnSignout.setOnClickListener {
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