package co.droidchef.tiphy.feature.trending.usecase

import co.droidchef.spyspanner.arch.UseCase
import co.droidchef.spyspanner.arch.UseCaseOutput
import co.droidchef.tiphy.network.model.response.TrendingGifsResponse
import co.droidchef.tiphy.network.service.GiphyService
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FetchTrendingGifsUseCase(
    private val giphyService: GiphyService,
    private val pageNumber: Long,
    private val pageSize: Long,
    private val schedulers: Scheduler
) : UseCase<TrendingGifsResponse> {

    override fun invoke(): Single<UseCaseOutput<TrendingGifsResponse, Throwable>> {
        return giphyService.getTrendingGifs(pageNumber, pageSize).flatMap {
            if (it.giphyList.isEmpty()) {
                Single.just(UseCaseOutput.Failure(Throwable("Nothing is Trending")))
            } else {
                Single.just(UseCaseOutput.Success(it))
            }
        }
            .subscribeOn(schedulers)
            .observeOn(schedulers)
    }
}
