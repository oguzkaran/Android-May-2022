package org.csystem.android.app.cinema

import org.csystem.android.app.cinema.data.entity.Cinema

object CinemaRepository {
    private val mCinemaList = ArrayList<Cinema>()

    fun save(cinema: Cinema) : Cinema
    {
        mCinemaList.add(cinema)

        return cinema
    }

    fun findAll() : List<Cinema> = mCinemaList

    fun count() = findAll().count()

    //...
}