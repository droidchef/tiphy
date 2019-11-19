package co.droidchef.tiphy

import co.droidchef.spyspanner.arch.UseCaseOutput
import co.droidchef.tiphy.feature.trending.usecase.FetchTrendingGifsUseCaseBuilder
import co.droidchef.tiphy.network.model.response.TrendingGifsResponse
import co.droidchef.tiphy.network.service.GiphyService
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins



class FetchTrendingGifsUseCase {

    private val giphyService: GiphyService = mockk(relaxed = true)

    private val trendingGifsResponse : TrendingGifsResponse = mockk(relaxed = true)

    private lateinit var fetchTrendingGifsUseCaseBuilder: FetchTrendingGifsUseCaseBuilder

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        fetchTrendingGifsUseCaseBuilder = FetchTrendingGifsUseCaseBuilder(giphyService)

    }

    @Test
    fun `test if successful output is returned by the usecase`() {

        every { giphyService.getTrendingGifs(0, 25) } returns Single.just(trendingGifsResponse)

        fetchTrendingGifsUseCaseBuilder.withScheduler(Schedulers.trampoline()).build().invoke().test().assertValue {
            return@assertValue it is UseCaseOutput.Success
        }
    }

}