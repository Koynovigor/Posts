package com.leonikl.posts

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leonikl.posts.database.Page
import com.leonikl.posts.screens.LoadingScreen
import com.leonikl.posts.screens.pass.CreatePassScreen
import com.leonikl.posts.screens.pass.EnterPassScreen
import com.leonikl.posts.view.MainViewModel
import com.leonikl.posts.view.MainViewModelFactory
import com.leonikl.posts.view.MyViewModel

class MainActivity : ComponentActivity() {

    private val model = MyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val owner = LocalViewModelStoreOwner.current
            lateinit var viewModel: MainViewModel
            owner?.let {
                viewModel = viewModel(
                    it,
                    "MainViewModel",
                    MainViewModelFactory(
                        LocalContext.current.applicationContext
                                as Application
                    )
                )
            }

            val page = Page(
                id = 0,
                page = "Hello!",
                password = "0000",
                state = false
            )
            viewModel.insertPage(page)
            val allPages by viewModel.allPages.observeAsState(listOf())
            for (i in allPages){
                model.pass = i
                model.statePass = i.state
                break
            }

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "Loading"
            ){
                composable("Loading"){
                    LoadingScreen(
                        navController = navController,
                        model = model
                    )
                }
                composable("EnterPassScreen") {
                    EnterPassScreen(
                        model = model
                    )
                }
                composable("CreatePassScreen") {
                    CreatePassScreen(
                        viewModel = viewModel,
                        model = model,
                        navController = navController
                    )
                }
            }
        }
    }
}

