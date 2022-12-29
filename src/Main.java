//Using jackson to serialize and deserialize
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
//Imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
class Application {
    // Main ------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        // Creating an object mapper instance (jackson)
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // Checking for exceptions
        try {
            // input stream points to a json file
            InputStream inputStream = new FileInputStream("C:/Users/amelm/OneDrive/Bureau/Job application Test - YuccaLabs/BoardingCards.json");
            // Deserializes from json file to BCards object (class) ---------------------------
            // Convert JSON array to a list of cards ------------------------------------------
            List<BCards> cards = Arrays.asList(mapper.readValue(inputStream, BCards[].class));
            // Sorting and printing the journey -----------------------------------------------
            LinkedList<BCards> SortedBC;
            SortedBC = (LinkedList<BCards>) SortBCards((cards));
            //SortedBC.forEach(System.out::println);
            Print(SortedBC);
        }
        // Handling FileNotFoundException and other exceptions ----------------------------------
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (JsonParseException e) {
            // Displaying the exception along with line
            // number using printStackTrace()
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to sort the boarding cards -------------------------------------------------------------------------------
    public static List<BCards> SortBCards(List<BCards> UnsortedCards) {
        //Print Unsorted cards (just to check dev side) --------------------------------------
        UnsortedCards.forEach(System.out::println);
        //Checking the list ------------------------------------------------------------------
        if (UnsortedCards == null || UnsortedCards.size() <= 1) return UnsortedCards;
        //Sorting boarding cards --------------------------------------------------------------
        LinkedList<BCards> sortedCards = new LinkedList<>();
        //Turning the unsorted list to hashmap structure --------------------------------------
        CardsMap cardMap = new CardsMap(UnsortedCards);
        //Picking a random card to begin with -------------------------------------------------
        BCards departure = UnsortedCards.get(0);
        BCards currentCard = departure;
        sortedCards.add(currentCard);
        //Iterate through until we reach the final destination --------------------------------
        while (cardMap.sources.containsKey(currentCard.getDestination())){
            currentCard = cardMap.sources.get(currentCard.getDestination());
            sortedCards.addLast(currentCard);
        }
        //now go back to the starting point and iterate to the beginning of the journey
        currentCard = departure;
        while (cardMap.destinations.containsKey(currentCard.getDeparture())){
            currentCard = cardMap.destinations.get(currentCard.getDeparture());
            sortedCards.addFirst(currentCard);
        }
        return sortedCards;
    }
    private static class CardsMap{
        HashMap<String, BCards> sources = new HashMap<>();
        HashMap<String, BCards> destinations= new HashMap<>();

        private CardsMap(List<BCards> UnsortedCards){
            UnsortedCards.forEach(card -> this.sources.put(card.getDeparture(),card));
            UnsortedCards.forEach(card -> this.destinations.put(card.getDestination(),card));
        }
    }

    public static void Print(LinkedList<BCards> sortedlist){
        //Output ------------------------------------------------------------------------------------------------
        int Card = 0;
        for (BCards card : sortedlist){
            System.out.println(++Card + ". " + card.Description());
        }
        System.out.println(++Card + ". You have arrived at your destination.");
    }

}