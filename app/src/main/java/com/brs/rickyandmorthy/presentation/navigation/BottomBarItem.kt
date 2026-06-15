package com.brs.rickyandmorthy.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(val route: String, val label: String, val icon: ImageVector) {
    object Characters : BottomBarItem(NavRoutes.CHARACTERS, "Characters", Icons.Default.Person)
    object Favorites : BottomBarItem(NavRoutes.FAVORITES, "Favorites", Icons.Default.Favorite)
}