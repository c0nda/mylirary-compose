package com.listener.mylibrary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.listener.mylibrary.component.bottombar.TabBarItem
import com.listener.mylibrary.component.bottombar.TabView
import com.listener.mylibrary.navigation.LibraryNavHostController
import com.listener.mylibrary.navigation.Routes
import com.listener.mylibrary.ui.theme.MyLibraryTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val tabBarState = rememberSaveable { (mutableStateOf(true)) }
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            when (navBackStackEntry?.destination?.route) {
                Routes.MainLogin.route -> tabBarState.value = false
                Routes.Profile.route -> tabBarState.value = true
                Routes.Catalog.route -> tabBarState.value = true
                Routes.ReadersList.route -> tabBarState.value = true
            }

            MyLibraryTheme {
                Scaffold(
                    modifier = Modifier
                        .background(Color.White),
                    bottomBar = {
                        TabView(
                            navController = navController,
                            tabBarState = tabBarState
                        )
                    }) {
                    LibraryNavHostController(
                        navController = navController,
                        tabBarState = tabBarState
                    )
                }
            }
        }
    }
}