package mn.athen.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class Landing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        var btmNavigation = R.id.landingBtmNavigation

       NavigationBarView.OnItemSelectedListener{item->
           when(item.itemId)
           {
               R.id.home -> {
                   Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()
                   // Respond to navigation item 1 click
                   true
               }
               R.id.cart -> {
                   Toast.makeText(this,"Cart",Toast.LENGTH_SHORT).show()
                   // Respond to navigation item 1 click
                   true
               }
               R.id.accoount -> {
                   Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show()
                   // Respond to navigation item 1 click
                   true
               }
               else -> false
           }
       }


    }
}