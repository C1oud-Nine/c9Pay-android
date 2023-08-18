package com.example.c9pay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.c9pay.module.handleErrorCode
import com.example.c9pay.retrofit.RetrofitInterface
import com.example.c9pay.retrofit.SignupRequest
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var edtNewID: EditText
    lateinit var edtNewUsername: EditText
    lateinit var edtNewPassword: EditText
    lateinit var edtNewEmail: EditText
    lateinit var btnSignupSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        edtNewID = findViewById(R.id.edtNewID)
        edtNewUsername = findViewById(R.id.edtNewUsername)
        edtNewPassword = findViewById(R.id.edtNewPassword)
        edtNewEmail = findViewById(R.id.edtNewEmail)
        btnSignupSubmit = findViewById(R.id.btnSignupSubmit)

        btnSignupSubmit.setOnClickListener {
            if (edtNewID.text.toString().isEmpty() || edtNewUsername.text.toString().isEmpty() ||
                edtNewPassword.text.toString().isEmpty() || edtNewEmail.text.toString().isEmpty()) {
                Toast.makeText(
                    this@SignupActivity,
                    "양식을 모두 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            else {
                val retrofitObj = RetrofitInterface.create()
                val signupRequest = SignupRequest(edtNewID.getText().toString(), edtNewUsername.getText().toString(),
                    edtNewPassword.getText().toString(), edtNewEmail.getText().toString())

                retrofitObj.postSignup(signupRequest).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if(response.isSuccessful) {
                            Log.d("success", "")
                            Toast.makeText(this@SignupActivity, "환영합니다!", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        }
                        else {
                            val responseErrorBody = JSONTokener(response.errorBody()?.string()!!).nextValue() as JSONObject
                            val errorCode = responseErrorBody.getString("errorCode")
                            Log.e("error : ", errorCode)
                            Toast.makeText(this@SignupActivity, handleErrorCode(errorCode), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(this@SignupActivity, "서버와 연결할 수 없습니다.", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("failure :", t.message.toString())
                    }
                })
            }
            return@setOnClickListener
        }
    }

    override fun onBackPressed() {
        Toast.makeText(this, "회원가입을 취소합니다.", Toast.LENGTH_SHORT).show()
        finish()
    }
}