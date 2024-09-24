package com.example.hagohae

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.example.hagohae.home.ui.HomeScreen
import com.example.hagohae.ui.theme.HagohaeTheme
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
                    HomeScreen(innerPadding)
                }
            }
        }
    }
}