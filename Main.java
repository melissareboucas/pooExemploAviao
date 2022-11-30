import java.util.*;
import java.text.DecimalFormat;
import java.util.Scanner;


class Main {

  static DecimalFormat formato = new DecimalFormat("#,##0.00"); 
 
  public static void main(String[] args) {
    Scanner leitor = new Scanner(System.in);
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    Scanner scanner3 = new Scanner(System.in);
    Scanner scanner4 = new Scanner(System.in);
    Scanner scanner5 = new Scanner(System.in);
    Scanner scanner6 = new Scanner(System.in);
    Scanner scanner7 = new Scanner(System.in);
    Scanner scanner8 = new Scanner(System.in);
    Scanner scanner9 = new Scanner(System.in);
    Scanner scanner10 = new Scanner(System.in);
    Scanner scanner11 = new Scanner(System.in);
    Scanner scanner12 = new Scanner(System.in);
    Scanner scanner13 = new Scanner(System.in);
    Scanner scanner14 = new Scanner(System.in);
    Scanner scanner15 = new Scanner(System.in);
    Scanner scanner16 = new Scanner(System.in);
    int menuMain = 0;
    String origin;
    String destiny;
    String confirmation;
    int reservationId;
    int seatNumber;
    String name;
    String cpf;
    String password;
    Flight flight;
    Reservation reservation;

    
    while (menuMain!=6) {
    System.out.println("------------");
    System.out.println("Escolha entre as operações abaixo o que deseja:");
    System.out.println("1. Comprar uma passagem");
    System.out.println("2. Alterar o assento");
    System.out.println("3. Alterar titularidade de uma reserva");
    System.out.println("4. Cancelar uma reserva");
    System.out.println("5. Imprimir lista de passageiros");
    System.out.println("6. Sair");
    menuMain=leitor.nextInt();



    switch (menuMain)
    {      

      case 1:         
        System.out.println("Informe o aeroporto de origem: ");
        System.out.println("FOR");
        System.out.println("CGH");
        System.out.println("SSA");
        System.out.println("BSB");
        System.out.println("MAO");
        origin = scanner.next();
        System.out.println("Informe o aeroporto de destino: ");
        System.out.println("FOR");
        System.out.println("CGH");
        System.out.println("SSA");
        System.out.println("BSB");
        System.out.println("MAO");
        destiny = scanner1.next();
        

        try {
          flight = PossibleFlights.checkFlightExists(origin, destiny);
          System.out.println("Valor da passagem: "+ Double.valueOf(formato.format(flight.getCurrentTicketValue())));
          System.out.println("Deseja seguir com a compra? Y/N");
          confirmation = scanner2.next();
          if (confirmation.equals("Y")) {
            System.out.println("Nome do passageiro(a)");
            name = scanner3.next();
            System.out.println("CPF do passageiro(a)");
            cpf = scanner4.next();
            try {
              reservation = new Reservation(flight.getFlightId(), cpf, name);
              System.out.println("Id da reserva: "+reservation.getReservationId());
              System.out.println("Assento: "+reservation.getSeatNumber());
              System.out.println("Valor: "+reservation.getTicketValue());
              break;
            } catch (NoAvailableSeatsException e) {
              System.out.println("Voo lotado.");                      
            }
          }
          break;
          
        } catch (FlightNotFoundException e) {
          try {
            flight = new Flight(origin, destiny);
            System.out.println("Valor da passagem: "+ Double.valueOf(formato.format(flight.getCurrentTicketValue())));
            System.out.println("Deseja seguir com a compra? Y/N");
            confirmation = scanner2.next();
            if (confirmation.equals("Y")) {
              System.out.println("Nome do passageiro(a)");
              name = scanner3.next();
              System.out.println("CPF do passageiro(a)");
              cpf = scanner4.next();
              try {
                reservation = new Reservation(flight.getFlightId(), cpf, name);
                System.out.println("Id da reserva: "+reservation.getReservationId());
                System.out.println("Assento: "+reservation.getSeatNumber());
                System.out.println("Valor: "+reservation.getTicketValue());
                break;
              } catch (NoAvailableSeatsException err) {
                System.out.println("Voo lotado.");                      
              }
            }
            break;
          } catch (InvalidFlightException err) {
            System.out.println("Voo inválido, informe dados corretos.");
          } catch (FlightAlreadyExistsException err) {
            System.out.println("Voo já existe no sistema.");  
          } catch (InvalidCityException err) {
            System.out.println("Origem ou destino inválidos. Informe um valor da lista.");  
          }
          
          
        }
        
        
        
        
         
      break;

      case 2:
        System.out.println("Informe o código da reserva: ");
        reservationId = scanner8.nextInt();
        System.out.println("Informe o novo assento: ");
        seatNumber = scanner9.nextInt();
        try {
          PossibleFlights.getFlightById(PossibleReservations.getReservationById(reservationId).getflightId()).changeSeatNumber(reservationId, seatNumber);
          System.out.println("Assento alterado com sucesso para: "+PossibleReservations.getReservationById(reservationId).getSeatNumber() );
          
        } catch (ReservationNotFoundException e) {
          System.out.println("Reserva não encontrada.");
        } catch (UnavailableSeatsException e) {
          System.out.println("Assento indisponível no momento, informe um valor válido ou busque um assento disponível");
        } catch (UnavailableNormalSeatsException e) {
          System.out.println("Assento indisponível no momento, informe um assento da classe premium");
        }
        
      break;
      
      case 3:
        System.out.println("Informe o código da reserva: ");
        reservationId = scanner10.nextInt();
        System.out.println("Informe o novo nome: ");
        name = scanner11.nextLine();
        System.out.println("Informe o novo cpf: ");
        cpf = scanner12.nextLine();

        try {
          PossibleFlights.getFlightById(PossibleReservations.getReservationById(reservationId).getflightId()).changePassangerInfo(reservationId, cpf, name);
          System.out.println("Nome e CPF alterado com sucesso para: "+PossibleReservations.getReservationById(reservationId).getPassangerName() + " " + PossibleReservations.getReservationById(reservationId).getPassangerCPF());
        } catch (ReservationNotFoundException e) {
          System.out.println("Reserva não encontrada.");
        }

      break;

      case 4:
        System.out.println("Informe o código da reserva: ");
        reservationId = scanner13.nextInt();
        try {
          System.out.println("Reserva cancelada com sucesso. Valor reembolsado: " + Double.valueOf(formato.format(PossibleFlights.getFlightById(PossibleReservations.getReservationById(reservationId).getflightId()).cancelReservation(reservationId))));
          
        } catch (ReservationNotFoundException e) {
          System.out.println("Reserva não encontrada.");
        }
      break;
        
      case 5:
        System.out.println("Informe a senha: ");
        password = scanner14.next();
        System.out.println("Informe o aeroporto de origem: ");
        origin = scanner15.next();
        System.out.println("Informe o aeroporto de destino: ");
        destiny = scanner16.next();
        try {
          ArrayList<Reservation> aux = PossibleFlights.getFlightReservations(password, origin, destiny);
          double auxTotalTicketsValue = 0;

          for (int i = 0; i< aux.size(); i++) {
            auxTotalTicketsValue += aux.get(i).getTicketValue();
            System.out.println("Assento: " + aux.get(i).getSeatNumber() + "| " +
                          "Nome: " + aux.get(i).getPassangerName() + "| " +
                         "CPF: " + aux.get(i).getPassangerCPF() + "| " +
                          "Valor da passagem: " + Double.valueOf(formato.format(aux.get(i).getTicketValue())));
          }
          System.out.println("Total: " + Double.valueOf(formato.format(auxTotalTicketsValue)));

        } catch (WrongPasswordException e) {
          System.out.println("Senha incorreta, tente novamente!");
        } catch (FlightNotFoundException e) {
          System.out.println("Voo não encontrado no sistema.");
        }
      break;

      case 6:
      break;
    
      default:
        System.out.println("Digite uma opção válida \n");
    }

    
  }

  }
}