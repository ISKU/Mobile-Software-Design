package lab.lab07_3;

public class SingerItem {

    public int resId;
    public String team;
    public String number;
    public String year;

    public SingerItem(int resId, String team, String number, String year) {
        this.resId = resId;
        this.team = team;
        this.number = number;
        this.year = year;
    }

    public int getIcon() {
        return resId;
    }

    public void setIcon(int resId) {
        this.resId = resId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}