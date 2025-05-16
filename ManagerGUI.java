import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Kelas ManagerGUI merepresentasikan antarmuka pengguna grafis
 * untuk mengelola toko buah (menambah, menghapus, memperbarui, dan melihat transaksi).
 */
public class ManagerGUI extends JFrame {
    private final Shop shop; // Referensi ke objek Shop untuk operasi data

    public ManagerGUI(Shop shop) {
        this.shop = shop;
        initUI(); // Inisialisasi antarmuka pengguna
    }

    /* =============================== UI =============================== */

    /** 
     * Inisialisasi komponen antarmuka pengguna
     */
    private void initUI() {
        // Pengaturan dasar JFrame
        setTitle("Panel Manager Toko Buah");
        setSize(540, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        /* ---------- Header ---------- */
        // Label judul di bagian atas
        JLabel titleLabel = new JLabel("\uD83C\uDF4E  Panel Manager Toko Buah  \uD83C\uDF4C", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBorder(new EmptyBorder(14, 10, 14, 10));
        add(titleLabel, BorderLayout.NORTH);

        /* ---------- Tombol Utama ---------- */
        // Panel menu tombol aksi utama
        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 12, 12));
        btnPanel.setBorder(BorderFactory.createTitledBorder("Menu"));

        // Membuat tombol-tombol menu
        JButton btnAdd    = createMenuButton("➕  Tambah Buah");
        JButton btnRemove = createMenuButton("🗑️  Hapus Buah");
        JButton btnUpdate = createMenuButton("✏️  Update Buah");
        JButton btnViewTx = createMenuButton("📜  Lihat History Pembeli");

        // Menambahkan tombol ke panel
        btnPanel.add(btnAdd);
        btnPanel.add(btnRemove);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnViewTx);

        add(btnPanel, BorderLayout.CENTER);

        /* ---------- Footer / Exit ---------- */
        // Panel footer di bagian bawah
        JPanel footerPanel = new JPanel(new BorderLayout());
        JLabel footerLbl = new JLabel("Kelola stok dan lihat transaksi pembeli", SwingConstants.CENTER);
        footerLbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
        footerLbl.setBorder(new EmptyBorder(6, 4, 6, 4));
        footerPanel.add(footerLbl, BorderLayout.CENTER);

        // Tombol keluar
        JButton btnExit = new JButton("🚪  Keluar");
        btnExit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnExit.setBackground(Color.LIGHT_GRAY);
        btnExit.addActionListener(e -> dispose()); // Menutup jendela saat tombol ditekan
        JPanel exitWrap = new JPanel();
        exitWrap.add(btnExit);
        footerPanel.add(exitWrap, BorderLayout.SOUTH);

        add(footerPanel, BorderLayout.SOUTH);

        /* ---------- Action Listeners ---------- */
        // Menetapkan aksi pada tiap tombol
        btnAdd.addActionListener(e -> addFruitDialog());
        btnRemove.addActionListener(e -> removeFruitDialog());
        btnUpdate.addActionListener(e -> updateFruitDialog());
        btnViewTx.addActionListener(e -> viewTransactions());

        setVisible(true); // Menampilkan GUI
    }

    /**
     * Membuat tombol menu dengan teks dan gaya tertentu
     * @param text Teks yang ditampilkan pada tombol
     * @return JButton yang telah dikustomisasi
     */
    private JButton createMenuButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return b;
    }

    /* ============================== LOGIC ============================== */

    /** 
     * Menampilkan dialog untuk menambahkan buah baru ke toko 
     */
    private void addFruitDialog() {
        // Field input untuk data buah baru
        JTextField idField       = new JTextField();
        JTextField nameField     = new JTextField();
        JTextField priceField    = new JTextField();
        JTextField stockField    = new JTextField();
        JTextField categoryField = new JTextField();

        // Menyusun field ke dalam array untuk ditampilkan di JOptionPane
        Object[] fields = {
                "ID:", idField,
                "Nama:", nameField,
                "Harga:", priceField,
                "Stok:", stockField,
                "Kategori:", categoryField
        };

        // Menampilkan dialog input
        int opt = JOptionPane.showConfirmDialog(this, fields, "Tambah Buah", JOptionPane.OK_CANCEL_OPTION);
        if (opt == JOptionPane.OK_OPTION) {
            try {
                // Mengambil dan memvalidasi input
                int id          = Integer.parseInt(idField.getText());
                String name     = nameField.getText();
                double price    = Double.parseDouble(priceField.getText());
                int stock       = Integer.parseInt(stockField.getText());
                String category = categoryField.getText();

                // Cek apakah ID sudah digunakan
                if (shop.findFruitById(id) != null) {
                    JOptionPane.showMessageDialog(this, "❌ ID sudah digunakan.");
                    return;
                }

                // Menambahkan buah baru
                shop.addFruit(new Fruit(id, name, price, stock, category));
                JOptionPane.showMessageDialog(this, "✅ Buah berhasil ditambahkan.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Input tidak valid!");
            }
        }
    }

    /** 
     * Menampilkan dialog untuk menghapus buah berdasarkan ID 
     */
    private void removeFruitDialog() {
        String idStr = JOptionPane.showInputDialog(this, "ID buah yang ingin dihapus:");
        if (idStr == null) return;
        try {
            int id = Integer.parseInt(idStr);
            Fruit toRemove = shop.findFruitById(id);
            if (toRemove == null) {
                JOptionPane.showMessageDialog(this, "❌ Buah tidak ditemukan.");
                return;
            }
            shop.getAllFruits().remove(toRemove); // Menghapus buah dari daftar
            JOptionPane.showMessageDialog(this, "✅ Buah dihapus.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "❌ ID tidak valid.");
        }
    }

    /** 
     * Menampilkan dialog untuk memperbarui informasi buah 
     */
    private void updateFruitDialog() {
        String idStr = JOptionPane.showInputDialog(this, "ID buah yang ingin diupdate:");
        if (idStr == null) return;
        try {
            int id = Integer.parseInt(idStr);
            Fruit f = shop.findFruitById(id);
            if (f == null) {
                JOptionPane.showMessageDialog(this, "❌ Buah tidak ditemukan.");
                return;
            }

            // Field input dengan nilai awal dari buah yang dipilih
            JTextField nameField     = new JTextField(f.getName());
            JTextField priceField    = new JTextField(String.valueOf(f.getPrice()));
            JTextField stockField    = new JTextField(String.valueOf(f.getStock()));
            JTextField categoryField = new JTextField(f.getCategory());

            Object[] fields = {
                    "Nama baru:", nameField,
                    "Harga baru:", priceField,
                    "Stok baru:", stockField,
                    "Kategori baru:", categoryField
            };

            // Menampilkan dialog update
            int opt = JOptionPane.showConfirmDialog(this, fields, "Update Buah", JOptionPane.OK_CANCEL_OPTION);
            if (opt == JOptionPane.OK_OPTION) {
                try {
                    // Mengupdate nilai properti buah
                    f.setName(nameField.getText());
                    f.setPrice(Double.parseDouble(priceField.getText()));
                    f.setStock(Integer.parseInt(stockField.getText()));
                    f.setCategory(categoryField.getText());
                    JOptionPane.showMessageDialog(this, "✅ Buah berhasil diperbarui.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "❌ Input tidak valid!");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "❌ ID tidak valid.");
        }
    }

    /** 
     * Menampilkan riwayat transaksi pembeli 
     */
    private void viewTransactions() {
        ArrayList<Transaction> txs = shop.getTransactions();
        if (txs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada transaksi.");
            return;
        }

        // Menyusun string berisi semua transaksi
        StringBuilder sb = new StringBuilder("[ Riwayat Transaksi ]\n\n");
        for (Transaction t : txs) {
            sb.append("👤 ").append(t.getBuyerName())
              .append(" | Total: Rp").append(t.getTotal())
              .append('\n');
        }

        // Menampilkan transaksi dalam JOptionPane
        JOptionPane.showMessageDialog(this, sb.toString(), "History Transaksi", JOptionPane.INFORMATION_MESSAGE);
    }
}
