package com.brs.rickyandmorthy.presentation.feature.character.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.rememberAsyncImagePainter
import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
    viewModel: CharactersListViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToFavorites: () -> Unit 
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val characters = viewModel.charactersPagingData.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Personajes") },
                actions = {
                    IconButton(onClick = onNavigateToFavorites) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favoritos")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.setQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Buscar personaje") },
                trailingIcon = {
                    IconButton(onClick = { viewModel.search(state.searchQuery) }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                }
            )

            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        count = characters.itemCount,
                        key = characters.itemKey { it.id }
                    ) { index ->
                        val character = characters[index]
                        if (character != null) {
                            CharacterRow(
                                character = character,
                                onClick = { onNavigateToDetail(character.id) },
                                onFavoriteClick = { viewModel.toggleFavorite(character) }
                            )
                        }
                    }

                    // Loading more state
                    when (val loadState = characters.loadState.append) {
                        is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                        is LoadState.Error -> {
                            item {
                                Text(
                                    text = loadState.error.message ?: "Error al cargar más",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                        else -> {}
                    }
                }

                // Initial load state
                when (val loadState = characters.loadState.refresh) {
                    is LoadState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is LoadState.Error -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = loadState.error.message ?: "Ocurrió un error")
                        }
                    }
                    else -> {
                        if (characters.itemCount == 0 && characters.loadState.append is LoadState.NotLoading) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(text = "No se encontraron personajes")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterRow(
    character: CharacterUi,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "${character.species} • ${character.status}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onFavoriteClick) {
                Icon(Icons.Default.Favorite, contentDescription = "Agregar a favoritos")
            }
        }
    }
}
