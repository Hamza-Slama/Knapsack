package moduleproject.exemple.com.knapsackgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.apache.commons.io.IOUtils
import java.io.IOException

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        try {
            val inputStream = assets.open("shape.gif")
            val bytes = IOUtils.toByteArray(inputStream)
            gifImageView.setBytes(bytes)
            gifImageView.startAnimation()
        } catch (ex: IOException) {

        }
/* try {
            val inputStream = assets.open("gifs.gif")
            val bytes = IOUtils.toByteArray(inputStream)
            gifImageViews.setBytes(bytes    )
            gifImageViews.startAnimation()
        } catch (ex: IOException) {

        }*/

        Handler().postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}
