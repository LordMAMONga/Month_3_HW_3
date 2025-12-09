package com.geeks.hw_3.data.model

import androidx.annotation.RawRes

data class OnBoardModel(
    val title: String,
    val desc: String,
    @RawRes val animationId: Int

)