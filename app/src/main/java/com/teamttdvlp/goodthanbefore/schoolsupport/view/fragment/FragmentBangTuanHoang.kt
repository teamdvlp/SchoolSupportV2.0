package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.retroschoolsupporttoolmodule.BangTuanHoangGridviewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentBangTuanHoangBinding

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.chemicalelement.IChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.BangTuanHoanViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.FrmtBangTuanHoanViewModel


class FragmentBangTuanHoang : Fragment() {

    lateinit var mBd : FragmentBangTuanHoangBinding
    lateinit var mViewModel : FrmtBangTuanHoanViewModel
    lateinit var gridAdapter: BangTuanHoangGridviewAdapter
    var gridBthList : ArrayList<IChemicalElement>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBd = DataBindingUtil.inflate(inflater, R.layout.fragment_bang_tuan_hoang, container, false)
        return mBd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    private fun addControls() {
        mViewModel = ViewModelProviders.of(this).get(FrmtBangTuanHoanViewModel::class.java)
        gridBthList = mViewModel.spawnData()
        sendDataToActivity(gridBthList!!)

        gridAdapter = BangTuanHoangGridviewAdapter(context!!, gridBthList!!)
        gridAdapter.adaptFor(mBd.btnHorGridview)

    }

    private fun addEvents() {
        val nav = Navigation.findNavController(activity!!, R.id.bth_bangtuanhoan_nav)
        gridAdapter.setOnItemClickListener {
            nav.navigate(R.id.action_bth_nav_fragment_bth_to_watch_ceinfo_fragment)
        }
    }

    private fun sendDataToActivity (source : ArrayList<IChemicalElement>) {
        val data = ArrayList<ChemicalElement>()
        for (e in source) {
            if (e is ChemicalElement) data.add(e)
        }
        var activityViewModel = ViewModelProviders.of(activity!!).get(BangTuanHoanViewModel::class.java)
        activityViewModel.searchCEListData.value = data
    }

}
