package com.example.tracker_data.repository

import com.example.tracker_data.local.TrackerDao
import com.example.tracker_data.mapper.toTrackableFood
import com.example.tracker_data.mapper.toTrackedFood
import com.example.tracker_data.mapper.toTrackedFoodEntity
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepositoryImpl @Inject constructor(
  val dao: TrackerDao,
  val api: OpenFoodApi
) : TrackerRepository {
  override suspend fun searchFood(
    searchText: String,
    page: Int,
    pageSize: Int
  ): Result<List<TrackableFood>> {
    return try {
      val searchApiResult = api.searchFood(
        searchText,
        page,
        pageSize
      )
      val trackableFood = searchApiResult.products.map {
        it.toTrackableFood()
      }
      Result.success(trackableFood)

    } catch (e: Exception) {
      e.printStackTrace()
      Result.failure(e)
    }
  }

  override suspend fun insertTrackedFood(food: TrackedFood) {
    dao.insertTrackedFood(food.toTrackedFoodEntity())
  }

  override suspend fun deleteTrackedFood(food: TrackedFood) {
    dao.deleteTrackedFood(food.toTrackedFoodEntity())
  }

  override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
    return dao.getFoodsForDate(localDate.dayOfMonth, localDate.monthValue, localDate.year)
      .map {
        it.map {
          it.toTrackedFood()
        }
      }
  }
}
