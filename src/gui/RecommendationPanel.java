package gui;

import model.SocialNetwork;
import util.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

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
        card.setPreferredSize(new Dimension(650, 450));
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title =
                new JLabel("Friend Recommendation");

        title.setFont(Theme.TITLE);
        title.setForeground(Color.WHITE);

        JLabel userLabel =
                new JLabel("Select User");

        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(Theme.NORMAL);

        userBox = new JComboBox<>();
        userBox.setPreferredSize(
                new Dimension(220,30)
        );

        JButton generateButton =
                new JButton("Generate Recommendation");

        generateButton.setBackground(
                Theme.PRIMARY
        );

        generateButton.setForeground(
                Color.WHITE
        );

        generateButton.setFocusPainted(false);

        generateButton.setFont(
                Theme.NORMAL
        );

        resultArea = new JTextArea(12,30);

        resultArea.setEditable(false);

        resultArea.setBackground(
                new Color(30,30,30)
        );

        resultArea.setForeground(
                Color.WHITE
        );

        resultArea.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        generateButton.addActionListener(
                e -> generateRecommendation()
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        card.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        card.add(userLabel, gbc);

        gbc.gridx = 1;

        card.add(userBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        card.add(generateButton, gbc);

        gbc.gridy = 3;

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