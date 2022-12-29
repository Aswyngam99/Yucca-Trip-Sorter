import com.fasterxml.jackson.annotation.JsonCreator;

public enum BCType {
    AIRPORT_BUS ("Airport Bus") ,
    FLIGHT ("Flight"),
    TRAIN ("Train");
    private final String type;

  BCType(String type){
        this.type = type;
    }
    @Override
    public String toString() {
        return type;
    }



}
