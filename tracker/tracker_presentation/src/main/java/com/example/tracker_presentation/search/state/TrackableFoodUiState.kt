package com.example.tracker_presentation.search.state

import com.example.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
  val food: TrackableFood,
  val isExpanded: Boolean = false,
  val amount: String = ""
)
