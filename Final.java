import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int id;
    private boolean present;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.present = false;
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
    private ArrayList<Student> students;
    private File file;
    private JTextField nameField;
    private JTextField idField;
    private JCheckBox presentBox;

    public AttendanceSystem() {
        super("Student Attendance System");
        students = new ArrayList<>();
        file = new File("attendance.txt");

        JPanel addPanel = new JPanel(new GridLayout(3, 2));
        addPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        addPanel.add(nameField);
        addPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        addPanel.add(idField);
        addPanel.add(new JLabel("Present:"));
        presentBox = new JCheckBox();
        addPanel.add(presentBox);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(this);

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
}
