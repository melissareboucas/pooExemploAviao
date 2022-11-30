import java.util.ArrayList;

public abstract class PossibleReservations {
  private static ArrayList<Reservation> allReservations = new ArrayList<>();

  public static void setReservation(Reservation reservation) {
    allReservations.add(reservation);
  }

  public static Reservation getReservation(int i) {
    return allReservations.get(i);
  }

  public static Reservation getReservationById(int id) throws ReservationNotFoundException {
    for (int i = 0; i<allReservations.size(); i++) {
      if (allReservations.get(i).getReservationId() == id) {
        return allReservations.get(i);
      } 
    } 
    throw new ReservationNotFoundException();
  }

  public static int getAllReservationsSize() {
    return allReservations.size();
  }


}