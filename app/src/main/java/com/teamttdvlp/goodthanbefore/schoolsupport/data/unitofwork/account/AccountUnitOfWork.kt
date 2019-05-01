package com.teamttdvlp.goodthanbefore.schoolsupport.data.unitofwork.account

import com.teamttdvlp.goodthanbefore.schoolsupport.data.entities.data.DataAction
import com.teamttdvlp.goodthanbefore.schoolsupport.data.entities.data.DataContext
import com.teamttdvlp.goodthanbefore.schoolsupport.domain.unitofwork.account.IAccountUnitOfWork
import io.reactivex.Completable

class AccountUnitOfWork : IAccountUnitOfWork {
    constructor() : super() {

    }

    constructor(context: DataContext) : super(context) {

    }

    override fun signUp(email: String, password: String, displayName: String) {
        var signUpAction = DataAction("SignUpAction",
            hashMapOf(Pair("emai", email), Pair("password", password), Pair("displayName", displayName))
        )
        getContext().addAction(signUpAction)
    }

    override fun commit() : Completable {
        return Completable.complete()
    }
}