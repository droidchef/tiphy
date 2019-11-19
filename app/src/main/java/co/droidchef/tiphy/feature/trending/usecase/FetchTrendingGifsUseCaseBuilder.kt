package co.droidchef.tiphy.feature.trending.usecase

import co.droidchef.spyspanner.arch.UseCase
import co.droidchef.spyspanner.arch.UseCaseBuilder
import co.droidchef.tiphy.network.model.response.TrendingGifsResponse
import co.droidchef.tiphy.network.service.GiphyService
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class FetchTrendingGifsUseCaseBuilder @Inject constructor(private val giphyService: GiphyService) :
    UseCaseBuilder<TrendingGifsResponse> {

    private var pageNumber : Long = 0

    private var pageSize : Long = 25

    fun forPage(pageNumber: Long): FetchTrendingGifsUseCaseBuilder {
        this.pageNumber = pageNumber
        return this
    }

    fun withPageSize(pageSize: Long) : FetchTrendingGifsUseCaseBuilder {
        this.pageSize = pageSize
        return this
    }

    private var scheduler: Scheduler = Schedulers.io()

    fun withScheduler(scheduler: Scheduler): FetchTrendingGifsUseCaseBuilder {
        this.scheduler = scheduler
        return this
    }


    override fun build(): UseCase<TrendingGifsResponse> {
        return FetchTrendingGifsUseCase(giphyService, pageNumber, pageSize, scheduler)
    }

}