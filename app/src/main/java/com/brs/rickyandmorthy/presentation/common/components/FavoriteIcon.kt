package com.brs.rickyandmorthy.presentation.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteIcon(modifier: Modifier){
    FloatingActionButton(
        modifier = Modifier
            .size(30.dp),
        containerColor = Color.Transparent,
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp),
        onClick ={println("Agregado a favorito")} )
    {
        Icon(Icons.Default.Favorite , "Favorito")

    }
}