// Kevin Pulikkottil
// 3/31/23
// JDK version 14.0.1
// this is a rewards program for NorthEast Airlines that rewards passengers when their flights get cancelled and is divided into multiple tiers

import java.io.*;
import java.util.*;

interface Tier { // interface for all tiers
    int getMiles(); // returns current miles earned

    int getCancelledFlights(); // returns current number of cancelled flights

    int getFlights(); // returns current total number of the passenger's flights

    void addFlight(boolean isCancelled); // adds new flight
}

class Gold implements Tier { // Gold tier class
    private int miles; // current miles earned
    private int cancelledFlights; // number of cancelled flights
    private int totalFlights; // total number of the passenger's flights

    public int getMiles() { // returns the miles earned so far
        return miles; // returns the current miles earned
    }

    public int getCancelledFlights() { // returns the current number of cancelled flights
        return cancelledFlights; // returns the current number of the passenger's flights
    }

    public int getFlights() { // returns the current total number of flights, including cancelled flights
        return totalFlights;
    }

    public void addFlight(boolean isCancelled) { // adds new flight
        if (isCancelled) { // if flight is cancelled
            miles += 1000; // add 1000 miles
            cancelledFlights++; // add 1 to number of cancelled flights
        }
        totalFlights++; // add 1 to total number of flights
    }
}

class Platinum implements Tier { // Platinum tier class
    private int miles; // current miles earned
    private int cancelledFlights; // number of cancelled flights
    private int totalFlights; // total number of flights, including cancelled flights

    public int getMiles() { // returns the miles earned so far
        return miles;
    }

    public int getCancelledFlights() { // returns the current number of cancelled flights
        return cancelledFlights;
    }

    public int getFlights() { // returns the current total number of flights, including cancelled flights
        return totalFlights;
    }

    public void addFlight(boolean isCancelled) { // adds new flight
        if (isCancelled) { // if flight is cancelled
            miles += 1000; // add 1000 miles
            cancelledFlights++; // add 1 to number of cancelled flights
        }
        totalFlights++; // add 1 to total flights

        if (cancelledFlights >= 50) {
            // Upgrade to Platinum Pro
            PlatinumPro platinumPro = new PlatinumPro();
            platinumPro.addFlight(isCancelled);
        }
    }
}

class ExecutivePlatinum implements Tier { // Executive Platinum tier class
    private int miles; // current miles earned
    private int cancelledFlights; // number of cancelled flights
    private int totalFlights; // total number of flights, including cancelled flights

    public int getMiles() { // returns the miles earned so far
        return miles;
    }

    public int getCancelledFlights() { // returns the current number of cancelled flights
        return cancelledFlights;
    }

    public int getFlights() { // returns current total number of flights, including cancelled flights
        return totalFlights;
    }

    public void addFlight(boolean isCancelled) { // adds new flight
        if (isCancelled) { // if flight is cancelled
            miles += 1000; // add 1000 miles
            cancelledFlights++; // add 1 to number of cancelled flights
        }
        totalFlights++; // add 1 to total flights

        if (cancelledFlights >= 100) { // if cancelled flights is greater than or equal to 100
            // Upgrade to Super Executive Platinum
            SuperExecutivePlatinum superExecutivePlatinum = new SuperExecutivePlatinum();
            superExecutivePlatinum.addFlight(isCancelled);
        }
    }
}

class PlatinumPro implements Tier { // Platinum Pro tier class
    private int miles; // current miles earned
    private int cancelledFlights; // number of cancelled flights
    private int totalFlights; // total flights, including cancelled flights

    public int getMiles() { // returns the miles earned so far
        return miles;
    }

    public int getCancelledFlights() { // returns the current number of cancelled flights
        return cancelledFlights;
    }

    public int getFlights() { // returns current total number of flights, including cancelled flights
        return totalFlights;
    }

    public void addFlight(boolean isCancelled) { // adds new flight
        if (isCancelled) { // if flight is cancelled
            miles += 1000; // add 1000 miles
            cancelledFlights++; // add 1 to number of cancelled flights
        }
        totalFlights++; // add 1 to total number of flights

        if (cancelledFlights >= 100) {
            // Upgrade to Super Executive Platinum
            SuperExecutivePlatinum superExecutivePlatinum = new SuperExecutivePlatinum();
            superExecutivePlatinum.addFlight(isCancelled);
        }
    }
}

class SuperExecutivePlatinum implements Tier { // Super Executive Platinum tier class
    private int miles; // current miles earned
    private int cancelledFlights; // number of cancelled flights
    private int totalFlights; // total flights, including cancelled flights

    public int getMiles() { // returns the miles earned so far
        return miles;
    }

    public int getCancelledFlights() { // returns the current number of cancelled flights
        return cancelledFlights;
    }

    public int getFlights() { // returns current total number of flights, including cancelled flights
        return totalFlights;
    }

    public void addFlight(boolean isCancelled) { // adds new flight
        if (isCancelled) { // if flight is cancelled
            miles += 2000; // add 1000 miles
            cancelledFlights++; // add 1 to number of cancelled flights
        }
        totalFlights++; // add 1 to total number of flights
    }
}

class Passenger { // passenger class
    private int id; // passenger id
    private Tier tier; // passenger's current tier
    private int totalMiles; // current total miles
    private int totalCancelledFlights; // total number of cancelled flights
    private int totalFlights; // total flights, including cancelled flights

    public Passenger(int id) { // constructor
        this.id = id; // set passenger id
        this.tier = new Gold(); // set tier to Gold
    }

    public int getId() { // returns the passenger id
        return id;
    }

    public Tier getTier() { // returns the tier the passenger is currently in
        return tier;
    }

    public int getTotalMiles() { // returns the total miles earned so far
        return totalMiles;
    }

    public int getTotalCancelledFlights() { // returns total number of cancelled flights
        return totalCancelledFlights;
    }

    public int getTotalFlights() { // returns total flights, including cancelled flights
        return totalFlights;
    }

    public void addFlight(boolean isCancelled) { // adds new flight
        tier.addFlight(isCancelled); // add flight to current tier
        totalFlights++; // add 1 to total number of flights
        if (isCancelled) { // if flight is cancelled
            totalCancelledFlights++; // add 1 to total number of cancelled flights
        }
    }

    public void upgradeTier() { // upgrades passenger to next tier
        if (tier instanceof Gold) { // if passenger is in Gold tier
            tier = new Platinum(); // upgrade to Platinum
        } else if (tier instanceof Platinum) { // if passenger is in Platinum tier
            tier = new PlatinumPro(); // upgrade to Platinum Pro
        } else if (tier instanceof PlatinumPro) { // if passenger is in Platinum Pro tier
            tier = new SuperExecutivePlatinum(); // upgrade to Super Executive Platinum
        }
    }
}

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
            // Display if passenger earned the mileage multiplier
            System.out.println("Mileage Multiplier: " + (passenger.getTotalFlights() >= 100));
        }
        scanner.close();
    }
}