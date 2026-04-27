package com.example.sampleapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleapp.ui.theme.*

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(iOSBackgroundLight)
            .statusBarsPadding()
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.ChevronLeft,
                contentDescription = "Back",
                tint = iOSBlue,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onBack() }
            )
            Text(
                "Settings",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                SettingsSection(
                    listOf(
                        SettingsItemData("Airplane Mode", Icons.Default.AirplanemodeActive, iOSOrange),
                        SettingsItemData("Wi-Fi", Icons.Default.Wifi, iOSBlue, "HomeNet"),
                        SettingsItemData("Bluetooth", Icons.Default.Bluetooth, iOSBlue, "On"),
                        SettingsItemData("Mobile Data", Icons.Default.NetworkCell, iOSGreen),
                        SettingsItemData("Personal Hotspot", Icons.Default.Link, iOSGreen)
                    )
                )
            }
            item { Spacer(Modifier.height(32.dp)) }
            item {
                SettingsSection(
                    listOf(
                        SettingsItemData("Notifications", Icons.Default.Notifications, iOSRed),
                        SettingsItemData("Sounds & Haptics", Icons.Default.VolumeUp, iOSRed),
                        SettingsItemData("Focus", Icons.Default.DarkMode, iOSPurple),
                        SettingsItemData("Screen Time", Icons.Default.HourglassBottom, iOSPurple)
                    )
                )
            }
            item { Spacer(Modifier.height(32.dp)) }
            item {
                SettingsSection(
                    listOf(
                        SettingsItemData("General", Icons.Default.Settings, Color.Gray),
                        SettingsItemData("Control Center", Icons.Default.Tune, Color.Gray),
                        SettingsItemData("Display & Brightness", Icons.Default.WbSunny, iOSBlue),
                        SettingsItemData("Home Screen", Icons.Default.Apps, iOSBlue)
                    )
                )
            }
        }
    }
}

data class SettingsItemData(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val value: String? = null
)

@Composable
fun SettingsSection(items: List<SettingsItemData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        items.forEachIndexed { index, item ->
            SettingsItem(item)
            if (index < items.size - 1) {
                Divider(
                    modifier = Modifier.padding(start = 56.dp),
                    thickness = 0.5.dp,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun SettingsItem(item: SettingsItemData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(item.color),
            contentAlignment = Alignment.Center
        ) {
            Icon(item.icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(12.dp))
        Text(item.title, fontSize = 17.sp, modifier = Modifier.weight(1f))
        if (item.value != null) {
            Text(item.value, fontSize = 17.sp, color = Color.Gray, modifier = Modifier.padding(end = 8.dp))
        }
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(20.dp))
    }
}
