import java.util.Scanner;

/**
 * Kelas MainApp berfungsi sebagai titik masuk utama (main method) 
 * untuk menjalankan aplikasi toko buah berbasis teks.
 * Pengguna dapat login sebagai Manager atau Buyer.
 */
public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Scanner untuk input dari pengguna
        Shop shop = new Shop();               // Objek Shop sebagai pusat data dan operasi toko

        // Loop utama aplikasi
        while (true) {
            // Menampilkan menu login
            System.out.println("\n[ Menu Login ]");
            System.out.println("1. Login sebagai Manager");
            System.out.println("2. Login sebagai Buyer");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            int opsi = sc.nextInt(); 
            sc.nextLine(); // Membersihkan newline agar tidak tertinggal di buffer

            if (opsi == 1) {
                // Login sebagai Manager
                System.out.print("Username: ");
                String user = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                // Verifikasi login hardcoded
                if (user.equals("manager") && pass.equals("admin123")) {
                    User manager = new Manager(user, pass);
                    manager.showMenu(shop, sc); // Tampilkan menu khusus manager
                } else {
                    System.out.println("Login gagal!");
                }
            } else if (opsi == 2) {
                // Login sebagai Buyer
                System.out.print("Masukkan nama anda: ");
                String name = sc.nextLine();

                User buyer = new Buyer(name, "buyer123"); // Password diset default
                buyer.showMenu(shop, sc); // Tampilkan menu khusus buyer
            } else if (opsi == 0) {
                // Keluar dari program
                System.out.println("Terima kasih!");
                break;
            } else {
                // Menangani input menu yang tidak valid
                System.out.println("Opsi tidak valid.");
            }
        }
    }
}
