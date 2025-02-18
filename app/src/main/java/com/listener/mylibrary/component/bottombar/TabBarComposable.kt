package com.listener.mylibrary.component.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.listener.mylibrary.Data.mode
import com.listener.mylibrary.R
import com.listener.mylibrary.navigation.Routes

@Composable
fun TabView(
    navController: NavController,
    tabBarState: MutableState<Boolean>
) {
    val catalog = TabBarItem(
        title = stringResource(id = R.string.catalog),
        icon = R.mipmap.ic_books,
        route = Routes.Catalog.route
    )
    val profile = TabBarItem(
        title = stringResource(id = R.string.profile),
        icon = R.mipmap.ic_profile,
        route = Routes.Profile.route
    )
    val readers = TabBarItem(
        title = stringResource(id = R.string.readers),
        icon = R.mipmap.ic_readers,
        route = Routes.ReadersList.route + "/"
    )
    val books = TabBarItem(
        title = stringResource(id = R.string.books),
        icon = R.mipmap.ic_issued_books,
        route = Routes.IssuedBooks.route
    )

    val tabBarItemsReader = listOf(catalog, profile)
    val tabBarItemsLibrarian = listOf(readers, books, catalog, profile)
    val tabBarItems = if (mode == 'L') tabBarItemsLibrarian else tabBarItemsReader

    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    AnimatedVisibility(visible = tabBarState.value) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBar(
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            tabBarItems.forEachIndexed { index, tabBarItem ->
                NavigationBarItem(
                    selected = currentRoute == tabBarItem.route,
                    colors = NavigationBarItemColors(
                        disabledIconColor = Color.Transparent,
                        disabledTextColor = Color.Black,
                        unselectedIconColor = Color.Transparent,
                        selectedIconColor = Color.Transparent,
                        selectedTextColor = Color.Black,
                        selectedIndicatorColor = Color.Transparent,
                        unselectedTextColor = Color.LightGray
                    ),
                    onClick = {
                        selectedTabIndex = index
                        navController.navigate(tabBarItem.route) {
                            if (currentRoute != null) {
                                popUpTo(currentRoute) {
                                    inclusive = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Image(
                            painter = painterResource(id = tabBarItem.icon),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    },
                    label = { Text(tabBarItem.title) })
            }
        }
    }
}