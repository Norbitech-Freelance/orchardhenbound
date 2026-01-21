package com.example.orchardhenbound.utils

import androidx.annotation.DrawableRes
import com.example.orchardhenbound.utils.getDrawableRes
import com.example.orchardhenbound.R

@DrawableRes
fun ItemType.getDrawableRes(): Int = when (this) {
    ItemType.GOOD_1 -> R.drawable.item_good_1
    ItemType.GOOD_2 -> R.drawable.item_good_2
    ItemType.GOOD_3 -> R.drawable.item_good_3
    ItemType.GOOD_4 -> R.drawable.item_good_4
    ItemType.GOOD_5 -> R.drawable.item_good_5
    ItemType.BAD_1 -> R.drawable.item_bad_1
    ItemType.BAD_2 -> R.drawable.item_bad_2
}

fun FallingItem.overlaps(
    playerX: Float,
    playerY: Float,
    playerW: Float,
    playerH: Float,
    itemSize: Float
): Boolean {
    val leftA = xPx
    val topA = yPx
    val rightA = xPx + itemSize
    val bottomA = yPx + itemSize

    val leftB = playerX
    val topB = playerY
    val rightB = playerX + playerW
    val bottomB = playerY + playerH

    return (rightA > leftB && leftA < rightB) && (bottomA > topB && topA < bottomB)
}
