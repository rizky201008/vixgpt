package com.vixiloc.vixgpt.presentation.sreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vixiloc.vixgpt.R
import com.vixiloc.vixgpt.domain.navigations.Destination
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Splash(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.tertiary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.vixgpt_transparent),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    }
    LaunchedEffect(key1 = true) {
        delay(3000)
        scope.launch {
            navController.popBackStack()
            navController.navigate(Destination.Chat.route)
        }
    }
}