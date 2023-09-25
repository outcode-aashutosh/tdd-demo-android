package com.outcode.groovy.playlist

import retrofit2.http.GET

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchAllPlaylists() : List<PlaylistRaw>
}
