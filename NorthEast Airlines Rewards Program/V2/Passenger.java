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

    public int getId() { // returnspassenger id
        return id;
    }

    public Tier getTier() { // returnstierpassenger is currently in
        return tier;
    }

    public int getTotalMiles() { // returnstotal miles earned so far
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