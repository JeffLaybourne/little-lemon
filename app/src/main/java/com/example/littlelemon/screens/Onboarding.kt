package com.example.littlelemon.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.karlaFontFamily
import com.example.littlelemon.navigation.Home
import com.example.littlelemon.sharedPreferences
import com.example.littlelemon.ui.theme.PrimaryGreen
import com.example.littlelemon.ui.theme.PrimaryYellow
import com.example.littlelemon.ui.theme.SecondaryDarkGray
import com.example.littlelemon.ui.theme.SecondaryDarkSlateGray
import com.example.littlelemon.ui.theme.SecondarySalmon

@Composable
fun Onboarding(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column (modifier = Modifier.imePadding()) {

        // Upper Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.4f)
        ){

            /**
             * LITTLE LEMON LOGO (HEADER)
              */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .weight(.3f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon Logo",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(25.dp)
                )
            }

            /**
             * LET'S GET TO KNOW YOU BOX
             */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryGreen)
                    .weight(.3f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Let's get to know you",
                    color = Color.White,
                    fontFamily = karlaFontFamily,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                )
            }

            /**
             * PERSONAL INFORMATION BOX
              */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .weight(.3f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Personal information",
                    fontFamily = karlaFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        // Lower Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.6f)
                .background(Color.White)
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            /**
             * INPUT FIELDS
             */
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(text = "First name") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = karlaFontFamily),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                })
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Last name") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = karlaFontFamily),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                })
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = karlaFontFamily),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                })
            )

            Spacer(modifier = Modifier.weight(0.8f))

            Button(
                onClick = {
                    if (firstName.isBlank()
                        && lastName.isBlank()){
                        Toast.makeText(context, "Registration unsuccessful. " +
                                "Please enter all data.", Toast.LENGTH_LONG).show()

                    // A better solution would be to validate with regex.
                    // However, this should satisfy the project req's for now.
                    } else if (!email.contains('@') || !email.contains(".com")) {
                        Toast.makeText(context, "Registration unsuccessful. " +
                                "Please enter a valid email. (ex: John@example.com)", Toast.LENGTH_LONG).show()
                    } else {
                        sharedPreferences.edit().putString("firstName", firstName).apply()
                        sharedPreferences.edit().putString("lastName", lastName).apply()
                        sharedPreferences.edit().putString("email", email).apply()
                        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()

                        Toast.makeText(context, "Registration successful!",
                            Toast.LENGTH_LONG).show()
                        navController.navigate(Home.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonColors(
                    containerColor = PrimaryYellow,
                    contentColor = SecondaryDarkSlateGray,
                    // Not used in the project requirements.
                    disabledContainerColor = SecondaryDarkGray,
                    disabledContentColor = Color(0xFFFFFFFF),
                ),
                border = BorderStroke(1.dp, SecondarySalmon)
            ) {
                Text(
                    text = "Register",
                    fontSize = 18.sp,
                    fontFamily = karlaFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}