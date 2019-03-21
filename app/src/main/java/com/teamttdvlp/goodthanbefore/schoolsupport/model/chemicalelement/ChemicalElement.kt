package com.teamttdvlp.goodthanbefore.schoolsupport.model.chemicalelement

import android.graphics.drawable.Drawable
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.chemicalelement.IChemicalElement
import java.io.Serializable

data class ChemicalElement (
    var background : Drawable? = null,
    var name : String,
    var symbol : String,
    var elecCount : Int,
    var notron_count : Int,
    var mass : String,
    var eleFigure : String,
    var inReality : String,
    var eleNegative : String,
    var oxydationNumbers : String? = null) : IChemicalElement, Serializable