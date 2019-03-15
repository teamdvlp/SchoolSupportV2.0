package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityViewProfileBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.UserProfilePagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.ViewProfileViewModel

class ViewProfileActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private lateinit var mBinding:ActivityViewProfileBinding
    private lateinit var mViewModel : ViewProfileViewModel
    private lateinit var mAdapter : UserProfilePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_profile)
        setup()
        addControls()
        addEvents()
    }

    private fun setup() {
        val currentUser: User = intent.getSerializableExtra("User") as User
        mViewModel = getViewModel({return@getViewModel  ViewProfileViewModel(currentUser)})
        mBinding.txtUserName.text = currentUser.DisplayName
        Picasso.get().load(currentUser.Avatar).into(mBinding.imgAvatar)
    }

    private fun addEvents() {
        mBinding.vpgProfile.addOnPageChangeListener(this)
        mBinding.tabLayout.setOnCheckedChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> mBinding.btnLastest.isChecked = true
            1 -> mBinding.btnClaps.isChecked = true
            2 -> mBinding.btnHighlights.isChecked = true
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            mBinding.btnLastest.id -> mBinding.vpgProfile.setCurrentItem(0, true)
            mBinding.btnClaps.id -> mBinding.vpgProfile.setCurrentItem(1, true)
            mBinding.btnHighlights.id -> mBinding.vpgProfile.setCurrentItem(2, true)
        }
    }

    private fun addControls() {
        mAdapter = UserProfilePagerAdapter(supportFragmentManager)
        mBinding.vpgProfile.adapter = mAdapter
    }
}
