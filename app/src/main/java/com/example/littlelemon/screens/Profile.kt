package com.example.littlelemon.screens

import android.content.Context.MODE_PRIVATE
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.karlaFontFamily
import com.example.littlelemon.navigation.Onboarding
import com.example.littlelemon.ui.theme.PrimaryGreen
import com.example.littlelemon.ui.theme.PrimaryYellow
import com.example.littlelemon.ui.theme.SecondaryDarkGray
import com.example.littlelemon.ui.theme.SecondaryDarkSlateGray
import com.example.littlelemon.ui.theme.SecondarySalmon

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Little Lemon", MODE_PRIVATE)

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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryGreen)
                    .weight(.3f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "User profile picture",
                    modifier = Modifier
                        .padding(15.dp)
                        .clip(RoundedCornerShape(100.dp))
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
                value = sharedPreferences.getString("firstName", "")!!,
                readOnly = true,
                onValueChange = {  },
                label = { Text(text = "First name") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = karlaFontFamily
                ),
                shape = RoundedCornerShape(10.dp),
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = sharedPreferences.getString("lastName", "")!!,
                readOnly = true,
                onValueChange = {  },
                label = { Text(text = "Last name") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = karlaFontFamily
                ),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = sharedPreferences.getString("email", "")!!,
                readOnly = true,
                onValueChange = {  },
                label = { Text(text = "Email") },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = karlaFontFamily
                ),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.weight(0.8f))

            Button(
                onClick = {
                    sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
                    Toast.makeText(context, "Logging Out...",
                        Toast.LENGTH_LONG).show()
                    navController.navigate(Onboarding.route) {
                        // Pop everything up to and including Onboarding.route off the back stack
                        // before navigating to the Onboarding screen. This prevents access
                        // to the Profile screen via back button presses.
                        popUpTo(Onboarding.route) { inclusive = true }
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
                    text = "Log Out",
                    fontSize = 18.sp,
                    fontFamily = karlaFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}