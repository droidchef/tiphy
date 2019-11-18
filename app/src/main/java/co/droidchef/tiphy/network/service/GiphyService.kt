package co.droidchef.tiphy.network.service

import co.droidchef.tiphy.network.model.response.TrendingGifsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {

    @GET("v1/gifs/trending?api_key=TSxdRZUaZZY56ujP08AYya50gDmtmeV1")
    fun getTrendingGifs(@Query("offset") page: Long, @Query("limit") pageSize: Long) : Single<TrendingGifsResponse>

}