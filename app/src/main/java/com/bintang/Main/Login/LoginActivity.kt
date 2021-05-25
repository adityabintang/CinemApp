package com.bintang.Main.Login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bintang.Utils.UserPreference
import com.bintang.cinemapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userPreference = UserPreference(this)

        btn_login.setOnClickListener {
            if (et_username.text.isEmpty()){
                et_username.error = "UserName TIdak Boleh Kosong"
            }else{
                userPreference.setNamaUser(et_username.text.toString())
                userPreference.setStatusUser(true)

                var intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}