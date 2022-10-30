package org.csystem.android.app.cinema

import org.csystem.android.app.cinema.data.entity.Cinema

object CinemaRepository {
    private val mCinemaList = ArrayList<Cinema>()

    fun save(cinema: Cinema) : Cinema
    {
        mCinemaList.add(cinema)

        cinema.id = mCinemaList.size
        return cinema
    }

    fun findAll() : List<Cinema> = mCinemaList

    fun count() = findAll().count()

    fun update(cinema: Cinema) : Boolean
    {
        val id = cinema.id

        if (id <= 0 || id > mCinemaList.size)
            return false;

        mCinemaList[id - 1] = cinema

        return true
    }

    //...
}