package com.robertmm.mvpdroid.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by roberto on 1/26/16.
 */
public class Album implements Comparable<Album>, Parcelable {
    private final int id;
    private final int userId;
    private final String title;

    public Album( int id,int userId,String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (userId != album.userId) return false;
        if (id != album.id) return false;
        return title.equals(album.title);

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + id;
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public int compareTo(Album another) {
        if (this.equals(another) || this.id == another.id) return 0;
        return (this.id > another.id) ? -1 : 1;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.userId);
        dest.writeString(this.title);
    }

    private Album(Parcel in) {
        this.id = in.readInt();
        this.userId = in.readInt();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>(){
        @Override
        public Album createFromParcel(Parcel source) {
                return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

}
