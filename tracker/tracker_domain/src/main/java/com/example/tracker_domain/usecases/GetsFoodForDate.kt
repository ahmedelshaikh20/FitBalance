package com.example.tracker_domain.usecases

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetsFoodForDate @Inject constructor(
  private val repository: TrackerRepository
) {

  suspend operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
    return repository.getFoodsForDate(date)
  }
}
