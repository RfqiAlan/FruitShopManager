import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    private ArrayList<Fruit> fruits = new ArrayList<>();       // Daftar buah di toko
    private ArrayList<Transaction> transactions = new ArrayList<>(); // Daftar transaksi pembelian
    private int nextId = 1;  // ID otomatis untuk buah baru

    /**
     * Menambahkan buah baru ke daftar dengan ID unik otomatis.
     * @param fruit objek Fruit yang akan ditambahkan
     */
    public void addFruit(Fruit fruit) {
        fruit.setId(nextId++);
        fruits.add(fruit);
    }

    /**
     * Mencari buah berdasarkan ID.
     * @param id ID buah yang dicari
     * @return objek Fruit jika ditemukan, null jika tidak
     */
    public Fruit findFruitById(int id) {
        for (Fruit f : fruits) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    /**
     * Mengembalikan daftar semua buah.
     * @return ArrayList buah
     */
    public ArrayList<Fruit> getAllFruits() {
        return fruits;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    /**
     * Mengembalikan daftar semua transaksi.
     * @return ArrayList transaksi
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Mencari buah berdasarkan nama (case insensitive).
     * @param name nama buah yang dicari
     * @return objek Fruit jika ditemukan, null jika tidak
     */
    public Fruit getFruitByName(String name) {
        for (Fruit f : fruits) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Mencari buah berdasarkan ID.
     * @param id ID buah yang dicari
     * @return objek Fruit jika ditemukan, null jika tidak
     */
    public Fruit getFruitById(int id) {
        for (Fruit f : fruits) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    /**
     * Menampilkan daftar buah yang tersedia.
     */
    public void viewFruits() {
        System.out.println("\n[ Daftar Buah ]");
        if (fruits.isEmpty()) System.out.println("Belum ada buah.");
        else for (Fruit f : fruits)
            System.out.println(f);
    }

    /**
     * Menu untuk menambahkan buah melalui input scanner.
     * @param sc Scanner untuk input pengguna
     */
    public void addFruitMenu(Scanner sc) {
        System.out.print("Nama buah: ");
        String name = sc.nextLine();
        System.out.print("Harga: ");
        double price = sc.nextDouble();
        System.out.print("Stok: ");
        int stock = sc.nextInt(); sc.nextLine();
        System.out.print("Kategori (Lokal/Import): ");
        String cat = sc.nextLine();
        addFruit(new Fruit(0, name, price, stock, cat));
        System.out.println("Buah berhasil ditambahkan!");
    }

    /**
     * Menu untuk menghapus buah berdasarkan ID melalui input scanner.
     * @param sc Scanner untuk input pengguna
     */
    public void removeFruitMenu(Scanner sc) {
        viewFruits();
        System.out.print("ID buah yang dihapus: ");
        int id = sc.nextInt(); sc.nextLine();
        fruits.removeIf(f -> f.getId() == id);
        System.out.println("Buah dihapus jika ditemukan.");
    }

    /**
     * Menu untuk memperbarui data buah berdasarkan ID.
     * @param sc Scanner untuk input pengguna
     */
    public void updateFruitMenu(Scanner sc) {
        viewFruits();
        System.out.print("ID buah yang diupdate: ");
        int id = sc.nextInt(); sc.nextLine();
        Fruit f = getFruitById(id);
        if (f != null) {
            System.out.print("Harga baru: ");
            f.setPrice(sc.nextDouble());
            System.out.print("Stok baru: ");
            f.setStock(sc.nextInt()); sc.nextLine();
            System.out.print("Kategori baru: ");
            f.setCategory(sc.nextLine());
            System.out.println("Data buah diupdate!");
        } else {
            System.out.println("Buah tidak ditemukan.");
        }
    }

    /**
     * Menambah buah ke keranjang pembeli berdasarkan ID dan jumlah yang dibeli.
     * Mengurangi stok buah jika berhasil.
     * @param sc Scanner untuk input pengguna
     * @param cart daftar keranjang pembeli
     */
    public void addToCart(Scanner sc, ArrayList<Fruit> cart) {
        viewFruits();
        System.out.print("ID buah yang ditambah: ");
        int id = sc.nextInt(); sc.nextLine();
        Fruit f = getFruitById(id);
        if (f != null) {
            System.out.print("Jumlah yang dibeli: ");
            int qty = sc.nextInt(); sc.nextLine();
            if (qty <= 0) {
                System.out.println("Jumlah tidak valid.");
                return;
            }
            if (f.getStock() >= qty) {
                // Menambahkan buah baru ke keranjang dengan harga sesuai jumlah
                cart.add(new Fruit(f.getId(), f.getName(), f.getPrice() * qty, qty, f.getCategory()));
                f.setStock(f.getStock() - qty);
                System.out.println("Berhasil ditambah ke keranjang!");
            } else {
                System.out.println("Stok tidak cukup.");
            }
        } else {
            System.out.println("Buah tidak ditemukan.");
        }
    }

    /**
     * Menampilkan isi keranjang pembeli.
     * @param cart daftar keranjang pembeli
     */
    public void viewCart(ArrayList<Fruit> cart) {
        System.out.println("\n[ Keranjang Anda ]");
        if (cart.isEmpty()) System.out.println("Keranjang kosong.");
        else for (Fruit f : cart)
            System.out.println(f);
    }

    /**
     * Melakukan checkout pembelian, menyimpan transaksi dan menampilkan total.
     * @param buyerName nama pembeli
     * @param cart daftar buah dalam keranjang
     */
    public void checkout(String buyerName, ArrayList<Fruit> cart) {
        double total = 0;
        for (Fruit f : cart) total += f.getPrice();
        transactions.add(new Transaction(buyerName, new ArrayList<>(cart), total));
        System.out.println("Total belanja: Rp" + total);
        System.out.println("Pembelian berhasil!");
    }

    /**
     * Menampilkan daftar transaksi pembelian (history pembeli).
     */
    public void viewTransactions() {
        System.out.println("\n[ History Pembeli ]");
        for (Transaction t : transactions)
            System.out.println(t.getBuyerName() + " beli Rp" + t.getTotal());
    }
}
