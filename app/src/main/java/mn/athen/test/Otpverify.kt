package mn.athen.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Otpverify : AppCompatActivity() {
    companion object {
        const val OTP_REPLY = "mn.athen.test.OTP"
    }

    private var count:Int = 0
    private var mobile:Int =0
    private var otp:Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverify)
         mobile = intent.getIntExtra(MainActivity.MOBILE,0)
         // Random.nextInt(100000,999999))
        sendotp()
    }

    private fun sendotp() {
        otp = 111111
    }

    fun confirm(v: View) {

        val txtOtp : EditText = findViewById(R.id.txtOtp)


        val intent = Intent()

        Log.i(OTP_REPLY, txtOtp.text.toString().length.toString());
        if(!TextUtils.isEmpty(txtOtp.text) && count<3 && txtOtp.text.toString().length==6)
        {
            count++
            val txt_otp = txtOtp.text.toString()
            intent.putExtra(OTP_REPLY,mobile)
            if(otp==txt_otp.toInt()) {
                setResult(RESULT_OK, intent)
                finish()
            }
            else if(count==3)
            {
                setResult(RESULT_CANCELED,intent)
                finish()
            }
            else
            {
                Toast.makeText(v.context,"Incorrect OTP. You have "+(3-count) +"attempts remaining",Toast.LENGTH_LONG).show()
            }

            txtOtp.text.clear()



        }



    }
}