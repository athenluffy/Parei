package mn.athen.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import mn.athen.test.databinding.ActivityLandingBinding
import org.json.JSONException
import org.json.JSONObject


class Landing : AppCompatActivity(),PaymentResultWithDataListener  {

    private lateinit var binding:ActivityLandingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


       // Checkout.preload(applicationContext)
        binding.landingBtmNavigation.setupWithNavController(navController)

    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
    }

    fun pay()
    {
        val co = Checkout()

        co.setKeyID("rzp_test_OScwfgVkujJCeh")
        val order = JSONObject()
        try {
            // to put name
            order.put("name", "Parei Foods")

            // put description
            order.put("description", "Test payment")

            // to set theme color
            order.put("theme.color", "#000")

            // put the currency
            order.put("currency", "INR")

            // put amount
            order.put("amount", 250*100)

            // put mobile number
            order.put("prefill.contact", "6009936182")

            // put email
            order.put("prefill.email", "pareifoods@gmail.com")

            // open razorpay to checkout activity
            co.open(this@Landing, order)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}