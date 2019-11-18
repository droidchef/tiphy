package co.droidchef.tiphy.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.droidchef.tiphy.feature.trending.viewmodel.TrendingGifsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass


@Singleton
class TiphyViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrendingGifsViewModel::class)
    internal abstract fun bindTrendingGifsActivityViewModel(viewModel: TrendingGifsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: TiphyViewModelFactory) : ViewModelProvider.Factory

}