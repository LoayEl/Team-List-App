package com.example.collegeteams.model

import android.content.Intent.ShortcutIconResource
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Team(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
    )
