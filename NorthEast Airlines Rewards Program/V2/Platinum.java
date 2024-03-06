class Platinum implements Tier { // Platinum tier class
    private int miles; // current miles earned
    private int cancelledFlights; // number of cancelled flights
    private int totalFlights; // total number of flights, including cancelled flights

    public int getMiles() { // returnsmiles earned so far
        return miles;
    }

    public int getCancelledFlights() { // returnscurrent number of cancelled flights
        return cancelledFlights;
    }

    public int getFlights() { // returnscurrent total number of flights, including cancelled flights
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