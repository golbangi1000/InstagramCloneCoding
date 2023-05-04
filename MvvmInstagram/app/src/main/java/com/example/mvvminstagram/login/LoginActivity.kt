package com.example.mvvminstagram.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvminstagram.R
import com.example.mvvminstagram.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel // 1번
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
//        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java) //위에거랑 같음

        binding.viewModel = loginViewModel
        binding.activity = this
        binding.lifecycleOwner = this
        setObserve()
    }

    fun setObserve(){
        loginViewModel.showInputNumberActivity.observe(this){
            if(it){
                finish()
                //startactivity는
                // intent를 받아서
                // 현재화면 this에서 InputNumberActivity로 화면을 전환
                var intent = Intent(this, InputNumberActivity::class.java)
                startActivity(intent)

            }
        }
        loginViewModel.showFindIDActivity.observe(this){
            if(it){
                startActivity(Intent(this,FindIdActivity::class.java))
            }
        }
    }



    fun findId(){
        println("findID")
        loginViewModel.showFindIDActivity.value = true
    }

    //구글 로그인이 성공한 결과값 받는 함수
    var googleLoginResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->

        val data = result.data
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.getResult(ApiException::class.java)
        // account.idToken  : 로그인한 사용자 정보를 암호화한 값
        loginViewModel.firebaseAuthWithGoogle(account.idToken)
    }
}