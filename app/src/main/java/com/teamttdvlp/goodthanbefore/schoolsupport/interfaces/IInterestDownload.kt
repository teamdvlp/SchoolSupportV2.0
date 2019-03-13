package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import java.lang.Exception

interface IInterestDownload {
    fun loadInterest(onAnImageLoadSuccess : (interst : Interest) -> Unit,
                     onGetCollectionFailed: (Exception) -> Unit )
}