package weerasmus.model;

import weerasmus.model.ErasmusProgram;
import weerasmus.model.Student;
import weerasmus.model.StudentEvaluation;

public class Professor extends User {
    private String responsibleDepartment;

    public Professor() {
    }

    public Professor(int userId, String firstName, String lastName, String idCardNumber,
                     String address, String phone, String email, String username,
                     String password, String verificationDocument, boolean verified,
                     String responsibleDepartment) {
        super(userId, firstName, lastName, idCardNumber, address, phone, email,
                username, password, verificationDocument, verified);
        this.responsibleDepartment = responsibleDepartment;
    }

    public String getResponsibleDepartment() {
        return responsibleDepartment;
    }

    public void setResponsibleDepartment(String responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }
	
	public void manageProgram(ErasmusProgram program) {
    if (program == null) {
        System.out.println("Δεν έχει δοθεί έγκυρο πρόγραμμα Erasmus.");
        return;
    }

    if (this.responsibleDepartment == null || this.responsibleDepartment.isBlank()) {
        System.out.println("Ο καθηγητής δεν έχει ορισμένο υπεύθυνο τμήμα.");
        return;
    }

    if (!this.responsibleDepartment.equalsIgnoreCase(program.getDepartment())) {
        System.out.println("Ο καθηγητής δεν μπορεί να διαχειριστεί πρόγραμμα άλλου τμήματος.");
        return;
    }

    System.out.println("Ο καθηγητής "
            + this.getFirstName() + " " + this.getLastName()
            + " διαχειρίζεται το πρόγραμμα Erasmus: "
            + program.getUniversityName());
}

public StudentEvaluation evaluateStudent(Student student) {
    if (student == null) {
        System.out.println("Δεν έχει δοθεί έγκυρος φοιτητής για αξιολόγηση.");
        return null;
    }

    StudentEvaluation evaluation = new StudentEvaluation();
    evaluation.setProfessorId(this.getUserId());
    evaluation.setStudentId(student.getUserId());
    evaluation.setComments("Αξιολόγηση φοιτητή από τον υπεύθυνο καθηγητή.");
    evaluation.submit();

    System.out.println("Ο καθηγητής "
            + this.getFirstName() + " " + this.getLastName()
            + " αξιολόγησε τον φοιτητή "
            + student.getFirstName() + " " + student.getLastName());

    return evaluation;
}
}
