package co.droidchef.tiphy.network

import co.droidchef.tiphy.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
class NetworkFactory {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun createOkHttpClient(interceptors: List<Interceptor>? = null): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            followRedirects(false)
            followSslRedirects(false)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
        }

        interceptors?.map {
            okHttpClientBuilder.addInterceptor(it)
        }

        return okHttpClientBuilder.build()

    }

    val okHttpClient: OkHttpClient by lazy {
        return@lazy createOkHttpClient()
    }


}