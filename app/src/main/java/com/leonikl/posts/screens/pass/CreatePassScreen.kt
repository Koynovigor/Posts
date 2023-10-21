package com.leonikl.posts.screens.pass

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.leonikl.posts.myfun.PasswordField
import com.leonikl.posts.myfun.ShowBars
import com.leonikl.posts.view.MainViewModel
import com.leonikl.posts.view.MyViewModel


@Composable
fun CreatePassScreen(
    viewModel: MainViewModel,
    model: MyViewModel,
    navController: NavHostController,
) {
    ShowBars(true)
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.add_pass)
            )
            PasswordField(
                placeholder = stringResource(id = R.string.pass),
                value = model.createPassword,
                onValueChange = {
                    model.createPassword = it
                }
            )
            PasswordField(
                placeholder = stringResource(id = R.string.pass),
                value = model.createPasswordRepeat,
                onValueChange = {
                    model.createPasswordRepeat = it
                }
            )
            var str by remember {
                mutableStateOf(false)
            }
            if (str){
                Text(text = stringResource(id = R.string.pass_dont_match))
            }
            if (model.createPassword != model.createPasswordRepeat){
                str = true
            }
            else{
                str = false
                if (model.createPassword.isNotBlank()){
                    TextButton(
                        onClick = {
                            model.pass.password = model.createPassword
                            model.pass.state = true
                            viewModel.updatePage(model.pass)
                            model.statePass = true
                            navController.navigate("EnterPassScreen"){
                                popUpTo(0)
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.save_pass))
                    }
                }
            }

        }
    }

}


