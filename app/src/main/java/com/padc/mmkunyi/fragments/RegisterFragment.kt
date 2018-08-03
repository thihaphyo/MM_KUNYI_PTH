package com.padc.mmkunyi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padc.mmkunyi.R

/**
 *
 * Created by Phyo Thiha on 8/3/18.
 */
class RegisterFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        return view
    }
}