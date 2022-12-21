public class BCards {
    //Attributes --------------------------------------------
    private String Departure;
    private String Destination;
    BCType Type;
    private String Seat;
    private String TransportName;
    private String BoardingGate;
    private String BaggageDropCounter;

    //Methods ------------------------------------------------
    public String getDeparture() { return Departure; }
    public void setDeparture(String Departure) { this.Departure = Departure; }
    public String getDestination() { return Destination; }
    public void setDestination(String Destination) { this.Destination = Destination; }
    public String getSeat() { return Seat; }
    public void setSeat(String Seat) { this.Seat = Seat; }
    public String getTransportName() { return TransportName; }
    public void setTransportName(String TransportName) { this.TransportName = TransportName; }
    public String getBoardingGate() { return BoardingGate; }
    public void setBoardingGate(String BoardingGate) { this.BoardingGate = BoardingGate; }
    public String getBaggageDropCounter() { return BaggageDropCounter; }
    public void setBaggageDropCounter(String BaggageDropCounter) { this.BaggageDropCounter = BaggageDropCounter; }
    //public String getType() { return Type.getPrintableName(); }
    //public void setType(BCType Type) { this.Type = Type; }

    @Override public String toString()
    {
        return "Boarding card [Departure=" + Departure + ", Destination=" + Destination
                + ", Type=" + Type.getPrintableName() + ", Seat="
                + Seat + ", TransportName=" + TransportName + ", BoardingGate=" + BoardingGate + ", BaggageDropCounter=" + BaggageDropCounter + "]";
    }
    private String getSeatDescription(){
        if (Seat == null){
            return " No seat assignment.";
        } else {
            return " Sit in seat " + Seat + ".";
        }
    }
    private String getBaggageDescription(){
        if (BaggageDropCounter == null){
            return " Baggage will be automatically transferred from your last leg.";
        } else {
            return " Baggage drop at ticket counter " + BaggageDropCounter + ".";
        }
    }
    public String Description(){
        switch(Type.getPrintableName()){
            case "Flight":
                return "Take the " + Type.getPrintableName() + " "+ getTransportName() + " from " + getDeparture() + " to " + getDestination() + ". Gate " +getBoardingGate() + "," + getSeatDescription() + ""+ getBaggageDescription();
            case "Train":
                return "Take the " + Type.getPrintableName() + " "+ getTransportName() + " from " + getDeparture() + " to " + getDestination() + "." + getSeatDescription();
            case "Airport Bus":
                return "Take the " + Type.getPrintableName() + " from " + getDeparture() + " to " + getDestination() + "." + getSeatDescription();
        }
        String errorMessage = "No Transport !";
        return  errorMessage;
    }
}
