package com.example.sampleapp.components

import com.example.sampleapp.R
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleapp.ui.theme.*

@Composable
fun HomeScreen(onOpenSettings: () -> Unit, onLock: () -> Unit) {
    var showControlCenter by remember { mutableStateOf(false) }
    var isIslandExpanded by remember { mutableStateOf(false) }
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // iOS Status Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
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

            // Dynamic Island
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy))
                    .width(if (isIslandExpanded) 340.dp else 120.dp)
                    .height(if (isIslandExpanded) 80.dp else 35.dp)
                    .clip(if (isIslandExpanded) RoundedCornerShape(30.dp) else CircleShape)
                    .background(Color.Black)
                    .align(Alignment.CenterHorizontally)
                    .clickable { isIslandExpanded = !isIslandExpanded }
                    .padding(if (isIslandExpanded) 12.dp else 0.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isIslandExpanded) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Album Art
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(iOSPink)
                        ) {
                            Icon(Icons.Filled.MusicNote, null, tint = Color.White, modifier = Modifier.align(Alignment.Center))
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Cruel Summer", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text("Taylor Swift", color = Color.Gray, fontSize = 14.sp)
                        }
                        Icon(Icons.Filled.SkipPrevious, null, tint = Color.White)
                        Icon(Icons.Filled.Pause, null, tint = Color.White, modifier = Modifier.size(32.dp))
                        Icon(Icons.Filled.SkipNext, null, tint = Color.White)
                    }
                }
            }

            Spacer(Modifier.height(40.dp))

            // Widgets Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IOSWidget(title = "WEATHER", icon = Icons.Filled.WbSunny, color = iOSOrange) {
                    Text("Cupertino", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("72°", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Text("Mostly Sunny", fontSize = 12.sp, color = Color.Gray)
                }
                IOSWidget(title = "CALENDAR", icon = Icons.Filled.CalendarMonth, color = iOSRed) {
                    Text("TUESDAY", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = iOSRed)
                    Text("26", fontSize = 36.sp, fontWeight = FontWeight.Light)
                    Text("No events", fontSize = 12.sp, color = Color.Gray)
                }
            }

            Spacer(Modifier.height(30.dp))

            // Apps Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                item { IOSAppIcon("Mail", Icons.Filled.Email, Brush.linearGradient(listOf(iOSBlue, iOSTeal))) }
                item { IOSAppIcon("Photos", Icons.Filled.Image, Brush.linearGradient(listOf(iOSPink, iOSOrange))) }
                item { IOSAppIcon("Music", androidx.compose.ui.res.painterResource(R.drawable.music)) }
                item { IOSAppIcon("Maps", Icons.Filled.Map, Brush.linearGradient(listOf(iOSGreen, iOSTeal))) }
                item { IOSAppIcon("Notes", Icons.Filled.Notes, Brush.linearGradient(listOf(iOSYellow, iOSOrange))) }
                item { IOSAppIcon("Settings", Icons.Filled.Settings, Brush.linearGradient(listOf(Color.Gray, Color.DarkGray)), onClick = onOpenSettings) }
                item { IOSAppIcon("Safari", androidx.compose.ui.res.painterResource(R.drawable.safari)) }
                item { IOSAppIcon("Files", Icons.Filled.Folder, Brush.linearGradient(listOf(iOSBlue, iOSTeal))) }
            }

            // Dock
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(35.dp))
                    .background(Color.White.copy(alpha = 0.3f))
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IOSAppIcon("Phone", Icons.Filled.Phone, Brush.linearGradient(listOf(iOSGreen, iOSTeal)))
                    IOSAppIcon("Messages", Icons.Filled.Message, Brush.linearGradient(listOf(iOSGreen, iOSTeal)))
                    IOSAppIcon("Browser", Icons.Filled.Public, Brush.linearGradient(listOf(iOSBlue, iOSTeal)))
                    IOSAppIcon("Camera", Icons.Filled.CameraAlt, Brush.linearGradient(listOf(Color.Gray, Color.DarkGray)))
                }
            }
            Spacer(Modifier.navigationBarsPadding())
        }

        // Control Center Trigger
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(100.dp)
                .clickable { showControlCenter = true }
        )

        // Control Center Overlay
        AnimatedVisibility(
            visible = showControlCenter,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable { showControlCenter = false }
            ) {
                GlassCard(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 60.dp)
                        .align(Alignment.TopCenter)
                ) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            ControlCenterToggle(Icons.Filled.Wifi, "WiFi", true)
                            ControlCenterToggle(Icons.Filled.Bluetooth, "BT", true)
                            ControlCenterToggle(Icons.Filled.AirplanemodeActive, "Plane", false)
                            ControlCenterToggle(Icons.Filled.FlashlightOn, "Torch", false)
                        }
                        Spacer(Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White.copy(alpha = 0.4f))
                                    .padding(horizontal = 12.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Icon(Icons.Filled.WbSunny, contentDescription = null, tint = Color.White)
                                Box(modifier = Modifier.fillMaxWidth(0.6f).fillMaxHeight().background(Color.White.copy(alpha = 0.5f)))
                            }
                            Spacer(Modifier.width(16.dp))
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White.copy(alpha = 0.4f))
                                    .padding(horizontal = 12.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Icon(Icons.Filled.VolumeUp, contentDescription = null, tint = Color.White)
                                Box(modifier = Modifier.fillMaxWidth(0.4f).fillMaxHeight().background(Color.White.copy(alpha = 0.5f)))
                            }
                        }
                    }
                }
            }
        }
    }
}
