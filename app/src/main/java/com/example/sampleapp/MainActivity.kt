package com.example.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import com.example.sampleapp.R
import com.example.sampleapp.components.*
import com.example.sampleapp.ui.theme.*

enum class AppState {
    LOCK_SCREEN,
    HOME_SCREEN,
    SETTINGS
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            IOSTheme {
                MainNavigation()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation() {
    var currentState by remember { mutableStateOf(AppState.LOCK_SCREEN) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Shared Wallpaper
        Image(
            painter = painterResource(id = R.drawable.ios_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) with fadeOut(animationSpec = tween(500))
            },
            label = "ScreenTransition"
        ) { state ->
            when (state) {
                AppState.LOCK_SCREEN -> {
                    Box(modifier = Modifier.fillMaxSize().clickable { currentState = AppState.HOME_SCREEN }) {
                        LockScreen(onUnlock = { currentState = AppState.HOME_SCREEN })
                    }
                }
                AppState.HOME_SCREEN -> {
                    HomeScreen(
                        onOpenSettings = { currentState = AppState.SETTINGS },
                        onLock = { currentState = AppState.LOCK_SCREEN }
                    )
                }
                AppState.SETTINGS -> {
                    SettingsScreen(onBack = { currentState = AppState.HOME_SCREEN })
                }
            }
        }
    }
}