package com.ruzug.anidoroido.home

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import coil3.compose.AsyncImage
import com.example.compose.AniDoroidoTheme
import com.ruzug.anidoroido.BottomNavigationBar
import com.ruzug.anidoroido.BottomNavigationEvents
import com.ruzug.anidoroido.R
import com.ruzug.anidoroido.datatmp.Anime
import com.ruzug.anidoroido.datatmp.SampleData
import kotlinx.serialization.Serializable

@Serializable
data object Home: NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    bottomNav: BottomNavigationEvents = BottomNavigationEvents(),
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                index = 0,
                bottomNavigation = bottomNav
            )
        }
    ) { padding ->
        body(viewModel, uiState, Modifier
            .padding(padding)
            .fillMaxSize()
        )
    }
}

@Composable
private fun body(
    viewModel: HomeViewModel,
    uiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        val foundAnime = SampleData.simpleAnimeList.filter {
            it.name.contains(uiState.searchText)
        }
        item {
            SearchBarWithButton(viewModel, uiState)
        }
        if (uiState.searchText.isEmpty()) {
            defaultBody()
        } else if (uiState.searchText.isNotEmpty() && foundAnime.isNotEmpty()) {
            foundSearch(foundAnime)
        } else {
            emptySearch()
        }
    }
}

private fun LazyListScope.defaultBody() {
    category("Trending Now", SampleData.simpleAnimeList)
    category("Popular", SampleData.simpleAnimeList)
    category("Seasonal", SampleData.simpleAnimeList)
}

private fun LazyListScope.foundSearch(animeList: List<Anime>) {
    item {
        AnimeSet(animeList)
    }
}

private fun LazyListScope.emptySearch() {
    item {
        Text(
            text = "No se ha encontrado nada.",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SearchBarWithButton(
    viewModel: HomeViewModel,
    uiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    Row {
        OutlinedTextField(
            value = uiState.searchText,
            onValueChange = { viewModel.updateSearchText(it) },
            label = { Text("Search") },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            },
            trailingIcon = {
                if (uiState.searchText.trim().isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear, contentDescription = "",
                        modifier = Modifier.clickable {
                            viewModel.updateSearchText("")
                        }
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = modifier.weight(1f)
        )
        OutlinedIconButton (
            onClick = { },
            modifier = Modifier.align(Alignment.CenterVertically)
                .padding(top = 8.dp, start = 8.dp)
                .width(56.dp)
                .height(56.dp),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary)
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = ""
            )
        }
    }
}

private fun LazyListScope.category(title: String, animeList: List<Anime>) {
    item {
        CategoryTitle(title)
    }
    item {
        AnimeSet(animeList)
    }
}

@Composable
private fun CategoryTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title.uppercase(),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier.fillMaxWidth()
    )
    Spacer(modifier.height(12.dp))
}

@Composable
private fun AnimeSet(
    animeList: List<Anime>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(animeList) { anime ->
            Anime(
                anime = anime,
                modifier = modifier
                    .fillParentMaxWidth(fraction = 0.33f)
            )
        }
    }
}

@Composable
private fun Anime(anime: Anime, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.aspectRatio(0.69f)
        ) {
            AsyncImage(
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
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 12.dp).fillMaxWidth()
        )
    }
}

@Preview(name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun PreviewHomeScreen() {
    AniDoroidoTheme {
        Surface {
            HomeScreen()
        }
    }
}