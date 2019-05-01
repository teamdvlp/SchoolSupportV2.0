//package com.teamttdvlp.goodthanbefore.schoolsupport.data.unitofwork
//
//import com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.user.implementation.UserInfo
//import com.teamttdvlp.goodthanbefore.schoolsupport.data.responsitory.user.UserResponsitory
//import com.teamttdvlp.goodthanbefore.schoolsupport.domain.responsitory.user.IUserResponsitory
//import com.teamttdvlp.goodthanbefore.schoolsupport.domain.unitofwork.user.IUserUnitOfWork
//import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
//import io.reactivex.Completable
//import io.reactivex.Single
//import io.reactivex.functions.Action
//import io.reactivex.functions.Consumer
//
//class UserUnitOfWork : IUserUnitOfWork {
//    private var userResponsitory : IUserResponsitory
//    private var newUsers = HashMap<String, User>()
//    private var dirtyUsers = HashMap<String, User>()
//    private var removeUsers = HashMap<String, User>()
//    private var newUserEvent : Completable
//    private var dirtyUserEvent: Completable
//    private var removeUserEvent: Completable
//
//    private lateinit var actionNewUserComplete: Action
//    private lateinit var actionDirtyUserComplete : Action
//    private lateinit var actionRemoveUserComplete: Action
//    private lateinit var actionNewUserError: Consumer<Throwable>
//    private lateinit var actionDirtyUserError : Consumer<in Throwable>
//    private lateinit var actionRemoveUserError: Consumer<Throwable>
//
//    private var userInfo : UserInfo
//    constructor() {
//        userInfo = UserInfo()
//        newUserEvent = Completable.create {
//            actionNewUserComplete = object : Action {
//                override fun run() {
//                    it.onComplete()
//                }
//            }
//
//            actionNewUserError = object  : Consumer<Throwable> {
//                override fun accept(t: Throwable?) {
//                    it.onError(t!!)
//                }
//            }
//        }
//
//        removeUserEvent = Completable.create {
//            actionRemoveUserComplete = object : Action {
//                override fun run() {
//                    it.onComplete()
//                }
//            }
//
//            actionRemoveUserError = object  : Consumer<Throwable> {
//                override fun accept(t: Throwable?) {
//                    it.onError(t!!)
//                }
//            }
//        }
//
//        dirtyUserEvent = Completable.create {
//            actionDirtyUserComplete = object : Action {
//                override fun run() {
//                    it.onComplete()
//                }
//            }
//
//            actionDirtyUserError = object  : Consumer<Throwable> {
//                override fun accept(t: Throwable?) {
//                    it.onError(t!!)
//                }
//            }
//        }
//
//        userResponsitory = UserResponsitory()
//    }
//
//    override fun getUserResponsitory(): IUserResponsitory {
//        return  userResponsitory
//    }
//
//    override fun commit() {
//
//    }
//
//    override fun getUser(id: String) : Single<User> {
//        return userInfo.readInfo(id)
//    }
//
//    override fun updateUser(id: String, value: HashMap<String,Any>) : Completable {
//
//    }
//
//    override fun addUser (user: User) : Completable {
//
//    }
//}