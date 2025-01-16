
import java.util.*;

public class TaxiBookingsystem {

    static List<Taxi> taxis = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static int customercounter = 1;

    public static void main(String[] args) {
        System.out.print("Enter Number of Taxis");
        int numtaxi = sc.nextInt();
        initializetTaxis(numtaxi);

        while (true) {

            System.out.print("\n1. Book taxi\n2. Display Taxi Details\n3. Exit");
            System.out.print("Enter Your Choice");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bookTaxi();
                    break;

                case 2:
                    displayTaxiDetails();
                    break;

                case 3:
                    System.out.println("exiting...");
                    return;

                default:
                    System.out.print("Invalid choice try again");
            }

        }

    }

    public static void initializetTaxis(int n) {
        for (int i = 0; i < n; i++) {
            taxis.add(new Taxi(i));
        }
    }

    public static void bookTaxi()
    {
            int customerId = customercounter++;
            System.out.print("Enter Pickup point A - F:");
            char pickup = sc.next().toUpperCase().charAt(0);
            System.out.print("Enter Drop Point A - F:");
            char drop = sc.next().toUpperCase().charAt(0);
            System.out.print("Enter Pickup Time (in hours): ");
            int pickuptime = sc.nextInt();

            Taxi selectedTaxi = null;
            int mindistance = Integer.MAX_VALUE;

            for (Taxi taxi : taxis) {
                if (taxi.isavailable(pickuptime)) {
                    int distance  = Math.abs(taxi.currentPoint - pickup);                
                }

                if (distance < mindistance || distance == mindistance &&  taxi.totalearning < selectedTaxi){
                    selectedTaxi = taxi;
                    mindistance = distance;
                }


            }

            if(selectedTaxi == null){
                System.out.print("Booking Rejected. No Taxis Available");
                return;
            }

            int droptime = pickuptime + Math.abs(drop - pickup);
            int amount = selectedTaxi.calculateEarnings(pickup, drop);
            int bookingid = selectedTaxi.bookings.size() + 1;

            Booking booking = new Booking(bookingid, customerId, pickuptime, droptime, amount, pickup, drop);
            selectedTaxi.addbooking(booking);
            System.out.println("Taxi - "+selectedTaxi.id+" is allocated");
    } 

    public static void displayTaxiDetails(){
        for (Taxi taxi : taxis) {
            System.out.println("Taxi- "+taxi.id+"Total Earnings: Rs."+taxi.totalearning);
            System.out.printf("%10s %10s %-5s %-5s %-12s %-9s %-6s%n","Booking ID","Customer ID","From","To","PickupTime","Droptime","Amount");

            for (Booking booking : taxi.bookings) {
                System.out.printf("%10s %10s %-5s %-5s %-12s %-9s %-6s%n",
                booking.amount,
                booking.bookingId,
                booking.customerId,booking.droptime,booking.from,booking.pickuptime,booking.to);
            }
        }
    }
}
