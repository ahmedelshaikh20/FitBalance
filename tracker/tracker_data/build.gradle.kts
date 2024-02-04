plugins {
  `android-library`
  `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

dependencies {
  implementation(project(Modules.core))
  implementation(project(Modules.trackerDomain))

  implementation(Retrofit.okHttp)
  implementation(Retrofit.retrofit)
  implementation(Retrofit.okHttpLoggingInterceptor)
  implementation(Retrofit.moshiConverter)

  "kapt"(Room.roomCompiler)
  implementation(Room.roomKtx)
  implementation(Room.roomRuntime)
}
android{
  namespace="com.example.tracker_data"
}

