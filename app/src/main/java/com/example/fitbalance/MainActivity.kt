package com.example.fitbalance

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.routes
import com.example.fitbalance.navigation.navigate
import com.example.fitbalance.ui.theme.FitbalanceTheme
import com.example.onboarding_presentation.age.screen.AgeScreen
import com.example.onboarding_presentation.gender.screen.GenderScreen
import com.example.onboarding_presentation.height.screen.HeightScreen
import com.example.onboarding_presentation.weight.screen.WeightScreen
import com.example.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FitbalanceTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          val scaffoldState = rememberScaffoldState()
          Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
            val navController = rememberNavController()
            val navHost =
              NavHost(navController = navController, startDestination = routes.WELCOME) {
                composable(routes.WELCOME) {
                  WelcomeScreen(onNavigate = navController::navigate)
                }
                composable(routes.GENDER) {
                  GenderScreen(onNavigate = navController::navigate)
                }
                composable(routes.AGE) {
                  AgeScreen(scaffoldState, onNavigate = navController::navigate)
                }
                composable(routes.HEIGHT) {
                  HeightScreen(scaffoldState, onNavigate = navController::navigate)
                }
                composable(routes.WEIGHT) {
                  WeightScreen(scaffoldState, onNavigate = navController::navigate)
                }
              }
          }

        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  FitbalanceTheme {
    Greeting("Android")
  }
}
