package com.ruzug.anidoroido.home.impl

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yzr.anidoroido.model.Anime

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
) {
    when(uiState) {
        HomeUiState.Error -> ShowError(modifier)
        HomeUiState.Loading -> Loading(modifier)
        is HomeUiState.Success -> FilteredList(modifier, uiState.animeList)
        is HomeUiState.EmptyQuery -> ShowAll(modifier, uiState.animeList)
    }
}

@Composable
fun ShowError(modifier: Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "No se ha encontrado ningún valor",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Loading(modifier: Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "Cargando",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ShowAll(modifier: Modifier, animeList: List<Anime>) {
    LazyColumn(
        modifier = modifier.padding(8.dp)
    ) {
        CategoryWithAnimeList("Trending", animeList)
        CategoryWithAnimeList("Popular", animeList)
    }
}

fun LazyListScope.CategoryWithAnimeList(title: String, animeList: List<Anime>) {
    item {
        CategoryTitle(title)
        Spacer(Modifier.height(12.dp))
        AnimeListRow(animeList)
    }
}

@Composable
private fun CategoryTitle(title: String) {
    Text(
        text = title.uppercase(),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    )
}

@Composable
private fun AnimeListRow(animeList: List<Anime>) {
    val state = rememberLazyListState()
    LazyRow(
        state = state,
        flingBehavior = rememberSnapFlingBehavior(state),
        modifier = Modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(animeList, key = { it.id }) { anime ->
            Column (modifier = Modifier.fillParentMaxWidth(1/3f)) {
                Box(
                    modifier = Modifier.aspectRatio(4f/6f)
                ) {
                    AnidoroidoAsyncImage(
                        contentScale = ContentScale.Crop,
                        model = anime.imageUrl,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = anime.name,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    minLines = 2,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp).fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun FilteredList(modifier: Modifier, animeList: List<Anime>) {
    // TODO Unimplemented
}

@PhonePreview
@Composable
fun PreviewHomeScreenError() {
    HomeScreen(
        uiState = HomeUiState.Error
    )
}

@Preview(name = "phone",  showSystemUi = true)
@Composable
fun PreviewHomeSuccess() {
    Scaffold {
        HomeScreen(
            modifier = Modifier.padding(it),
            uiState = HomeUiState.EmptyQuery(
                listOf(
                    Anime(0, "Gurren Lagann", ""),
                    Anime(0, "Boku no Hero Academia", ""),
                    Anime(0, "Shingeki no Kyojin", ""),
                    Anime(0, "Code Geass", ""),
                    Anime(0, "One Piece", ""),
                )
            )
        )
    }
}
