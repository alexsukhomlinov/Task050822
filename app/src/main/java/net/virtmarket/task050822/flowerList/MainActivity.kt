package net.virtmarket.task050822.flowerList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.virtmarket.task050822.R

import android.app.Activity
import android.content.Intent

import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import net.virtmarket.task050822.addFlower.*
import net.virtmarket.task050822.friendDetail.FriendDetailActivity

import net.virtmarket.task050822.data.Friend

const val FRIEND_ID = "friend id"

class MainActivity : AppCompatActivity() {
    private val newFriendActivityRequestCode = 1
    private val friendsListViewModel by viewModels<FriendsListViewModel> {
        FFriendsListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instantiates headerAdapter and friendsAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter{ -> headAdapterOnClick()}
        val friendsAdapter = FriendsAdapter { friend, itemPosition -> adapterOnClick(friend, itemPosition) }
        val concatAdapter = ConcatAdapter(headerAdapter, friendsAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        friendsListViewModel.friendsLiveData.observe(this, {
            it?.let {
                friendsAdapter.submitList(it as MutableList<Friend>)
                headerAdapter.updateFriendCount(it.size)
            }
        })

    }

    /* Opens FlowerDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(friend: Friend, itemPosition: Int) {
        val intent = Intent(this, FriendDetailActivity()::class.java)
        intent.putExtra(FRIEND_ID, friend.id)
        intent.putExtra(ITEM_POSITION, itemPosition)
        println("ITEM POSITION IN MAIM="+itemPosition )
        startActivityForResult(intent, newFriendActivityRequestCode)
    }

    private fun headAdapterOnClick(){
        val intent = Intent(this, UserActivity()::class.java)
        startActivityForResult(intent, newFriendActivityRequestCode)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts friend into viewModel. */
        if (requestCode == newFriendActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val friendName = data.getStringExtra(FRIEND_NAME)
                val friendDescription = data.getStringExtra(FRIEND_DESCRIPTION)

                val friendImageString = data.getStringExtra(FRIEND_IMAGE)

                val friendItenIdString = data.getStringExtra(FRIEND_ID)

                val friendItemPosition = data.getIntExtra(ITEM_POSITION, 0)

                val friendImage= friendImageString?.toIntOrNull()
                val friendItenId= friendItenIdString?.toIntOrNull()

                friendsListViewModel.insertFriend(friendName, friendDescription, friendImage, friendItenId, friendItemPosition )
            }
        }
    }
}