package net.virtmarket.task050822.flowerList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.virtmarket.task050822.R

class HeaderAdapter(private val onClick: () -> Unit) : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {

    private var friendCount: Int = 0

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View, val onClick: () -> Unit) : RecyclerView.ViewHolder(view){
        private val friendNumberTextView: TextView = itemView.findViewById(R.id.friend_number_text)

        //button initiation:
        private val buttonToUsers = itemView.findViewById(R.id.buttonToUsers) as Button

        init {
            buttonToUsers.setOnClickListener {

                    onClick()

            }
        }


        fun bind(friendCount: Int) {
            friendNumberTextView.text = friendCount.toString()
        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view, onClick )
    }

    /* Binds number of friends to the header. */
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(friendCount)
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    /* Updates header to display number of friends when a friend is added or subtracted. */
    fun updateFriendCount(updatedFriendCount: Int) {
        friendCount = updatedFriendCount
        notifyDataSetChanged()
    }
}

