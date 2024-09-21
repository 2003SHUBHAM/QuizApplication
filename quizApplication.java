import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class quizApplication extends JFrame implements ActionListener {
    JButton rules;
    JButton back;
    JTextField t;

    quizApplication(){
        getContentPane().setBackground(Color.white);
        setLayout(null);



        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/img.png"));
        JLabel l1 = new JLabel(i1);
        l1.setBounds(0,100,600,400);
        add(l1);

        JLabel text = new JLabel("WELCOME CODERS");
        text.setBounds(800,100,400,100);
        text.setFont(new Font("Viner Hand ITC",Font.BOLD,40));
        text.setForeground(new Color(30,144,250));
        add(text);

        JLabel text1 = new JLabel("ENTER YOUR NAME ");
        text1.setBounds(900,150,400,100);
        text1.setFont(new Font("SAN SERIF",Font.BOLD,20));
        text1.setForeground(new Color(30,144,250));
        add(text1);

        t = new JTextField();
        t.setBounds(880,230,300,40);
        t.setFont(new Font("SAN SERIF",Font.BOLD,25));
        t.setForeground(Color.BLACK);
        t.setBackground(Color.WHITE);
        add(t);

        rules = new JButton("RULES");
        rules.setBounds(880,300,100,40);
        rules.setFont(new Font("Aril",Font.PLAIN,20));
        rules.setBackground(new Color(30,144,250));
        rules.setForeground(Color.WHITE);
        rules.addActionListener(this);
        add(rules);

        back= new JButton("BACK");
        back.setBounds(1030,300,100,40);
        back.setFont(new Font("Aril",Font.PLAIN,20));
        back.setBackground(new Color(30,144,250));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setSize(1400,700);
        setBounds(0,0,1400,700);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== rules){
            String name = t.getText();
            setVisible(false);
            new rules(name);

        } else if (e.getSource()== back) {
            System.exit(0);

        }
    }

    public static void main(String[] args) {
        new quizApplication();
    }



}
