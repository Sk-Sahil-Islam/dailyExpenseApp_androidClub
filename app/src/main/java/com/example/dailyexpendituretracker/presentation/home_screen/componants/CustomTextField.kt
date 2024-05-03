package com.example.dailyexpendituretracker.presentation.home_screen.componants


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    label: String,
    primaryColor: Color,
    onBackgroundColor: Color,
    onValueChange: (String) -> Unit,
    isError: Boolean,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        isError = isError,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = label
            )
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = onBackgroundColor,
            focusedTextColor = onBackgroundColor,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = primaryColor,
            focusedLabelColor = primaryColor,
            cursorColor = primaryColor,
            unfocusedIndicatorColor = onBackgroundColor,
            unfocusedLabelColor = onBackgroundColor,
            unfocusedLeadingIconColor = onBackgroundColor,
            unfocusedPlaceholderColor = onBackgroundColor,
            unfocusedPrefixColor = onBackgroundColor,
            unfocusedSuffixColor = onBackgroundColor,
            unfocusedTrailingIconColor = onBackgroundColor,
            focusedLeadingIconColor = onBackgroundColor,
            focusedTrailingIconColor = onBackgroundColor,
            errorLeadingIconColor = onBackgroundColor,
            errorTrailingIconColor = onBackgroundColor
        )
    )
}
