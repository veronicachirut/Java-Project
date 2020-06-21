package project.GUI;

import project.classes.Waiter;
import project.csv.Audit;
import project.database.DataBase;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class WaitersForm extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args)
    {
		/* It posts an event (Runnable) at the end of Swings event list and is
		processed after all other GUI events are processed.*/
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                try {
                    WaitersForm frame = new WaitersForm();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public WaitersForm() throws SQLException {
        JFrame jFrame = new JFrame("Waiters");

        JLabel text = new JLabel("Add a new waiter: ");
        text.setBounds(130, 60, 200, 30);
        jFrame.add(text);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameTextField = new JTextField();
        nameLabel.setBounds(40,100,200,30);
        nameTextField.setBounds(90,100,200,30);
        jFrame.add(nameLabel);
        jFrame.add(nameTextField);

        JLabel ageLabel = new JLabel("Age: ");
        JTextField ageTextField = new JTextField();
        ageLabel.setBounds(40,150,200,30);
        ageTextField.setBounds(90,150,200,30);
        jFrame.add(ageLabel);
        jFrame.add(ageTextField);

        JLabel hireDateLabel = new JLabel("Hire Date: ");
        JTextField hireDateTextField = new JTextField();
        hireDateLabel.setBounds(20,200,200,30);
        hireDateTextField.setBounds(90,200,200,30);
        jFrame.add(hireDateLabel);
        jFrame.add(hireDateTextField);

        JLabel salaryLabel = new JLabel("Salary: ");
        JTextField salaryTextField = new JTextField();
        salaryLabel.setBounds(40,250,200,30);
        salaryTextField.setBounds(90,250,200,30);
        jFrame.add(salaryLabel);
        jFrame.add(salaryTextField);

        JButton jButtonAdd = new JButton("Save waiter");
        jButtonAdd.setBounds(110,300,150,30);
        jButtonAdd.addActionListener(addActionListener(nameTextField,ageTextField,hireDateTextField,salaryTextField));
        jFrame.add(jButtonAdd);

        JLabel show = new JLabel("List of waiters");
        show.setBounds(650, 50, 200, 30);
        jFrame.add(show);

        JList<Waiter> waiters = new JList<>();
        waiters.setBounds(500,90,400,200);
        waiters.setListData(getWaitersArray());
        jFrame.add(waiters);

        JButton deleteWaiterButton = new JButton("Delete Chef");
        deleteWaiterButton.setBounds(600,310,200,30);
        deleteWaiterButton.addActionListener(deleteWaiterActionListener(waiters));
        jFrame.add(deleteWaiterButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(875, 10, 100, 30);
        refreshButton.addActionListener(refreshActionListener(waiters, jFrame));
        jFrame.add(refreshButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(goBackActionListener());
        backButton.setBounds(10, 10, 100, 30);
        jFrame.add(backButton);

        jFrame.setBounds(200, 200, 1000, 500);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static ActionListener addActionListener(JTextField nameTextField, JTextField ageTextField, JTextField hireDateTextField, JTextField salaryTextField){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = action ->{
            String name = nameTextField.getText();
            String age = ageTextField.getText();
            int intAge = Integer.parseInt(age);
            String hireDate = hireDateTextField.getText();
            String salary = salaryTextField.getText();
            int intSalary = Integer.parseInt(salary);


            Waiter waiter = new Waiter(name, intAge, hireDate, intSalary);
            try {
                dataBase.addWaiter(waiter);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Waiter added successfully");
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Waiters: addActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static ActionListener deleteWaiterActionListener(JList<Waiter> jList){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = actionEvent -> {
            try {
                dataBase.deleteChef(jList.getSelectedValue().getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                jList.setListData(getWaitersArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Waiter successfully erased");
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Waiters: deleteWaiterActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static  ActionListener refreshActionListener(JList<Waiter> jList, Frame jFrame){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = actionEvent -> {
            try {
                jList.setListData(getWaitersArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jFrame.add(jList);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Waiters: refreshActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static Waiter [] getWaitersArray() throws SQLException {
        DataBase dataBase = new DataBase();
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Waiters: getWaitersArray, " + Thread.currentThread().getName());
        return dataBase.getWaiters().toArray(new Waiter[0]);
    }

    private ActionListener goBackActionListener() {
        ActionListener actionListener = actionEvent -> {
            MainForm frame = null;
            frame = new MainForm();
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Waiters: goBackActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }
}