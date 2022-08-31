package net.virtmarket.task050822.friendDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.virtmarket.task050822.data.DataSource
import net.virtmarket.task050822.data.Friend


class FriendDetailViewModel (private val datasource: DataSource) : ViewModel() {
    /* Queries datasource to returns a friend that corresponds to an id. */
    fun getFriendForId(id: Long) : Friend? {
        return datasource.getFriendForId(id)
    }

    /* Queries datasource to remove a friend. */
    fun removeFriend(friend: Friend) {
        datasource.removeFriend(friend)
    }
}

class FriendDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FriendDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

