package com.leonikl.posts.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leonikl.posts.R
import com.leonikl.posts.database.Page
import com.leonikl.posts.myfun.PasswordField
import com.leonikl.posts.view.MainViewModel
import com.leonikl.posts.view.MyViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    viewModel: MainViewModel,
    model: MyViewModel
) {
    val focusManager = LocalFocusManager.current

    val allPages by viewModel.allPages.observeAsState(listOf())

    var index by remember {
        mutableIntStateOf(allPages.size - 2)
    }
    var enterText by remember {
        mutableStateOf(allPages[index].page)
    }
    model.savePage = allPages[index]

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var changePass by remember {
        mutableStateOf(false)
    }
    var textPass by remember {
        mutableStateOf(false)
    }
    var wrongPass by remember {
        mutableIntStateOf(0)
    }
    var matchPass by remember {
        mutableIntStateOf(0)
    }
    var successfully by remember {
        mutableStateOf(false)
    }
    model.createPassword = ""
    model.createPasswordRepeat = ""
    model.enterPassword = ""

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Scaffold(
                bottomBar = {
                    Text(
                        text = "by L3on1kL\n" +
                                "beta v 1.0.0",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom =  5.dp)
                            .alpha(0.4f),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(
                            top = 10.dp,
                            start = 10.dp,
                            end = 10.dp,
                            bottom = it.calculateBottomPadding()
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(
                            onClick = {
                                changePass = !changePass
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.change_pass),
                                color = Color.Black,
                                fontFamily = FontFamily(
                                    Font(R.font.comic_sans, FontWeight.Normal)
                                ),
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "ArrowBack"
                        )
                    }
                    if (changePass){
                        PasswordField(
                            placeholder = stringResource(id = R.string.pass),
                            value = model.enterPassword,
                            onValueChange = {
                                model.enterPassword = it
                            }
                        )
                        PasswordField(
                            placeholder = stringResource(id = R.string.new_pass),
                            value = model.createPassword,
                            onValueChange = {
                                model.createPassword = it
                            }
                        )
                        PasswordField(
                            placeholder = stringResource(id = R.string.new_pass),
                            value = model.createPasswordRepeat,
                            onValueChange = {
                                model.createPasswordRepeat = it
                            }
                        )
                        TextButton(
                            onClick = {
                                if(model.enterPassword != model.pass.password){
                                    wrongPass = R.string.wrong_pass
                                }
                                else if(model.createPassword != model.createPasswordRepeat){
                                    matchPass = R.string.pass_dont_match
                                }
                                else{
                                    model.pass.password = model.createPassword
                                    model.pass.state = true
                                    viewModel.updatePage(model.pass)
                                    changePass = false
                                    textPass = false
                                    successfully = true
                                    wrongPass = 0
                                    matchPass = 0
                                }
                            }
                        ) {
                            Text(
                                color = Color.Black,
                                fontFamily = FontFamily(
                                    Font(R.font.comic_sans, FontWeight.Normal)
                                ),
                                text = stringResource(id = R.string.save_pass)
                            )
                        }
                        if (textPass){
                            Text(
                                color = Color.Black,
                                fontFamily = FontFamily(
                                    Font(R.font.comic_sans, FontWeight.Normal)
                                ),
                                text = stringResource(id = wrongPass)
                            )
                            Text(
                                color = Color.Black,
                                fontFamily = FontFamily(
                                    Font(R.font.comic_sans, FontWeight.Normal)
                                ),
                                text = stringResource(id = matchPass)
                            )
                        }
                    }
                    if (successfully){
                        Text(
                            color = Color.Black,
                            fontFamily = FontFamily(
                                Font(R.font.comic_sans, FontWeight.Normal)
                            ),
                            text = stringResource(id = R.string.successfully_changed)
                        )
                    }
                    if (drawerState.isClosed){
                        changePass = false
                        textPass = false
                        successfully = false
                        wrongPass = 0
                        matchPass = 0
                    }
                }
            }

        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            color = Color.Black,
                            fontFamily = FontFamily(
                                Font(R.font.comic_sans, FontWeight.Normal)
                            ),
                            text = stringResource(id = R.string.day)
                                    + " $index"
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.updatePage(model.savePage)
                                index = allPages.size - 2 + 1
                                viewModel.insertPage(
                                    Page(
                                        id = index + 1
                                    )
                                )
                                enterText = allPages[index].page
                                model.savePage = allPages[index]
                                focusManager.clearFocus()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "add"
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "MoreVert")
                        }
                    }
                )
            },
            bottomBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(
                                onClick = {
                                    viewModel.updatePage(model.savePage)
                                    index -= 1
                                    if (index < 1) index = allPages.size - 2
                                    enterText = allPages[index].page
                                    model.savePage = allPages[index]
                                    focusManager.clearFocus()
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = "left"
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.updatePage(model.savePage)
                                    index += 1
                                    if (index > (allPages.size - 2)) index = 1
                                    enterText = allPages[index].page
                                    model.savePage = allPages[index]
                                    focusManager.clearFocus()
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = "right"
                                )
                            }
                        }
                    }
                )
            }
        ) {
            TextField(
                modifier = Modifier
                    .imePadding()
                    .padding(it)
                    .fillMaxSize(),
                value = enterText,
                onValueChange = { text ->
                    enterText = text
                    allPages[index].page = enterText
                    model.savePage = allPages[index]
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.comic_sans, FontWeight.Normal)
                    )
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                ),
            )

        }
    }
}
