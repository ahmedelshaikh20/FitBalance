package com.example.fitbalance.navigation

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.core.util.UiEvent


fun NavController.navigate(event: UiEvent.Navigate) {
  this.navigate(event.route)
}
