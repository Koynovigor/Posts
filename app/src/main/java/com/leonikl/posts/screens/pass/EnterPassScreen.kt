package com.leonikl.posts.screens.pass

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.leonikl.posts.R
import com.leonikl.posts.myfun.MyTextField
import com.leonikl.posts.view.MainViewModel
import com.leonikl.posts.view.MyViewModel

@Composable
fun EnterPassScreen(
    model: MyViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.enter_pass)
        )
        MyTextField(
            placeholder = stringResource(id = R.string.pass),
            value = model.password,
            onValueChange = {
                model.password = it
            }
        )
        var str by remember {
            mutableStateOf("")
        }
        TextButton(
            onClick = {
                str = if (model.pass.password == model.password){
                    "Успешно!"
                } else{
                    "Не верный пароль!"
                }
                Log.d("my", model.pass.password)
            }
        ) {
            Text(text = "Вход")
        }
        Text(text = str)
    }
}