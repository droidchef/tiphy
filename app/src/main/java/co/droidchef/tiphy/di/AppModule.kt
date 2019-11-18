package co.droidchef.tiphy.di

import co.droidchef.tiphy.network.di.NetworkModule
import co.droidchef.tiphy.network.di.NetworkServicesModule
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        NetworkServicesModule::class,
        ViewModelModule::class
    ]
)
class AppModule