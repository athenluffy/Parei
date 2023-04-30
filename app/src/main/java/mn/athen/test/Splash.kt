package mn.athen.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        WindowCompat.getInsetsController(window, window.decorView)
            .hide(WindowInsetsCompat.Type.systemBars())
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
        }
        val intent = Intent(this, Landing::class.java)
        startActivity(intent)
        finish()

    }
}