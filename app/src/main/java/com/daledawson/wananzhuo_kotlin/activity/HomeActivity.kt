package com.daledawson.wananzhuo_kotlin.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.daledawson.kotlin_kaiyan.fragment.SearchFragment
import com.daledawson.kotlin_kaiyan.fragment.MineFragment
import com.daledawson.kotlin_kaiyan.fragment.ProjectFragment
import com.daledawson.kotlin_kaiyan.fragment.SystemFragment
import com.daledawson.wananzhuo_kotlin.MainFragment
import com.daledawson.wananzhuo_kotlin.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/16
 * 修改时间：
 * 修改备注：
 */
class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var TAG: String = "HomeActivity"
    var fragmentList: Array<Fragment>? = null
    var bottomNavigationView: BottomNavigationView? = null
    var container: FrameLayout? = null
    var mainFragment: MainFragment? = null
    var searchFragment: SearchFragment? = null
    var systemFragment: SystemFragment? = null
    var mineFragment: MineFragment? = null
    var projectFragment: ProjectFragment? = null
    //默认选择第一个fragment
    var lastSelectedPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigationView = findViewById(R.id.home_BottomView)
        initFragments()
    }

    //初始化
    private fun initFragments() {
        //监听切换事件
        bottomNavigationView?.setOnNavigationItemSelectedListener(this)
        mainFragment = MainFragment()
        systemFragment = SystemFragment()
        searchFragment = SearchFragment()
        mineFragment = MineFragment()
        projectFragment = ProjectFragment()
        fragmentList = arrayOf(
            mainFragment!!, systemFragment!!,searchFragment!!, projectFragment!!, mineFragment!!
        )
        lastSelectedPosition = 0
        //默认提交第一个
        getSupportFragmentManager()
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
        var transaction = supportFragmentManager.beginTransaction()
        fragmentList?.get(lastIndex)?.let { transaction.hide(it) }
        if (!fragmentList?.get(index)?.isAdded()!!) {
            transaction.add(R.id.container_Fl, fragmentList!![index])
        }
        transaction.show(fragmentList!![index]).commit()
    }
}