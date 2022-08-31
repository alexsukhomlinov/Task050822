package net.virtmarket.task050822.data

import android.content.res.Resources
import net.virtmarket.task050822.R
import net.virtmarket.task050822.data.Friend

/* Returns initial list of flowers. */
fun friendList(resources: Resources): List<Friend> {
    return listOf(
        Friend(
            id = 1,
            name = resources.getString(R.string.flower1_name),
            image = R.drawable.rose,
            description = resources.getString(R.string.flower1_description)
        ),
        Friend(
            id = 2,
            name = resources.getString(R.string.flower2_name),
            image = R.drawable.freesia,
            description = resources.getString(R.string.flower2_description)
        ),
        Friend(
            id = 3,
            name = resources.getString(R.string.flower3_name),
            image = R.drawable.lily,
            description = resources.getString(R.string.flower3_description)
        )


    )
}
