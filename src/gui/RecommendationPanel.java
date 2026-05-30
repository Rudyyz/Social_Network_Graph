package gui;

import java.awt.*;
import java.util.Set;
import javax.swing.*;
import model.SocialNetwork;
import util.Theme;

public class RecommendationPanel extends JPanel {

    private SocialNetwork network;

    private JComboBox<String> userBox;

    private JTextArea resultArea;

    public RecommendationPanel(SocialNetwork network) {

        this.network = network;

        initializeUI();
    }

    private void initializeUI() {

        setBackground(Theme.BG);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setBackground(Theme.CARD);
        card.setPreferredSize(new Dimension(700, 480));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1)
        ));
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Friend Recommendation");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Discover new people based on your social graph.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(200, 200, 200));

        JLabel userLabel = new JLabel("Select User");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(Theme.NORMAL);

        userBox = new JComboBox<>();
        userBox.setPreferredSize(new Dimension(260, 34));
        userBox.setBackground(new Color(40, 40, 40));
        userBox.setForeground(Color.WHITE);
        userBox.setFont(Theme.NORMAL);

        JButton generateButton = new JButton("Generate Recommendation");
        generateButton.setBackground(Theme.PRIMARY);
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.setFont(Theme.NORMAL);
        generateButton.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));

        resultArea = new JTextArea(14, 34);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(20, 20, 20));
        resultArea.setForeground(Color.WHITE);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(20, 20, 20));

        generateButton.addActionListener(e -> generateRecommendation());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(title, gbc);

        gbc.gridy = 1;
        card.add(subtitle, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        card.add(userLabel, gbc);

        gbc.gridx = 1;
        card.add(userBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        card.add(generateButton, gbc);

        gbc.gridy = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        card.add(scrollPane, gbc);

        add(card);
    }

    public void refreshUsers() {

        userBox.removeAllItems();

        for(String user : network.getUsers()) {

            userBox.addItem(user);
        }
    }

    private void generateRecommendation() {

        String user =
                (String) userBox.getSelectedItem();

        if(user == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Belum ada user!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Set<String> recommendations =
                network.getRecommendations(user);

        resultArea.setText("");

        resultArea.append(
                "=== PEOPLE YOU MAY KNOW ===\n\n"
        );

        resultArea.append(
                "User : " + user + "\n\n"
        );

        if(recommendations.isEmpty()) {

            resultArea.append(
                    "Tidak ada rekomendasi."
            );

        } else {

            int no = 1;

            for(String friend : recommendations) {

                resultArea.append(
                        no + ". " +
                                friend +
                                "\n"
                );

                no++;
            }
        }
    }
}