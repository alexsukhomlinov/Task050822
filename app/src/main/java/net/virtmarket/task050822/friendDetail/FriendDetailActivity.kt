package net.virtmarket.task050822.friendDetail

import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.Display
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import net.virtmarket.task050822.R
import net.virtmarket.task050822.addFlower.FRIEND_DESCRIPTION
import net.virtmarket.task050822.addFlower.FRIEND_IMAGE
import net.virtmarket.task050822.addFlower.FRIEND_NAME
import net.virtmarket.task050822.addFlower.ITEM_POSITION
import net.virtmarket.task050822.flowerList.FRIEND_ID


class FriendDetailActivity : AppCompatActivity() {
    private val friendDetailViewModel by viewModels<FriendDetailViewModel> {
        FriendDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friend_detail_activity)

        val display: Display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width: Int = size.x
        val height: Int = size.y
        val minsize:Int = minOf(width, height)

        val itemPosition: Int =intent.getIntExtra(ITEM_POSITION,0)
        println("ITEM POSITION IN DETAIL ACTIVITY="+itemPosition)

        var currentFriendId: Long? = null

        /* Connect variables to UI elements. */
        val friendName: TextView = findViewById(R.id.friend_detail_name)
        val friendImage: ImageView = findViewById(R.id.friend_detail_image)
        friendImage.getLayoutParams().height = height/4
        friendImage.getLayoutParams().width = height/4

        val friendDescription: EditText = findViewById(R.id.friend_detail_description)
        val removeFriendButton: Button = findViewById(R.id.remove_button)
        val saveFriendButton: Button = findViewById(R.id.save_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentFriendId = bundle.getLong(FRIEND_ID)
           // val itemPosition =bundle.getInt(ITEM_POSITION)

        }

        /* If currentFriendId is not null, get corresponding friend and set name, image and
        description */
        currentFriendId?.let {
            val currentFriend = friendDetailViewModel.getFriendForId(it)
            friendName.text = currentFriend?.name
            if (currentFriend?.image == null) {
                friendImage.setImageResource(R.drawable.rose)
            } else {
                friendImage.setImageResource(currentFriend.image)
            }
            friendDescription.setText(currentFriend?.description)
            val name = currentFriend?.name


            val image = currentFriend?.image.toString()
            val id=it.toString()


            removeFriendButton.setOnClickListener {
                if (currentFriend != null) {
                    friendDetailViewModel.removeFriend(currentFriend)
                }
                finish()
            }

            saveFriendButton.setOnClickListener {
                if (currentFriend != null) {
                    //remove old item:
                    friendDetailViewModel.removeFriend(currentFriend)
                    //insert new item with change:
                    val description = friendDescription.getText().toString()
                    val resultIntent = Intent()
                    resultIntent.putExtra(FRIEND_NAME, name)
                    resultIntent.putExtra(FRIEND_IMAGE, image)
                    resultIntent.putExtra(FRIEND_DESCRIPTION, description)
                    resultIntent.putExtra(FRIEND_ID, id)
                    resultIntent.putExtra(ITEM_POSITION, itemPosition)
                    setResult(Activity.RESULT_OK, resultIntent)

                }
                finish()
            }

        }

    }
}