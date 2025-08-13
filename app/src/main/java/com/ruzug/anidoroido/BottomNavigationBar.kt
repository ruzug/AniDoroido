package com.ruzug.anidoroido

import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar(
    index: Int,
    bottomNavigation: BottomNavigationEvents = BottomNavigationEvents(),
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        windowInsets = NavigationBarDefaults.windowInsets,
        modifier = modifier.imePadding()
    ) {
        var i = 0
        NavigationBarItem(
            selected = index == i++,
            onClick = { bottomNavigation.navigateHome() },
            icon = { Icon(
                Icons.Default.Home,
                contentDescription = "Home"
            )},
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = index == i++,
            onClick = { bottomNavigation.navigateLogin() },
            icon = { Icon(
                Icons.Default.AccountCircle,
                contentDescription = "Login"
            )},
            label = { Text("Login") }
        )
        NavigationBarItem(
            selected = index == i++,
            onClick = { bottomNavigation.navigateAnimeList() },
            icon = { Icon(
                imageVector = ImageVector.vectorResource(R.drawable.rounded_movie_24),
                contentDescription = "Anime List"
            )},
            label = { Text("Anime List") }
        )
        NavigationBarItem(
            selected = index == i++,
            onClick = { bottomNavigation.navigateMangaList() },
            icon = { Icon(
                imageVector = ImageVector.vectorResource(R.drawable.rounded_menu_book_24),
                contentDescription = "Manga List"
            )},
            label = { Text("Manga List") }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    BottomNavigationBar(1)
}