package com.brs.rickyandmorthy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.brs.rickyandmorthy.presentation.feature.character.detail.CharacterDetailScreen
import com.brs.rickyandmorthy.presentation.feature.character.list.CharactersListScreen
import com.brs.rickyandmorthy.presentation.feature.favorites.FavoritesScreen

@Composable
fun AppNavGraph(navController: NavHostController, startDestination: String = NavRoutes.CHARACTERS , modifier: Modifier) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavRoutes.CHARACTERS) {
            CharactersListScreen(
                onNavigateToDetail = { id -> navController.navigate("${NavRoutes.DETAIL}/$id") },
                onNavigateToFavorites = { navController.navigate(NavRoutes.FAVORITES) }
            )
        }
        composable(
            route = "${NavRoutes.DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            CharacterDetailScreen(
                characterId = id,
                onBack = { navController.popBackStack() }
            )
        }
        composable(NavRoutes.FAVORITES) {
            FavoritesScreen(onNavigateToDetail = { id -> navController.navigate("${NavRoutes.DETAIL}/$id") })
        }
    }
}