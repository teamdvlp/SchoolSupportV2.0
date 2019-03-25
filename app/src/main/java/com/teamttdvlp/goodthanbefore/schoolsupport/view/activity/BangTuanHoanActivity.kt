package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.retroschoolsupporttoolmodule.Search_Chem_Element_Adapter
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentWatchCeinfoBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.logError
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentWatchCEInfo
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.BangTuanHoanViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.WatchCEInfoViewModel
import kotlinx.android.synthetic.main.activity_bang_tuan_hoan.*
import kotlinx.android.synthetic.main.activity_main.*

class BangTuanHoanActivity : AppCompatActivity() {

    private lateinit var fadeOut: Animation

    private lateinit var fadeIn: Animation

    lateinit var mViewModel : BangTuanHoanViewModel

    private var rcvSearchList = ArrayList<ChemicalElement>()

    private lateinit var rcvSearchAdapter : Search_Chem_Element_Adapter

    private lateinit var BangTuanHoanNav : NavController

    private var isSearchOn = false

    private var isStartAppFocus = true

    private lateinit var inputMethodManager : InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bang_tuan_hoan)
        setUpFunctionalSupporter()
        setUpAnimation()
        addControls()
        addEvents()
    }

    private fun setUpFunctionalSupporter () {
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun addControls() {
        BangTuanHoanNav = Navigation.findNavController(this, R.id.bth_bangtuanhoan_nav)
        mViewModel = getViewModel()
        mViewModel.searchCEListData.observe(this, Observer {
            setUpSearchRCV(it)
        })

        // Make keyboard not auto turn on when start activity
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun setUpSearchRCV (data : ArrayList<ChemicalElement>) {
        rcvSearchAdapter = Search_Chem_Element_Adapter(this)
        rcvSearchAdapter.adaptFor(rcv_search)
        rcvSearchAdapter.setData(data)
        rcvSearchAdapter.observe(edt_search)
        rcvSearchAdapter.setOnItemClickListener (object : Search_Chem_Element_Adapter.OnItemClickListener {
            override fun onItemClick(element: ChemicalElement) {
                if ((BangTuanHoanNav.currentDestination != BangTuanHoanNav.graph.findNode(R.id.bth_nav_fragment_watch_ceinfo))) {
                    var bundle = Bundle()
                    bundle.putParcelable("chemical_element", element)
                    BangTuanHoanNav.navigate(R.id.action_bth_nav_fragment_bth_to_watch_ceinfo_fragment, bundle)
                    rcv_search.visibility = View.GONE
                } else {
                    // Update element to FragmentWatchCEInfo because it is observing to this
                    // livedata
                    mViewModel.chosenChemicalElement.value = element
                    rcv_search.visibility = View.GONE
                    inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
                }
            }
        })
    }

    private fun addEvents() {

        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        btn_cancel.setOnClickListener {
            if (rcv_search.visibility != View.GONE)
                rcv_search.visibility = View.GONE
        }

        edt_search.isFocusableInTouchMode = true

        edt_search.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (rcv_search.visibility != View.VISIBLE)
                    rcv_search.visibility = View.VISIBLE
                edt_search.showSoftInputOnFocus = true
                if (!edt_search.isFocused) {
                    edt_search.requestFocus()
                    inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
                }
                return@setOnTouchListener true
            }
            false
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
