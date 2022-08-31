package net.virtmarket.task050822.data


import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialFriendList = friendList(resources)
    private val friendsLiveData = MutableLiveData(initialFriendList)
    private val initialUserList = userList(resources)
    private val usersLiveData = MutableLiveData(initialUserList)



    /* Adds friend to liveData and posts value. */
    fun addFriend(friend: Friend, friendItenId: Int?, friendItemPosition:Int?) {
        val currentList = friendsLiveData.value
        var itemPosition = 0
        if (friendItemPosition != null) {
            itemPosition=friendItemPosition
        }
        if (currentList == null) {
            friendsLiveData.postValue(listOf(friend))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(itemPosition, friend)
            friendsLiveData.postValue(updatedList)
        }
    }

    /* Adds user to liveData and posts value. */
    fun addUser(user: User, friendItenId: Int?, userItemPosition:Int?) {
        val currentList = usersLiveData.value
        var itemPosition = 0
        if (userItemPosition != null) {
            itemPosition=userItemPosition
        }
        if (currentList == null) {
            usersLiveData.postValue(listOf(user))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(itemPosition, user)
            usersLiveData.postValue(updatedList)
        }
    }

    /* Removes friend from liveData and posts value. */
    fun removeFriend(friend: Friend) {
        val currentList = friendsLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(friend)
            friendsLiveData.postValue(updatedList)
        }
    }

    fun removeUser(user: User) {
        val currentList = usersLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(user)
            usersLiveData.postValue(updatedList)
        }
    }

    /* Returns flower given an ID. */
    fun getFriendForId(id: Long): Friend? {
        friendsLiveData.value?.let { friends ->
            return friends.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getUserForId(id: Long): User? {
        usersLiveData.value?.let { users ->
            return users.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getFriendList(): LiveData<List<Friend>> {
        return friendsLiveData
    }

    fun getUserList(): LiveData<List<User>> {
        return usersLiveData
    }

    /* Returns a random friend asset for friends that are added. */
    fun getRandomUserImageAsset(): Int? {
        val randomNumber = (initialUserList.indices).random()
        return initialUserList[randomNumber].image
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}