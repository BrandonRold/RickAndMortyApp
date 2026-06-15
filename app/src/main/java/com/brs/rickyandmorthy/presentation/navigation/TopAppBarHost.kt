package com.brs.rickyandmorthy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHost(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (val currentRoute = navBackStackEntry?.destination?.route) {
        NavRoutes.CHARACTERS -> {
            CenterAlignedTopAppBar(title = { Text("Characters") })
        }
        NavRoutes.FAVORITES -> {
            CenterAlignedTopAppBar(title = { Text("Favorites") })
        }
        else -> {

            if (currentRoute?.startsWith("${NavRoutes.DETAIL}/") == true || currentRoute == "${NavRoutes.DETAIL}/{id}") {
                CenterAlignedTopAppBar(
                    title = { Text("Detail") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back")
                        }
                    }
                )
            } else {
                CenterAlignedTopAppBar(title = { Text("Rick and Morty") })
            }
        }
    }
}