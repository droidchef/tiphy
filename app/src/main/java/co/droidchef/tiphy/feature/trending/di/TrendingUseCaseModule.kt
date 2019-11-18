package co.droidchef.tiphy.feature.trending.di

import co.droidchef.tiphy.feature.trending.usecase.FetchTrendingGifsUseCaseBuilder
import co.droidchef.tiphy.network.service.GiphyService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TrendingUseCaseModule {

    @Provides
    @Singleton
    fun provideFetchTrendingGifsUseCaseBuilder(giphyService: GiphyService) =
        FetchTrendingGifsUseCaseBuilder(giphyService)
}