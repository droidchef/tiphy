package co.droidchef.tiphy.di

import co.droidchef.tiphy.network.di.NetworkModule
import dagger.Module

@Module(
    includes = [
        NetworkModule::class
    ]
)
class AppModule {
}