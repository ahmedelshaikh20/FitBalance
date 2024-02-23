package com.example.tracker_presentation.search.state

import com.example.tracker_domain.model.TrackableFood

data class SearchState(
  val searchQuery: String = "",
  val isHintVisible: Boolean = true,
  val isSearching: Boolean = false,
  val trackableFood: List<TrackableFoodUiState> = emptyList()
)
