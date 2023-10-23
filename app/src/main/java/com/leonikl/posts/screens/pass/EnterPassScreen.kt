package com.leonikl.posts.screens.pass

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
    var textColor by remember {
        mutableStateOf(Color.Transparent)
    }
    var str by remember {
        mutableStateOf(true)
    }
    var dialog by remember {
        mutableStateOf(false)
    }
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
                text = stringResource(id = R.string.enter_pass),
                fontFamily = FontFamily(
                    Font(R.font.comic_sans, FontWeight.Normal)
                ),
                color = Color.Black,
            )
            PasswordField(
                placeholder = stringResource(id = R.string.pass),
                value = model.enterPassword,
                onValueChange = {
                    model.enterPassword = it
                }
            )
            TextButton(
                onClick = {
                    str = model.pass.password == model.enterPassword
                    model.enterPassword = ""
                    if (str){
                        navController.navigate("PostsScreen")
                    }
                    else {
                        textColor = Color.Black
                    }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.log_in),
                    fontFamily = FontFamily(
                        Font(R.font.comic_sans, FontWeight.Normal)
                    ),
                    color = Color.Black,
                )
            }
            Text(
                text = stringResource(id = R.string.wrong_pass),
                color = textColor,
                fontFamily = FontFamily(
                    Font(R.font.comic_sans, FontWeight.Normal)
                ),

            )
            TextButton(
                onClick = {
                    dialog = true
                }
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_pass),
                    fontFamily = FontFamily(
                        Font(R.font.comic_sans, FontWeight.Normal)
                    ),
                    color = Color.Black,
                )
                if (dialog){
                    AlertDialog(
                        onDismissRequest = {
                            dialog = false
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    dialog = false
                                }
                            ) {
                                Text(
                                    text = stringResource(id = R.string.ok),
                                    fontFamily = FontFamily(
                                        Font(R.font.comic_sans, FontWeight.Normal)
                                    ),
                                    color = Color.Black,
                                )
                            }
                        },
                        title = {
                            Text(
                                text = stringResource(id = R.string.forgot_pass),
                                fontFamily = FontFamily(
                                    Font(R.font.comic_sans, FontWeight.Normal)
                                ),
                                color = Color.Black,
                            )
                        },
                        text = {
                            Text(
                                text = stringResource(id = R.string.text_dialog),
                                fontFamily = FontFamily(
                                    Font(R.font.comic_sans, FontWeight.Normal)
                                ),
                                color = Color.Black,
                            )
                        }
                    )
                }
            }
        }
    }
}