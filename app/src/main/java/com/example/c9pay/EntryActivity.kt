package com.example.c9pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.c9pay.retrofit.userservice.APIMethods
import com.example.c9pay.retrofit.userservice.dto.LoginRequest
import com.example.c9pay.retrofit.userservice.dto.LoginResponseError
import com.example.c9pay.retrofit.userservice.dto.LoginResponseOK


class EntryActivity : AppCompatActivity() {
    var backPressedTime: Long = 0
    lateinit var edtID: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnSignup: Button
    lateinit var pbLogin: ProgressBar

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
                val loginRequest = LoginRequest(edtID.getText().toString(), edtPassword.getText().toString())
                postLogin(loginRequest)
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
        }
        else {
            Toast.makeText(
                this@EntryActivity,
                "한 번 더 뒤로가기 버튼을 누르면 앱을 종료합니다.",
                Toast.LENGTH_LONG
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun postLogin (loginRequest: LoginRequest) {
        pbLogin = findViewById(R.id.pbLogin)
        pbLogin.visibility = View.VISIBLE

        APIMethods.postLogin(
            loginRequest
        ) { response ->
            when (response) {
                is LoginResponseOK -> {
                    Log.d("EntryActivity", "Login success")
                }
                is LoginResponseError -> {
                    Log.d("tag", "" + response.code)
                    Log.d("tag", response.message)
                    Log.d("tag", response.method)
                }
                else -> {
                    Toast.makeText(
                        applicationContext,
                        "로그인 실패했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        pbLogin.visibility = View.GONE
    }
}