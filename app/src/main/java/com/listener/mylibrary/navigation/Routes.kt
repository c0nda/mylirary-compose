package com.listener.mylibrary.navigation


sealed class Routes(val route: String) {

    data object MainLogin : Routes("login")
    data object Catalog : Routes("catalog")
    data object Profile : Routes("profile")
    data object IssuedBooks : Routes("issued_books")
    data object ReadersList : Routes("readers")
    data object Registration : Routes("registration")
    data object CurrentBook : Routes("current_book")
}