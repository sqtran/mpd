package karabelas.mpd;

public class ParkingMovingInfraction {

    private int id;

    private String code;

    private String description;

    private String shortDescription;

    private float fine;

    private int display;

    public ParkingMovingInfraction() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public float getFine() {
        return Float.valueOf(fine);
    }

    public void setFine(float fine) {
        this.fine = fine;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(500);
        sb.append("ID:" + this.id);
        sb.append(", Code:" + this.code);
        sb.append(", Description:" + this.description);
        sb.append(",Fine:" + this.fine);
        return sb.toString();

    }
}
