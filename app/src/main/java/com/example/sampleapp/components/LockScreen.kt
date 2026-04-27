package com.example.sampleapp.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LockScreen(onUnlock: () -> Unit) {
    var currentTime by remember { mutableStateOf(SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())) }
    var currentDate by remember { mutableStateOf(SimpleDateFormat("EEEE, d MMMM", Locale.getDefault()).format(Date())) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            currentDate = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault()).format(Date())
            kotlinx.coroutines.delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Status Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("9:41", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.SignalCellularAlt, null, tint = Color.White, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Icon(Icons.Filled.Wifi, null, tint = Color.White, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Icon(Icons.Filled.BatteryFull, null, tint = Color.White, modifier = Modifier.size(20.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 60.dp),
            contentAlignment = Alignment.TopCenter
        ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = currentDate.uppercase(),
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
            Text(
                text = currentTime,
                color = Color.White,
                fontSize = 100.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.offset(y = (-10).dp)
            )
        }

        // Notifications Placeholder
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.9f)
                .padding(top = 100.dp)
        ) {
            NotificationCard("Messages", "Sarah", "See you at 8:00 PM tonight! 🍕")
            Spacer(Modifier.height(12.dp))
            NotificationCard("Instagram", "Photography", "Liked your latest photo")
        }

        // Bottom Actions
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp, vertical = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LockActionButton(icon = Icons.Filled.FlashlightOn)
                Text(
                    text = "Swipe up to unlock",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
                LockActionButton(icon = Icons.Filled.CameraAlt)
            }
        }
    }
}

@Composable
fun NotificationCard(app: String, title: String, body: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White.copy(alpha = 0.25f))
            .padding(16.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(20.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.5f)))
                Spacer(Modifier.width(8.dp))
                Text(app.uppercase(), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White.copy(alpha = 0.6f))
                Spacer(Modifier.weight(1f))
                Text("2m ago", fontSize = 11.sp, color = Color.White.copy(alpha = 0.6f))
            }
            Spacer(Modifier.height(4.dp))
            Text(title, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 16.sp)
            Text(body, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
        }
    }
}

@Composable
fun LockActionButton(icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
    }
}
