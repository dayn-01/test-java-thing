package CCE103PROJECT;

import java.awt.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.time.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

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
	ButtonAddStudentAttendance,
	ButtonEditStudentAttendance,
	ButtonDeleteStudentAttendance;
	
	JComboBox 
	ComboStudentyear,
	ComboStudentCourse;
	
	JTable table;
	JScrollPane sc;
	DefaultTableModel des;

	int row;
	private ArrayList<Student> students;
    private File file;
    private JTextField nameField;
    private JTextField idField;
    private JCheckBox presentBox;


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
		DateNowLabel = new JLabel("AttendDate(M/D/Y):");
		DateNowLabel.setBounds(630, 100,105, 50);
		add(DateNowLabel);
		
		DateNowData = new Jlabel();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String formattedDateTime = now.format(formatter);
		DateNowData.setText(formattedDateTime);
		DateNowData.setBounds(745,110,250,30);
		add(DateNowData);
		
		//attendance status
		presentBox = new JCheckBox();
		presentBox.setBounds(50, 200, 80, 25);
		add(presentBox);

		
		//Course and Year
		
		StudentYear = new JLabel("Grade/Year Level : ");
		StudentYear.setBounds(200,150,160,80);
		add(StudentYear);

		
		StudentCourse = new JLabel("Course: ");
		StudentCourse.setBounds(450,150,160,80);
		add(StudentCourse);
		
		String [] year = {"1st year", "2nd year", "3rd year", "4th year"};
		ComboStudentyear = new JComboBox(year);
		ComboStudentyear.setBounds(310,180,100,25);
		add(ComboStudentyear);
		
		 
		String [] course = {"BS in Information Technology", "BS in Computer Science", "OTHERS"};
		ComboStudentCourse = new JComboBox(course);
		ComboStudentCourse.setBounds(450,205,200,30);
		add(ComboStudentCourse);
		
		// buttons
		ButtonAddStudentAttendance = new JButton("Add Student");
		ButtonAddStudentAttendance.addActionListener(this);
		add(ButtonAddStudentAttendance);
		
		setVisible(true);
		setLocationRelativeTo(null);
		
		JButton displayButton = new JButton("Display Attendance");
		displayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAttendance();
			}
		});
		buttonPanel.add(displayButton);

	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Final();
			
	}

	public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Student")) {
            String name = nameField.getText();
            int id = Integer.parseInt(idField.getText());
            boolean present = presentBox.isSelected();
            students.add(new Student(name, id));
            if (present) {
                markPresent(id);
            } else {
                markAbsent(id);
            }
            saveAttendance();
            JOptionPane.showMessageDialog(this, "Student added.");
            nameField.setText("");
            idField.setText("");
            presentBox.setSelected(false);
        }
    }

    public void markPresent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setPresent(true);
                break;
            }
        }
    }

    public void markAbsent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setPresent(false);
                break;
            }
        }
    }

    public void saveAttendance() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("ID, Name, Present\n");
            for (Student student : students) {
                writer.write(student.getId() + ", " + student.getName() + ", " + student.isPresent() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving attendance to file.");
            e.printStackTrace();
        }
    }
	
	public void displayAttendance() {
    try {
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        ArrayList<String> data = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            data.add(line);
        }

        bufferedReader.close();
        reader.close();

        JTable table = new JTable(data.size() - 1, 3);
        table.setValueAt("ID", 0, 0);
        table.setValueAt("Name", 0, 1);
        table.setValueAt("Present", 0, 2);
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i).split(",");
            table.setValueAt(row[0].trim(), i - 1, 0);
            table.setValueAt(row[1].trim(), i - 1, 1);
            table.setValueAt(row[2].trim(), i - 1, 2);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Attendance Data");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    } catch (IOException e) {
        System.out.println("Error reading attendance from file.");
        e.printStackTrace();
    }
}
