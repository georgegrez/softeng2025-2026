package weerasmus.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import weerasmus.controller.ProfessorController;
import weerasmus.model.Student;

public class ProgramProgressFrame extends JFrame {
    private final ProfessorController professorController;
    private final int professorId;

    private JTextField programIdField;
    private JTable studentsTable;
    private DefaultTableModel tableModel;

    public ProgramProgressFrame(ProfessorController professorController, int professorId) {
        this.professorController = professorController;
        this.professorId = professorId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Παρακολούθηση Πορείας Προγράμματος");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new GridLayout(1, 4, 8, 8));
        programIdField = new JTextField();
        JButton loadButton = new JButton("Φόρτωση φοιτητών");
        JButton openStudentButton = new JButton("Προβολή φοιτητή");

        top.add(new JLabel("Program ID:"));
        top.add(programIdField);
        top.add(loadButton);
        top.add(openStudentButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Όνομα", "Επώνυμο", "Τμήμα"}, 0);
        studentsTable = new JTable(tableModel);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(studentsTable), BorderLayout.CENTER);

        loadButton.addActionListener(e -> selectProgram(parseProgramId()));
        openStudentButton.addActionListener(e -> openSelectedStudent());
    }

    private int parseProgramId() {
        try {
            return Integer.parseInt(programIdField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void showFrame(int professorId) {
        setVisible(true);
    }

    public void selectProgram(int programId) {
        ArrayList<Student> students = professorController.getActiveStudentsForProgram(professorId, programId);
        if (students == null || students.isEmpty()) {
            showNoActiveStudentsMessage();
        } else {
            refreshActiveStudentsTable(students);
        }
    }

    public void refreshActiveStudentsTable(ArrayList<Student> students) {
        tableModel.setRowCount(0);

        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getUserId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getDepartment()
            });
        }
    }

    private void openSelectedStudent() {
        int row = studentsTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Επιλέξτε φοιτητή.");
            return;
        }

        int studentId = (int) tableModel.getValueAt(row, 0);
        selectStudent(studentId);
    }

    public void selectStudent(int studentId) {
        professorController.openStudentProgressFrame(parseProgramId(), studentId);
    }

    public void showNoActiveStudentsMessage() {
        JOptionPane.showMessageDialog(this,
                "Δεν υπάρχουν ενεργοί φοιτητές για το πρόγραμμα.",
                "Πληροφορία",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void showStudentProgressNotFoundMessage() {
        JOptionPane.showMessageDialog(this,
                "Δεν υπάρχουν διαθέσιμες πληροφορίες για τον φοιτητή.",
                "Πληροφορία",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
