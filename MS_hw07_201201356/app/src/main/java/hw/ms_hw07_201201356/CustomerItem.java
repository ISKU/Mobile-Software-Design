package hw.ms_hw07_201201356;

public class CustomerItem {

    private int iconResourceId;
    private String name;
    private String birth;
    private String number;

    public CustomerItem(int iconResourceId, String name, String birth, String number) {
        this.iconResourceId = iconResourceId;
        this.name = name;
        this.birth = birth;
        this.number = number;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}