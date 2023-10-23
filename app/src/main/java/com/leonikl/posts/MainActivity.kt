package com.leonikl.posts

import android.app.Application
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
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
import com.leonikl.posts.screens.PostsScreen
import com.leonikl.posts.screens.pass.CreatePassScreen
import com.leonikl.posts.screens.pass.EnterPassScreen
import com.leonikl.posts.ui.theme.PostsTheme
import com.leonikl.posts.view.MainViewModel
import com.leonikl.posts.view.MainViewModelFactory
import com.leonikl.posts.view.MyViewModel

class MainActivity : ComponentActivity() {

    private val model = MyViewModel()

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

            val owner = LocalViewModelStoreOwner.current
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
            viewModel.insertPage(model.passPage)
            viewModel.insertPage(model.page)
            viewModel.insertPage(
                Page(
                    id = 2
                )
            )

            val allPages by viewModel.allPages.observeAsState(listOf())
            for (i in allPages){
                model.pass = i
                model.statePass = i.state
                break
            }

            val navController = rememberNavController()

            PostsTheme{
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
                            model = model,
                            navController = navController
                        )
                    }
                    composable("CreatePassScreen") {
                        CreatePassScreen(
                            viewModel = viewModel,
                            model = model,
                            navController = navController
                        )
                    }
                    composable("PostsScreen"){
                        PostsScreen(
                            viewModel = viewModel,
                            model = model
                        )
                    }
                }
            }

        }
    }

    override fun onPause() {
        super.onPause()
        if (model.savePage.id != 0){
            viewModel.updatePage(model.savePage)
        }
    }
    override fun onStop() {
        super.onStop()
        if (model.savePage.id != 0){
            viewModel.updatePage(model.savePage)
        }
    }
}

