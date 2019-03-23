package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentWatchCeinfoBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.logError
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.BangTuanHoanViewModel

class FragmentWatchCEInfo : Fragment() {
    
    lateinit var mBinding : FragmentWatchCeinfoBinding

    lateinit var activityViewModel : BangTuanHoanViewModel

    var sentChemicalELement : ChemicalElement? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_watch_ceinfo, container, false)
        sentChemicalELement = arguments!!.getParcelable("chemical_element")
        if (sentChemicalELement != null) {
            mBinding.chemicalElement = sentChemicalELement
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    fun addControls () {
        activityViewModel = ViewModelProviders.of(activity!!).get(BangTuanHoanViewModel::class.java)
        activityViewModel.chosenChemicalElement.observe(this, Observer {
            if (sentChemicalELement == null) {
                mBinding.chemicalElement = it
            } else {
                sentChemicalELement = null
            }
        })
    }

    fun addEvents () {

    }

}