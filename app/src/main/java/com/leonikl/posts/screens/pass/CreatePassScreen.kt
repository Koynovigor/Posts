package com.leonikl.posts.screens.pass

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
import androidx.navigation.NavHostController
import com.leonikl.posts.R
import com.leonikl.posts.myfun.MyTextField
import com.leonikl.posts.view.MainViewModel
import com.leonikl.posts.view.MyViewModel


@Composable
fun CreatePassScreen(
    viewModel: MainViewModel,
    model: MyViewModel,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.add_pass)
        )
        MyTextField(
            placeholder = stringResource(id = R.string.pass),
            value = model.password,
            onValueChange = {
                model.password = it
            }
        )
        MyTextField(
            placeholder = stringResource(id = R.string.pass),
            value = model.passwordRepeat,
            onValueChange = {
                model.passwordRepeat = it
            }
        )
        var str by remember {
            mutableStateOf("")
        }
        Text(
            text = str
        )
        if (model.password != model.passwordRepeat){
            str = "Пароли не совпадают!"
        }
        else{
            str = ""
            if (model.password.isNotBlank()){
                TextButton(
                    onClick = {
                        model.pass.password = model.password
                        model.pass.state = true
                        viewModel.updatePage(model.pass)
                        model.statePass = true
                        navController.navigate("EnterPassScreen")
                    }
                ) {
                    Text(text = "Сохранить")
                }
            }
        }

    }
}


