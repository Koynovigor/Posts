package com.leonikl.posts.myfun

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.leonikl.posts.R

@Composable
fun PasswordField(
    placeholder: String?,
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder!!,
                color = Color.Gray
            )
        },
        singleLine = true,
        modifier = Modifier
            .padding(5.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF1F2F4),
            unfocusedContainerColor = Color(0xFFF1F2F4),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(percent = 10),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation(),
        textStyle = TextStyle(
            color = Color.Black,
            fontFamily = FontFamily(
                Font(R.font.comic_sans, FontWeight.Normal)
            )
        ),
    )
}