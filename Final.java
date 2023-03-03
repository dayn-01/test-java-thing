
package CCE103PROJECT;

import java.awt.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.time.*

//create student object
class Student {
    private String name;
    private int id;
    private boolean present;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.present = true;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}


public class Final extends JFrame implements ActionListener {
	
	JLabel
	AppTitle
	StudentName, 
	StudentID,
	DateNowLabel, 
	DateNowData,
	AttendanceStatus,
	StudentGrade,
	StudentCourse

	JTextField 
	FieldStudentName, 
	FieldStudentID,
	
	bf1,bf2, bf3, yf;
	JPanel UIPanel;
	JButton add, display, edit, delete;
	
	JTextArea snr,idr, adr, yr, asr, cr;
	JRadioButton pr, ab;
	JCheckBox ylc1, ylc2, ylc3, ylc4;
	JComboBox c;
	JTable table;
	JScrollPane sc;
	DefaultTableModel des;
	ArrayList<Student> studentList;
	int row;


	public Final() {	
		
		//app title
		setTitle("Student Attendance Monitoring System");
		JLabel label = new JLabel("");
		add(label);	
		
		//adding label info
		//app title label
		JLabel AppTitle = new JLabel("Student Attendance Monitoring System");
		AppTitle.setFont(new Font("Arial Black ", Font.BOLD, 20));
		AppTitle.setBounds(320,10,500,80);
		setSize(1000,800);	
		getContentPane().setBackground(Color.PINK);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 
		add(AppTitle);
		
		//panel
		UIPanel = new JPanel();
		add(UIPanel);
		setLayout(null);
		
		//Student Name
		StudentName = new JLabel("Enter Student's Full Name :");
		StudentName.setBounds(50,100,160,50);
		add(StudentName);
		
		FieldStudentName = new JTextField(20);
		FieldStudentName.setBounds(210,110,200,30);
		add(FieldStudentName);
		
		//Student ID
		StudentID = new JLabel("ID No :");
		StudentID.setBounds(450,100,100,50);
		add(StudentID);
		 
		FieldStudentID = new JTextField(20);
		FieldStudentID.setBounds(490,110,100,30);
		add(FieldStudentID);
		 
		//Attendance Date Now
		DateNow = new JLabel("AttendDate(M/D/Y):");
		DateNow.setBounds(630, 100,105, 50);
		add(DateNow);
		
		DateNowDate = new Jlabel();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String formattedDateTime = now.format(formatter);
		DateNowData.setText(formattedDateTime);
		DateNowData.setBounds(745,110,30,30);
		add(DateNowData);
		
		
		 as = new JLabel("Attendance Status");
		 as.setBounds(50, 150,160, 80);
		 add(as);
		 
		 pr = new JRadioButton("Present");
		 pr.setBackground(Color.PINK);
		 pr.setBounds(50, 200, 80, 25);
		 add(pr);
		 
		 ab = new JRadioButton("Absent");
		 ab.setBackground(Color.PINK);
		 ab.setBounds(50, 220, 100, 25);
		 add(ab);
		 
		 asr = new JTextArea();
		 asr.setBounds(473,321,142,377);
		 add(asr);
		 
		 
		 yl = new JLabel("Grade/Year Level : ");
		 yl.setBounds(200,150,160,80);
		 add(yl);
		 
		 yr = new JTextArea();
		 yr.setBounds(615,321,142,377);
		 add(yr);
		 
		 ylc1 = new JCheckBox("1st Year");
		 ylc1.setBackground(Color.PINK);
		 ylc1.setBounds(310,180,100,25);
	     add(ylc1);
			
		 ylc2 = new JCheckBox("2nd Year");
		 ylc2.setBackground(Color.PINK);
		 ylc2.setBounds(310,200,100,25);
	     add(ylc2);
			
		 ylc3 = new JCheckBox("3rd Year");
		 ylc3.setBackground(Color.PINK);
		 ylc3.setBounds(310,220,100,25);
		 add(ylc3);
				 
		 ylc4 = new JCheckBox("4th Year");
		 ylc4.setBackground(Color.PINK);
		 ylc4.setBounds(310,240,100,25);
		 add(ylc4);
		 
		 bc = new JLabel("Course: ");
		 bc.setBounds(450,150,160,80);
		 add(bc);
		 
		 cr = new JTextArea();		 
		 cr.setBounds(754,321,142,377);
		 add(cr);
		 
		 String [] course = {"BS in Information Technology", "BS in Computer Science", "OTHERS"};
		 c = new JComboBox(course);
		 c.setBounds(450,205,200,30);
		 add(c);
		 	 
		 
		 add = new JButton("Add");
		 add.setBackground(Color.WHITE);
		 add.setFont(new Font("Arial Black ", Font.BOLD,15 ));
		 add.setBounds(720,190,100,30);
		 add(add);
		 
		 display = new JButton("Display");
		 display.setBackground(Color.WHITE);
		 display.setFont(new Font("Arial Black ", Font.BOLD,15 ));
		 display.setBounds(720,230,100,30);
		 add(display);
		 
		 
		 table = new JTable();
		 des = new DefaultTableModel();
		 Object[] columns = {"Student Name","Student ID","Attend Date","Attendance Status","YearLevel","Course"};
		 Object[] row = new Object[5];
		 des.setColumnIdentifiers(columns);
		 table.setModel(des);
		 
		 JScrollPane sc = new JScrollPane(table);
		 sc.setBounds(50,300,850,400);
		 add(sc);

		 
		 
		 setVisible(true);
		 setLocationRelativeTo(null);
		 
		 //add gui thingy
		 
		 
		
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Final();
		
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	/*	{
			 ActionListener listener = new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
					 
				 }*/
		}
	}
