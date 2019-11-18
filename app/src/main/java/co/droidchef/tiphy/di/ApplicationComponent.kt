package co.droidchef.tiphy.di

import co.droidchef.tiphy.TiphyApplication
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
}