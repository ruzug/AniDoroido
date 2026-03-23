package com.yzr.anidoroido

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzr.anidoroido.data.repository.AnimeRepository
import com.yzr.anidoroido.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val data: AnimeRepository,
): ViewModel() {

    val uiState: StateFlow<MainUiState> =
        data.getAnime()
            .map<List<Anime>, MainUiState>(MainUiState::Success)
            .onStart {
                syncAnimeList()
                emit(MainUiState.Loading)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MainUiState.Loading,
            )

    fun syncAnimeList() {
        viewModelScope.launch {
            data.syncAnimeList()
        }
    }
}

sealed interface MainUiState {
    data object Loading: MainUiState
    data class Success(
        val animeList: List<Anime>
    ): MainUiState
}