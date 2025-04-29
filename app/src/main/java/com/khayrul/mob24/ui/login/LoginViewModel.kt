package com.khayrul.mob24.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _greetings = MutableStateFlow("")
    val greetings = _greetings.asStateFlow()

    fun greet(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            _greetings.value = "Hello $name"
        }
    }

    fun login(email: String, pass: String) {

    }

    fun validateEmail(email: String): String? {
        return  null
    }
}