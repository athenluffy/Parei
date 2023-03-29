package mn.athen.test

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import mn.athen.test.databinding.ActivityMainBinding

//keytool -exportcert -alias androiddebugkey -keystore "C:\Users\DELL\.android\debug.keystore" | "C:\openssl\bin\openssl" sha1 -binary | "C:\openssl\bin\openssl" base64
//openssl 0.9e x 64
class MainActivity : AppCompatActivity() {
    companion object {
        const val MOBILE = "mn.athen.test.MOBILE_OTP"
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.txtLoginMobile.requestFocusFromTouch()

        val callbackManager = CallbackManager.Factory.create();

        binding.loginButton.registerCallback(callbackManager,object: FacebookCallback<LoginResult>
        {
            override fun onSuccess(result: LoginResult) {
                Log.i("Success", result.accessToken.toString())
            }

            override fun onCancel() {
                Log.i("Cancelled","Cancelled");
            }

            override fun onError(error: FacebookException) {
                Log.i("Error", error.message.toString())
            }
        })
    }

    fun sendotp(v:View) {
        if(TextUtils.isEmpty(binding.txtLoginMobile.text) || !validate(binding.txtLoginMobile.text.toString())) {
            binding.txtInputLoginMobile.error = "Please Enter a Valid Mobile No."
            binding.txtInputLoginMobile.requestFocus()
            return
        }
        binding.txtInputLoginMobile.isErrorEnabled=false
        val intent = Intent(v.context, Otpverify::class.java)

        intent.putExtra(MOBILE,findViewById<EditText>(R.id.txtLoginMobile).text)
        verifyOtp.launch(intent)
    }

    private fun validate(text: String): Boolean {
        if(text.length!=10)
            return false
        return true
    }

    private val verifyOtp = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val intent = Intent(applicationContext,Landing::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "Unable to Login !!! Please Try Again", Toast.LENGTH_LONG).show()
        }
    }



}