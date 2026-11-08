package com.example.recipecomposeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    // Состояние для хранения Intent
    private var deepLinkIntent by mutableStateOf<Intent?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Проверяем, запущено ли приложение по ссылке
        deepLinkIntent = intent

        setContent {
            RecipesApp(deepLinkIntent = deepLinkIntent)
        }
    }

    // Вызывается, если приложение уже открыто (warm start) и пришла новая ссылка
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        deepLinkIntent = intent
    }
}