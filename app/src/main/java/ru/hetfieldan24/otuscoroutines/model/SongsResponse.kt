package ru.hetfieldan24.otuscoroutines.model

import com.google.gson.annotations.SerializedName

class SongsResponse {

    var genre: String? = null

    @SerializedName("tracks")
    var songs: List<Songs>? = null

    class Songs {
        @SerializedName("uNm")
        var userName: String? = null

        @SerializedName("name")
        var songName: String? = null

        @SerializedName("img")
        var imageUrl: String? = null

        @SerializedName("pl")
        var subGenre: SubGenre? = null

        class SubGenre {
            var name: String? = null
        }
    }
}
