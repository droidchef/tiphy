package co.droidchef.tiphy.feature.trending.view

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import co.droidchef.tiphy.R
import co.droidchef.tiphy.TiphyApplication
import co.droidchef.tiphy.feature.detail.GifDetailActivity
import co.droidchef.tiphy.feature.trending.di.TrendingModule
import co.droidchef.tiphy.feature.trending.viewmodel.TrendingGifsViewModel
import co.droidchef.tiphy.network.model.response.Giphy
import javax.inject.Inject


class TrendingGifsActivity : AppCompatActivity(), OnGiphyClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TrendingGifsViewModel

    private lateinit var rvGifsPreviewList: RecyclerView

    private lateinit var gifPreviewAdapter: GifPreviewAdapter

    private lateinit var layoutManager: GridLayoutManager

    var pastVisiblesItems: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        (application as TiphyApplication).component.addTrendingComponent(TrendingModule())
            .inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[TrendingGifsViewModel::class.java]

        rvGifsPreviewList = findViewById(R.id.rvGifsGrid)
        layoutManager = GridLayoutManager(this, 3)
        rvGifsPreviewList.layoutManager = layoutManager
        gifPreviewAdapter =
            GifPreviewAdapter(this, arrayListOf(), this)
        rvGifsPreviewList.addItemDecoration(VerticalSpaceItemDecoration(16))
        gifPreviewAdapter.setHasStableIds(true)
        rvGifsPreviewList.adapter = gifPreviewAdapter

        viewModel.giphiesLiveData.observe(this, Observer {
            gifPreviewAdapter.addMoreGiphies((it))
            gifPreviewAdapter.notifyDataSetChanged()
        })

        rvGifsPreviewList.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        viewModel.loadMore()
                    }
                }

            }
        })

        viewModel.originalGipyLiveData.observe(this, Observer {

            val intent = Intent(this,GifDetailActivity::class.java)
            intent.putExtra("ORIGINAL_URL", it)
            startActivity(intent)
        })

        viewModel.showTrendingGifs()
    }

    inner class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.bottom = verticalSpaceHeight
        }
    }

    override fun onGiphyClick(giphy: Giphy) {
        Toast.makeText(this, giphy.id, Toast.LENGTH_LONG).show()
        viewModel.onGiphyClicked(giphy)
    }

}
