package com.teamttdvlp.goodthanbefore.schoolsupport.data.unitofwork

import com.teamttdvlp.goodthanbefore.schoolsupport.data.entities.data.DataContext
import io.reactivex.Completable

abstract class IUnitOfWork {
    private var mContext: DataContext
    constructor() {
        mContext = DataContext()
    }
    constructor(context: DataContext) {
        mContext = context
    }
    fun getContext () : DataContext {
        return mContext
    }
    abstract fun commit () : Completable
}