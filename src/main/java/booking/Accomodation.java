package booking;

public class Accomodation {
    private int accomodationId;
    private String accomodationType;
    private String accomodationBedType;
    private int maxGuestNr;
    private String accomodationDescription;
    private double accomodationValue;
    private String season;

    public Accomodation() {

    }

    public Accomodation(double accomodationValue, String season) {
        this.accomodationValue = accomodationValue;
        this.season = season;
    }

    public Accomodation(int accomodationId, String accomodationType, String accomodationBedType, int maxGuestNr, String accomodationDescription, double accomodationValue, String season) {
        this.accomodationId = accomodationId;
        this.accomodationType = accomodationType;
        this.accomodationBedType = accomodationBedType;
        this.maxGuestNr = maxGuestNr;
        this.accomodationDescription = accomodationDescription;
        this.accomodationValue = accomodationValue;
        this.season = season;
    }

    public int getAccomodationId() {
        return accomodationId;
    }

    public void setAccomodationId(int accomodationId) {
        this.accomodationId = accomodationId;
    }

    public String getAccomodationType() {
        return accomodationType;
    }

    public void setAccomodationType(String accomodationType) {
        this.accomodationType = accomodationType;
    }

    public String getAccomodationBedType() {
        return accomodationBedType;
    }

    public void setAccomodationBedType(String accomodationBedType) {
        this.accomodationBedType = accomodationBedType;
    }

    public int getMaxGuestNr() {
        return maxGuestNr;
    }

    public void setMaxGuestNr(int maxGuestNr) {
        this.maxGuestNr = maxGuestNr;
    }

    public String getAccomodationDescription() {
        return accomodationDescription;
    }

    public void setAccomodationDescription(String accomodationDescription) {
        this.accomodationDescription = accomodationDescription;
    }

    public double getAccomodationValue() {
        return accomodationValue;
    }

    public void setAccomodationValue(double accomodationValue) {
        this.accomodationValue = accomodationValue;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Accomodation addAccomodation(BookingRegistry registry) {
        System.out.print("Add accomodation ID: ");
        accomodationId = registry.userInputInt();
        System.out.print("Add accomodation type: ");
        accomodationType = registry.userInputString();
        System.out.print("Add accomodation bed type: ");
        accomodationBedType = registry.userInputString();
        System.out.print("Add accomodation maximum guest number: ");
        maxGuestNr = registry.userInputInt();
        System.out.print("Add accomodation description: ");
        accomodationDescription = registry.userInputString();
        System.out.print("Add accomodation price: ");
        accomodationValue = registry.userInputDouble();
        System.out.print("Add accomodation season: ");
        season = registry.userInputString();
        Accomodation accomodation = new Accomodation(accomodationId, accomodationType, accomodationBedType, maxGuestNr, accomodationDescription, accomodationValue, season);
        return accomodation;
    }

    @Override
    public String toString() {
        return "Accomodation{" +
                "accomodationId=" + accomodationId +
                ", accomodationType='" + accomodationType + '\'' +
                ", accomodationBedType='" + accomodationBedType + '\'' +
                ", maxGuestNr=" + maxGuestNr +
                ", accomodationDescription='" + accomodationDescription + '\'' +
                ", accomodationValue=" + accomodationValue +
                ", season='" + season + '\'' +
                '}';
    }
}
