package gui;

import model.SocialNetwork;
import util.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphPanel extends JPanel {

    private SocialNetwork network;

    public GraphPanel(SocialNetwork network) {

        this.network = network;

        setBackground(Theme.BG);
    }

    public void refreshGraph() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 =
                (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        drawTitle(g2);

        HashMap<String, ArrayList<String>> graph =
                network.getGraph();

        if(graph.isEmpty()) {

            drawEmptyMessage(g2);
            return;
        }

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2 + 30;

        int radius = 220;

        ArrayList<String> users =
                new ArrayList<>(graph.keySet());

        HashMap<String, Point> positions =
                new HashMap<>();

        int n = users.size();

        for(int i = 0; i < n; i++) {

            double angle =
                    2 * Math.PI * i / n;

            int x =
                    centerX +
                            (int)(radius * Math.cos(angle));

            int y =
                    centerY +
                            (int)(radius * Math.sin(angle));

            positions.put(
                    users.get(i),
                    new Point(x,y)
            );
        }

        drawEdges(g2, graph, positions);

        drawNodes(g2, users, positions);
    }

    private void drawTitle(Graphics2D g2) {

        g2.setColor(Color.WHITE);

        g2.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        g2.drawString(
                "Social Network Graph Visualization",
                30,
                40
        );
    }

    private void drawEmptyMessage(Graphics2D g2) {

        g2.setColor(Color.LIGHT_GRAY);

        g2.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        18
                )
        );

        g2.drawString(
                "Belum ada user yang ditambahkan",
                getWidth()/2 - 130,
                getHeight()/2
        );
    }

    private void drawEdges(
            Graphics2D g2,
            HashMap<String, ArrayList<String>> graph,
            HashMap<String, Point> positions) {

        g2.setStroke(
                new BasicStroke(2f)
        );

        g2.setColor(Color.WHITE);

        HashSet<String> drawn =
                new HashSet<>();

        for(String user : graph.keySet()) {

            Point p1 =
                    positions.get(user);

            for(String friend :
                    graph.get(user)) {

                String key1 =
                        user + "-" + friend;

                String key2 =
                        friend + "-" + user;

                if(drawn.contains(key1)
                        || drawn.contains(key2))
                    continue;

                Point p2 =
                        positions.get(friend);

                g2.drawLine(
                        p1.x,
                        p1.y,
                        p2.x,
                        p2.y
                );

                drawn.add(key1);
            }
        }
    }

    private void drawNodes(
            Graphics2D g2,
            ArrayList<String> users,
            HashMap<String, Point> positions) {

        int nodeSize = 70;

        for(String user : users) {

            Point p =
                    positions.get(user);

            g2.setColor(
                    Theme.PRIMARY
            );

            g2.fillOval(
                    p.x - nodeSize/2,
                    p.y - nodeSize/2,
                    nodeSize,
                    nodeSize
            );

            g2.setColor(Color.WHITE);

            g2.setStroke(
                    new BasicStroke(2f)
            );

            g2.drawOval(
                    p.x - nodeSize/2,
                    p.y - nodeSize/2,
                    nodeSize,
                    nodeSize
            );

            g2.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            12
                    )
            );

            FontMetrics fm =
                    g2.getFontMetrics();

            int textWidth =
                    fm.stringWidth(user);

            g2.drawString(
                    user,
                    p.x - textWidth/2,
                    p.y + 5
            );
        }
    }
}