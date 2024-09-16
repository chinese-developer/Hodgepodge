package com.component.whatif

import android.os.Parcel
import android.os.Parcelable

internal data class Poster(
  val id: Long,
  val name: String?,
  val release: String?,
  val playtime: String?,
  val description: String?,
) : Parcelable {

  constructor(parcel: Parcel) : this(
    parcel.readLong(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeLong(id)
    parcel.writeString(name)
    parcel.writeString(release)
    parcel.writeString(playtime)
    parcel.writeString(description)
  }

  override fun describeContents() = 0

  companion object CREATOR : Parcelable.Creator<Poster> {
    override fun createFromParcel(parcel: Parcel): Poster {
      return Poster(parcel)
    }

    override fun newArray(size: Int): Array<Poster?> {
      return arrayOfNulls(size)
    }

    fun create() = Poster(
      id = 0,
      name = "黑神话悟空",
      release = "2024",
      playtime = "1 h 43 min",
      description = "《黑神话：悟空》是一款以中国神话为背景的动作角色扮演游戏，" +
        "故事取材于中国古典小说“四大名著”之一的《西游记》。 " +
        "您将扮演一位“天命人”，为了探寻昔日传说的真相，踏上一条充满危险与惊奇的西游之路。 " +
        "作为“天命人”，您将在整个旅程中遭遇强大的敌人与可敬的对手。",
    )
  }
}
