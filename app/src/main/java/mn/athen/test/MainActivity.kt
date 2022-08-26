package mn.athen.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendotp(view: View) {
        Toast.makeText(this,"Otp Send",Toast.LENGTH_LONG).show()
        startActivity(Intent(this,Otpverify::class.java))
    }
}