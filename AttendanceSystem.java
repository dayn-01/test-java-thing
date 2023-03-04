package CCE103PROJECT;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.time.format.DateTimeFormatter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Student {
	private String year;
	private String course;
    	private String name;
    	private int id;
    	private boolean present;

    public Student(String name, int id,String course, String year) {
        this.name = name;
        this.id = id;
        this.present = false;
        this.course = course;
        this.year = year;
    }
    public String getyear() {
    	return year;
    	
    } 	
    public String getCourse() {
    	return course;
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

public class AttendanceSystem extends JFrame implements ActionListener {
	
	private JLabel
	AppTitle,
	StudentName, 
	StudentID,
	DateNowLabel, 
	DateNowData,
	AttendanceStatus,
	StudentCourse,
	StudentYear;

	private JTextField 
	FieldStudentName, 
	FieldStudentID;
	
	private JPanel addPanel;
	
	private JButton 
	ButtonUpdateTable, 
	ButtonAddStudentAttendance,
	ButtonEditStudentAttendance,
	ButtonDeleteStudentAttendance;

	private JComboBox<String> ComboStudentyear, ComboStudentCourse;
    	private ArrayList<Student> students;
   	private File file;
    	private JCheckBox presentBox,absentBox;

    public AttendanceSystem() {
        super("Student Attendance System");
        students = new ArrayList<>();
        file = new File("attendance.txt");
		
		//app title
		setTitle("Student Attendance Monitoring System");
		JLabel label = new JLabel("");
		add(label);	
		
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
		addPanel = new JPanel();
		add(addPanel);
		setLayout(null);
		
		
		//name
		StudentName = new JLabel("Enter Student's Full Name :");
		StudentName.setBounds(50,100,160,50);
		addPanel.add(StudentName);
		
		FieldStudentName = new JTextField(20);
		FieldStudentName.setBounds(210,110,200,30);
		addPanel.add(FieldStudentName);
	
        //id
		StudentID = new JLabel("ID No :");
		StudentID.setBounds(450,100,100,50);
		addPanel.add(StudentID);
		 
		FieldStudentID = new JTextField(20);
		FieldStudentID.setBounds(490,110,100,30);
		addPanel.add(FieldStudentID);	
		
		//date now
		DateNowLabel = new JLabel("AttendDate(M/D/Y):");
		DateNowLabel.setBounds(630, 100,105, 50);
		addPanel.add(DateNowLabel);
		
		DateNowData = new JLabel();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String formattedDateTime = now.format(formatter);
		DateNowData.setText(formattedDateTime);
		DateNowData.setBounds(745,110,250,30);
		addPanel.add(DateNowData);
		
        //year
		StudentYear = new JLabel("Grade/Year Level : ");
		StudentYear.setBounds(200,150,160,80);
		addPanel.add(StudentYear);
		
		ComboStudentyear = new JComboBox<>(new String[]{"1st year", "2nd year", "3rd year", "4th year"});
		ComboStudentyear.setBounds(310,180,100,25);
		addPanel.add(ComboStudentyear);
		
        //course
		StudentCourse = new JLabel("Course: ");
		StudentCourse.setBounds(450,150,160,80);
		addPanel.add(StudentCourse);
		
		ComboStudentCourse = new JComboBox<>(new String[]{"BS in Information Technology", "BS in Computer Science", "OTHERS"});
		ComboStudentCourse.setBounds(450,205,200,30);
		addPanel.add(ComboStudentCourse);
		
		//present
        addPanel.add(new JLabel("Present:"));
        presentBox = new JCheckBox();
        addPanel.add(presentBox);
        //absent
        addPanel.add(new JLabel("Absent:"));
        absentBox = new JCheckBox();
        addPanel.add(absentBox);
		        

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(this);
		
		

		//for showing things 
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(addPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton displayButton = new JButton("Display Attendance");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAttendance();
            }
        });
        buttonPanel.add(displayButton);
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Student")) {
            String name = FieldStudentName.getText();
            String course = (String) StudentCourse.getSelectedItem();
            String year = (String) ComboStudentyear.getSelectedItem();
            int id = Integer.parseInt(FieldStudentID.getText());
            boolean present = presentBox.isSelected();
            students.add(new Student(name, id, course, year));
            if (present) {
                markPresent(id);
            } else {
                markAbsent(id);
            }
            saveAttendance();
            JOptionPane.showMessageDialog(this, "Student added.");
            FieldStudentName.setText("");
            FieldStudentID.setText("");
            presentBox.setSelected(false);
        }
    }

    public void markPresent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
            	System.out.println("Present");
                student.setPresent(true);
                
                break;
            }
        }
    }

    public void markAbsent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
            	System.out.println("Absent");
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
                writer.write(student.getId() + ", " + student.getName() + ", " + student.isPresent()+ ", " + student.getyear()+ ", "+student.getCourse()+"\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving attendance to file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AttendanceSystem();
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

            String[] columns = { "ID", "Name", "Attendance Status","Year" ,"Course"};
            String[][] rows = new String[data.size() - 1][columns.length];
			String isAbsent = "Present";
			
            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i).split(",");
                rows[i - 1][0] = row[0].trim();
                rows[i - 1][1] = row[1].trim();
				if(row[2].trim().equals("false")) {
					isAbsent = "Absent";
				}
                rows[i - 1][2] = isAbsent;
                rows[i - 1][3] = row[3].trim();
                rows[i - 1][4] = row[4].trim();
            }

            JTable table = new JTable(rows, columns);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(200);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);

            table.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    TableModel model = (TableModel) e.getSource();
                    String columnName = model.getColumnName(column);
                    Object data = model.getValueAt(row, column);

                    // update data in the file
                    try {
                        FileReader reader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(reader);

                        ArrayList<String> newData = new ArrayList<>();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.startsWith(rows[row][0])) {
                                // update the line with the new data
                                String[] parts = line.split(",");
                                parts[column] = data.toString();
                                line = String.join(",", parts);
                            }
                            newData.add(line);
                        }

                        bufferedReader.close();
                        reader.close();

                        FileWriter writer = new FileWriter(file);
                        for (String newDataLine : newData) {
                            writer.write(newDataLine + "\n");
                        }
                        writer.close();

                    } catch (IOException ex) {
                        System.out.println("Error updating attendance data.");
                        ex.printStackTrace();
                    }
                }
            });

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

}
