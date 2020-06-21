package project.GUI;

import project.classes.Food;
import project.csv.Audit;
import project.database.DataBase;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class FoodsForm extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args)
    {
		/* It posts an event (Runnable) at the end of Swings event list and is
		processed after all other GUI events are processed.*/
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                try {
                    FoodsForm frame = new FoodsForm();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FoodsForm() throws SQLException {
        JFrame jFrame = new JFrame("Food");

        JLabel text = new JLabel("Add a new food: ");
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

        JLabel ingredientsLabel = new JLabel("Ingredients: ");
        JTextField ingredientsTextField = new JTextField();
        ingredientsLabel.setBounds(20,200,200,30);
        ingredientsTextField.setBounds(90,200,200,30);
        jFrame.add(ingredientsLabel);
        jFrame.add(ingredientsTextField);

        JLabel amountLabel = new JLabel("Amount: ");
        JTextField amountTextField = new JTextField();
        amountLabel.setBounds(20,250,200,30);
        amountTextField.setBounds(90,250,200,30);
        jFrame.add(amountLabel);
        jFrame.add(amountTextField);

        JButton jButtonAdd = new JButton("Save food");
        jButtonAdd.setBounds(110,300,150,30);
        jButtonAdd.addActionListener(addActionListener(nameTextField, categoryTextField, ingredientsTextField, amountTextField));
        jFrame.add(jButtonAdd);

        JLabel show = new JLabel("List of foods");
        show.setBounds(600, 50, 200, 30);
        jFrame.add(show);

        JList<Food> foods = new JList<>();
        foods.setBounds(300,100,650,200);
        foods.setListData(getFoodsArray());
        jFrame.add(foods);

        JButton searchButton = new JButton("Search after name: ");
        JTextField searchTextField = new JTextField();
        searchButton.setBounds(90,360,200,30);
        searchTextField.setBounds(90,400,200,30);

        JList<Food> searchFoods = new JList<>();
        searchFoods.setBounds(300,400,650,30);
        searchFoods.setListData(searchFood(searchTextField.getText()));

        searchButton.addActionListener(searchActionListener(searchFoods, jFrame, searchTextField));


        jFrame.add(searchFoods);
        jFrame.add(searchButton);
        jFrame.add(searchTextField);


        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(875, 10, 100, 30);
        refreshButton.addActionListener(refreshActionListener(foods, jFrame));
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

    private static ActionListener addActionListener(JTextField nameTextField, JTextField categoryTextField, JTextField ingredientsTextField, JTextField amountTextField){
        DataBase dataBase = new DataBase();
        ActionListener actionListener = action ->{
            String name = nameTextField.getText();
            String category = categoryTextField.getText();
            String ingredients = ingredientsTextField.getText();
            String amount = amountTextField.getText();
            int intAmount = Integer.parseInt(amount);

            Food food = new Food(name, category, ingredients, intAmount);
            try {
                dataBase.addFood(food);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Food added successfully");
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Foods: addActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static  ActionListener refreshActionListener(JList<Food> jList, Frame jFrame){
        ActionListener actionListener = actionEvent -> {
            try {
                jList.setListData(getFoodsArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jFrame.add(jList);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Foods: refreshActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static  ActionListener searchActionListener(JList<Food> jList, Frame jFrame, JTextField searchTextField){
        ActionListener actionListener = actionEvent -> {
            try {
                jList.setListData(searchFood(searchTextField.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jFrame.add(jList);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Foods: searchActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }

    private static Food [] getFoodsArray() throws SQLException {
        DataBase dataBase = new DataBase();
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Foods: getFoodsArray, " + Thread.currentThread().getName());
        return dataBase.getFoods().toArray(new Food[0]);
    }

    private static Food [] searchFood(String foodName) throws SQLException {
        DataBase dataBase = new DataBase();
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Foods: searchFood, " + Thread.currentThread().getName());
        return dataBase.searchFood(foodName).toArray(new Food[0]);
    }

    private ActionListener goBackActionListener() {
        ActionListener actionListener = actionEvent -> {
            MainForm frame = null;
            frame = new MainForm();
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        Audit audit = Audit.getInstance();
        audit.WriteToAudit("Foods: goBackActionListener, " + Thread.currentThread().getName());
        return actionListener;
    }
}