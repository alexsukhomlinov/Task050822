package net.virtmarket.task050822.flowerList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import net.virtmarket.task050822.R
//import net.virtmarket.task030822.data.Flower
import net.virtmarket.task050822.data.User

class UsersAdapter (private val onClick: (User) -> Unit) :
    ListAdapter<User, UsersAdapter.UserViewHolder>(UserDiffCallback) {

    /* ViewHolder for User, takes in the inflated view and the onClick behavior. */
    class UserViewHolder(itemView: View, val onClick: (User) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val userTextView: TextView = itemView.findViewById(R.id.user_text)
        private val userImageView: ImageView = itemView.findViewById(R.id.user_image)
        private val addAsFriendButton: Button = itemView.findViewById(R.id.button_add_as_friend)

        private var currentUser: User? = null

        init {
            addAsFriendButton.setOnClickListener {
                currentUser?.let {
                    onClick(it)
                }
            }
        }

        /* Bind user name and image. */
        fun bind(user: User) {
            currentUser = user

            userTextView.text = user.name
            if (user.image != null) {
                userImageView.setImageResource(user.image)
            } else {
                userImageView.setImageResource(R.drawable.rose)
            }
        }
    }

    /* Creates and inflates view and return UserViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view, onClick)
    }

    /* Gets current user and uses it to bind view. */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

    }
}

object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}