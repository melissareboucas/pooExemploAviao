import java.util.*;
import java.lang.Math;

public class Flight {
  
  private String origin;
  private String destiny;
  private static int flightIdReference = 0;
  private final int flightId;
  private int normalSeatsQuantity = 214;
  private int premiumSeatsQuantity = 6;
  private ArrayList<Reservation> reservations = new ArrayList<>();
  private double currentTicketValue = (100 + Math.pow(5, Math.log10(0)));



  public Flight(String origin, String destiny) throws InvalidFlightException, FlightAlreadyExistsException, InvalidCityException {
    this.origin = origin;
    this.destiny = destiny;
    if (origin.equals(destiny)) {
      throw new InvalidFlightException();
    }
    if((!origin.equals("FOR") && !origin.equals("CGH") && !origin.equals("SSA") && !origin.equals("BSB") && !origin.equals("MAO")) || (!destiny.equals("FOR") && !destiny.equals("CGH") && !destiny.equals("SSA") && !destiny.equals("BSB") && !destiny.equals("MAO"))) {
      throw new InvalidCityException();
    }
    for (int i = 0; i < PossibleFlights.getSize(); i++) {
      if (PossibleFlights.getFlight(i).getFlightOrigin() == origin && PossibleFlights.getFlight(i).getFlightDestiny() == destiny) {
        throw new FlightAlreadyExistsException();
      }    
    } 
    PossibleFlights.setFlight(this);
    
    flightIdReference++;
    flightId = flightIdReference;
  }

  public int getFlightId(){
    return flightId;
  }

  public String getFlightOrigin() {
    return origin;
  }

  public String getFlightDestiny() {
    return destiny;
  }

  public int getNormalSeatsQuantity() {
    return normalSeatsQuantity;
  }
  
  public int getPremiumSeatsQuantity() {
    return premiumSeatsQuantity;
  }

  public void decreaseNormalSeatsQuantity() {
    normalSeatsQuantity--;
  }

  public void decreasePremiumSeatsQuantity() {
    premiumSeatsQuantity--;
  }

  public void addReservation(Reservation e) {
    reservations.add(e);
  }

  public Reservation getReservation(int reservationId) {
    for (int i = 0; i < reservations.size(); i++) {
      if (reservations.get(i).getReservationId() == reservationId) {
        return reservations.get(i);
      }
    }
    return null;
  }

  public void changeSeatNumber(int reservationId, int newSeatNumber) throws UnavailableSeatsException, UnavailableNormalSeatsException {
    if (getNormalSeatsQuantity() > 0) {
      for (int i = 0; i<reservations.size(); i++) {
          if (reservations.get(i).getSeatNumber() == newSeatNumber) {
            throw new UnavailableSeatsException();
          }     
      }
      getReservation(reservationId).setSeatNumber(newSeatNumber);
      if (newSeatNumber < 6) {
        //cobrar 50 reais a mais!
        getReservation(reservationId).increaseTicketValue(50);
        normalSeatsQuantity++;
        premiumSeatsQuantity--;

      } 
      
    } else {
      if (getPremiumSeatsQuantity() > 0) {
        if (newSeatNumber > 6) {
          throw new UnavailableNormalSeatsException();
        }
        for (int i = 0; i<reservations.size(); i++) {
          if (reservations.get(i).getSeatNumber() == newSeatNumber) {
            throw new UnavailableSeatsException();
          }
        } 
        getReservation(reservationId).setSeatNumber(newSeatNumber);
        normalSeatsQuantity++;
        premiumSeatsQuantity--;
      } else {
        throw new UnavailableSeatsException();
      }
    }
    
  }

  public void changePassangerInfo(int reservationId, String cpf, String name) throws ReservationNotFoundException {
    for (int i = 0; i < reservations.size(); i++) {
      if (reservations.get(i).getReservationId() == reservationId) {
        reservations.get(i).updatePassanger(cpf, name);
        return;
      }
    }
    throw new ReservationNotFoundException();
  }

  public double cancelReservation(int reservationId) throws ReservationNotFoundException {
    for (int i = 0; i < reservations.size(); i++) {
      if (reservations.get(i).getReservationId() == reservationId) {
        if (reservations.get(i).getSeatNumber() > 6) {
          normalSeatsQuantity++;
        } else {
          premiumSeatsQuantity++;
        }
        //pegar dinheiro da passagem e guardar numa variável value
        double value = reservations.get(i).getTicketValue();
        reservations.remove(i);
        setCurrentTicketValue();
        return value;
      }
    }
    throw new ReservationNotFoundException();
  }

  public double getCurrentTicketValue() {
    return currentTicketValue;
  }

  public void setCurrentTicketValue() {
    currentTicketValue = (100 + Math.pow(5, Math.log10(reservations.size()+1)));
  }

  public ArrayList<Reservation> getAllReservations() {
    return reservations;
  }








}