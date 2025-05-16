import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * GUI utama aplikasi toko buah.
 * Menyediakan:
 * – Tampilan utama berisi 5 tombol
 * – Dialog tambah ke keranjang yang sudah menampilkan daftar buah
 */
public class FruitShopGUI {

    private JFrame frame;
    private Shop shop;
    private Buyer buyer;
    private Transaction transaction;

    public FruitShopGUI() {
        shop = new Shop();
        buyer = new Buyer("guest", "guest123");

        initialize();
    }

    /* ====================== INISIALISASI FRAME UTAMA ==================== */
    private void initialize() {
        frame = new JFrame("Fruit Shop Application");
        frame.setSize(700, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(240, 248, 255));

        // Tombol-tombol utama
        JButton btnAddToCart = createStyledButton("Tambah ke Keranjang");
        JButton btnViewCart = createStyledButton("Lihat Keranjang");
        JButton btnCheckout = createStyledButton("Checkout");
        JButton btnManagerLogin = createStyledButton("Login Manager");
        JButton btnExit = createStyledButton("Keluar");

        btnAddToCart.addActionListener(e -> openAddToCartDialog());
        btnViewCart.addActionListener(e -> viewCart());
        btnCheckout.addActionListener(e -> checkout());
        btnManagerLogin.addActionListener(e -> managerLogin());
        btnExit.addActionListener(e -> System.exit(0));

        // Susun di grid: 5 tombol (1 slot kosong biar rapih)
        mainPanel.add(btnAddToCart);
        mainPanel.add(btnViewCart);
        mainPanel.add(btnCheckout);
        mainPanel.add(btnManagerLogin);
        mainPanel.add(btnExit);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    /* ========================== UTILITAS BUTTON ========================= */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        return button;
    }

    /* ================== DIALOG: TAMBAH BUAH KE KERANJANG ================= */
    private void openAddToCartDialog() {
        ArrayList<Fruit> fruits = shop.getAllFruits();
        if (fruits.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tidak ada buah tersedia.");
            return;
        }

        // ── Dialog modal ────────────────────────────────────────────────────
        JDialog dialog = new JDialog(frame, "Tambah Buah ke Keranjang", true);
        dialog.setSize(420, 220);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(frame);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.anchor = GridBagConstraints.WEST;

        // Label + Combo
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("Pilih buah:"), gbc);

        JComboBox<Fruit> comboFruit = new JComboBox<>();
        fruits.forEach(comboFruit::addItem);
        comboFruit.setPreferredSize(new Dimension(240, 26));
        gbc.gridx = 1;
        dialog.add(comboFruit, gbc);

        // Label + Spinner qty
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("Jumlah:"), gbc);

        JSpinner spnQty = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        ((JSpinner.DefaultEditor) spnQty.getEditor()).getTextField()
                .setHorizontalAlignment(JTextField.CENTER);
        spnQty.setPreferredSize(new Dimension(80, 26));
        gbc.gridx = 1;
        dialog.add(spnQty, gbc);

        // Tombol Tambah
        JButton btnAdd = new JButton("➕ Tambah");
        btnAdd.setBackground(new Color(60, 179, 113));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(btnAdd, gbc);

        /* ---------- AKSI: klik “Tambah” ---------- */
        btnAdd.addActionListener(ev -> {
            Fruit selected = (Fruit) comboFruit.getSelectedItem();
            int qty = (Integer) spnQty.getValue();

            if (selected == null)
                return;

            if (qty > selected.getStock()) {
                JOptionPane.showMessageDialog(dialog,
                        "Stok tidak mencukupi (tersisa " + selected.getStock() + ").");
                return;
            }

            // Masukkan ke keranjang (salin objek dengan harga total)
            buyer.getCart().add(new Fruit(
                    selected.getId(),
                    selected.getName(),
                    selected.getPrice() * qty, // harga total
                    qty,
                    selected.getCategory()));
            // Kurangi stok asli
            selected.setStock(selected.getStock() - qty);

            JOptionPane.showMessageDialog(dialog,
                    "✔ " + qty + " × " + selected.getName() + " ditambahkan ke keranjang.");
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    /* ============================= KERANJANG ============================ */
    private void viewCart() {
        ArrayList<Fruit> cart = buyer.getCart();
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Keranjang kosong.");
            return;
        }
        StringBuilder sb = new StringBuilder("[ 🛒 Keranjang Anda ]\n\n");
        for (Fruit f : cart) {
            sb.append(f.getName())
                    .append(" | Qty: ").append(f.getStock())
                    .append(" | Subtotal: Rp").append(f.getPrice())
                    .append('\n');
        }
        JOptionPane.showMessageDialog(frame, sb.toString());
    }

    private void checkout() {
        ArrayList<Fruit> cart = buyer.getCart();
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Keranjang kosong.");
            return;
        }
        shop.checkout(buyer.getUsername(), cart);
        cart.clear();
        JOptionPane.showMessageDialog(frame, "💳 Pembelian berhasil!");

    }

    /* =========================== LOGIN MANAGER ========================== */
    private void managerLogin() {
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        Object[] message = {
                "Username:", userField,
                "Password:", passField
        };

        int option = JOptionPane.showConfirmDialog(frame, message,
                "Login Manajer", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if ("manager".equals(username) && "admin123".equals(password)) {
                new ManagerGUI(shop).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Login gagal.");
            }
        }
    }

    /* ================================ MAIN ============================== */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FruitShopGUI::new);
    }
}
