package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.ChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.BangTuanHoanActivity

/**
 * @see BangTuanHoanActivity
 */
class BangTuanHoanViewModel(app : Application) : AndroidViewModel(app) {

    var searchCEListData = MutableLiveData<ArrayList<ChemicalElement>>()

}