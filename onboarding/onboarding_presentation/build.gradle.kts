plugins{
  `android-library`
  `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android{
  namespace="com.example.onboarding_presentation"
}

dependencies{
  implementation(project(Modules.core))
  implementation(project(Modules.onboardingDomain))
  implementation(project(Modules.coreUi))
  debugImplementation("androidx.compose.ui:ui-tooling:1.6.0")

}
