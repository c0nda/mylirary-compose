package com.listener.mylibrary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.listener.mylibrary.Data.readers
import com.listener.mylibrary.R
import com.listener.mylibrary.Reader
import com.listener.mylibrary.navigation.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Math.random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val options = listOf(stringResource(R.string.reader), stringResource(R.string.librarian))
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) }

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
                        text = stringResource(R.string.registration),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                modifier = Modifier.height(85.dp),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Routes.ReadersList.route + "/") {
                            popUpTo(Routes.Registration.route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
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
                text = stringResource(R.string.reader_librarian),
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 40.dp, top = 100.dp),
                fontWeight = FontWeight.Light,
                color = Color.Black
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.padding(start = 40.dp, end = 40.dp)
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = { },
                    modifier = Modifier
                        .menuAnchor()
                        .height(50.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        cursorColor = Color.Transparent
                    ),
                    textStyle = TextStyle.Default.copy(fontSize = 14.sp)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(color = Color.White)
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            text = { Text(text = selectionOption) }
                        )
                    }
                }
            }
            Text(
                text = stringResource(R.string.name),
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 40.dp, top = 5.dp),
                fontWeight = FontWeight.Light
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                singleLine = true
            )
            Text(
                text = stringResource(R.string.phone_number),
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 40.dp, top = 5.dp),
                fontWeight = FontWeight.Light
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                singleLine = true
            )
            TextButton(
                onClick = {
                    if (name.text.isNotEmpty() && phoneNumber.text.isNotEmpty()) {
                        readers.add(
                            Reader(
                                id = (random() * 10000).toLong(),
                                name = name.text,
                                cardIssueDate = "14.11.2024",
                                lastRegistrationDate = "14.11.2024",
                                booksOnHand = mutableListOf()
                            )
                        )
                        navController.navigate(Routes.ReadersList.route + "/Логин и пароль отправлены на номер ${phoneNumber.text}") {
                            popUpTo(Routes.Registration.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Заполните пустые поля")
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
                    .width(200.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.to_register),
                    fontSize = 14.sp
                )
            }
        }
    }
}