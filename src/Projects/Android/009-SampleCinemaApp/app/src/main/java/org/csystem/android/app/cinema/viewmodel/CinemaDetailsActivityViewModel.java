package org.csystem.android.app.cinema.viewmodel;

import androidx.annotation.NonNull;

import org.csystem.android.app.cinema.CinemaDetailsActivity;
import org.csystem.android.app.cinema.data.entity.Cinema;

public class CinemaDetailsActivityViewModel {
    private final CinemaDetailsActivity m_activity;
    private Cinema m_cinema;

    public CinemaDetailsActivityViewModel(CinemaDetailsActivity activity)
    {
        m_activity = activity;
        m_cinema = new Cinema("", 0, 0, "", "");
    }

    public void setName(String s)
    {
        m_cinema.setName(s);
    }

    public String getName()
    {
        return m_cinema.getName();
    }

    public int getType()
    {
        return m_cinema.getType();
    }

    public void setType(int i)
    {
        m_cinema.setType(i);
    }

    public int getYear()
    {
        return m_cinema.getYear();
    }

    public void setYear(int i)
    {
        m_cinema.setYear(i);
    }

    public void setDirector(@NonNull String s)
    {
        m_cinema.setDirector(s);
    }

    public String getDirector()
    {
        return m_cinema.getDirector();
    }

    public void setCompany(@NonNull String s)
    {
        m_cinema.setCompany(s);
    }

    public String getCompany()
    {
        return m_cinema.getCompany();
    }

    public int getId()
    {
        return m_cinema.getId();
    }

    public void setId(int i)
    {
        m_cinema.setId(i);
    }

    public Cinema getCinema()
    {
        return m_cinema;
    }

    public void setCinema(Cinema cinema)
    {
        m_cinema = cinema;
    }

    public void handleUpdateButton()
    {
        m_activity.updateButtonClickCallback();
    }

    public void handleDeleteButton()
    {
        m_activity.deleteButtonClickCallback();
    }

}
