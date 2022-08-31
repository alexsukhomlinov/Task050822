package net.virtmarket.task050822.flowerList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.virtmarket.task050822.R
import net.virtmarket.task050822.data.Friend

class FriendsAdapter (private val onClick: (Friend, itemPosition:Int) -> Unit) :
    ListAdapter<Friend, FriendsAdapter.FriendViewHolder>(FriendDiffCallback) {

    /* ViewHolder for Frirnd, takes in the inflated view and the onClick behavior. */
    class FriendViewHolder(itemView: View, val onClick: (Friend, itemPosition:Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val friendTextView: TextView = itemView.findViewById(R.id.friend_text)
        private val friendImageView: ImageView = itemView.findViewById(R.id.friend_image)
        private var currentFriend: Friend? = null
        //val itemPosition:Int = getAbsoluteAdapterPosition()

        init {
            itemView.setOnClickListener {
                currentFriend?.let {
                    val itemPosition:Int = getAbsoluteAdapterPosition()-1
                    println("ABSOLUTE ADAPTER POSITION="+itemPosition)
                    onClick(it, itemPosition)
                }
            }
        }

        /* Bind friend name and image. */
        fun bind(friend: Friend) {
            currentFriend = friend

            friendTextView.text = friend.name
            if (friend.image != null) {
                friendImageView.setImageResource(friend.image)
            } else {
                friendImageView.setImageResource(R.drawable.rose)
            }
        }
    }

    /* Creates and inflates view and return FriendViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_item, parent, false)
        return FriendViewHolder(view, onClick)
    }

    /* Gets current friend and uses it to bind view. */
    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = getItem(position)
        holder.bind(friend)

    }
}

object FriendDiffCallback : DiffUtil.ItemCallback<Friend>() {
    override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem.id == newItem.id
    }
}