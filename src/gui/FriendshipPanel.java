package gui;

import model.SocialNetwork;
import util.Theme;

import javax.swing.*;
import java.awt.*;

public class FriendshipPanel extends JPanel {

    private SocialNetwork network;

    private JComboBox<String> userA;
    private JComboBox<String> userB;

    private DashboardPanel dashboardPanel;
    private GraphPanel graphPanel;

    public FriendshipPanel(
            SocialNetwork network,
            DashboardPanel dashboardPanel,
            GraphPanel graphPanel) {

        this.network = network;
        this.dashboardPanel = dashboardPanel;
        this.graphPanel = graphPanel;

        initializeUI();
    }

    private void initializeUI() {

        setBackground(Theme.BG);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setBackground(Theme.CARD);
        card.setPreferredSize(new Dimension(550, 300));
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title = new JLabel("Create Friendship");
        title.setFont(Theme.TITLE);
        title.setForeground(Color.WHITE);

        JLabel userALabel = new JLabel("User A");
        userALabel.setForeground(Color.WHITE);
        userALabel.setFont(Theme.NORMAL);

        JLabel userBLabel = new JLabel("User B");
        userBLabel.setForeground(Color.WHITE);
        userBLabel.setFont(Theme.NORMAL);

        userA = new JComboBox<>();
        userB = new JComboBox<>();

        userA.setPreferredSize(new Dimension(200,30));
        userB.setPreferredSize(new Dimension(200,30));

        JButton addButton = new JButton("Tambah Friendship");

        addButton.setBackground(Theme.PRIMARY);
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(Theme.NORMAL);

        addButton.addActionListener(e -> createFriendship());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        card.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        card.add(userALabel, gbc);

        gbc.gridx = 1;
        card.add(userA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        card.add(userBLabel, gbc);

        gbc.gridx = 1;
        card.add(userB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        card.add(addButton, gbc);

        add(card);
    }

    public void refreshUsers() {

        userA.removeAllItems();
        userB.removeAllItems();

        for(String user : network.getUsers()) {

            userA.addItem(user);
            userB.addItem(user);
        }
    }

    private void createFriendship() {

        String a = (String) userA.getSelectedItem();
        String b = (String) userB.getSelectedItem();

        if(a == null || b == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Tambahkan user terlebih dahulu!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if(a.equals(b)) {

            JOptionPane.showMessageDialog(
                    this,
                    "User tidak boleh berteman dengan dirinya sendiri!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean success = network.addFriendship(a,b);

        if(success) {

            dashboardPanel.updateData();

            graphPanel.repaint();

            JOptionPane.showMessageDialog(
                    this,
                    "Friendship berhasil dibuat!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Gagal membuat friendship!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}