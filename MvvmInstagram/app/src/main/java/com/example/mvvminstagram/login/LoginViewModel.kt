package com.example.mvvminstagram.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    var id : MutableLiveData<String> = MutableLiveData("")
    var password : MutableLiveData<String> = MutableLiveData("")


    //로그인 성공했을때 쓰는 변수
    var showInputNumberActivity : MutableLiveData<Boolean> = MutableLiveData(false)
    var showFindIDActivity : MutableLiveData<Boolean> = MutableLiveData(false)

}