
package CCE103PROJECT;

import java.awt.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.time.*

//create student object
class StudentInfo {
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
	AppTitle,
	StudentName, 
	StudentID,
	DateNowLabel, 
	DateNowData,
	AttendanceStatus,
	StudentCourse,
	StudentYear;

	JTextField 
	FieldStudentName, 
	FieldStudentID;
	
	JPanel UIPanel;
	
	JButton 
	ButtonUpdateTable, 
	ButtonEditStudentAttendance,
	ButtonDeleteStudentAttendance;
	
	JRadioButton 
	RadioStudentPresent, 
	RadioStudentAbsent;
	
	JComboBox 
	ComboStudentyear,
	ComboStudentCourse;
	
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
		DateNowData.setBounds(745,110,250,30);
		add(DateNowData);
		
		//attendance status
		RadioStudentPresent = new JRadioButton("Present");
		RadioStudentPresent.setBounds(50, 200, 80, 25);
		add(RadioStudentPresent);
		
		RadioStudentAbsent = new JRadioButton("Absent");
		RadioStudentAbsent.setBounds(50, 220, 100, 25);
		add(RadioStudentAbsent);
		
		//Course and Year
		
		StudentYear = new JLabel("Grade/Year Level : ");
		StudentYear.setBounds(200,150,160,80);
		add(StudentYear);

		
		StudentCourse = new JLabel("Course: ");
		StudentCourse.setBounds(450,150,160,80);
		add(StudentCourse);
		
		String [] course = {"1st year", "2nd year", "3rd year", "4th year"};
		ComboStudentyear = new JComboBox(course);
		ComboStudentyear.setBounds(310,180,100,25);
		add(ComboStudentyear);
		
		 
		String [] course = {"BS in Information Technology", "BS in Computer Science", "OTHERS"};
		ComboStudentCourse = new JComboBox(course);
		ComboStudentCourse.setBounds(450,205,200,30);
		add(ComboStudentCourse);
		 
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
