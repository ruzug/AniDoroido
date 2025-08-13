package com.ruzug.anidoroido

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.ruzug.anidoroido.list.AnimeList
import com.ruzug.anidoroido.list.AnimeListScreen
import com.ruzug.anidoroido.home.Home
import com.ruzug.anidoroido.home.HomeScreen
import com.ruzug.anidoroido.list.MangaList
import com.ruzug.anidoroido.list.MangaListScreen
import com.ruzug.anidoroido.login.Login
import com.ruzug.anidoroido.login.LoginScreen

@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(Home)

    val bottomNav = BottomNavigationEvents(
        navigateHome = { navigateTo(backStack, Home) },
        navigateLogin = { navigateTo(backStack, Login) },
        navigateAnimeList = { navigateTo(backStack, AnimeList) },
        navigateMangaList = { navigateTo(backStack, MangaList) },
    )
    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<Home> {
                HomeScreen (
                    bottomNav = bottomNav
                )
            }
            entry<Login> {
                LoginScreen(
                    bottomNav = bottomNav
                )
            }
            entry<AnimeList> {
                AnimeListScreen(
                    bottomNav = bottomNav
                )
            }
            entry<MangaList> {
                MangaListScreen(
                    bottomNav = bottomNav
                )
            }
        }
    )
}

data class BottomNavigationEvents (
    val navigateHome: () -> Unit = { },
    val navigateLogin: () -> Unit = { },
    val navigateAnimeList: () -> Unit = { },
    val navigateMangaList: () -> Unit = { },
)

private fun navigateTo(navBackStack: NavBackStack, navKey: NavKey) {
    if (navBackStack.last() != navKey) {
        navBackStack.removeIf { nav ->
            nav == navKey && navKey !is Home || navBackStack.count { it == Home } > 1
        }
        navBackStack.add(navKey)
    }
}