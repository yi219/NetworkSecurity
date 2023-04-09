package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_applock_pw.*

class AppPasswordActivity : AppCompatActivity(){
    private var oldPwd=""
    private var changePWUnlock = false

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applock_pw)

        val buttonArray = arrayListOf<Button>(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,erasen)
        for (button in buttonArray){
            button.setOnClickListener(btnListener)
        }
        p1.requestFocus()
    }

    private val btnListener = View.OnClickListener { view ->
        var currentValue = -1
        when(view.id){
            R.id.n1 -> currentValue = 1
            R.id.n2 -> currentValue = 2
            R.id.n3 -> currentValue = 3
            R.id.n4 -> currentValue = 4
            R.id.n5 -> currentValue = 5
            R.id.n6 -> currentValue = 6
            R.id.n7 -> currentValue = 7
            R.id.n8 -> currentValue = 8
            R.id.n9 -> currentValue = 9
            R.id.n0 -> currentValue = 0
            R.id.erasen -> onDeleteKey()
        }

        val strCurrentValue = currentValue.toString()
        if(currentValue != -1){
            when {
                p1.isFocused -> {
                    setEditText(p1, p2, strCurrentValue)
                }
                p2.isFocused -> {
                    setEditText(p2, p3, strCurrentValue)
                }
                p3.isFocused -> {
                    setEditText(p3, p4, strCurrentValue)
                }
                p4.isFocused -> {
                    p4.setText(strCurrentValue)
                }
            }
        }

        if(p4.text.isNotEmpty() && p3.text.isNotEmpty() && p2.text.isNotEmpty() && p1.text.isNotEmpty()){
            inputType(intent.getIntExtra("type", 0))
        }
    }

    private fun onDeleteKey(){
        when {
            p1.isFocused -> {
                p1.setText("")
            }
            p2.isFocused -> {
                p1.setText("")
                p1.requestFocus()
            }
            p3.isFocused -> {
                p2.setText("")
                p2.requestFocus()
            }
            p4.isFocused -> {
                p3.setText("")
                p3.requestFocus()
            }
        }
    }

    private fun onClear(){
        p1.setText("")
        p2.setText("")
        p3.setText("")
        p4.setText("")
        p1.requestFocus()
    }

    private fun inputedPassword():String{
        return "${p1.text}${p2.text}${p3.text}${p4.text}"
    }

    private fun setEditText(currentEditText: EditText, nextEditText: EditText, strCurrentValue: String){
        currentEditText.setText(strCurrentValue)
        nextEditText.requestFocus()
        nextEditText.setText("")
    }

    private fun inputType(type: Int){
        when(type){
            AppLockConst.ENABLE_PASSLOCK -> {
                if(oldPwd.isEmpty()){
                    oldPwd = inputedPassword()
                    onClear()
                    inputinfo.text = "다시 한 번 입력해주세요"
                    inputinfo2.text = ""
                }
                else{
                    if(oldPwd==inputedPassword()){
                        AppLock(this).setPassLock(inputedPassword())
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                    else{
                        onClear()
                        oldPwd=""
                        inputinfo.text = "일치하지 않습니다."
                        inputinfo2.text = "비밀번호를 입력해주세요"
                    }
                }
            }

            AppLockConst.DISABLE_PASSLOCK -> {
                if(AppLock(this).isPassLockSet()){
                    if(AppLock(this).checkPassLock(inputedPassword())){
                        AppLock(this).removePassLock()
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                    else {
                        inputinfo.text = "비밀번호가 틀립니다"
                        onClear()
                    }
                }
                else{
                    setResult(Activity.RESULT_CANCELED)
                    finish()
                }
            }

            AppLockConst.UNLOCK_PASSWORD -> {
                if(AppLock(this).checkPassLock(inputedPassword())){
                    setResult(Activity.RESULT_OK)
                    finish()
                }
                else{
                    inputinfo.text = "비밀번호가 틀립니다"
                    onClear()
                }
            }

            AppLockConst.CHANGE_PASSWORD -> {
                if(AppLock(this).checkPassLock(inputedPassword()) && !changePWUnlock){
                    onClear()
                    changePWUnlock = true
                    inputinfo.text = "새로운 비밀번호를 입력해주세요"
                    inputinfo2.text = ""
                }
                else if(changePWUnlock){
                    if(oldPwd.isEmpty()){
                        oldPwd = inputedPassword()
                        onClear()
                        inputinfo.text = "다시 한 번 입력해주세요"
                        inputinfo2.text = ""
                    }
                    else{
                        if(oldPwd == inputedPassword()){
                            AppLock(this).setPassLock(inputedPassword())
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                        else{
                            onClear()
                            oldPwd = ""
                            inputinfo.text = "일치하지 않습니다"
                            inputinfo2.text = "현재 비밀번호를 다시 입력해주세요"
                            changePWUnlock = false
                        }
                    }
                }
                else{
                    inputinfo.text = "비밀번호가 틀립니다"
                    inputinfo2.text = ""
                    changePWUnlock = false
                    onClear()
                }
            }
        }
    }
}