package net.virtmarket.task050822.addFlower


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import net.virtmarket.task050822.R
import com.google.android.material.textfield.TextInputEditText

const val FRIEND_NAME = "name"
const val FRIEND_IMAGE= "image"
const val FRIEND_DESCRIPTION = "description"
const val FRIEND_ID = "friend_id"
const val ITEM_POSITION = "item_position"



class AddFriendActivity : AppCompatActivity() {
    private lateinit var addFriendName: TextInputEditText
    //private lateinit var addFriendDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_friend_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addFriend()
        }
        addFriendName = findViewById(R.id.add_friend_name)
        //addFriendDescription = findViewById(R.id.add_friend_description)
    }

    /* The onClick action for the done button. Closes the activity and returns the new flower name
    and description as part of the intent. If the name or description are missing, the result is set
    to cancelled. */

    private fun addFriend() {
        val resultIntent = Intent()

        if (addFriendName.text.isNullOrEmpty() ) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addFriendName.text.toString()
            ///val description = addFriendDescription.text.toString()
            resultIntent.putExtra(FRIEND_NAME, name)
            resultIntent.putExtra(FRIEND_DESCRIPTION, "description")
            resultIntent.putExtra(FRIEND_IMAGE, R.drawable.rose)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}
