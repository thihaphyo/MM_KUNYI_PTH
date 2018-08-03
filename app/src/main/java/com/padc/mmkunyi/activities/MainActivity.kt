package com.padc.mmkunyi.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.padc.mmkunyi.R
import com.padc.mmkunyi.adapters.JobListAdapter
import com.padc.mmkunyi.data.models.JobModel
import com.padc.mmkunyi.data.vos.JobVO
import com.padc.mmkunyi.delegates.BeforeLoginDelegate
import com.padc.mmkunyi.delegates.JobsDelegate
import com.padc.mmkunyi.events.ApiErrorEvent
import com.padc.mmkunyi.events.ForceGetJobEvent
import com.padc.mmkunyi.events.SuccessGetJobEvent
import com.padc.mmkunyi.utils.AppConstants
import com.padc.mmkunyi.viewpods.LoginHeaderViewPod
import kotlinx.android.synthetic.main.activity_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity(), JobsDelegate, BeforeLoginDelegate {

    private lateinit var mAdapter: JobListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigationView.setNavigationItemSelectedListener(mDrawerItemSelectedListener)

        mAdapter = JobListAdapter(this)
        rvJobList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvJobList.adapter = mAdapter

        swl.isRefreshing = true

        JobModel.getObjectReference()!!.loadJobList()

        swl.setOnRefreshListener {
            JobModel.getObjectReference()!!.forceRefreshJobList()
        }


        val vpBeforeLogin = navigationView.getHeaderView(0) as LoginHeaderViewPod
        vpBeforeLogin.setDelegate(this)
        rvJobList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            private var isReachedEnd: Boolean = false

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && (recyclerView!!.layoutManager as LinearLayoutManager)
                                .findLastCompletelyVisibleItemPosition() == recyclerView.adapter.itemCount - 1
                        && !isReachedEnd) {
                    isReachedEnd = true
                    JobModel.getObjectReference()!!.loadJobList()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = recyclerView!!.layoutManager.childCount
                val totalItemCount: Int = recyclerView.layoutManager.itemCount
                val pastVisibleItemCount: Int = (recyclerView.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition()

                if ((visibleItemCount + pastVisibleItemCount) < totalItemCount) {
                    isReachedEnd = false
                    JobModel.getObjectReference()!!.loadJobList()
                }

            }


        })

    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetJobEvent(successGetJobEvent: SuccessGetJobEvent) {
        swl.isRefreshing = false
        mAdapter.appendData(successGetJobEvent.jobList, rvJobList as RecyclerView)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessForceRefresh(successForceEvent: ForceGetJobEvent) {

        mAdapter.setData(successForceEvent.jobList, rvJobList as RecyclerView)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFailureApiEvent(apiErrorEvent: ApiErrorEvent) {
        swl.isRefreshing = false
        Snackbar.make(rvJobList, apiErrorEvent.errorMessage, Snackbar.LENGTH_INDEFINITE).show()
    }

    override fun onTapJob(jobVO: JobVO) {

        val intent = Intent(applicationContext, JobDetailActivity::class.java)
        intent.putExtra(AppConstants.JOB_ID, jobVO.jobPostId)
        startActivity(intent)

    }

    override fun onTapApply(jobVO: JobVO) {

    }


    private val mDrawerItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {
        drawer.closeDrawer(GravityCompat.START)
        false
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        //        when (item.itemId) {
////            R.id.navigation_home -> {
////                message.setText(R.string.title_home)
////                return@OnNavigationItemSelectedListener true
////            }
////            R.id.navigation_dashboard -> {
////                message.setText(R.string.title_dashboard)
////                return@OnNavigationItemSelectedListener true
////            }
////            R.id.navigation_notifications -> {
////                message.setText(R.string.title_notifications)
////                return@OnNavigationItemSelectedListener true
////            }
//        }
        false
    }

    override fun onTapJoinNow() {
        val intent = Intent(applicationContext, UserControlActivity::class.java)
        intent.putExtra(AppConstants.isLoginView, true)
        startActivity(intent)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START)
            return true
        }

        return false
    }
}
