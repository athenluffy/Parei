package mn.athen.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Otpverify : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverify)
    }

    fun confirm() {
        startActivity(Intent(this,Landing::class.java))
        finish()
    }
}