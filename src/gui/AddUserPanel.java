package gui;

import model.SocialNetwork;
import util.Theme;

import javax.swing.*;
import java.awt.*;

public class AddUserPanel extends JPanel {

    private SocialNetwork network;

    private FriendshipPanel friendshipPanel;
    private MutualFriendPanel mutualPanel;
    private RecommendationPanel recommendationPanel;
    private PathPanel pathPanel;

    private DashboardPanel dashboardPanel;
    private GraphPanel graphPanel;

    private JTextField nameField;

    public AddUserPanel(
            SocialNetwork network,
            FriendshipPanel friendshipPanel,
            MutualFriendPanel mutualPanel,
            RecommendationPanel recommendationPanel,
            PathPanel pathPanel,
            DashboardPanel dashboardPanel,
            GraphPanel graphPanel) {

        this.network = network;

        this.friendshipPanel = friendshipPanel;
        this.mutualPanel = mutualPanel;
        this.recommendationPanel = recommendationPanel;
        this.pathPanel = pathPanel;

        this.dashboardPanel = dashboardPanel;
        this.graphPanel = graphPanel;

        initializeUI();
    }

    private void initializeUI() {

        setBackground(Theme.BG);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(500, 250));
        card.setBackground(Theme.CARD);
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel("Add New User");
        title.setFont(Theme.TITLE);
        title.setForeground(Color.WHITE);

        JLabel nameLabel = new JLabel("User Name");
        nameLabel.setFont(Theme.NORMAL);
        nameLabel.setForeground(Color.WHITE);

        nameField = new JTextField(20);
        nameField.setFont(Theme.NORMAL);

        JButton addButton = new JButton("Tambah User");
        addButton.setBackground(Theme.SUCCESS);
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(Theme.NORMAL);

        addButton.addActionListener(e -> addUser());

        gbc.insets = new Insets(10,10,10,10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        card.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        card.add(nameLabel, gbc);

        gbc.gridx = 1;
        card.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        card.add(addButton, gbc);

        add(card);
    }

    private void addUser() {

        String name = nameField.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nama user tidak boleh kosong!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean success = network.addUser(name);

        if (success) {

            friendshipPanel.refreshUsers();
            mutualPanel.refreshUsers();
            recommendationPanel.refreshUsers();
            pathPanel.refreshUsers();

            dashboardPanel.updateData();

            graphPanel.repaint();

            JOptionPane.showMessageDialog(
                    this,
                    "User berhasil ditambahkan!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            nameField.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "User sudah ada!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}