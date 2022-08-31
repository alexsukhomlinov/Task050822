package net.virtmarket.task050822.flowerList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.virtmarket.task050822.data.DataSource
import net.virtmarket.task050822.data.Friend
import kotlin.random.Random


class FriendsListViewModel (val dataSource: DataSource) : ViewModel() {

        val friendsLiveData = dataSource.getFriendList()

        /* If the name and description are present, create new Flower and add it to the datasource */
        fun insertFriend(flowerName: String?, flowerDescription: String?, friendImage: Int?, friendItenId:Int?, friendItemPosition:Int?) {
            if (flowerName == null || flowerDescription == null) {
                return
            }

            if(friendImage==null){
                val image = dataSource.getRandomUserImageAsset()
                val newFlower = Friend(
                    Random.nextLong(),
                    flowerName,
                    image,
                    flowerDescription
                )
                dataSource.addFriend(newFlower, friendItenId, friendItemPosition)
            }
            else{
                val image =friendImage
                val newFlower = Friend(
                    Random.nextLong(),
                    flowerName,
                    image,
                    flowerDescription
                )
                dataSource.addFriend(newFlower,friendItenId, friendItemPosition)
            }

        }
    }

    class FFriendsListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FriendsListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FriendsListViewModel(
                    dataSource = DataSource.getDataSource(context.resources)
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }