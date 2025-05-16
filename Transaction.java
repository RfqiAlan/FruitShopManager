import java.util.ArrayList;

/**
 * Kelas Transaction merepresentasikan transaksi pembelian oleh seorang pembeli.
 */
public class Transaction {
    private String buyerName;           // Nama pembeli
    private ArrayList<Fruit> items;     // Daftar buah yang dibeli
    private double total;               // Total harga transaksi

    /**
     * Konstruktor untuk membuat objek transaksi baru.
     *
     * @param buyerName nama pembeli
     * @param items daftar buah yang dibeli
     * @param total total harga dari semua buah
     */
    public Transaction(String buyerName, ArrayList<Fruit> items, double total) {
        this.buyerName = buyerName;
        this.items = items;
        this.total = total;
    }

    /**
     * Mengambil nama pembeli.
     *
     * @return nama pembeli
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * Mengambil daftar buah yang dibeli.
     *
     * @return list buah yang termasuk dalam transaksi
     */
    public ArrayList<Fruit> getItems() {
        return items;
    }

    /**
     * Mengambil total harga transaksi.
     *
     * @return total harga
     */
    public double getTotal() {
        return total;
    }
}
