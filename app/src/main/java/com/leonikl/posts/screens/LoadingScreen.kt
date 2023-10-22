package com.leonikl.posts.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.leonikl.posts.myfun.ShowBars
import com.leonikl.posts.view.MyViewModel
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonState
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonType
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSJetPackComposeProgressButton
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    navController: NavHostController,
    model: MyViewModel
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ShowBars(false)
            val submitButtonState by remember { mutableStateOf(SSButtonState.LOADING) }
            SSJetPackComposeProgressButton(
                type = SSButtonType.WHEEL,
                width = 45.dp,
                height = 45.dp,
                onClick = { },
                assetColor = Color.Gray,
                buttonState = submitButtonState,
                colors = androidx.compose.material.ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                elevation = null
            )
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
    }
}
