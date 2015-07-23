package com.example.noa.timer;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;


// class for creation of Parcelable Container for start-time and end-time pairs
public class Pair implements Parcelable {
    public Long start = new Long(0);
    public Long end = new Long(0);

    public Pair(Long s, Long e) {
        this.start = s;
        this.end = e;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(this.start);
        out.writeLong(this.end);
    }

    private Pair(Parcel in) {
        this.start = in.readLong();
        this.end = in.readLong();
    }

    public static final Parcelable.Creator<Pair> CREATOR
            = new Parcelable.Creator<Pair>() {
        public Pair createFromParcel(Parcel in) {
            return new Pair(in);
        }

        public Pair[] newArray(int size) {
            return new Pair[size];
        }
    };
    // convert longs to string
    public String toString() {
        return String.format("%s - %s;", this.start, this.end);
    }

}
