/**
 * Kelas Manager mewarisi dari kelas User dan merepresentasikan 
 * pengguna dengan hak akses sebagai manajer toko.
 * Manajer memiliki kemampuan untuk mengelola data buah dan melihat transaksi.
 */
public class Manager extends User {

    /**
     * Konstruktor untuk membuat objek Manager dengan username dan password.
     * 
     * @param username nama pengguna
     * @param password kata sandi
     */
    public Manager(String username, String password) {
        super(username, password);
    }

    /**
     * Menampilkan menu manajer di terminal dan menangani input pengguna.
     * Menu mencakup fitur untuk menambah, menghapus, memperbarui buah, dan melihat riwayat pembeli.
     * 
     * @param shop objek Shop yang berisi data buah dan transaksi
     * @param sc scanner untuk membaca input dari pengguna
     */
    @Override
    public void showMenu(Shop shop, java.util.Scanner sc) {
        int choice;
        do {
            // Menampilkan pilihan menu
            System.out.println("\n[ Manager Menu ]");
            System.out.println("1. Tambah Buah");
            System.out.println("2. Hapus Buah");
            System.out.println("3. Update Buah");
            System.out.println("4. Lihat History Pembeli");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");

            // Membaca input pilihan dari pengguna
            choice = sc.nextInt(); 
            sc.nextLine(); // Membersihkan newline

            // Menjalankan aksi sesuai pilihan
            switch (choice) {
                case 1:
                    shop.addFruitMenu(sc); // Menambahkan buah baru
                    break;
                case 2:
                    shop.removeFruitMenu(sc); // Menghapus buah
                    break;
                case 3:
                    shop.updateFruitMenu(sc); // Memperbarui buah
                    break;
                case 4:
                    shop.viewTransactions(); // Menampilkan riwayat transaksi
                    break;
            }

        } while (choice != 0); // Ulangi hingga user memilih logout
    }
}
