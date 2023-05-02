package com.example.mvvminstagram.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvminstagram.R
import com.example.mvvminstagram.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var  auth : FirebaseAuth  //회원가입을 관리하는 변수
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
        auth = FirebaseAuth.getInstance()
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
                Intent(Intent.ACTION_VIEW, Uri.parse("https://naver.com"))
                startActivity(intent)

            }
        }
        loginViewModel.showFindIDActivity.observe(this){
            if(it){
                startActivity(Intent(this,FindIdActivity::class.java))
            }
        }
    }

    fun loginWithSignupEmail(){  //이 function이 xml에서 () -> activity.loginWithSignupEmail 이걸로 버튼눌렸을때 작동됨
        println("Email")
        // loginViewModel의 id랑 password는 MutableLiveData니깐 타입할때 바뀌고 variable들에 저장되고
        auth.createUserWithEmailAndPassword(loginViewModel.id.value.toString(), loginViewModel.password.value.toString()).addOnCompleteListener {
            //addOnCompleteListner는 서버에서 결과값이 넘어오는 부분
                if(it.isSuccessful){
                    //lginViewModel의 showInputNumberActivity의 value를 true로 바꿈
                    loginViewModel.showInputNumberActivity.value = true
                } else{
                    //아이디가 있을경우

                }
            }

    }

    fun findId(){
        println("findID")
        loginViewModel.showFindIDActivity.value = true
    }
}