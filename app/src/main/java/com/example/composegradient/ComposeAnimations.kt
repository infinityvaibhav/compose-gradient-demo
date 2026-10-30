package com.example.composegradient

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleText(
    text: String = "Hello World",
    color: Color = Color.Red,
    fontSize: TextUnit = 24.sp
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        modifier = Modifier.padding(16.dp)
    )
}

// Animated visibility
@Composable
fun DemoAnimatedVisibility(modifier: Modifier = Modifier) {
    var isVisible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleText()

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(
                animationSpec = tween(600),
                initialOffsetX = { fullWidth -> -fullWidth }
            ) + fadeIn(tween(600)),
            exit = slideOutHorizontally(
                animationSpec = tween(600),
                targetOffsetX = { fullWidth -> fullWidth }
            ) + fadeOut(tween(600))
        ) {
            SimpleText(text = "Animate me", color = Color.Blue, fontSize = 48.sp)
        }


        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text(text = "Toggle")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDemoAnimatedVisibility() {
    DemoAnimatedVisibility()
}