package com.example.sql_login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sql_login_register.databinding.ActivityRegisterBinding


class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.subutton.setOnClickListener {
            val suname = binding.suname.text.toString()
            val supassword = binding.supassword.text.toString()
            signupDatabase(suname, supassword)
        }
        binding.loginRedirectText.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signupDatabase(username: String, password: String){
        val insertedRowId = databaseHelper.insertUser(username, password)
        if (insertedRowId != -1L){
            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }

}