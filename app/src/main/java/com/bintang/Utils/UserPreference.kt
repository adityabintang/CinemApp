package com.bintang.Utils

import android.content.Context
import com.bintang.Utils.Const.NAMA_USER
import com.bintang.Utils.Const.PREF_NAME
import com.bintang.Utils.Const.STATUS_USER

class UserPreference(val context: Context) {
    private val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    //function untuk set Value
    fun setStatusUser(status: Boolean){
        val editor = preference.edit()
        editor.putBoolean(STATUS_USER, status)
        editor.apply()
    }

    fun getStatusUser():Boolean{
        return preference.getBoolean(STATUS_USER, false)
    }

    fun setNamaUser(value: String){
        val editor = preference.edit()
        editor.putString(NAMA_USER, value)
        editor.apply()
    }

    fun getNamaUser():String? {
        return preference.getString(NAMA_USER, "")
    }
}