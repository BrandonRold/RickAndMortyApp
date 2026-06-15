package com.brs.rickyandmorthy.presentation.feature.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.brs.rickyandmorthy.presentation.feature.character.list.CharactersListScreen
import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit,

) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Favoritos") },
                    navigationIcon = {

            }
            )
        }
    ) { padding ->
        Column (modifier = Modifier.padding(padding)) {
            var query by remember { mutableStateOf ("") }
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.search(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text("Busca tu personaje") }
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.favorites) { character ->
                    FavoriteRow(
                        character = character,
                        onClick = { onNavigateToDetail(character.id) },
                        onDelete = { viewModel.requestRemove(character) }
                    )
                }
            }
        }

        if (state.showConfirmDialog) {
            val pending = state.pendingDelete
            AlertDialog(
                onDismissRequest = { viewModel.cancelRemove() },
                title = { Text("Remover favorito") },
                text = { Text("Estas seguro de remover a ${pending?.name ?: "de tus "} favoritos?") },
                confirmButton = {
                    TextButton(onClick = { viewModel.confirmRemove() }) { Text("Remover") }
                },
                dismissButton = {
                    TextButton(onClick = { viewModel.cancelRemove() }) { Text("Cancelar") }
                }
            )
        }
    }
}

@Composable
private fun FavoriteRow(
    character: CharacterUi,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Row(modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer( modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "${character.species} • ${character.status}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}