package com.example.tracker_data.repository

import com.example.tracker_data.remote.OpenFoodApi
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class TrackerRepositoryImplTest {

  lateinit var repository: TrackerRepositoryImpl
  lateinit var mockkWebServer: MockWebServer
  lateinit var api: OpenFoodApi
  lateinit var okHttpClient: OkHttpClient

  @Before
  fun setUp() {
    mockkWebServer = MockWebServer()
    okHttpClient = OkHttpClient.Builder()
      .writeTimeout(1, TimeUnit.SECONDS)
      .readTimeout(1, TimeUnit.SECONDS)
      .connectTimeout(1, TimeUnit.SECONDS)
      .build()
    api = Retrofit.Builder()
      .addConverterFactory(MoshiConverterFactory.create())
      .baseUrl(mockkWebServer.url("/"))
      .client(okHttpClient)
      .build()
      .create(OpenFoodApi::class.java)

    repository = TrackerRepositoryImpl(
      dao = mockk(relaxed = true),
      api = api
    )
  }

  @After
  fun tearDown() {
    mockkWebServer.shutdown()
  }


  @Test
  fun `Search food , valid Response , return Results`() = runBlocking {
    mockkWebServer.enqueue(
      MockResponse().setResponseCode(200)
        .setBody(validFoodResponse)
    )
    val results = repository.searchFood("apple", 1, 40)
    assertThat(results.isSuccess).isTrue()
  }

}
