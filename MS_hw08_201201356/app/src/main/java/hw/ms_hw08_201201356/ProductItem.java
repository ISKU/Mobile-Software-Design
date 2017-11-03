package hw.ms_hw08_201201356;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductItem implements Parcelable {

    public static final String PRODUCT_ITEM_KEY = "data";

    public int resId;
    public String maker;
    public String title;
    public int price;
    public String description;

    public ProductItem(int resId, String maker, String title, int price, String description) {
        this.resId = resId;
        this.maker = maker;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    protected ProductItem(Parcel in) {
        resId = in.readInt();
        maker = in.readString();
        title = in.readString();
        price = in.readInt();
        description = in.readString();
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel in) {
            return new ProductItem(in);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(resId);
        parcel.writeString(maker);
        parcel.writeString(title);
        parcel.writeInt(price);
        parcel.writeString(description);
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPirce(int pirce) {
        this.price = pirce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
