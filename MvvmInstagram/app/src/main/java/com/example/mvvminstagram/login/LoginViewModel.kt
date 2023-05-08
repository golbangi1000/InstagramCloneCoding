package com.example.mvvminstagram.login

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvminstagram.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginViewModel(application: Application) : AndroidViewModel(application){

    //회원가입을 관리하는 변수
    var auth =  FirebaseAuth.getInstance()
    var id : MutableLiveData<String> = MutableLiveData("")
    var password : MutableLiveData<String> = MutableLiveData("")



    //로그인 성공했을때 쓰는 변수
    var showInputNumberActivity : MutableLiveData<Boolean> = MutableLiveData(false)
    var showFindIDActivity : MutableLiveData<Boolean> = MutableLiveData(false)
    val context = getApplication<Application>().applicationContext

    var googleSigninClient : GoogleSignInClient



    init {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

            googleSigninClient = GoogleSignIn.getClient(context, gso)
    }

    fun loginWithSignupEmail(){  //이 function이 xml에서 () -> activity.loginWithSignupEmail 이걸로 버튼눌렸을때 작동됨
        println("Email")
        // loginViewModel의 id랑 password는 MutableLiveData니깐 타입할때 바뀌고 variable들에 저장되고
        auth.createUserWithEmailAndPassword(id.value.toString(), password.value.toString()).addOnCompleteListener {
            //addOnCompleteListner는 서버에서 결과값이 넘어오는 부분
            if(it.isSuccessful){
                //lginViewModel의 showInputNumberActivity의 value를 true로 바꿈
                showInputNumberActivity.value = true
            } else{
                //아이디가 있을경우

            }
        }

    }

        fun loginGoogle(view : View){

            var i = googleSigninClient.signInIntent
            (view.context as? LoginActivity)?.googleLoginResult?.launch(i)
        }

        fun firebaseAuthWithGoogle(idToken: String?){
            val credential = GoogleAuthProvider.getCredential(idToken,null)
            auth.signInWithCredential(credential).addOnCompleteListener {
                if(it.isSuccessful){
                    //loginViewModel의 showInputNumberActivity의 value를 true로 바꿈
                    showInputNumberActivity.value = true
                } else{
                    //아이디가 있을경우

                }
            }

        }
}