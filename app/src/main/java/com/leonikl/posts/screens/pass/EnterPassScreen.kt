package com.leonikl.posts.screens.pass

import android.util.Log
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
import com.leonikl.posts.view.MyViewModel

@Composable
fun EnterPassScreen(
    model: MyViewModel,
    navController: NavHostController
) {
    ShowBars(true)
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.enter_pass)
            )
            PasswordField(
                placeholder = stringResource(id = R.string.pass),
                value = model.enterPassword,
                onValueChange = {
                    model.enterPassword = it
                }
            )
            var str by remember {
                mutableStateOf(true)
            }
            TextButton(
                onClick = {
                    str = model.pass.password == model.enterPassword
                    model.enterPassword = ""
                    if (str){
                        navController.navigate("PostsScreen")
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.log_in))
            }
            Text(
                text = if (str) {
                    ""
                } else stringResource(id = R.string.wrong_pass)
            )
        }
    }

}