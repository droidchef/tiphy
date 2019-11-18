package co.droidchef.tiphy.feature.trending.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import co.droidchef.spyspanner.arch.BaseViewModel
import co.droidchef.spyspanner.arch.UseCaseOutput
import co.droidchef.tiphy.feature.trending.usecase.FetchTrendingGifsUseCaseBuilder
import co.droidchef.tiphy.network.model.response.Giphy
import javax.inject.Inject

class TrendingGifsViewModel @Inject constructor(
    private val fetchTrendingGifsUseCaseBuilder: FetchTrendingGifsUseCaseBuilder
) : BaseViewModel() {

    private val _giphiesLiveData = MutableLiveData<ArrayList<Giphy>>()

    val giphiesLiveData : LiveData<ArrayList<Giphy>> = _giphiesLiveData

    var pageNumber = 0L

    var pageSize = 25L

    fun showTrendingGifs() {

        val disposable = fetchTrendingGifsUseCaseBuilder
            .forPage(pageNumber)
            .build()
            .invoke()
            .subscribe {
                it ->
                when (it) {
                    is UseCaseOutput.Success -> {
                        _giphiesLiveData.postValue(it.result.giphyList)
                    }
                    is UseCaseOutput.Failure -> {
                        // TODO Handle Failure
                    }
                }
            }

        disposables.add(disposable)

    }

    fun loadMore() {

        val disposable = fetchTrendingGifsUseCaseBuilder
            .forPage(pageNumber+pageSize)
            .withPageSize(incrementAndGetPageSize())
            .build()
            .invoke()
            .subscribe {
                    it ->
                when (it) {
                    is UseCaseOutput.Success -> {
                        _giphiesLiveData.postValue(it.result.giphyList)
                    }
                    is UseCaseOutput.Failure -> {
                        // TODO Handle Failure
                    }
                }
            }

        disposables.add(disposable)

    }

    private fun incrementAndGetPageSize() : Long{
        pageSize += 25
        return pageSize
    }


}
