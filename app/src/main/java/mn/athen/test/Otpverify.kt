package mn.athen.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText

class Otpverify : AppCompatActivity() {
    companion object {
        const val OTP_REPLY = "mn.athen.test.OTP"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverify)
    }

    fun confirm(v: View) {

        val txtOtp : EditText = v.findViewById(R.id.txtOtp)

        val intent = Intent()
        if(TextUtils.isEmpty(txtOtp.text))
        {
            setResult(RESULT_CANCELED,intent)
        }
        else
        {
            val otp = txtOtp.text.toString()
            intent.putExtra(OTP_REPLY,otp)
            setResult(RESULT_OK,intent)
        }
        finish()
    }
}