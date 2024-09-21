

import ChattingApplication.QuizApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class test extends JFrame implements ActionListener {
    private List<Question> questionPool; // Pool of all questions
    private List<Question> selectedQuestions; // List of randomly selected questions
    private int currentQuestionIndex = 0;
    private JLabel questionLabel;
    private JRadioButton[] option;
    private int score = 0;
    private JButton next;
    private JLabel qno;
    test(){
        setLayout(null);
       // getContentPane().setBackground(Color.white);
        //setUndecorated(true);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icons/img_4.png"));
        Image i2 = image.getImage().getScaledInstance(1000,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i1 = new JLabel(i3);
        i1.setBounds(400,0,1000,300);
        add(i1);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/img_6.png"));
        Image i5 = i4.getImage().getScaledInstance(400,300,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel i7 = new JLabel(i6);
        i7.setBounds(0,0,400,300);
        add(i7);

        questionLabel = new JLabel("");
        questionLabel.setLocation(100,300);
        questionLabel.setSize(1000,100);
        questionLabel.setFont(new Font("Arial",Font.BOLD,40));
        add(questionLabel);

        qno = new JLabel("");
        qno.setSize(100,55);
        qno.setFont(new Font("Arial",Font.BOLD,18));
        qno.setLocation(30,325);
        add(qno);




        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4,1));  // 4 options per question
        optionsPanel.setForeground(Color.WHITE);
       optionsPanel.setSize(800,200);
       optionsPanel.setLocation(75,400);
       option = new JRadioButton[4];
       ButtonGroup optionsGroup = new ButtonGroup();
        add(optionsPanel);

        for (int i = 0; i < 4; i++) {
            option[i] = new JRadioButton();
            optionsGroup.add(option[i]);
            optionsPanel.add(option[i]);

        }
        add(optionsPanel);

        next = new JButton("Next");
        next.setBackground(Color.WHITE);
        next.setFont(new Font("SAN SERIF",Font.BOLD,30));
        next.setForeground(Color.BLACK);
        next.setSize(100,70);
        next.setLocation(900,400);
        next.addActionListener(this);
        add(next);




        setSize(1400,850);
        setLocation(0,0);

        try {
            initializeQuestionPoolFromFile("src/question.txt");  // Reads questions from file
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading questions file.");
           System.exit(1);
        }

        startQuiz();
        setVisible(true);
        start();



    } String a = "Ques";
    public void start(){
        qno.setText("" + a + ".");



    }
    private void initializeQuestionPoolFromFile(String filePath) throws IOException {
        questionPool = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String questionText = parts[0];
            String[] options = new String[4];
            for (int i = 0; i < 4; i++) {
                options[i] = parts[i + 1];
            }
            try{
                int correctOption = Integer.parseInt(parts[5]); // Correct option is the last value
                questionPool.add(new Question(questionText, options, correctOption));
            } catch (Exception e) {
                e.printStackTrace();
            }


    }
        scanner.close();

    }
    // Shuffle questions and select random 10 questions to start the quiz
    private void startQuiz() {
        // Shuffle the question pool to get random questions
        Collections.shuffle(questionPool);
        // Select the first 10 questions from the shuffled pool
        selectedQuestions = questionPool.subList(0, 10); // Pick the first 10 from the shuffled pool

        currentQuestionIndex = 0;
        score = 0;
        showNextQuestion();
    }



    // Show the next question
    private void showNextQuestion() {
        if (currentQuestionIndex < selectedQuestions.size()) {
            Question question = selectedQuestions.get(currentQuestionIndex);
            questionLabel.setText(question.getQuestionText());
            questionLabel.setFont(new Font("Arial",Font.BOLD,20));



            // Set the options for the current question
            for (int i = 0; i < 4; i++) {
                option[i].setText(question.getOptions()[i]);
                option[i].setSelected(false);  // Deselect previous choices
            }

            currentQuestionIndex++;
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Over! Your Score is : " + score);
            // Restart the quiz by selecting new random 10 questions
            startQuiz();
        }
    }

    // Check the answer and calculate the score
    private void checkAnswer() {
        Question currentQuestion = selectedQuestions.get(currentQuestionIndex - 1);
        int selectedOption = -1;

        for (int i = 0; i < 4; i++) {
            if (option[i].isSelected()) {
                selectedOption = i + 1;  // Option index is 1-based
                break;
            }
        }

        if (selectedOption == currentQuestion.getCorrectOption()) {
            score++;
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new test();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == next){
            checkAnswer();
            showNextQuestion();
           start();
        }
    }
}
// Question class to hold question text, options, and correct answer
class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

