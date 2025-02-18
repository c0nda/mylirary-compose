package com.listener.mylibrary.component

import android.util.Log
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.listener.mylibrary.Data.readers
import com.listener.mylibrary.R
import com.listener.mylibrary.component.listitem.Reader
import com.listener.mylibrary.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ReadersList(
    modifier: Modifier = Modifier,
    navController: NavController,
    message: String?
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier
            .fillMaxSize()
            .background(color = Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.readers_list),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                modifier = Modifier.height(85.dp)
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) {
                Snackbar(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 90.dp)) {
                    Text(text = message.toString())
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(innerPadding)
        ) {
            LaunchedEffect(key1 = Unit) {
                scope.launch {
                    Log.e("message", message.toString())
                    if (!message.isNullOrEmpty()) snackbarHostState.showSnackbar(message)
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        enabled = false,
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = stringResource(id = R.string.search)
                            )
                        },
                        modifier = Modifier
                            .height(70.dp)
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black
                        ),
                        placeholder = { Text(stringResource(id = R.string.search)) },
                        textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                }
                items(readers) { reader ->
                    Reader(reader = reader)
                }
            }
            Surface(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(bottom = 80.dp, end = 16.dp)
                    .background(Color.White)
            ) {
                BasicTooltipBox(
                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(
                        spacingBetweenTooltipAndAnchor = 0.dp
                    ),
                    tooltip = {
                        Text(
                            text = stringResource(id = R.string.add),
                            color = Color.Black,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp
                        )
                    },
                    state = rememberBasicTooltipState()
                ) {
                    IconButton(
                        modifier = Modifier.background(Color.White),
                        onClick = { navController.navigate(Routes.Registration.route) }) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}