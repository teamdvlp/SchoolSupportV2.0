package com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support

import androidx.lifecycle.MutableLiveData

inline fun<T> MutableLiveData<T>.notifiChanged() {
    this.value = this.value
}