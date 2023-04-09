package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_applock.*


class PWActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applock)

        init()

        btnSetlock.setOnClickListener {
            val intent = Intent(this, AppPasswordActivity::class.java).apply {
                putExtra(AppLockConst.type, AppLockConst.ENABLE_PASSLOCK)
            }
            startActivityForResult(intent, AppLockConst.ENABLE_PASSLOCK)
        }

        btnDellock.setOnClickListener {
            val intent = Intent(this, AppPasswordActivity::class.java).apply {
                putExtra(AppLockConst.type, AppLockConst.DISABLE_PASSLOCK)
            }
            startActivityForResult(intent, AppLockConst.DISABLE_PASSLOCK)
        }

        btnChangepw.setOnClickListener {
            val intent = Intent(this, AppPasswordActivity::class.java).apply {
                putExtra(AppLockConst.type, AppLockConst.CHANGE_PASSWORD)
            }
            startActivityForResult(intent, AppLockConst.CHANGE_PASSWORD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            AppLockConst.ENABLE_PASSLOCK ->
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(this, "암호 설정 완료", Toast.LENGTH_SHORT).show()
                    init()
                    finish()
                }

            AppLockConst.DISABLE_PASSLOCK ->
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(this, "암호 삭제 완료", Toast.LENGTH_SHORT).show()
                    init()
                    finish()
                }

            AppLockConst.CHANGE_PASSWORD ->
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(this, "암호 변경 완료", Toast.LENGTH_SHORT).show()
                    init()
                    finish()
                }

            AppLockConst.UNLOCK_PASSWORD ->
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(this, "잠금 해제", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
        }
    }

    override fun onStart(){
        super.onStart()
        if(AppLock(this).isPassLockSet()){
            val intent = Intent(this, AppPasswordActivity::class.java).apply{
                putExtra(AppLockConst.type, AppLockConst.UNLOCK_PASSWORD)
            }
            startActivityForResult(intent, AppLockConst.UNLOCK_PASSWORD)
        }
    }

    override fun onPause(){
        super.onPause()
        if(AppLock(this).isPassLockSet()){

        }
    }

    private fun init(){
        if(AppLock(this).isPassLockSet()){
            btnSetlock.isEnabled = false
            btnDellock.isEnabled = true
            btnChangepw.isEnabled = true
        }
        else {
            btnSetlock.isEnabled = true
            btnDellock.isEnabled = false
            btnChangepw.isEnabled = false
        }
    }

}
