package com.ruzug.anidoroido.home.impl

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzr.anidoroido.data.repository.AnimeRepository
import com.yzr.anidoroido.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY, "")

    val uiState: StateFlow<HomeUiState> =
        searchQuery.flatMapLatest { query ->
            if (query.isEmpty()) {
                animeRepository.getAnime().map<List<Anime>, HomeUiState> { animeList ->
                    HomeUiState.EmptyQuery(animeList)
                }.catch { emit(HomeUiState.Error) }
            } else {
                animeRepository.getAnime().map<List<Anime>, HomeUiState> { animeList ->
                    HomeUiState.Success(animeList)
                }.catch { emit(HomeUiState.Error) }
            }

        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading,
        )

    init {
        viewModelScope.launch {
            animeRepository.syncAnimeList()
        }
    }
}

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val animeList: List<Anime>) : HomeUiState
    data class EmptyQuery(val animeList: List<Anime>) : HomeUiState
    data object Error : HomeUiState
}

private const val SEARCH_QUERY = "searchQuery"
