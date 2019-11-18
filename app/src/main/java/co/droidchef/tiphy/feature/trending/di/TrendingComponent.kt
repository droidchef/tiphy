package co.droidchef.tiphy.feature.trending.di

import co.droidchef.tiphy.feature.trending.view.TrendingGifsActivity
import dagger.Subcomponent

@Subcomponent(
    modules = [
        TrendingModule::class
    ]
)
interface TrendingComponent {

    fun inject(activity: TrendingGifsActivity)

}