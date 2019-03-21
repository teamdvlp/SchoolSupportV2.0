package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.annotation.TargetApi
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.retroschoolsupporttoolmodule.Search_Chem_Element_Adapter
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.BangTuanHoanViewModel
import kotlinx.android.synthetic.main.activity_bang_tuan_hoan.*

class BangTuanHoanActivity : AppCompatActivity() {

    private lateinit var fadeOut: Animation

    private lateinit var fadeIn: Animation

    lateinit var mViewModel : BangTuanHoanViewModel

    private var rcvSearchList = ArrayList<ChemicalElement>()

    private lateinit var rcvSearchAdapter : Search_Chem_Element_Adapter

    private lateinit var bthNav : NavController

    private var isSearchOn = false

    private var isStartAppFocus = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bang_tuan_hoan)
        setUpAnimation()
        addControls()
        addEvents()
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun addControls() {
        bthNav = Navigation.findNavController(this, R.id.bth_bangtuanhoan_nav)
        mViewModel = getViewModel()
        mViewModel.searchCEListData.observe(this, Observer {
            setUpSearchRCV(it)
        })

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun setUpSearchRCV (data : ArrayList<ChemicalElement>) {
        rcvSearchAdapter = Search_Chem_Element_Adapter(this)
        rcvSearchAdapter.adaptFor(rcv_search)
        rcvSearchAdapter.setData(data)
        rcvSearchAdapter.setOnItemClickListener (object : Search_Chem_Element_Adapter.OnItemClickListener {
            override fun onItemClick(element: ChemicalElement) {
                var bundle = Bundle()
                bthNav.navigate(R.id.action_bth_nav_fragment_bth_to_watch_ceinfo_fragment, bundle)
            }
        })
    }

    private fun addEvents() {
        btn_cancel.setOnClickListener {
            if (rcv_search.visibility != View.GONE)
                rcv_search.visibility = View.GONE
        }

        edt_search.setOnFocusChangeListener {
                _, isFocus ->
            if (isFocus) {
                if (isStartAppFocus) {
                    isStartAppFocus = false
                } else {
                    rcv_search.visibility = View.VISIBLE
                }
            }
        }

        edt_search.setOnClickListener {
            if (rcv_search.visibility != View.VISIBLE)
                rcv_search.visibility = View.VISIBLE
            edt_search.showSoftInputOnFocus = true
        }

        btn_search.setOnClickListener {
            if (rcv_search.visibility != View.VISIBLE)
                rcv_search.visibility = View.VISIBLE
        }
    }

    private fun setUpAnimation () {
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        fadeOut.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                rcv_search.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })

        fadeIn.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {
                Log.e("WHAT THE FUCK", "CAC")
                rcv_search.visibility = View.VISIBLE
            }

        })
    }
}
