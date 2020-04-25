package com.shkiper.whether.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var fragmentList = mutableListOf<Fragment>()
    private var fragmentTitle = mutableListOf<String>()



    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int = fragmentList.size

    fun addFragment(fm: Fragment,str : String){
        fragmentList.add(fm)
        fragmentTitle.add(str)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }
}