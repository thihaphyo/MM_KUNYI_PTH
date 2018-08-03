package com.padc.mmkunyi.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padc.mmkunyi.R
import com.padc.mmkunyi.activities.UserControlActivity
import com.padc.mmkunyi.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 *
 * Created by Phyo Thiha on 8/3/18.
 */
class LoginFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnSignUp.setOnClickListener {
            val intent = Intent(view.context, UserControlActivity::class.java)
            intent.putExtra(AppConstants.isLoginView, false)
            startActivity(intent)
        }
    }
}