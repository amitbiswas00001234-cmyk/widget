package com.example.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.sampleapp.components.*
import com.example.sampleapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            IOSTheme {
                HomeScreen()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen() {
    var showControlCenter by remember { mutableStateOf(false) }
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Wallpaper
        Image(
            painter = painterResource(id = R.drawable.ios_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Main Content (Home Screen)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Dynamic Island Placeholder
            Spacer(Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(35.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(40.dp))

            // Widgets Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IOSWidget(title = "WEATHER", icon = Icons.Default.WbSunny, color = iOSOrange) {
                    Text("Cupertino", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("72°", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Text("Mostly Sunny", fontSize = 12.sp, color = Color.Gray)
                }
                IOSWidget(title = "CALENDAR", icon = Icons.Default.CalendarMonth, color = iOSRed) {
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
                item { IOSAppIcon("Mail", Icons.Default.Email, Brush.linearGradient(listOf(iOSBlue, iOSTeal))) }
                item { IOSAppIcon("Photos", Icons.Default.Image, Brush.linearGradient(listOf(iOSPink, iOSOrange))) }
                item { IOSAppIcon("Music", Icons.Default.MusicNote, Brush.linearGradient(listOf(iOSRed, iOSOrange))) }
                item { IOSAppIcon("Maps", Icons.Default.Map, Brush.linearGradient(listOf(iOSGreen, iOSTeal))) }
                item { IOSAppIcon("Notes", Icons.Default.Notes, Brush.linearGradient(listOf(iOSYellow, iOSOrange))) }
                item { IOSAppIcon("Settings", Icons.Default.Settings, Brush.linearGradient(listOf(Color.Gray, Color.DarkGray))) }
                item { IOSAppIcon("Safari", Icons.Default.Explore, Brush.linearGradient(listOf(iOSBlue, iOSTeal))) }
                item { IOSAppIcon("Files", Icons.Default.Folder, Brush.linearGradient(listOf(iOSBlue, iOSTeal))) }
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
                    IOSAppIcon("Phone", Icons.Default.Phone, Brush.linearGradient(listOf(iOSGreen, iOSTeal)))
                    IOSAppIcon("Messages", Icons.Default.Message, Brush.linearGradient(listOf(iOSGreen, iOSTeal)))
                    IOSAppIcon("Browser", Icons.Default.Public, Brush.linearGradient(listOf(iOSBlue, iOSTeal)))
                    IOSAppIcon("Camera", Icons.Default.CameraAlt, Brush.linearGradient(listOf(Color.Gray, Color.DarkGray)))
                }
            }
            Spacer(Modifier.navigationBarsPadding())
        }

        // Control Center Trigger (Invisible top-right area)
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
                            ControlCenterToggle(Icons.Default.Wifi, "WiFi", true)
                            ControlCenterToggle(Icons.Default.Bluetooth, "BT", true)
                            ControlCenterToggle(Icons.Default.AirplanemodeActive, "Plane", false)
                            ControlCenterToggle(Icons.Default.FlashlightOn, "Torch", false)
                        }
                        Spacer(Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            // Brightness Slider simulation
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White.copy(alpha = 0.4f))
                                    .padding(horizontal = 12.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Icon(Icons.Default.WbSunny, contentDescription = null, tint = Color.White)
                                Box(modifier = Modifier.fillMaxWidth(0.6f).fillMaxHeight().background(Color.White.copy(alpha = 0.5f)))
                            }
                            Spacer(Modifier.width(16.dp))
                            // Volume Slider simulation
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White.copy(alpha = 0.4f))
                                    .padding(horizontal = 12.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Icon(Icons.Default.VolumeUp, contentDescription = null, tint = Color.White)
                                Box(modifier = Modifier.fillMaxWidth(0.4f).fillMaxHeight().background(Color.White.copy(alpha = 0.5f)))
                            }
                        }
                    }
                }
            }
        }
    }
}