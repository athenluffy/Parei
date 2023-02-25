package mn.athen.test.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _user=MutableLiveData(0)


    var email: String?=null
    var password: String? =null

    fun onLoginButtonClick(view: View)
    {
        if(email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            return
        }

    }



}