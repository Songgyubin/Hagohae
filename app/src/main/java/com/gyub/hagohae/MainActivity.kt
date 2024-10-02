package com.gyub.hagohae

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.gyub.hagohae.home.HomeScreen
import net.skyscanner.backpack.compose.theme.BpkTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BpkTheme {
                Scaffold(
                    contentColor = BpkTheme.colors.surfaceDefault
                ) { innerPadding ->
                    HomeScreen(innerPadding, navigateMissionDetail)
                }
            }
        }
    }
}