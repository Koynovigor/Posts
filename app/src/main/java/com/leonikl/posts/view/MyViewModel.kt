package com.leonikl.posts.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leonikl.posts.database.Page

class MyViewModel:ViewModel() {

    var enterPassword by mutableStateOf("")
    var createPassword by mutableStateOf("")
    var createPasswordRepeat by mutableStateOf("")

    var pass: Page = Page()
    var statePass by mutableStateOf(pass.state)

    val passPage = Page(
        id = 0,
        page = "passPage",
        password = "0000",
        state = false
    )
    val page = Page(
        id = 1,
        page = "",
        password = "",
        state = false
    )

    var enterText by mutableStateOf("")

    var savePage = Page()
}