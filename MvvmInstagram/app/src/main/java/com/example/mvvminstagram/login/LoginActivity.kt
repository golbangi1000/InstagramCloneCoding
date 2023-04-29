package com.example.mvvminstagram.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvminstagram.R
import com.example.mvvminstagram.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    lateinit var binding : ActivityLoginBinding
//    lateinit var loginViewModel: LoginViewModel // 1번
    val loginViewModel : LoginViewModel by viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java] // indexing operator
//        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.viewModel = loginViewModel
        binding.activity = this
        binding.lifecycleOwner = this
        setObserve()
    }

    fun setObserve(){
        loginViewModel.showInputNumberActivity.observe(this){
            if(it){
                finish()
                startActivity(Intent(this,InputNumberActivity::class.java))
            }
        }
        loginViewModel.showFindIDActivity.observe(this){
            if(it){
                startActivity(Intent(this,FindIdActivity::class.java))
            }
        }
    }

    fun loginEmail(){
        println("Email")
        loginViewModel.showInputNumberActivity.value= true

    }

    fun findId(){
        println("findID")
        loginViewModel.showFindIDActivity.value = true
    }
}