package com.app.nshape_movie_task.domain.entity

import com.google.gson.annotations.SerializedName

data class GeneralErrorDto (
  @SerializedName("status_code")
  val status_code:Int,
  @SerializedName("status_message")
  val status_message:String,
  @SerializedName("success")
  val success:Boolean
)