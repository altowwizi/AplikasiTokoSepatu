package com.example.sportshoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginMasuk(view : View) {
        val Usname  = username.text.toString()
        val Psword  = password.text.toString()

        if (Usname.isEmpty() || Psword.isEmpty()) {
            Toast.makeText(this, "Masukkan Username!", Toast.LENGTH_SHORT).show()
        }
        else if (Usname == "altn" && Psword == "121120"){
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true
        }
        else {
            Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}