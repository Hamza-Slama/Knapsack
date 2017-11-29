package moduleproject.exemple.com.knapsackgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnJouer.setOnClickListener {
            var intent = Intent(this , PlayActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
