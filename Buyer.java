import java.util.ArrayList;

/**
 * Kelas Buyer merepresentasikan pengguna dengan peran pembeli dalam sistem.
 * Pembeli memiliki keranjang belanja (cart) dan dapat melihat serta membeli buah dari toko.
 */
public class Buyer extends User {
    // List untuk menyimpan buah yang ditambahkan ke keranjang
    private ArrayList<Fruit> cart = new ArrayList<>();

    /**
     * Konstruktor Buyer memanggil konstruktor superclass (User).
     *
     * @param username nama pengguna
     * @param password kata sandi
     */
    public Buyer(String username, String password) {
        super(username, password);
    }

    /**
     * Mengembalikan daftar buah yang ada di keranjang pembeli.
     *
     * @return list buah di keranjang
     */
    public ArrayList<Fruit> getCart() {
        return cart;
    }

    /**
     * Menampilkan menu interaktif untuk pembeli dan menangani input pengguna.
     * Pembeli dapat melihat buah, menambahkan ke keranjang, melihat isi keranjang, dan melakukan pembelian.
     *
     * @param shop objek Shop yang menyediakan data buah dan transaksi
     * @param sc   scanner untuk membaca input pengguna
     */
    public void showMenu(Shop shop, java.util.Scanner sc) {
        int choice;
        do {
            // Menampilkan menu untuk pembeli
            System.out.println("\n[ Buyer Menu ]");
            System.out.println("1. Lihat Buah");              // Menampilkan semua buah yang tersedia
            System.out.println("2. Tambah ke Keranjang");     // Menambahkan buah ke keranjang
            System.out.println("3. Lihat Keranjang");         // Menampilkan isi keranjang
            System.out.println("4. Beli");                    // Melakukan proses checkout/pembayaran
            System.out.println("0. Logout");                  // Keluar dari menu buyer
            System.out.print("Pilih: ");

            // Membaca pilihan user
            choice = sc.nextInt(); sc.nextLine();

            // Menjalankan aksi berdasarkan pilihan
            switch (choice) {
                case 1:
                    shop.viewFruits();                // Menampilkan daftar buah
                    break;
                case 2:
                    shop.addToCart(sc, cart);         // Menambahkan buah ke keranjang
                    break;
                case 3:
                    shop.viewCart(cart);              // Menampilkan isi keranjang
                    break;
                case 4:
                    shop.checkout(this.getUsername(), cart);  // Checkout: beli semua isi keranjang
                    cart.clear();                    // Mengosongkan keranjang setelah checkout
                    break;
            }
        } while (choice != 0);  // Ulangi sampai user memilih logout
    }
}
