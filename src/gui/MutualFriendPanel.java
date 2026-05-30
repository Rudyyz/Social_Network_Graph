package gui;

import model.SocialNetwork;
import util.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MutualFriendPanel extends JPanel {

    private SocialNetwork network;

    private JComboBox<String> user1Box;
    private JComboBox<String> user2Box;

    private JTextArea resultArea;

    public MutualFriendPanel(SocialNetwork network) {

        this.network = network;

        initializeUI();
    }

    private void initializeUI() {

        setBackground(Theme.BG);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setBackground(Theme.CARD);
        card.setPreferredSize(new Dimension(650, 400));
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title = new JLabel("Mutual Friends Finder");
        title.setFont(Theme.TITLE);
        title.setForeground(Color.WHITE);

        JLabel user1Label = new JLabel("User 1");
        user1Label.setForeground(Color.WHITE);
        user1Label.setFont(Theme.NORMAL);

        JLabel user2Label = new JLabel("User 2");
        user2Label.setForeground(Color.WHITE);
        user2Label.setFont(Theme.NORMAL);

        user1Box = new JComboBox<>();
        user2Box = new JComboBox<>();

        user1Box.setPreferredSize(new Dimension(220,30));
        user2Box.setPreferredSize(new Dimension(220,30));

        JButton searchButton =
                new JButton("Cari Mutual Friends");

        searchButton.setBackground(Theme.PRIMARY);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setFont(Theme.NORMAL);

        resultArea = new JTextArea(10,30);
        resultArea.setEditable(false);

        resultArea.setBackground(new Color(30,30,30));
        resultArea.setForeground(Color.WHITE);

        resultArea.setFont(new Font(
                "Consolas",
                Font.PLAIN,
                14
        ));

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        searchButton.addActionListener(
                e -> findMutualFriends()
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        card.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        card.add(user1Label, gbc);

        gbc.gridx = 1;
        card.add(user1Box, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        card.add(user2Label, gbc);

        gbc.gridx = 1;
        card.add(user2Box, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        card.add(searchButton, gbc);

        gbc.gridy = 4;
        card.add(scrollPane, gbc);

        add(card);
    }

    public void refreshUsers() {

        user1Box.removeAllItems();
        user2Box.removeAllItems();

        for(String user : network.getUsers()) {

            user1Box.addItem(user);
            user2Box.addItem(user);
        }
    }

    private void findMutualFriends() {

        String user1 =
                (String) user1Box.getSelectedItem();

        String user2 =
                (String) user2Box.getSelectedItem();

        if(user1 == null || user2 == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Belum ada user!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        List<String> mutual =
                network.getMutualFriends(
                        user1,
                        user2
                );

        resultArea.setText("");

        resultArea.append(
                "=== MUTUAL FRIENDS ===\n\n"
        );

        resultArea.append(
                "User 1 : " + user1 + "\n"
        );

        resultArea.append(
                "User 2 : " + user2 + "\n\n"
        );

        if(mutual.isEmpty()) {

            resultArea.append(
                    "Tidak ada mutual friends."
            );

        } else {

            for(String friend : mutual) {

                resultArea.append(
                        "• " + friend + "\n"
                );
            }
        }
    }
}