import java.util.*;

class Train {
    int trainNo;
    String name, from, to, date;
    int slSeats, acSeats, genSeats;

    final int SL_FARE = 300;
    final int AC_FARE = 900;
    final int GEN_FARE = 150;

    Train(int trainNo, String name, String from, String to, String date,
          int slSeats, int acSeats, int genSeats) {

        this.trainNo = trainNo;
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
        this.slSeats = slSeats;
        this.acSeats = acSeats;
        this.genSeats = genSeats;
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);

    // Stores train seats per Train + Date
    static Map<String, Train> trainDB = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("===== TRAIN TICKET BOOKING SYSTEM =====");

        while (true) {

            // ===== FROM & TO =====
            System.out.print("\nEnter From Station: ");
            String from = sc.nextLine().trim().toLowerCase();

            System.out.print("Enter To Station: ");
            String to = sc.nextLine().trim().toLowerCase();

            // ===== DATE SELECTION =====
            System.out.println("\nSelect Journey Date:");
            System.out.println("1. Today");
            System.out.println("2. Tomorrow");
            System.out.println("3. Choose Date");
            System.out.print("Enter choice: ");
            int dateChoice = Integer.parseInt(sc.nextLine());

            String date;
            if (dateChoice == 1) {
                date = java.time.LocalDate.now().toString();
            } else if (dateChoice == 2) {
                date = java.time.LocalDate.now().plusDays(1).toString();
            } else if (dateChoice == 3) {
                System.out.print("Enter date (YYYY-MM-DD): ");
                date = sc.nextLine();
            } else {
                System.out.println("Invalid date choice!");
                continue;
            }

            // ===== LOAD TRAINS ONLY ONCE PER DATE =====
            loadTrain(101, "Chennai Express", "chennai", "bangalore", date, 20, 10, 30);
            loadTrain(102, "Intercity SF", "chennai", "bangalore", date, 18, 8, 25);
            loadTrain(103, "Vaigai Express", "madurai", "chennai", date, 22, 12, 35);
            loadTrain(104, "Rockfort Exp", "chennai", "trichy", date, 25, 15, 40);
            loadTrain(105, "Rameshwaram Exp", "rameshwaram", "chennai", date, 20, 10, 30);

            // ===== SEARCH TRAINS =====
            List<Train> available = new ArrayList<>();
            System.out.println("\nAvailable Trains:");
            for (Train t : trainDB.values()) {
                if (t.from.equals(from) && t.to.equals(to) && t.date.equals(date)) {
                    available.add(t);
                    System.out.println(t.trainNo + " - " + t.name);
                }
            }

            if (available.isEmpty()) {
                System.out.println("No trains available!");
                continue;
            }

            // ===== SELECT TRAIN =====
            System.out.print("\nEnter Train Number: ");
            int trainNo = Integer.parseInt(sc.nextLine());
            Train train = trainDB.get(trainNo + "|" + date);

            if (train == null) {
                System.out.println("Invalid train!");
                continue;
            }

            // ===== CLASS =====
            System.out.println("\nChoose Class:");
            System.out.println("1. Sleeper (SL)");
            System.out.println("2. AC");
            System.out.println("3. General");
            System.out.print("Enter choice: ");
            int classChoice = Integer.parseInt(sc.nextLine());

            String travelClass;
            int availableSeats;
            int fare;

            if (classChoice == 1) {
                travelClass = "SL";
                availableSeats = train.slSeats;
                fare = train.SL_FARE;
            } else if (classChoice == 2) {
                travelClass = "AC";
                availableSeats = train.acSeats;
                fare = train.AC_FARE;
            } else if (classChoice == 3) {
                travelClass = "GENERAL";
                availableSeats = train.genSeats;
                fare = train.GEN_FARE;
            } else {
                System.out.println("Invalid class!");
                continue;
            }

            System.out.println("Available Seats: " + availableSeats);

            // ===== SEAT COUNT =====
            System.out.print("Enter number of seats: ");
            int seatCount = Integer.parseInt(sc.nextLine());

            if (seatCount > availableSeats) {
                System.out.println("Not enough seats!");
                continue;
            }

            // ===== PASSENGER DETAILS =====
            List<String> passengers = new ArrayList<>();
            for (int i = 1; i <= seatCount; i++) {
                System.out.print("Enter Passenger " + i + " Name: ");
                passengers.add(sc.nextLine());
            }

            int totalFare = seatCount * fare;

            // ===== PAYMENT =====
            System.out.println("\nTotal Fare: ₹" + totalFare);
            System.out.print("Confirm Payment (yes/no): ");
            String confirm = sc.nextLine();

            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Payment cancelled!");
                continue;
            }

            // ===== UPDATE SEATS =====
            if (travelClass.equals("SL")) train.slSeats -= seatCount;
            else if (travelClass.equals("AC")) train.acSeats -= seatCount;
            else train.genSeats -= seatCount;

            // ===== TICKET CONFIRMATION =====
            String pnr = "PNR" + (10000 + new Random().nextInt(90000));

            System.out.println("\n===== TICKET CONFIRMED =====");
            System.out.println("PNR       : " + pnr);
            System.out.println("Train     : " + train.trainNo + " - " + train.name);
            System.out.println("Route     : " + train.from + " → " + train.to);
            System.out.println("Date      : " + train.date);
            System.out.println("Class     : " + travelClass);
            System.out.println("Seats     : " + seatCount);
            System.out.println("Passengers: " + passengers);
            System.out.println("Total ₹   : " + totalFare);
            System.out.println("===========================");

            // ===== REPEAT =====
            System.out.print("\nBook another ticket? (yes/no): ");
            if (!sc.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Thank you! Have a nice Jurney...");
                break;
            }
        }
    }

    // Prevents seat reset
    static void loadTrain(int no, String name, String from, String to,
                          String date, int sl, int ac, int gen) {

        String key = no + "|" + date;
        if (!trainDB.containsKey(key)) {
            trainDB.put(key, new Train(no, name, from, to, date, sl, ac, gen));
        }
    }
}
