package com.brs.rickyandmorthy.presentation.common.components.topbar


import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustomized(
    state: TopBarState,
    onBack: () -> Unit = {},
    onMenu: () -> Unit = {}
){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,

        ),
        title = {
            Column() {
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(text = state.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        },
        navigationIcon = {
            if (state.showBackButton){
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBackIosNew , contentDescription = "Back")
                }
            }
        },
        actions = {
            if (state.showMenuButton){
                IconButton(onClick = onMenu) {
                    Icon(Icons.Default.Menu , contentDescription = "Options")
                }
            }
        }


    )
}