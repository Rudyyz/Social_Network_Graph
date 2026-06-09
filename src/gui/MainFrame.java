package gui;

import model.SocialNetwork;
import util.Theme;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    private SocialNetwork network;

    private DashboardPanel dashboardPanel;
    private AddUserPanel addUserPanel;
    private FriendshipPanel friendshipPanel;
    private MutualFriendPanel mutualPanel;
    private RecommendationPanel recommendationPanel;
    private BFSPathPanel pathPanel;
    private DFSPathPanel dfsPathPanel;
    private GraphPanel graphPanel;

    public MainFrame() {

        network = new SocialNetwork();

        initializeFrame();

        createSidebar();

        createContent();

        setVisible(true);
    }

    private void initializeFrame() {

        setTitle("Social Network Graph");

        setSize(1300, 800);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
    }

    private void createSidebar() {

        JPanel sidebar = new JPanel();

        sidebar.setBackground(
                Theme.SIDEBAR
        );

        sidebar.setPreferredSize(
                new Dimension(240,0)
        );

        sidebar.setLayout(
                new GridLayout(9,1,10,10)
        );

        JButton dashboardBtn =
                createButton("Dashboard");

        JButton userBtn =
                createButton("Add User");

        JButton friendshipBtn =
                createButton("Friendship");

        JButton mutualBtn =
                createButton("Mutual Friends");

        JButton recommendationBtn =
                createButton("Recommendation");

        JButton pathBtn =
                createButton("Path BFS");

        JButton dfsPathBtn =
                createButton("Path DFS");

        JButton graphBtn =
                createButton("Graph View");

        sidebar.add(dashboardBtn);
        sidebar.add(userBtn);
        sidebar.add(friendshipBtn);
        sidebar.add(mutualBtn);
        sidebar.add(recommendationBtn);
        sidebar.add(pathBtn);
        sidebar.add(dfsPathBtn);
        sidebar.add(graphBtn);

        add(sidebar, BorderLayout.WEST);

        dashboardBtn.addActionListener(
                e -> showPage("dashboard")
        );

        userBtn.addActionListener(
                e -> showPage("user")
        );

        friendshipBtn.addActionListener(
                e -> showPage("friendship")
        );

        mutualBtn.addActionListener(
                e -> showPage("mutual")
        );

        recommendationBtn.addActionListener(
                e -> showPage("recommendation")
        );

        pathBtn.addActionListener(
                e -> showPage("path")
        );

        dfsPathBtn.addActionListener(
                e -> showPage("dfsPath")
        );

        graphBtn.addActionListener(
                e -> showPage("graph")
        );
    }

    private JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setBackground(
                Theme.PRIMARY
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                Theme.NORMAL
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        return button;
    }

    private void createContent() {

        cardLayout =
                new CardLayout();

        contentPanel =
                new JPanel(cardLayout);

        dashboardPanel =
                new DashboardPanel(network);

        graphPanel =
                new GraphPanel(network);

        friendshipPanel =
                new FriendshipPanel(
                        network,
                        dashboardPanel,
                        graphPanel
                );

        mutualPanel =
                new MutualFriendPanel(network);

        recommendationPanel =
                new RecommendationPanel(network);

        pathPanel =
                new BFSPathPanel(network);

        dfsPathPanel =
                new DFSPathPanel(network);

        addUserPanel =
                new AddUserPanel(
                        network,
                        friendshipPanel,
                        mutualPanel,
                        recommendationPanel,
                        pathPanel,
                        dfsPathPanel,
                        dashboardPanel,
                        graphPanel
                );

        contentPanel.add(
                dashboardPanel,
                "dashboard"
        );

        contentPanel.add(
                addUserPanel,
                "user"
        );

        contentPanel.add(
                friendshipPanel,
                "friendship"
        );

        contentPanel.add(
                mutualPanel,
                "mutual"
        );

        contentPanel.add(
                recommendationPanel,
                "recommendation"
        );

        contentPanel.add(
                pathPanel,
                "path"
        );

        contentPanel.add(
                dfsPathPanel,
                "dfsPath"
        );

        contentPanel.add(
                graphPanel,
                "graph"
        );

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        cardLayout.show(
                contentPanel,
                "dashboard"
        );
    }

    private void showPage(String page) {

        if(page.equals("graph")) {

            graphPanel.refreshGraph();
        }

        dashboardPanel.updateData();

        cardLayout.show(
                contentPanel,
                page
        );
    }
}