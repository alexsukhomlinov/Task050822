package net.virtmarket.task050822.flowerList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.virtmarket.task050822.R

class UserHeaderAdapter (private val onClick: () -> Unit) : RecyclerView.Adapter<UserHeaderAdapter.UserHeaderViewHolder>(){

    private var userCount: Int = 0

    /* ViewHolder for displaying header. */
    class UserHeaderViewHolder(view: View, val onClick: () -> Unit) : RecyclerView.ViewHolder(view){
        private val userNumberTextView: TextView = itemView.findViewById(R.id.user_number_text)

        //button initiation:
        private val buttonToFriends = itemView.findViewById(R.id.buttonToFriends) as Button

        init {
            buttonToFriends.setOnClickListener {

                onClick()

            }
        }


        fun bind(userCount: Int) {
            userNumberTextView.text = userCount.toString()
        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_header_item, parent, false)
        return UserHeaderViewHolder(view, onClick )
    }

    /* Binds number of flowers to the header. */
    override fun onBindViewHolder(holder: UserHeaderViewHolder, position: Int) {
        holder.bind(userCount)
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    /* Updates header to display number of flowers when a flower is added or subtracted. */
    fun updateUserCount(updatedUserCount: Int) {
        userCount = updatedUserCount
        notifyDataSetChanged()
    }
}

