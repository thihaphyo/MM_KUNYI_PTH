package com.padc.mmkunyi.activities

import android.os.Bundle
import com.padc.mmkunyi.R
import com.padc.mmkunyi.fragments.LoginFragment
import com.padc.mmkunyi.fragments.RegisterFragment
import com.padc.mmkunyi.utils.AppConstants

/**
 *
 * Created by Phyo Thiha on 8/3/18.
 */
class UserControlActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_control)
        val isLogin = intent.extras.getBoolean(AppConstants.isLoginView)
        if (isLogin) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.flContainer, LoginFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.flContainer, RegisterFragment()).commit()
        }
    }

}