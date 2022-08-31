package net.virtmarket.task050822.flowerList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import net.virtmarket.task050822.R
import net.virtmarket.task050822.addFlower.AddFriendActivity
import net.virtmarket.task050822.addFlower.FRIEND_DESCRIPTION
import net.virtmarket.task050822.addFlower.FRIEND_IMAGE
import net.virtmarket.task050822.addFlower.FRIEND_NAME
//import net.virtmarket.task030822.data.Flower
import net.virtmarket.task050822.data.User

class UserActivity : AppCompatActivity() {
    private val newUserActivityRequestCode = 1
    private val usersListViewModel by viewModels<UsersListViewModel> {
        UsersListViewModelFactory(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        /* Instantiates headerAdapter and friendsAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = UserHeaderAdapter{ -> headAdapterOnClick()}
        val usersAdapter = UsersAdapter { user -> adapterOnClick(user) }
        val concatAdapter = ConcatAdapter(headerAdapter, usersAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        usersListViewModel.usersLiveData.observe(this, {
            it?.let {
                usersAdapter.submitList(it as MutableList<User>)
                headerAdapter.updateUserCount(it.size)
            }
        })




        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    /* Move user to friend when RecyclerView item is clicked. */
    private fun adapterOnClick(user: User) {

        removeUser(user)
        addFriend(user)
    }

    private fun headAdapterOnClick(){
        val intent = Intent(this, MainActivity()::class.java)
        startActivity(intent)

    }


    /* Adds user to userList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, AddFriendActivity::class.java)
        startActivityForResult(intent, newUserActivityRequestCode)
    }

    private fun addFriend(user:User) {
        val image = user.image.toString()

        //intln("DRAWABLE IN USER="+ image)

        val resultIntent = Intent()

        val name = user.name

        val description = "Description"
        resultIntent.putExtra(FRIEND_NAME, name)
        resultIntent.putExtra(FRIEND_IMAGE, image)
        resultIntent.putExtra(FRIEND_DESCRIPTION, description)
        setResult(Activity.RESULT_OK, resultIntent)

        finish()
    }

    private fun removeUser(user:User) {
        usersListViewModel.removeUser(user)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts user
         into viewModel. */
        if (requestCode == newUserActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val userName = data.getStringExtra(FRIEND_NAME)
                val userDescription = data.getStringExtra(FRIEND_DESCRIPTION)


                usersListViewModel.insertUser(userName, userDescription)
            }
        }
    }
}