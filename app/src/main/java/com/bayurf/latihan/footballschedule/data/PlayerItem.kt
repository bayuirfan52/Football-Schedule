package com.bayurf.latihan.footballschedule.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerItem(
    val idPlayer: String?,

    @SerializedName("strPlayer")
    val playerName: String?,

    val strPosition: String?,
    val strHeight: String?,
    val strWeight: String?,

    @SerializedName("strCutout")
    val playerIcon: String?,

    @SerializedName("strFanart1")
    val playerBanner: String?,

    val strDescriptionEN: String?
) : Parcelable