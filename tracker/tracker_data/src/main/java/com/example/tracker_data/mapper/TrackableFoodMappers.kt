package com.example.tracker_data.mapper

import com.example.tracker_data.remote.dto.Nutriments
import com.example.tracker_data.remote.dto.Product
import com.example.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood {
  return TrackableFood(
    name = productName,
    caloriesPer100g = nutriments.energyKcal100g.roundToInt(),
    carbsPer100g = nutriments.carbohydrates100g.roundToInt(),
    fatPer100g = nutriments.fat100g.roundToInt(),
    proteinPer100g = nutriments.proteins100g.roundToInt(),
    imageUrl = imageFrontThumbUrl

  )
}

fun TrackableFood.toProduct(): Product {
  return Product(
    productName = name,
    nutriments = Nutriments(
      carbohydrates100g = carbsPer100g.toDouble(),
      proteins100g = proteinPer100g.toDouble(),
      fat100g = fatPer100g.toDouble(),
      energyKcal100g = caloriesPer100g.toDouble()
    ),
    imageFrontThumbUrl = imageUrl.toString()

  )
}
