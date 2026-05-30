package gui;

import model.SocialNetwork;
import util.Theme;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private SocialNetwork network;

    private JLabel totalUsersLabel;
    private JLabel totalFriendshipsLabel;

    public DashboardPanel(SocialNetwork network) {

        this.network = network;

        initializeUI();
    }

    private void initializeUI() {

        setBackground(Theme.BG);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Theme.BG);

        JLabel title =
                new JLabel("Social Network Dashboard");

        title.setForeground(Color.WHITE);
        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        topPanel.add(title);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();

        centerPanel.setBackground(Theme.BG);

        centerPanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        40,
                        100
                )
        );

        JPanel userCard =
                createCard(
                        "Total Users",
                        Theme.PRIMARY
                );

        JPanel friendshipCard =
                createCard(
                        "Total Friendships",
                        Theme.SUCCESS
                );

        totalUsersLabel =
                (JLabel) userCard.getComponent(1);

        totalFriendshipsLabel =
                (JLabel) friendshipCard.getComponent(1);

        centerPanel.add(userCard);
        centerPanel.add(friendshipCard);

        add(centerPanel, BorderLayout.CENTER);

        updateData();
    }

    private JPanel createCard(
            String title,
            Color color) {

        JPanel card = new JPanel();

        card.setPreferredSize(
                new Dimension(280,180)
        );

        card.setBackground(
                Theme.CARD
        );

        card.setLayout(
                new GridBagLayout()
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                Color.WHITE
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        JLabel valueLabel =
                new JLabel("0");

        valueLabel.setForeground(
                color
        );

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        42
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        card.add(titleLabel, gbc);

        gbc.gridy = 1;

        card.add(valueLabel, gbc);

        return card;
    }

    public void updateData() {

        totalUsersLabel.setText(
                String.valueOf(
                        network.getTotalUsers()
                )
        );

        totalFriendshipsLabel.setText(
                String.valueOf(
                        network.getTotalFriendships()
                )
        );
    }
}