package co.droidchef.tiphy.di

import co.droidchef.tiphy.TiphyApplication
import co.droidchef.tiphy.feature.trending.di.TrendingComponent
import co.droidchef.tiphy.feature.trending.di.TrendingModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: TiphyApplication)

    fun addTrendingComponent(trendingModule: TrendingModule): TrendingComponent
}