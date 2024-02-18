plugins {
  `android-library`
  `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
  namespace = "com.example.tracker_presentation"
}

dependencies {
  implementation(project(Modules.coreUi))
  implementation(project(Modules.core))

  implementation(project(Modules.trackerDomain))

  implementation(Coil.coilCompose)
  debugImplementation("androidx.compose.ui:ui-tooling:1.6.0")
}
