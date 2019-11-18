package co.droidchef.tiphy.feature.detail

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import co.droidchef.tiphy.R
import com.bumptech.glide.Glide

class GifDetailActivity : AppCompatActivity() {

    lateinit var ivOriginalGiphy: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_detail)

        ivOriginalGiphy = findViewById(R.id.ivGiphyOriginal)

        Glide.with(this).load(intent.getStringExtra("ORIGINAL_URL")).into(ivOriginalGiphy)

    }
}
