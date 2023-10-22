package com.leonikl.posts.screens

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leonikl.posts.database.Page
import com.leonikl.posts.view.MainViewModel
import com.leonikl.posts.view.MyViewModel

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
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Day $index") },
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
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding(),
                    start = 5.dp,
                    end = 5.dp
                )
                .fillMaxSize(),
            value = enterText,
            onValueChange = { text ->
                enterText = text
                allPages[index].page = enterText
                model.savePage = allPages[index]
            },
            textStyle = TextStyle(
                fontSize = 14.sp
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
