package com.example.dailyexpendituretracker.presentation.home_screen.componants

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyexpendituretracker.data.db.Expense
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ExpenseAddDialog(
    modifier: Modifier = Modifier,
    expense: Expense = Expense(),

    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onAdd:(expense: Expense) -> Unit
) {
    var title by remember { mutableStateOf(expense.title) }
    var amount by remember{ mutableStateOf(expense.amount.toString()) }
    Box(
        modifier = modifier
    ) {
        CustomDialog(showDialog = showDialog, onDismissRequest = onDismissRequest) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {

                Text(
                    text = "Add Expense",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column {
                    CustomTextField(
                        value = title,
                        label = "Title",
                        primaryColor = MaterialTheme.colorScheme.primary,
                        onBackgroundColor = MaterialTheme.colorScheme.onBackground,
                        onValueChange = {
                            title = it
                        },
                        isError = false
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    CustomTextField(
                        value = amount,
                        label = "Amount",
                        primaryColor = MaterialTheme.colorScheme.primary,
                        onBackgroundColor = MaterialTheme.colorScheme.onBackground,
                        onValueChange = {
                            amount = it
                        },
                        isError = false
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(Modifier.align(Alignment.End)) {
                    TextButton(
                        text = "Cancel",
                        onClick = onCancel
                    )
                    TextButton(
                        text = "Add",
                        onClick = {
                            val currentTime = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
                            onAdd(
                                Expense(
                                    id = expense.id,
                                    title = title,
                                    amount = amount.toDouble(),
                                    date = currentTime,
                                    dateLong = System.currentTimeMillis()
                                )
                            )
                            title = ""
                            amount = "0.0"
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { onClick() }
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}