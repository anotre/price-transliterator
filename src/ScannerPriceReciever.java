package src;

import java.util.Scanner;

public class ScannerPriceReciever implements PriceReciever {
    Scanner scanner;

    public ScannerPriceReciever(Scanner scanner) {
        this.scanner = scanner;
    }

    public String priceRecieve() {
        int price = this.scanner.nextInt();
        return String.valueOf(price);
    }

    public void closeScanner() {
        this.scanner.close();
    }
}
