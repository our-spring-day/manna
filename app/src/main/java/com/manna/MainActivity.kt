package com.manna

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.manna.base.BaseActivity
import com.manna.databinding.ActivityMainBinding
import com.manna.ui.AddMeetFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replace(AddMeetFragment(), false)
    }

    fun replace(fragment: Fragment, isBackStack: Boolean = true) {
        if (isBackStack) {
            supportFragmentManager.beginTransaction().replace(R.id.fl_meet, fragment)
                .addToBackStack(null).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fl_meet, fragment)
                .commit()
        }
    }
}
