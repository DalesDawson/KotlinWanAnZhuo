package com.daledawson.wananzhuo_kotlin.activity

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.daledawson.kotlin_kaiyan.fragment.SearchFragment
import com.daledawson.kotlin_kaiyan.fragment.MineFragment
import com.daledawson.kotlin_kaiyan.fragment.ProjectFragment
import com.daledawson.kotlin_kaiyan.fragment.SystemFragment
import com.daledawson.wananzhuo_kotlin.fragment.MainFragment
import com.daledawson.wananzhuo_kotlin.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.daledawson.wananzhuo_kotlin.base.BaseActivity
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_home.*


/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class HomeActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var TAG: String = "HomeActivity"
    private var fragmentList: Array<Fragment>? = null
    private var mainFragment: MainFragment? = null
    private var searchFragment: SearchFragment? = null
    private var systemFragment: SystemFragment? = null
    private var mineFragment: MineFragment? = null
    private var projectFragment: ProjectFragment? = null
    //默认选择第一个fragment
    var lastSelectedPosition = 0

    override fun initView() {
        ImmersionBar.with(this).titleBar(R.id.toolbar).init()
    }

    override fun initData() {
        initFragments()
    }

    override fun getLayoutId(): Int = R.layout.activity_home2

    //初始化
    private fun initFragments() {
        //监听切换事件
        home_BottomView?.setOnNavigationItemSelectedListener(this)
        mainFragment = MainFragment()
        systemFragment = SystemFragment()
        searchFragment = SearchFragment()
        mineFragment = MineFragment()
        projectFragment = ProjectFragment()
        fragmentList = arrayOf(
            mainFragment!!, systemFragment!!, searchFragment!!, projectFragment!!, mineFragment!!
        )
        lastSelectedPosition = 0
        //默认提交第一个
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_Fl, mainFragment!!)//添加
            .show(mainFragment!!)//展示
            .commit()//提交
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                if (0 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 0)
                    lastSelectedPosition = 0
                }
                return true
            }
            R.id.navigation_system -> {
                if (1 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 1)
                    lastSelectedPosition = 1
                }
                return true
            }
            R.id.navigation_search -> {
                if (2 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 2)
                    lastSelectedPosition = 2
                }
                return true
            }
            R.id.navigation_project -> {
                if (3 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 3)
                    lastSelectedPosition = 3
                }
                return true
            }
            R.id.navigation_mine -> {
                if (4 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 4)
                    lastSelectedPosition = 4
                }
                return true
            }
        }
        return false
    }

    /**
     * 切换Fragment
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    private fun setDefaultFragment(lastIndex: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        fragmentList?.get(lastIndex)?.let { transaction.hide(it) }
        if (!fragmentList?.get(index)?.isAdded!!) {
            transaction.add(R.id.container_Fl, fragmentList!![index])
        }
        transaction.show(fragmentList!![index]).commit()
    }

}