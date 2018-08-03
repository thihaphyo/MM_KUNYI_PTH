package com.padc.mmkunyi.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.padc.mmkunyi.delegates.BeforeLoginDelegate
import kotlinx.android.synthetic.main.view_pod_before_login.view.*

/**
 *
 * Created by Phyo Thiha on 8/3/18.
 */
class LoginHeaderViewPod : RelativeLayout {

    private lateinit var mDelegate: BeforeLoginDelegate

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        btnJoin.setOnClickListener { mDelegate.onTapJoinNow() }

    }

    fun setDelegate(delegate: BeforeLoginDelegate) {
        mDelegate = delegate
    }
}