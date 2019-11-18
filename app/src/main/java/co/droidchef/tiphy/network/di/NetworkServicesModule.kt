package co.droidchef.tiphy.network.di

import co.droidchef.tiphy.network.RetrofitFactory
import co.droidchef.tiphy.network.service.GiphyService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class
    ]
)
class NetworkServicesModule {

    @Provides
    @Singleton
    internal fun provideGiphyService(retrofitFactory: RetrofitFactory): GiphyService {
        return retrofitFactory.retrofit.create(GiphyService::class.java)
    }
}