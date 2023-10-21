package com.leonikl.posts.myfun

import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ShowBars(
    isSystemBarsVisible: Boolean,
    isNavigationBarVisible: Boolean,
    isStatusBarVisible: Boolean
){
    rememberSystemUiController().apply {
        this.isSystemBarsVisible = isSystemBarsVisible
        this.isNavigationBarVisible = isNavigationBarVisible
        this.isStatusBarVisible = isStatusBarVisible
    }
}

@Composable
fun ShowBars(
    state: Boolean
){
    rememberSystemUiController().apply {
        this.isSystemBarsVisible = state
        this.isNavigationBarVisible = state
        this.isStatusBarVisible = state
    }
}