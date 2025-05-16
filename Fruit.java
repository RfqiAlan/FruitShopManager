/**
 * Kelas Fruit merepresentasikan sebuah buah di toko.
 * Setiap buah memiliki ID unik, nama, harga, jumlah stok, dan kategori.
 */
public class Fruit {
    // Atribut privat untuk menyimpan data buah
    private int id;              // ID unik buah
    private String name;         // Nama buah
    private double price;        // Harga buah
    private int stock;           // Jumlah stok buah
    private String category;     // Kategori buah (misal: lokal, impor, musiman, dll)

    /**
     * Konstruktor untuk menginisialisasi data buah.
     *
     * @param id ID unik buah
     * @param name nama buah
     * @param price harga buah
     * @param stock stok buah
     * @param category kategori buah
     */
    public Fruit(int id, String name, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // ========================== GETTER ==========================

    public int getId() { return id; }                 // Mengambil ID buah
    public String getName() { return name; }          // Mengambil nama buah
    public double getPrice() { return price; }        // Mengambil harga buah
    public int getStock() { return stock; }           // Mengambil jumlah stok buah
    public String getCategory() { return category; }  // Mengambil kategori buah

    // ========================== SETTER ==========================

    public void setId(int id) { this.id = id; }                           // Mengatur ID buah
    public void setName(String name) { this.name = name; }               // Mengatur nama buah
    public void setPrice(double price) { this.price = price; }           // Mengatur harga buah
    public void setStock(int stock) { this.stock = stock; }              // Mengatur jumlah stok buah
    public void setCategory(String category) { this.category = category; } // Mengatur kategori buah

    /**
     * Override dari method toString untuk menampilkan representasi string dari objek buah.
     * Digunakan agar ketika objek ditampilkan (misalnya di ComboBox), informasi penting terlihat.
     */
    @Override
    public String toString() {
        return "[" + id + "] " + name + " - Rp" + price + " | Stok: " + stock;
    }
}
