package com.listener.mylibrary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.listener.mylibrary.Data.mode
import com.listener.mylibrary.Data.passwords
import com.listener.mylibrary.R
import com.listener.mylibrary.navigation.Routes
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLogin(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var numberAndId by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
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
                        text = stringResource(id = R.string.login),
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
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier
                .background(color = Color.White)
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.id),
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 40.dp, top = 140.dp),
                fontWeight = FontWeight.Light,
                color = Color.Black
            )
            OutlinedTextField(
                value = numberAndId,
                onValueChange = { numberAndId = it },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Black
                ),
                textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                singleLine = true
            )
            Text(
                text = stringResource(id = R.string.password),
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 40.dp, top = 5.dp),
                fontWeight = FontWeight.Light
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Black
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                singleLine = true
            )
            TextButton(
                onClick = {
                    mode = numberAndId.text.getOrNull(0)
                    if (mode != null && (mode == 'L' || mode == 'R')
                        && numberAndId.text in passwords
                        && passwords[numberAndId.text] == password.text
                    ) {
                        navController.navigate(Routes.Catalog.route) {
                            popUpTo(Routes.MainLogin.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Неверный логин или пароль")
                        }
                    }
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp, end = 100.dp, top = 35.dp)
                    .height(40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.to_login),
                    fontSize = 14.sp
                )
            }
            Text(
                text = stringResource(id = R.string.forgot_password),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(25.dp)
                    .clickable {}
                    .padding(top = 5.dp)
            )
        }
    }
}