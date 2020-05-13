package com.example.agendiario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ideas")

public class Idea {

    public Idea(String creationDate, @Nullable String name, String description, int facility) {
        mCreationDate = creationDate;
        mName = name;
        mDescription = description;
        mFacility = facility;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ideaId")
    private int mId;

    private String mCreationDate;

    @Nullable
    @ColumnInfo(name = "ideaName")
    private String mName;

    private String mDescription;

    private int mFacility;

    public String getCreationDate() {
        return mCreationDate;
    }

    public void setCreationDate(String creationDate) {
        mCreationDate = creationDate;
    }

    @Nullable
    public String getName() {
        return mName;
    }

    public void setName(@Nullable String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getFacility() {
        return mFacility;
    }

    public void setFacility(int facility) {
        mFacility = facility;
    }
}
