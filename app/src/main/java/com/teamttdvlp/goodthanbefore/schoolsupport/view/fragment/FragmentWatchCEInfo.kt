package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentWatchCeinfoBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement

class FragmentWatchCEInfo : Fragment() {
    
    lateinit var mBinding : FragmentWatchCeinfoBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_watch_ceinfo, container, false)
        mBinding.chemicalElement = ChemicalElement(context!!.getDrawable(R.drawable.green), "Natri", "Na", 11, 12, "23", "1s2.2s2", "hihi", "2.58", "1")
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    fun addControls () {
        
    }

    fun addEvents () {

    }

}