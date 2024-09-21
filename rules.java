import ChattingApplication.QuizApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class rules extends JFrame implements ActionListener {
    String name;
    JButton start;
    JButton exit;
    rules(String name){
        this.name = name ;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel naam = new JLabel("Welcome " + name +  " to the quiz");
        naam.setBounds(200,0,1000,40);
        naam.setFont(new Font("Arial",Font.BOLD,45));
        naam.setForeground(new Color(30,144,250));
        add(naam);

        JLabel rules = new JLabel();
        rules.setBounds(100,70,900,500);
        rules.setFont(new Font("Tahoma",Font.PLAIN,30));
        rules.setText("<html>" + "1. if you score 10 then you are the king." + "<br><br>"+
                "2. if you score 9 then you are brave soilder." +  "<br><br>"+
                "3. if you score 8 then you are fighter." +  "<br><br>"+
                "4. if you score 7 then you are  normal person." +  "<br><br>"+
                "5. if you score 6 then you are lazy." +  "<br><br>"+
                "6. if you score 5 then you are weak." +  "<br><br>"+
                "<html>"
        );
        add(rules);

        start = new JButton("Start");
        start.setBounds(800,100,100,50);
        start.setBackground(new Color(30,144,250));
        start.setForeground(Color.WHITE);
        start.setFont(new Font("Arial",Font.BOLD,20));
        start.addActionListener(this);
        add(start);

        exit  = new JButton("Exit");
        exit.setBounds(1000,100,100,50);
        exit.setBackground(new Color(30,144,250));
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Arial",Font.BOLD,20));
        exit.addActionListener(this);
        add(exit);




        setSize(1200,600);
        setLocation(100,100);
        setVisible(true);


    }
    public static void main(String[] args) {
        new rules("user");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== start){
            setVisible(false);
            new test();

        }
        else if(e.getSource() == exit){
            setVisible(false);
            new quizApplication();
        }
    }
}
