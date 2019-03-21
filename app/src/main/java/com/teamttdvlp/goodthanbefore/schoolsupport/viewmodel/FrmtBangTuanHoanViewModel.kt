package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.chemicalelement.IChemicalElement
import com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement.process.ChemicalElementDataSpawner

class FrmtBangTuanHoanViewModel(app : Application) : AndroidViewModel(app) {

    val dataSpawner = ChemicalElementDataSpawner(app)

    fun spawnData () : ArrayList<IChemicalElement>{
        return dataSpawner.spawnData()
    }

}