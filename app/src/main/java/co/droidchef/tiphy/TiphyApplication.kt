package co.droidchef.tiphy

import android.app.Application
import co.droidchef.tiphy.di.ApplicationComponent
import co.droidchef.tiphy.di.DaggerApplicationComponent

class TiphyApplication : Application() {

    private val component: ApplicationComponent = DaggerApplicationComponent.builder().build()

    override fun onCreate() {
        super.onCreate()

        component.inject(this)
    }
}