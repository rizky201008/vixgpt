package com.vixiloc.vixgpt.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vixiloc.vixgpt.ui.theme.assistanceBubbleShape
import com.vixiloc.vixgpt.ui.theme.userBubbleShape
import dev.jeziellago.compose.markdowntext.MarkdownText
import tech.devscion.typist.Typist
import tech.devscion.typist.TypistSpeed

@Composable
fun ChatBubble(
    role: Role,
    modifier: Modifier,
    text: String,
    animated: Boolean = false,
    animationEnded: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (role == Role.USER) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        if (role == Role.ASSISTANCE) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
                    .padding(10.dp)
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(shape = if (role == Role.USER) userBubbleShape else assistanceBubbleShape),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            shape = if (role == Role.USER) userBubbleShape else assistanceBubbleShape
        ) {
            val textStyle = TextStyle(
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )

            if (role == Role.ASSISTANCE) {
                if (animated) {
                    Typist(
                        text = text,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        isCursorVisible = false,
                        typistSpeed = TypistSpeed.EXTRA_FAST,
                        textStyle = textStyle,
                        onAnimationEnd = { animationEnded.invoke() }
                    )
                } else {
                    if (text == "processing") {
                        ChatLoading(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )
                    } else {
                        MarkdownText(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            markdown = text,
                            color = LocalContentColor.current,
                            style = textStyle
                        )
                    }
                }
            } else {
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    style = textStyle
                )
            }
        }
        if (role == Role.USER) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
                    .padding(10.dp)
            )
        }
    }
}

enum class Role {
    USER,
    ASSISTANCE
}
