package net.virtmarket.task050822.flowerList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.virtmarket.task050822.data.DataSource
import net.virtmarket.task050822.data.User
import kotlin.random.Random

class UsersListViewModel (val dataSource: DataSource) : ViewModel()  {

    val usersLiveData = dataSource.getUserList()

    /* If the name and description are present, create new Flower and add it to the datasource */

    fun insertUser(userName: String?, userDescription: String?) {
        if (userName == null || userDescription == null) {
            return
        }

        val image = dataSource.getRandomUserImageAsset()
        val newUser = User(
            Random.nextLong(),
            userName,
            image,

        )

        dataSource.addUser(newUser, null,null)
    }

    fun removeUser(user: User) {
        dataSource.removeUser(user)
    }


}


class UsersListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsersListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}