package com.example.littlelemon.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val karlaFontFamily = FontFamily(
    Font(R.font.karla_regular, FontWeight.Normal)
)

@Composable
fun Onboarding() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    //TODO sharedPreferences

    Column (modifier = Modifier.imePadding()) {
        // TODO: make 3 boxes. Little Lemon Logo,
        //  Let's get to know you, Personal information
        //  The rest can be in its own Column since all of the
        //  padding is shared between those.

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
//                    .padding(10.dp)
                )
            }

            /**
             * LET'S GET TO KNOW YOU BOX
             */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF495E57))
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

        /**
         * INPUT FIELDS
         */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.6f)
                .background(Color.White)
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
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
                shape = RoundedCornerShape(10.dp)
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
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.weight(0.8f))

            Button(
                onClick = { /*TODO: save to sharedPreferences (unless next task
                               says otherwise) before deleting the TextFields.*/
                    firstName = ""
                    lastName = ""
                    email = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                enabled = (firstName.isNotEmpty()
                        && lastName.isNotEmpty()
                        // A better solution would be to validate with regex.
                        // However, this should satisfy the project req's for now.
                        && email.contains('@')
                        && email.contains(".com")),
                colors = ButtonColors(
                    containerColor = Color(0xFFf4CE14),
                    contentColor = Color(0xFF333333),
                    disabledContainerColor = Color(0xFFAFAFAF),
                    disabledContentColor = Color(0xFFFFFFFF),
                ),
            ) {
                Text(
                    text = "Register",
                    fontSize = 18.sp,
                    fontFamily = karlaFontFamily,
                    modifier = Modifier
//                        .padding(5.dp)
                )
            }

            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding()
}