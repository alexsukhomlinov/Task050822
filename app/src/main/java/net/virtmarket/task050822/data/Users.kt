package net.virtmarket.task050822.data

import android.content.res.Resources
import net.virtmarket.task050822.R

fun userList(resources: Resources): List<User> {
    return listOf(
        User(
            id = 1,
            name = resources.getString(R.string.flower1_name),
            image = R.drawable.rose

        ),
        User(
            id = 2,
            name = resources.getString(R.string.flower2_name),
            image = R.drawable.freesia
        ),
        User(
            id = 3,
            name = resources.getString(R.string.flower3_name),
            image = R.drawable.lily

        ),
        User(
            id = 4,
            name = resources.getString(R.string.flower4_name),
            image = R.drawable.sunflower

        ),
        User(
            id = 5,
            name = resources.getString(R.string.flower5_name),
            image = R.drawable.peony,

        ),
        User(
            id = 6,
            name = resources.getString(R.string.flower6_name),
            image = R.drawable.daisy

        ),
        User(
            id = 7,
            name = resources.getString(R.string.flower7_name),
            image = R.drawable.lilac
        ),
        User(
            id = 8,
            name = resources.getString(R.string.flower8_name),
            image = R.drawable.marigold

        ),
        User(
            id = 9,
            name = resources.getString(R.string.flower9_name),
            image = R.drawable.poppy

        ),
        User(
            id = 10,
            name = resources.getString(R.string.flower10_name),
            image = R.drawable.daffodil
        ),

        User(
            id = 11,
            name = resources.getString(R.string.flower11_name),
            image = R.drawable.dahlia,
        )

    )
}