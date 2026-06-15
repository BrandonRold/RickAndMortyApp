package com.brs.rickyandmorthy

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.brs.rickyandmorthy.presentation.feature.character.list.component.CharacterCard
import com.brs.rickyandmorthy.presentation.navigation.AppNavGraph
import com.brs.rickyandmorthy.presentation.navigation.BottomBar
import com.brs.rickyandmorthy.presentation.navigation.TopAppBarHost
import com.brs.rickyandmorthy.ui.theme.RickyAndMorthyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                MyApp()
        }
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MyApp(){
    MaterialTheme() {
        Surface {
            val navController = rememberNavController()
            Scaffold(
                topBar = { TopAppBarHost(navController = navController) },
                bottomBar = { BottomBar(navController = navController, modifier = Modifier) }
            ) { innerPadding ->
                AppNavGraph(navController = navController , modifier = Modifier.padding(innerPadding))
            }
        }
    }
}


