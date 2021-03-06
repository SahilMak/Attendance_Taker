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
  
  /**
   * Constructor
   */
  
  public Attendance() {
    // Set window title
    setTitle("Attendance");
    
    // Specify layout
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    
    // Add components
    panel.add(list);
    panel.add(add);
    panel.add(done);
    add(panel);
    
    // Add action listeners
    add.addActionListener(this);
    done.addActionListener(this);
    
    // Center panel components
    list.setAlignmentX(CENTER_ALIGNMENT);
    add.setAlignmentX(CENTER_ALIGNMENT);
    done.setAlignmentX(CENTER_ALIGNMENT);
    
    // Prompt for date
    date = (String) JOptionPane.showInputDialog(null, "Enter today's date", "Date", JOptionPane.INFORMATION_MESSAGE, null, null, "mm_dd_yy");
    
    // Set the tab size
    list.setTabSize(4);
    
    // Remove focus
    list.setFocusable(false);
    
    // Create file
    try {
      // Choose directory where file will be saved
      output = new PrintWriter("C:" + File.separator + "Users" + File.separator + "Name" + File.separator +
      "Downloads" + File.separator + "Attendance" + date + ".txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    action = e.getActionCommand();
    
    if("Add".equals(action)) {
      // Create new window
      info = new JPanel(new BorderLayout(5, 5));
      
      // Add labels
      labels = new JPanel(new GridLayout(0, 1, 2, 2));
      labels.add(new JLabel("Name", SwingConstants.RIGHT));
      labels.add(new JLabel("Email", SwingConstants.RIGHT));
      info.add(labels, BorderLayout.WEST);
      
      // Add text fields
      fields = new JPanel(new GridLayout(0, 1, 2, 2));
      name = new JTextField();
      email = new JTextField();
      name.addAncestorListener(new RequestFocusListener(false));
      fields.add(name);
      fields.add(email);
      info.add(fields, BorderLayout.CENTER);
      
      // Display the window
      JOptionPane.showMessageDialog(this, info, "Enter Info", JOptionPane.QUESTION_MESSAGE);
      
      // Print to file
      output.println(name.getText() + "\t\t" + email.getText());
      
      // Display in text field
      list.append("\t\t\t\t\t\t" + name.getText() + "\t\t" + email.getText() + "\n");
    }
    else if("Done".equals(action)) {
      output.close();
      this.dispose();
    }
  }
  
  class RequestFocusListener implements AncestorListener {
    private boolean removeListener;
    
    /**
     * Constructor
     */
     
     public RequestFocusListener() {
       this(true);
     }
     
     /**
      * Constructor
      */
    
    public RequestFocusListener(boolean removeListener) {
      this.removeListener = removeListener;
    }
    
    @Override
    public void ancestorAdded(AncestorEvent arg0) {
      JComponent component = arg0.getComponent();
      component.requestFocusInWindow();
      
      if (removeListener)
        component.removeAncestorLstener(this);
    }
    
    @Override
    public void ancestorMoved(AncestorEvent arg0) {
    }
    
    @Override
    public void ancestorRemoved(AncestorEvent arg0) {
    }
  }
}
