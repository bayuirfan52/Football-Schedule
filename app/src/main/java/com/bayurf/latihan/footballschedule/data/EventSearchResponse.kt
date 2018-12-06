package com.bayurf.latihan.footballschedule.data

import com.google.gson.annotations.SerializedName

data class EventSearchResponse(
    @field:SerializedName("event")
    val events: MutableList<EventItem>
)