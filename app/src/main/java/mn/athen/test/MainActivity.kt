package mn.athen.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
//keytool -exportcert -alias androiddebugkey -keystore "C:\Users\DELL\.android\debug.keystore" | "C:\openssl\bin\openssl" sha1 -binary | "C:\openssl\bin\openssl" base64
//openssl 0.9e x 64
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val callbackManager = CallbackManager.Factory.create();
        val loginbutton = findViewById<LoginButton>(R.id.login_button)
        loginbutton.registerCallback(callbackManager,object: FacebookCallback<LoginResult>
        {
            override fun onSuccess(result: LoginResult) {
                Log.i("Success", result.accessToken.toString());
            }

            override fun onCancel() {
                Log.i("Cancelled","Cancelled");
            }

            override fun onError(error: FacebookException) {
                Log.i("Error", error.message.toString());
            }
        })
    }

    fun sendotp() {
        Toast.makeText(this,"Otp Send",Toast.LENGTH_LONG).show()
        startActivity(Intent(this,Otpverify::class.java))
    }
}