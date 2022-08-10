package com.example.upnout.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val _text = MutableLiveData<String>().apply {
        value = "Hi "
    }

    val text: LiveData<String> = _text
}