package lab.lab3_2;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    public int number;
    public String message;

    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }

    public SimpleData(Parcel src) {
        this.number = src.readInt();
        this.message = src.readString();
    }

    public static final Creator CREATOR = new Creator() {
        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(message);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
