package com.vixiloc.vixgpt.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vixiloc.vixgpt.R

@Composable
fun MainTopApBar(modifier: Modifier, onClear: () -> Unit, onSetting: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = stringResource(id = R.string.app_name),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
        Box(modifier = Modifier) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                IconButton(onClick = { onClear.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.ClearAll,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(onClick = { onSetting.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}