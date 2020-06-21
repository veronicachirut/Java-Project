package project.GUI;

import project.csv.Audit;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainForm extends JFrame {

    private JPanel contentPane;
    public Audit audit = Audit.getInstance();

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() {
                try {
                    MainForm frame = new MainForm();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainForm() {
        setTitle("Restaurant App");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 1000, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel welcomeMessageLabel = new JLabel("Welcome!");
        welcomeMessageLabel.setBounds(400, 10, 200, 30);
        welcomeMessageLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        contentPane.add(welcomeMessageLabel);

        JButton ordersFrame = new JButton("Orders");
        ordersFrame.addActionListener(goToOrdersActionListener());
        ordersFrame.setBounds(30, 60, 930, 120);
        contentPane.add(ordersFrame);

        JButton chefsFrame = new JButton("Chefs");
        chefsFrame.addActionListener(goToChefsActionListener());
        chefsFrame.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        chefsFrame.setBounds(30, 190, 455, 120);
        contentPane.add(chefsFrame);

        JButton waitersFrame = new JButton("Waiters");
        waitersFrame.addActionListener(goToWaitersActionListener());
        waitersFrame.setBounds(500, 190, 460, 120);
        contentPane.add(waitersFrame);

        JButton foodsFrame = new JButton("Foods");
        foodsFrame.addActionListener(goToFoodsActionListener());
        foodsFrame.setBounds(30, 320, 455, 120);
        contentPane.add(foodsFrame);

        JButton drinksFrame = new JButton("Drinks");
        drinksFrame.addActionListener(goToDrinksActionListener());
        drinksFrame.setBounds(500, 320, 460, 120);
        contentPane.add(drinksFrame);
    }

    private ActionListener goToChefsActionListener(){
        ActionListener actionListener = actionEvent -> {
            ChefsForm frame = null;
            try {
                frame = new ChefsForm();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        audit.WriteToAudit("Main: Go To Chefs, " + Thread.currentThread().getName());
        return actionListener;
    }

    private ActionListener goToWaitersActionListener(){
        ActionListener actionListener = actionEvent -> {
            WaitersForm frame = null;
            try {
                frame = new WaitersForm();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        audit.WriteToAudit("Main: Go To Waiters, " + Thread.currentThread().getName());
        return actionListener;
    }

    private ActionListener goToOrdersActionListener() {
        ActionListener actionListener = actionEvent -> {
            OrdersForm frame = null;
            try {
                frame = new OrdersForm();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        audit.WriteToAudit("Main: Go To Orders, " + Thread.currentThread().getName());
        return actionListener;
    }

    private ActionListener goToFoodsActionListener() {
        ActionListener actionListener = actionEvent -> {
            FoodsForm frame = null;
            try {
                frame = new FoodsForm();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        audit.WriteToAudit("Main: Go To Foods, " + Thread.currentThread().getName());
        return actionListener;
    }

    private ActionListener goToDrinksActionListener() {
        ActionListener actionListener = actionEvent -> {
            DrinksForm frame = null;
            try {
                frame = new DrinksForm();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        audit.WriteToAudit("Main: Go To Drinks, " + Thread.currentThread().getName());
        return actionListener;
    }
}