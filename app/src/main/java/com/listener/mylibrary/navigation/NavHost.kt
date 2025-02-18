package com.listener.mylibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.listener.mylibrary.component.Catalog
import com.listener.mylibrary.component.CurrentBook
import com.listener.mylibrary.component.IssuedBooks
import com.listener.mylibrary.component.MainLogin
import com.listener.mylibrary.component.Profile
import com.listener.mylibrary.component.ReadersList
import com.listener.mylibrary.component.Registration
import kotlin.reflect.typeOf

@Composable
fun LibraryNavHostController(
    navController: NavHostController,
    tabBarState: MutableState<Boolean>
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainLogin.route,
    ) {
        composable(Routes.MainLogin.route) {
            LaunchedEffect(Unit) {
                tabBarState.value = false
            }
            MainLogin(navController = navController)
        }
        composable(Routes.Catalog.route) {
            LaunchedEffect(Unit) {
                tabBarState.value = true
            }
            Catalog(navController = navController)
        }
        composable(Routes.Profile.route) {
            LaunchedEffect(Unit) {
                tabBarState.value = true
            }
            Profile(navController = navController)
        }
        composable(Routes.IssuedBooks.route) {
            LaunchedEffect(Unit) {
                tabBarState.value = true
            }
            IssuedBooks()
        }
        composable(Routes.ReadersList.route + "/{message}") { stackEntry ->
            val message = stackEntry.arguments?.getString("message")
            LaunchedEffect(Unit) {
                tabBarState.value = true
            }
            ReadersList(navController = navController, message = message)
        }
        composable(Routes.Registration.route) {
            LaunchedEffect(Unit) {
                tabBarState.value = false
            }
            Registration(navController = navController)
        }
        composable(Routes.CurrentBook.route + "/{book}") { stackEntry ->
            val bookName = stackEntry.arguments?.getString("book")
            LaunchedEffect(Unit) {
                tabBarState.value = false
            }
            if (bookName != null) {
                CurrentBook(bookName = bookName, navController = navController)
            }
        }
    }
}
