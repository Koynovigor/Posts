package com.leonikl.posts.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leonikl.posts.database.Page

class MyViewModel:ViewModel() {

    var password by mutableStateOf("")
    var passwordRepeat by mutableStateOf("")

    var pass: Page = Page()
    var statePass by mutableStateOf(pass.state)

}