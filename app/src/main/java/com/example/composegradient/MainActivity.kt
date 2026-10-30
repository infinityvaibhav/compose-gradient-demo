package com.example.composegradient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GradientBackgroundDemo()
        }
    }
}

@Composable
fun GradientBackgroundDemo() {

    var targetAlpha by remember { mutableFloatStateOf(0f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(10000)
    )

    LaunchedEffect(Unit) {
        targetAlpha = 1f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                createGradient(alpha = animatedAlpha)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Hello Android",
            color = Color.White,
            fontSize = 48.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun createGradient(alpha: Float = 0.7f): Brush = Brush.linearGradient(
    colors = listOf(
        Color.Blue.copy(alpha = alpha),
        Color.Magenta.copy(alpha = alpha)
    ),
    start = Offset.Zero,
    end = Offset.Infinite
)

@Composable
fun MyApp() {
    GradientBackgroundDemo()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}