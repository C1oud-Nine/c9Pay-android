package com.example.c9pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.c9pay.module.handleErrorCode
import com.example.c9pay.retrofit.LoginRequest
import com.example.c9pay.retrofit.RetrofitInterface
import com.example.c9pay.retrofit.LoginResponse
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntryActivity : AppCompatActivity() {
    var backPressedTime: Long = 0

    lateinit var edtID: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnSignup: Button

    companion object {
        var entryActivity: EntryActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        MainActivity.mainActivity?.finish()
        entryActivity = this

        edtID = findViewById(R.id.edtID)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)

        btnLogin.setOnClickListener {
            if (edtID.text.toString().isEmpty() || edtPassword.text.toString().isEmpty()) {
                Toast.makeText(
                    this@EntryActivity,
                    "ID와 비밀번호 모두 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            else {
                val retrofitObj = RetrofitInterface.create()
                val loginRequest = LoginRequest(edtID.getText().toString(), edtPassword.getText().toString())

                retrofitObj.postLogin(loginRequest).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if(response.isSuccessful) {
                            val responseBody = response.body()!!
                            Log.d("success : ", responseBody.token)

                            Toast.makeText(this@EntryActivity, "안녕하세요!", Toast.LENGTH_SHORT)
                                .show()

                            val toMainIntent = Intent(this@EntryActivity, MainActivity::class.java)
                            toMainIntent.putExtra("authorization", responseBody.token)
                            startActivity(toMainIntent)
                        }
                        else {
                            val responseErrorBody = JSONTokener(response.errorBody()?.string()!!).nextValue() as JSONObject
                            val errorCode = responseErrorBody.getString("errorCode")
                            Log.e("error : ", errorCode)
                            Toast.makeText(this@EntryActivity, handleErrorCode(errorCode), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@EntryActivity, "서버와 연결할 수 없습니다.", Toast.LENGTH_SHORT)
                            .show()
                        Log.e("failure :", t.message.toString())
                    }
                })
            }
        }

        btnSignup.setOnClickListener {
            val toSignupIntent = Intent(this@EntryActivity, SignupActivity::class.java)
            startActivity(toSignupIntent)
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(
                this@EntryActivity,
                "한 번 더 뒤로가기 버튼을 누르면 앱을 종료합니다.",
                Toast.LENGTH_LONG
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}