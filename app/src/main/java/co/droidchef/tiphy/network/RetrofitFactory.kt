package co.droidchef.tiphy.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitFactory @Inject constructor(
    private val networkFactory: NetworkFactory
) {
    private fun create(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
        val converterFactory = GsonConverterFactory.create(gson.create())
        val callAdapterFactory = RxJava2CallAdapterFactory.create()

        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            client(okHttpClient)
            addConverterFactory(converterFactory)
            addCallAdapterFactory(callAdapterFactory)
        }.build()
    }

    val retrofit: Retrofit by lazy {
        return@lazy create(
            GIPHY_BASE_URL,
            networkFactory.okHttpClient
        )
    }

    companion object {

        const val GIPHY_BASE_URL = "https://api.giphy.com/"

    }

}