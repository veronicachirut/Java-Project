package project.GUI;

import project.classes.Drink;
import project.classes.Food;
import project.csv.Audit;
import project.database.DataBase;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.print.DocFlavor;
import javax.swing.*;

public class DrinksForm extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args)
    {
		/* It posts an event (Runnable) at the end of Swings event list and is
		processed after all other GUI events are processed.*/
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                try {
                    DrinksForm frame = new DrinksForm();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DrinksForm() throws SQLException {
        JFrame jFrame = new JFrame("Drinks");

        JLabel text = new JLabel("Add a new drink: ");
        text.setBounds(130, 60, 200, 30);
        jFrame.add(text);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameTextField = new JTextField();
        nameLabel.setBounds(20,100,200,30);
        nameTextField.setBounds(90,100,200,30);
        jFrame.add(nameLabel);
        jFrame.add(nameTextField);

        JLabel categoryLabel = new JLabel("Category: ");
        JTextField categoryTextField = new JTextField();
        categoryLabel.setBounds(20,150,200,30);
        categoryTextField.setBounds(90,150,200,30);
        jFrame.add(categoryLabel);
        jFrame.add(categoryTextField);

        JLabel sugaryLabel = new JLabel("Sugary: ");
        JTextField sugaryTextField = new JTextField();
        sugaryLabel.setBounds(20,200,200,30);
        sugaryTextField.setBounds(90,200,200,30);
        jFrame.add(sugaryLabel);
        jFrame.add(sugaryTextField);

        JLabel spirituousLabel = new JLabel("Spirituous: ");
        JTextField spirituousTextField = new JTextField();
        spirituousLabel.setBounds(20,250,200,30);
        spirituousTextField.setBounds(90,250,200,30);
        jFrame.add(spirituousLabel);
        jFrame.add(spirituousTextField);

        JButton jButtonAdd = new JButton("Save food");
        jButtonAdd.setBounds(110,300,150,30);
        jButtonAdd.addActionListener(addActionListener(nameTextField, categoryTextField, sugaryTextField, spirituousTextField));
        jFrame.add(jButtonAdd);

        JLabel show = new JLabel("List of drinks");
        show.setBounds(600, 50, 200, 30);
        jFrame.add(show);

        JList<Drink> drinks = new JList<>();
        drinks.setBounds(300,100,650,200);
        drinks.setListData(getDrinksArray());
        jFrame.add(drinks);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(875, 10, 100, 30);
        refreshButton.addActionListener(refreshActionListener(drinks, jFrame));
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

    private static ActionListener addActionListener(JTextField nameTextField, JTextField categoryTextField, JTextField sugaryTextField, JTextField spirituousTextField){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = action ->{
            String name = nameTextField.getText();
            String category = categoryTextField.getText();
            String sugary = sugaryTextField.getText();
            boolean intSugary = Boolean.parseBoolean(sugary);
            String spirituous = spirituousTextField.getText();
            boolean intSpirituous = Boolean.parseBoolean(spirituous);

            Drink drink = new Drink(name, category, intSugary, intSpirituous);
            try {
                dataBase.addDrink(drink);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Drink added successfully");
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Drinks: addActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static  ActionListener refreshActionListener(JList<Drink> jList, Frame jFrame){
        ActionListener actionListener = actionEvent -> {
            try {
                jList.setListData(getDrinksArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jFrame.add(jList);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Drinks: refreshActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static Drink [] getDrinksArray() throws SQLException {
        DataBase dataBase = new DataBase();
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Drinks: getDrinksArray, " + Thread.currentThread().getName());
        return dataBase.getDrinks().toArray(new Drink[0]);
    }


    private ActionListener goBackActionListener() {
        ActionListener actionListener = actionEvent -> {
            MainForm frame = null;
            frame = new MainForm();
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Drinks: goBackActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }
}