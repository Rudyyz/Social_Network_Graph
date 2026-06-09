package gui;

import algorithm.BFS;
import model.SocialNetwork;
import util.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BFSPathPanel extends JPanel {

    private SocialNetwork network;

    private JComboBox<String> startBox;
    private JComboBox<String> endBox;

    private JTextArea resultArea;

    public BFSPathPanel(SocialNetwork network) {

        this.network = network;

        initializeUI();
    }

    private void initializeUI() {

        setBackground(Theme.BG);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setBackground(Theme.CARD);
        card.setPreferredSize(new Dimension(700, 500));
        card.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title =
                new JLabel("Connection Path Finder (BFS)");

        title.setFont(Theme.TITLE);
        title.setForeground(Color.WHITE);

        JLabel startLabel =
                new JLabel("Start User");

        startLabel.setForeground(Color.WHITE);
        startLabel.setFont(Theme.NORMAL);

        JLabel endLabel =
                new JLabel("Destination User");

        endLabel.setForeground(Color.WHITE);
        endLabel.setFont(Theme.NORMAL);

        startBox = new JComboBox<>();
        endBox = new JComboBox<>();

        startBox.setPreferredSize(
                new Dimension(220,30)
        );

        endBox.setPreferredSize(
                new Dimension(220,30)
        );

        JButton findButton =
                new JButton("Find Connection Path");

        findButton.setBackground(
                Theme.PRIMARY
        );

        findButton.setForeground(
                Color.WHITE
        );

        findButton.setFocusPainted(false);

        findButton.setFont(
                Theme.NORMAL
        );

        resultArea = new JTextArea(15,35);

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
                        15
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        scrollPane.setPreferredSize(
                new Dimension(500,220)
        );

        findButton.addActionListener(
                e -> findPath()
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        card.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        card.add(startLabel, gbc);

        gbc.gridx = 1;

        card.add(startBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        card.add(endLabel, gbc);

        gbc.gridx = 1;

        card.add(endBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        card.add(findButton, gbc);

        gbc.gridy = 4;

        card.add(scrollPane, gbc);

        add(card);
    }

    public void refreshUsers() {

        startBox.removeAllItems();
        endBox.removeAllItems();

        for(String user : network.getUsers()) {

            startBox.addItem(user);
            endBox.addItem(user);
        }
    }

    private void findPath() {

        String start =
                (String) startBox.getSelectedItem();

        String end =
                (String) endBox.getSelectedItem();

        if(start == null || end == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Belum ada user!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        BFS.BFSResult bfsResult =
                BFS.findPath(
                        network.getGraph(),
                        start,
                        end
                );

        List<String> path =
                bfsResult.getPath();

        List<String> visitedOrder =
                bfsResult.getVisitedOrder();

        resultArea.setText("");

        StringBuilder result = new StringBuilder();

        result.append("=== BFS CONNECTION PATH ===\n\n");
        result.append("From : ").append(start).append("\n");
        result.append("To   : ").append(end).append("\n\n");

        result.append("Alur Pencarian BFS:\n\n");

        for(int i = 0; i < visitedOrder.size(); i++) {

            result.append(visitedOrder.get(i));

            if(i < visitedOrder.size() - 1) {
                result.append(" -> ");
            }
        }

        result.append("\n\n");

        if(path == null || path.isEmpty()) {

            result.append("Shortest Path BFS:\n\n");
            result.append("Tidak ada jalur koneksi.");

            resultArea.setText(result.toString());
            return;
        }

        result.append("Shortest Path BFS:\n\n");

        for(int i = 0; i < path.size(); i++) {

            result.append(path.get(i));

            if(i < path.size() - 1) {
                result.append("\n↓\n");
            }
        }

        result.append("\n\nJumlah Node Dilalui : ")
                .append(path.size());

        resultArea.setText(result.toString());
    }
}