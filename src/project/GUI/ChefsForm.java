package project.GUI;

import project.classes.Chef;
import project.csv.Audit;
import project.database.DataBase;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class ChefsForm extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args)
    {
		/* It posts an event (Runnable) at the end of Swings event list and is
		processed after all other GUI events are processed.*/
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                try {
                    ChefsForm frame = new ChefsForm();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChefsForm() throws SQLException {
        JFrame jFrame = new JFrame("Chefs");

        JLabel text = new JLabel("Add a new chef: ");
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

        JButton jButtonAdd = new JButton("Save chef");
        jButtonAdd.setBounds(110,300,150,30);
        jButtonAdd.addActionListener(addActionListener(nameTextField,ageTextField,hireDateTextField,salaryTextField));
        jFrame.add(jButtonAdd);

        JLabel show = new JLabel("List of chefs");
        show.setBounds(650, 50, 200, 30);
        jFrame.add(show);

        JList<Chef> chefs = new JList<>();
        chefs.setBounds(500,90,400,200);
        chefs.setListData(getChefsArray());
        jFrame.add(chefs);

        JButton deleteChefButton = new JButton("Delete Chef");
        deleteChefButton.setBounds(600,310,200,30);
        deleteChefButton.addActionListener(deleteChefActionListener(chefs));
        jFrame.add(deleteChefButton);

        JButton orderByHireDate = new JButton("Order by salary");
        orderByHireDate.setBounds(600, 350, 200, 30);
        orderByHireDate.addActionListener(getOrderedChefsBySalaryActionListener(chefs, jFrame));
        jFrame.add(orderByHireDate);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(875, 10, 100, 30);
        refreshButton.addActionListener(refreshActionListener(chefs, jFrame));
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

            Chef chef = new Chef(name, intAge, hireDate, intSalary);
            try {
                dataBase.addChef(chef);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Chef added successfully");
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Chefs: addActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static ActionListener deleteChefActionListener(JList<Chef> jList){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = actionEvent -> {
            try {
                dataBase.deleteChef(jList.getSelectedValue().getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                jList.setListData(getChefsArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Chef successfully erased");
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Chefs: deleteChefActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static  ActionListener refreshActionListener(JList<Chef> jList, Frame jFrame){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = actionEvent -> {
            try {
                jList.setListData(getChefsArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jFrame.add(jList);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Chefs: refreshActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static Chef [] getChefsArray() throws SQLException {
        DataBase dataBase = new DataBase();
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Chefs: getChefsArray, " + Thread.currentThread().getName());
        return dataBase.getChefs().toArray(new Chef[0]);
    }
    private static Chef [] getOrderedChefsBySalaryArray() throws SQLException {
        DataBase dataBase = new DataBase();
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Chefs: getOrderedChefsBySalaryArray, " + Thread.currentThread().getName());
        return dataBase.getOrderedChefsBySalary().toArray(new Chef[0]);
    }

    private static  ActionListener getOrderedChefsBySalaryActionListener(JList<Chef> jList, Frame jFrame){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = actionEvent -> {
            try {
                jList.setListData(getOrderedChefsBySalaryArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jFrame.add(jList);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Chefs: getOrderedChefsBySalaryActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private ActionListener goBackActionListener() {
        ActionListener actionListener = actionEvent -> {
            MainForm frame = null;
            frame = new MainForm();
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Chefs: Go to main page, " + Thread.currentThread().getName());
        return actionListener;
    }
}