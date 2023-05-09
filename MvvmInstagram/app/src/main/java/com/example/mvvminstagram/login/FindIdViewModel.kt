package com.example.mvvminstagram.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FindIdViewModel : ViewModel() {
    var auth = FirebaseAuth.getInstance()
    var firestore = FirebaseFirestore.getInstance()
    var id = ""
    var phoneNumber = ""
    var toastMessage = MutableLiveData("")


    fun findMyId(){
        firestore.collection("findIds").whereEqualTo("phoneNumber",phoneNumber).get().addOnCompleteListener{
        }
    }

    fun findMyPassword(){

    }
}