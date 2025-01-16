public class Booking {

    int bookingId, customerId, pickuptime, droptime, amount;

    char from, to;

    public Booking(int bookingId, int customerId, int pickuptime, int droptime, int amount, char to, char from) {
        this.amount = amount;
        this.bookingId = bookingId;
        this.to = to;
        this.customerId = customerId;
        this.droptime = droptime;
        this.from = from;
        this.pickuptime = pickuptime;
    }
}
