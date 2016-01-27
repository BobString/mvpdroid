package com.robertmm.mvpdroid.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by roberto on 1/26/16.
 */
public class Photo implements Comparable<Photo>, Parcelable {
    private final int albumId;
    private final int id;
    private final String title;
    private final String url;
    private final String thumbnailUrl;

    public Photo(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (albumId != photo.albumId) return false;
        if (id != photo.id) return false;
        if (!title.equals(photo.title)) return false;
        if (!url.equals(photo.url)) return false;
        return thumbnailUrl.equals(photo.thumbnailUrl);

    }

    @Override
    public int hashCode() {
        int result = albumId;
        result = 31 * result + id;
        result = 31 * result + title.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + thumbnailUrl.hashCode();
        return result;
    }

    @Override
    public int compareTo(Photo another) {
        if (this.equals(another) || this.id == another.id) return 0;
        return (this.id > another.id) ? -1 : 1;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.albumId);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.thumbnailUrl);
    }

    private Photo(Parcel in) {
        this.albumId = in.readInt();
        this.id = in.readInt();
        this.title = in.readString();
        this.url = in.readString();
        this.thumbnailUrl = in.readString();
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>(){
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

}
