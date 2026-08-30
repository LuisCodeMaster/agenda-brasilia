package com.luiscode.agendabrasilia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luiscode.agendabrasilia.ui.HomeScreen
import com.luiscode.agendabrasilia.ui.SettingsScreen
import com.luiscode.agendabrasilia.ui.theme.AgendaBrasiliaTheme
import com.luiscode.agendabrasilia.ui.theme.AppTheme

@Composable
fun App() {
    var currentTheme by remember {
        mutableStateOf(AppTheme.LIGHT)
    }

    var showSettings by remember {
        mutableStateOf(false)
    }

    AgendaBrasiliaTheme(
        theme = currentTheme
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (showSettings) {
                SettingsScreen(
                    currentTheme = currentTheme,
                    onThemeSelected = { theme ->
                        currentTheme = theme
                    }
                )
            } else {
                HomeScreen()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Button(
                    onClick = {
                        showSettings = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("🏠 Início")
                }

                Button(
                    onClick = {
                        showSettings = true
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("⚙️ Configurações")
                }
            }
        }
    }
}
