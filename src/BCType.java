public enum BCType {
    AIRPORT_BUS ("Airport Bus"),
    FLIGHT("Flight"),
    TRAIN("Train");

    public String getPrintableName() {
        return this.printableName;
    }

    private String printableName;

   BCType(String printableName){
        this.printableName = printableName;
    }
}
