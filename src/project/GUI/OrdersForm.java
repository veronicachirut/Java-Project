package project.GUI;
import project.classes.Order;
import project.csv.Audit;
import project.database.DataBase;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class OrdersForm extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args)
    {
		/* It posts an event (Runnable) at the end of Swings event list and is
		processed after all other GUI events are processed.*/
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                try {
                    OrdersForm frame = new OrdersForm();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public OrdersForm() throws SQLException {
        JFrame jFrame = new JFrame("Orders");

        JLabel text = new JLabel("Add a new order: ");
        text.setBounds(130, 60, 200, 30);
        jFrame.add(text);

        JLabel orderMenuLabel = new JLabel("Order Menu: ");
        JTextField orderMenuTextField = new JTextField();
        orderMenuLabel.setBounds(20,100,200,30);
        orderMenuTextField.setBounds(100,100,200,30);
        jFrame.add(orderMenuLabel);
        jFrame.add(orderMenuTextField);

        JLabel priceLabel = new JLabel("Price: ");
        JTextField priceTextField = new JTextField();
        priceLabel.setBounds(40,150,200,30);
        priceTextField.setBounds(100,150,200,30);
        jFrame.add(priceLabel);
        jFrame.add(priceTextField);

        JLabel chefLabel = new JLabel("Prepared by: ");
        JTextField chefTextField = new JTextField();
        chefLabel.setBounds(20,200,200,30);
        chefTextField.setBounds(100,200,200,30);
        jFrame.add(chefLabel);
        jFrame.add(chefTextField);

        JLabel waiterLabel = new JLabel("Delivered by: ");
        JTextField waiterTextField = new JTextField();
        waiterLabel.setBounds(20,250,200,30);
        waiterTextField.setBounds(100,250,200,30);
        jFrame.add(waiterLabel);
        jFrame.add(waiterTextField);

        JButton jButtonAdd = new JButton("Save order");
        jButtonAdd.setBounds(110,300,150,30);
        jButtonAdd.addActionListener(addActionListener(orderMenuTextField, priceTextField, chefTextField, waiterTextField));
        jFrame.add(jButtonAdd);

        JLabel show = new JLabel("List of orders");
        show.setBounds(650, 50, 200, 30);
        jFrame.add(show);

        JList<Order> orders = new JList<>();
        orders.setBounds(500,90,400,200);
        orders.setListData(getOrdersArray());
        jFrame.add(orders);

        JButton deleteChefButton = new JButton("Delete Order");
        deleteChefButton.setBounds(600,310,200,30);
        deleteChefButton.addActionListener(deleteOrderActionListener(orders));
        jFrame.add(deleteChefButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(875, 10, 100, 30);
        refreshButton.addActionListener(refreshActionListener(orders, jFrame));
        jFrame.add(refreshButton);

        JLabel orderIdLabel = new JLabel("Order ID = ");
        orderIdLabel.setBounds(30,370,150,30);
        jFrame.add(orderIdLabel);

        JTextField orderIdTextField = new JTextField();
        orderIdTextField.setBounds(100, 370, 50, 30);
        jFrame.add(orderIdTextField);

        JLabel newOrderMenuLabel = new JLabel("New order menu = ");
        newOrderMenuLabel.setBounds(175, 370, 200, 30);
        jFrame.add(newOrderMenuLabel);

        JLabel newOrderPriceLabel = new JLabel("New price order = ");
        newOrderPriceLabel.setBounds(175, 400, 200, 30);
        jFrame.add(newOrderPriceLabel);

        JTextField newOrderMenuTextField = new JTextField();
        newOrderMenuTextField.setBounds(300, 370, 400, 30);
        jFrame.add(newOrderMenuTextField);

        JTextField newOrderPriceTextField = new JTextField();
        newOrderPriceTextField.setBounds(300, 400, 400, 30);
        jFrame.add(newOrderPriceTextField);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(updateOrderMenuActionListener(orderIdTextField, newOrderMenuTextField, newOrderPriceTextField));
        updateButton.setBounds(750, 370, 100, 30);
        jFrame.add(updateButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(goBackActionListener());
        backButton.setBounds(10, 10, 100, 30);
        jFrame.add(backButton);

        jFrame.setBounds(200, 200, 1000, 500);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static ActionListener addActionListener(JTextField orderMenuTextField, JTextField priceTextField, JTextField chefTextField, JTextField waiterTextField){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = action ->{
            String orderMenu = orderMenuTextField.getText();
            String price = priceTextField.getText();
            double doublePrice = Double.parseDouble(price);
            String chefId = chefTextField.getText();
            int intChefId = Integer.parseInt(chefId);
            String waiterId = waiterTextField.getText();
            int intWaiterId = Integer.parseInt(waiterId);


            Order order = new Order(orderMenu, doublePrice, intChefId, intWaiterId);
            try {
                dataBase.addOrder(order);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Order added successfully");
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Orders: addActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static ActionListener deleteOrderActionListener(JList<Order> jList){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = actionEvent -> {
            try {
                dataBase.deleteOrder(jList.getSelectedValue().getOrderId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                jList.setListData(getOrdersArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Order successfully erased");
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Orders: deleteOrderActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static ActionListener updateOrderMenuActionListener(JTextField orderIdTextField, JTextField newOrderMenuTextField, JTextField newOrderPriceTextField) {
        DataBase dataBase = new DataBase();
        ActionListener actionListener = actionEvent -> {
            try {
                dataBase.updateOrderMenu(newOrderMenuTextField.getText(), Integer.parseInt(orderIdTextField.getText()));
                dataBase.updateOrderPrice(Double.parseDouble(newOrderPriceTextField.getText()), Integer.parseInt(orderIdTextField.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Order successfully updated");
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Orders: updateOrderMenuActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static  ActionListener refreshActionListener(JList<Order> jList, Frame jFrame){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = actionEvent -> {
            try {
                jList.setListData(getOrdersArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jFrame.add(jList);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Orders: refreshActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static Order [] getOrdersArray() throws SQLException {
        DataBase dataBase = new DataBase();
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Orders: getOrdersArray, " + Thread.currentThread().getName());
        return dataBase.getOrders().toArray(new Order[0]);
    }

    private ActionListener goBackActionListener() {
        ActionListener actionListener = actionEvent -> {
            MainForm frame = null;
            frame = new MainForm();
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Orders: goBackActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }
}