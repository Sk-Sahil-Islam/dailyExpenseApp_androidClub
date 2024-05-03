package com.example.dailyexpendituretracker.presentation.home_screen.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyexpendituretracker.data.db.Expense

@Composable
fun ExpenseItem(
    modifier: Modifier = Modifier,
    expense: Expense,
    onDelete: (Expense) -> Unit,
    onEdit: (Expense) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primary.copy(0.3f))
            .clickable { onEdit(expense) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row {
                Text(text = expense.date)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.offset(x = 8.dp, y = (-8).dp),
                    onClick = { onDelete(expense) }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "delete"
                    )
                }
            }
            Row {
                Text(text = expense.title, fontSize = 18.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = expense.amount.toString(), fontSize = 18.sp)
            }
        }
    }
}