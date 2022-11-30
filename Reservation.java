public class Reservation {

  private int seatNumber;
  private static int reservationIdReference = 100000;
  private final int reservationId;
  private int flightId;
  private String passangerName;
  private String passangerCPF;
  private double ticketValue;

  public Reservation(int flightId, String passangerCPF, String passangerName) throws NoAvailableSeatsException {
    reservationIdReference++;
    reservationId = reservationIdReference;
    this.flightId = flightId;
    this.passangerName = passangerName;
    this.passangerCPF = passangerCPF;
    ticketValue = PossibleFlights.getFlightById(flightId).getCurrentTicketValue();
    PossibleFlights.getFlightById(flightId).setCurrentTicketValue();
    PossibleReservations.setReservation(this);

    if (PossibleFlights.getFlightById(flightId).getNormalSeatsQuantity() > 0) {
      seatNumber = PossibleFlights.getFlightById(flightId).getNormalSeatsQuantity()+6;
      PossibleFlights.getFlightById(flightId).decreaseNormalSeatsQuantity();
      PossibleFlights.getFlightById(flightId).addReservation(this);
      
    } else {
      if (PossibleFlights.getFlightById(flightId).getPremiumSeatsQuantity() > 0) {
        seatNumber = PossibleFlights.getFlightById(flightId).getPremiumSeatsQuantity();
        PossibleFlights.getFlightById(flightId).decreasePremiumSeatsQuantity();
        PossibleFlights.getFlightById(flightId).addReservation(this);
        
      } else {
        throw new NoAvailableSeatsException();
      }
    }
    

    
  }

  public int getReservationId() {
    return reservationId;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(int newSeatNumber) {
    seatNumber = newSeatNumber;
  }

  public void updatePassanger(String cpf, String name) {
    passangerCPF = cpf;
    passangerName = name;
  }

  public String getPassangerName() {
    return passangerName;
  }

  public String getPassangerCPF() {
    return passangerCPF;
  }

  public double getTicketValue() {
    return ticketValue;
  }

  public void increaseTicketValue(int value) {
    ticketValue = ticketValue + value;
  }

  public int getflightId() {
    return flightId;
  }

}