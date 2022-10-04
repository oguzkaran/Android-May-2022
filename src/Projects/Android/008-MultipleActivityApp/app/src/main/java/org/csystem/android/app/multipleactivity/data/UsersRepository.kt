package org.csystem.android.app.multipleactivity.data

object UsersRepository { // Bu sınıf çok daha genel yazılacaktır. İleride göreceğiz
    private val mUsers: MutableList<RegisterInfo> = ArrayList()

    fun save(registerInfo: RegisterInfo) = mUsers.run { add(registerInfo); registerInfo}
    fun findAll() = mUsers.toList()
    fun findByUsernameAndPassword(username: String, password: String) = mUsers.find {it.username == username && it.password == password}
    //...
}