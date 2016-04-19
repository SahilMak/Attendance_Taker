import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class Attendance extends JFrame implements ActionListener {
  JTextArea list = new JTextArea();
  JTextField name;
  JTextField email;
  JButton add = new JButton("Add");
  JButton done = new JButton("Done");
  JPanel panel = new JPanel();
  JPanel info = new JPanel();
  JPanel labels = new JPanel();
  JPanel fields;
  PrintWriter output;
  String date;
  String action = "";
  
  public static void main(String[] args) {
    JFrame frame = new BrownBoss();
    frame.setSize(800, 600);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
