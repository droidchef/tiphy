package co.droidchef.tiphy.network.di

import co.droidchef.tiphy.network.NetworkFactory
import co.droidchef.tiphy.network.RetrofitFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideNetworkFactory() = NetworkFactory()

    @Provides
    @Singleton
    internal fun provideRetrofitFactory(networkFactory: NetworkFactory) =
        RetrofitFactory(networkFactory)

}