package br.ftoniolo.chucknorrisfacts.model.network

import br.ftoniolo.chucknorrisfacts.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class WebClient() {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            var httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()

            val client = httpClient
                .addInterceptor(logging)
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS)
                .build()

            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_CHUCK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api: ChuckApiService by lazy {
            retrofit.create(ChuckApiService::class.java)
        }

    }

}