import java.util.*; 


public abstract class PossibleFlights {
  private static String password = "123456";
  private static ArrayList<Flight> flights = new ArrayList<>();

  public static void setFlight(Flight flight) {
    flights.add(flight);
  }

  public static Flight getFlight(int i) {
    return flights.get(i);
  }

  public static int getFlightsSize() {
    return flights.size();
  }

  public static Flight getFlightById(int id) throws FlightNotFoundException{
    for (int i = 0; i<flights.size(); i++) {
      if (flights.get(i).getFlightId() == id) {
        return flights.get(i);
      } 
    } 
    throw new FlightNotFoundException();
  }

  public static int getSize() {
    return flights.size();
  }
  
  public static Flight checkFlightExists(String origin, String destiny) throws FlightNotFoundException {
    for (int i = 0; i < flights.size(); i++) {
      if (flights.get(i).getFlightOrigin().equals(origin) && flights.get(i).getFlightDestiny().equals(destiny)) {
          return flights.get(i);
        }
      }
      throw new FlightNotFoundException();
  }

  public static ArrayList<Reservation> getFlightReservations(String pw, String origin, String destiny) throws WrongPasswordException, FlightNotFoundException {
    if (pw.equals(password)) {
      for (int i = 0; i < flights.size(); i++) {
        if (flights.get(i).getFlightOrigin().equals(origin) && flights.get(i).getFlightDestiny().equals(destiny)) {
          return flights.get(i).getAllReservations();
        }
      }
      throw new FlightNotFoundException();
    }
    throw new WrongPasswordException();
  }

  

  
}