public class NARP { // main class (NorthEast Airlines Rewards Program)
    public static void main(String[] args) throws IOException {
        // Read input file
        Scanner input = new Scanner(new File("input.txt"));
        // Create map of passengers
        Map<Integer, Passenger> passengers = new HashMap<>();
        // Read each line of input file
        while (input.hasNextLine()) {
            // Get next line
            String line = input.nextLine();
            // Split line into array of strings
            String[] values = line.split(" ");
            // Get passenger id
            int id = Integer.parseInt(values[0]);
            // Get passenger
            Passenger passenger = passengers.get(id);
            // If passenger is null, create new passenger
            if (passenger == null) {
                passenger = new Passenger(id);
                passengers.put(id, passenger);
            }
            // Add flight to passenger
            passenger.addFlight(values[1].equals("Y"));
            // If 25 flight was cancelled, check if passenger complained
            if (passenger.getTotalCancelledFlights() == 25 && values[2].equals("Y")) {
                // Upgrade passenger to next tier
                passenger.upgradeTier();
            }
            // if flight cancellation is 50 and passengers never complained, upgrade to
            // platinum pro, otherwise platinum
            if (passenger.getTotalCancelledFlights() == 50) {
                if (values[2].equals("N")) {
                    passenger.upgradeTier();
                } else {
                    passenger.upgradeTier();
                    passenger.upgradeTier();
                }
            }
            // if flight cancellation is 100 and passengers never complained, upgrade to
            // super executive platinum, otherwise executive platinum
            if (passenger.getTotalCancelledFlights() == 100) {
                if (values[2].equals("N")) {
                    passenger.upgradeTier();
                } else {
                    passenger.upgradeTier();
                    passenger.upgradeTier();
                    passenger.upgradeTier();
                }
            }

        }
        // Close input file
        input.close();

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        // Prompt user to enter passenger id
        System.out.print("Enter a passenger id: ");
        // Read passenger id
        int id = scanner.nextInt();
        // Get passenger
        Passenger passenger = passengers.get(id);
        // If passenger null, display error message
        if (passenger == null) {
            System.out.println("Passenger not found.");
        } else {
            // Display passenger's tier
            System.out.println("Tier: " + passenger.getTier().getClass().getSimpleName());
            // Display passenger's total miles
            System.out.println("Total Miles: " + passenger.getTotalMiles());
            // Display passenger's total cancelled flights
            System.out.println("Total Cancelled Flights: " + passenger.getTotalCancelledFlights());
            // Display if passenger earnedmileage multiplier
            System.out.println("Mileage Multiplier: " + (passenger.getTotalFlights() >= 100));
        }
        scanner.close();
    }
}