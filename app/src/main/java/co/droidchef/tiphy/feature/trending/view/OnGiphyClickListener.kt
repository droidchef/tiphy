package co.droidchef.tiphy.feature.trending.view

import co.droidchef.tiphy.network.model.response.Giphy

interface OnGiphyClickListener {
    fun onGiphyClick(giphy: Giphy)
}