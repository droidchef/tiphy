package co.droidchef.tiphy.network.model.response

import com.google.gson.annotations.SerializedName

data class TrendingGifsResponse(
    @SerializedName("data")
    val giphyList: ArrayList<Giphy>,
    val pagination: Pagination,
    val meta: Meta
)

data class Giphy(
    val type: String,
    val id: String,
    val images: Images
)

data class Images(
    val original: Original,
    @SerializedName("preview_gif")
    val previewGif: PreviewGif
)

data class Original(
    val url: String
)

data class PreviewGif(
    val url: String
)

data class Meta(
    val status: Int,
    val msg: String,
    val responseId: String
)

data class Pagination(
    val totalCount: Long,
    val count: Int,
    val offset: Long
)

