import java.util.*;

class Train {
    int trainNo;
    String name, from, to;
    int slSeats, acSeats, genSeats;
    int slFare, acFare, genFare;

    Train(int trainNo, String name, String from, String to,
          int slSeats, int acSeats, int genSeats,
          int slFare, int acFare, int genFare) {

        this.trainNo = trainNo;
        this.name = name;
        this.from = from;
        this.to = to;
        this.slSeats = slSeats;
        this.acSeats = acSeats;
        this.genSeats = genSeats;
        this.slFare = slFare;
        this.acFare = acFare;
        this.genFare = genFare;
    }
}

class Ticket {
    String pnr;
    Train train;
    String travelClass;
    List<String> passengers;
    int totalFare;

    Ticket(String pnr, Train train, String travelClass,
           List<String> passengers, int totalFare) {
        this.pnr = pnr;
        this.train = train;
        this.travelClass = travelClass;
        this.passengers = passengers;
        this.totalFare = totalFare;
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Train> trains = new ArrayList<>();

    public static void main(String[] args) {

        // ===== PRELOADED TRAINS =====
        trains.add(new Train(101, "Chennai Express", "Chennai", "Bangalore", 20, 10, 30, 300, 900, 150));
        trains.add(new Train(102, "Intercity SF", "Chennai", "Trichy", 15, 8, 25, 320, 950, 180));
        trains.add(new Train(201, "Southern Mail", "Madurai", "Chennai", 25, 12, 40, 280, 850, 140));

        System.out.println("===== TRAIN TICKET BOOKING SYSTEM =====");

        // ===== SEARCH =====
        System.out.print("Enter From Station: ");
        String from = sc.nextLine();

        System.out.print("Enter To Station: ");
        String to = sc.nextLine();

        List<Train> availableTrains = new ArrayList<>();

        System.out.println("\nAvailable Trains:");
        for (Train t : trains) {
            if (t.from.equalsIgnoreCase(from) &&
                t.to.equalsIgnoreCase(to)) {

                availableTrains.add(t);
                System.out.println(t.trainNo + " - " + t.name);
            }
        }

        if (availableTrains.isEmpty()) {
            System.out.println("No trains available!");
            return;
        }

        // ===== SELECT TRAIN =====
        System.out.print("\nEnter Train Number: ");
        int trainNo = sc.nextInt();

        Train selectedTrain = null;
        for (Train t : availableTrains) {
            if (t.trainNo == trainNo) {
                selectedTrain = t;
                break;
            }
        }

        if (selectedTrain == null) {
            System.out.println("Invalid Train Number!");
            return;
        }

        // ===== CLASS =====
        System.out.println("\nChoose Class:");
        System.out.println("1. Sleeper (SL)");
        System.out.println("2. AC");
        System.out.println("3. General");
        System.out.print("Enter choice: ");
        int classChoice = sc.nextInt();

        String travelClass = "";
        int availableSeats = 0;
        int fare = 0;

        switch (classChoice) {
            case 1:
                travelClass = "SL";
                availableSeats = selectedTrain.slSeats;
                fare = selectedTrain.slFare;
                break;
            case 2:
                travelClass = "AC";
                availableSeats = selectedTrain.acSeats;
                fare = selectedTrain.acFare;
                break;
            case 3:
                travelClass = "GENERAL";
                availableSeats = selectedTrain.genSeats;
                fare = selectedTrain.genFare;
                break;
            default:
                System.out.println("Invalid class!");
                return;
        }

        System.out.println("Available Seats: " + availableSeats);

        // ===== SEAT COUNT =====
        System.out.print("Enter number of seats: ");
        int seatCount = sc.nextInt();

        if (seatCount > availableSeats) {
            System.out.println("Not enough seats available!");
            return;
        }

        sc.nextLine(); // clear buffer

        // ===== MULTIPLE PASSENGERS INPUT (FIX) =====
        List<String> passengers = new ArrayList<>();
        for (int i = 1; i <= seatCount; i++) {
            System.out.print("Enter Passenger " + i + " Name: ");
            passengers.add(sc.nextLine());
        }

        int totalFare = seatCount * fare;

        // ===== PAYMENT =====
        System.out.println("\nTotal Fare: ₹" + totalFare);
        System.out.print("Confirm Payment (yes/no): ");
        String pay = sc.nextLine();

        if (!pay.equalsIgnoreCase("yes")) {
            System.out.println("Payment cancelled!");
            return;
        }

        // ===== UPDATE SEATS =====
        if (travelClass.equals("SL")) selectedTrain.slSeats -= seatCount;
        else if (travelClass.equals("AC")) selectedTrain.acSeats -= seatCount;
        else selectedTrain.genSeats -= seatCount;

        // ===== CONFIRM TICKET =====
        String pnr = "PNR" + (10000 + new Random().nextInt(90000));
        Ticket ticket = new Ticket(pnr, selectedTrain, travelClass, passengers, totalFare);

        printTicket(ticket);
    }

    static void printTicket(Ticket t) {
        System.out.println("\n===== TICKET CONFIRMED =====");
        System.out.println("PNR        : " + t.pnr);
        System.out.println("Train      : " + t.train.trainNo + " - " + t.train.name);
        System.out.println("Route      : " + t.train.from + " → " + t.train.to);
        System.out.println("Class      : " + t.travelClass);
        System.out.println("Passengers :");
        for (String p : t.passengers) {
            System.out.println("  - " + p);
        }
        System.out.println("Seats      : " + t.passengers.size());
        System.out.println("Total Fare : ₹" + t.totalFare);
        System.out.println("===========================");
    }
}
