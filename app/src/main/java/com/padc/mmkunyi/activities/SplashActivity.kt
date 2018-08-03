package com.padc.mmkunyi.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.padc.mmkunyi.R
import kotlinx.android.synthetic.main.activity_splash.*


/**
 *
 * Created by Phyo Thiha on 7/29/18.
 */
class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = object : Thread() {
            override fun run() {
                try {
                    val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.splash_animation)
                    tvTitle.startAnimation(animation)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        timer.start()
    }
}