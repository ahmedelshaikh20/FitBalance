package com.example.tracker_domain.usecases

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import javax.inject.Inject

class DeleteTrackedFood @Inject constructor(
  private val repository: TrackerRepository
) {

  suspend operator fun invoke(
    food: TrackedFood
  ) {
    repository.deleteTrackedFood(food)
  }
}
