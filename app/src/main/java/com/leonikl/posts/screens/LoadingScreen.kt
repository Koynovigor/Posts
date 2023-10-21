package com.leonikl.posts.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.leonikl.posts.myfun.ShowBars
import com.leonikl.posts.view.MyViewModel
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    navController: NavHostController,
    model: MyViewModel
) {
    ShowBars(false)
    LaunchedEffect(
        key1 = true
    ){
        delay(2500)
        navController.navigate(
            if (model.statePass) "EnterPassScreen"
            else "CreatePassScreen"
        ){
            popUpTo(0)
        }
    }

}
