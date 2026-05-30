package weerasmus.app;

import java.util.Date;
import weerasmus.controller.AuthController;
import weerasmus.controller.CourseMappingController;
import weerasmus.controller.EligibilityController;
import weerasmus.controller.HousingController;
import weerasmus.controller.ProfessorController;
import weerasmus.controller.ProgramController;
import weerasmus.database.InMemoryDatabase;
import weerasmus.model.Accommodation;
import weerasmus.model.Course;
import weerasmus.model.CourseMapping;
import weerasmus.model.ErasmusProgram;
import weerasmus.model.EligibilityRule;
import weerasmus.model.Professor;
import weerasmus.model.ProgramApplication;
import weerasmus.model.Student;
import weerasmus.model.StudyLevel;
import weerasmus.repository.AccommodationRepository;
import weerasmus.repository.CourseMappingRepository;
import weerasmus.repository.CourseRepository;
import weerasmus.repository.EligibilityRuleRepository;
import weerasmus.repository.ErasmusProgramRepository;
import weerasmus.repository.HousingApplicationRepository;
import weerasmus.repository.MessageRepository;
import weerasmus.repository.ProfessorRepository;
import weerasmus.repository.ProgramApplicationRepository;
import weerasmus.repository.ProgramParticipationRepository;
import weerasmus.repository.StudentEvaluationRepository;
import weerasmus.repository.StudentRepository;
import weerasmus.repository.TranscriptRepository;
import weerasmus.service.AccommodationSearchService;
import weerasmus.service.ApplicationDecisionService;
import weerasmus.service.CourseMappingService;
import weerasmus.service.EmailService;
import weerasmus.service.EligibilityService;
import weerasmus.service.FileValidationService;
import weerasmus.service.HousingApplicationService;
import weerasmus.service.MessageService;
import weerasmus.service.ParticipationService;
import weerasmus.service.PdfExportService;
import weerasmus.service.ProgramSearchService;
import weerasmus.service.ProgramValidationService;
import weerasmus.service.StudentEvaluationService;
import weerasmus.service.TranscriptAnalysisService;
import weerasmus.service.UserValidationService;

public class AppContext {
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final ErasmusProgramRepository erasmusProgramRepository;
    private final ProgramApplicationRepository programApplicationRepository;
    private final ProgramParticipationRepository programParticipationRepository;
    private final AccommodationRepository accommodationRepository;
    private final HousingApplicationRepository housingApplicationRepository;
    private final CourseRepository courseRepository;
    private final CourseMappingRepository courseMappingRepository;
    private final TranscriptRepository transcriptRepository;
    private final EligibilityRuleRepository eligibilityRuleRepository;
    private final MessageRepository messageRepository;
    private final StudentEvaluationRepository studentEvaluationRepository;

    private final FileValidationService fileValidationService;
    private final UserValidationService userValidationService;
    private final EmailService emailService;
    private final ProgramSearchService programSearchService;
    private final ProgramValidationService programValidationService;
    private final AccommodationSearchService accommodationSearchService;
    private final HousingApplicationService housingApplicationService;
    private final TranscriptAnalysisService transcriptAnalysisService;
    private final EligibilityService eligibilityService;
    private final PdfExportService pdfExportService;
    private final CourseMappingService courseMappingService;
    private final MessageService messageService;
    private final StudentEvaluationService studentEvaluationService;
    private final ParticipationService participationService;
    private final ApplicationDecisionService applicationDecisionService;

    private final AuthController authController;
    private final ProgramController programController;
    private final HousingController housingController;
    private final EligibilityController eligibilityController;
    private final CourseMappingController courseMappingController;
    private final ProfessorController professorController;

    public AppContext() {
        studentRepository = new StudentRepository();
        professorRepository = new ProfessorRepository();
        erasmusProgramRepository = new ErasmusProgramRepository();
        programApplicationRepository = new ProgramApplicationRepository();
        programParticipationRepository = new ProgramParticipationRepository();
        accommodationRepository = new AccommodationRepository();
        housingApplicationRepository = new HousingApplicationRepository();
        courseRepository = new CourseRepository();
        courseMappingRepository = new CourseMappingRepository();
        transcriptRepository = new TranscriptRepository();
        eligibilityRuleRepository = new EligibilityRuleRepository();
        messageRepository = new MessageRepository();
        studentEvaluationRepository = new StudentEvaluationRepository();

        fileValidationService = new FileValidationService();
        userValidationService = new UserValidationService();
        emailService = new EmailService();
        programSearchService = new ProgramSearchService(erasmusProgramRepository);
        programValidationService = new ProgramValidationService();
        accommodationSearchService = new AccommodationSearchService(accommodationRepository);
        housingApplicationService = new HousingApplicationService();
        transcriptAnalysisService = new TranscriptAnalysisService(fileValidationService);
        eligibilityService = new EligibilityService();
        pdfExportService = new PdfExportService();
        courseMappingService = new CourseMappingService(courseRepository, courseMappingRepository);
        messageService = new MessageService(messageRepository);
        studentEvaluationService = new StudentEvaluationService();
        participationService = new ParticipationService(studentRepository);
        applicationDecisionService = new ApplicationDecisionService();

        authController = new AuthController(
                studentRepository,
                fileValidationService,
                userValidationService,
                emailService
        );

        programController = new ProgramController(
                erasmusProgramRepository,
                courseRepository,
                eligibilityRuleRepository,
                programSearchService,
                programValidationService
        );

        housingController = new HousingController(
                accommodationRepository,
                housingApplicationRepository,
                accommodationSearchService,
                housingApplicationService
        );

        eligibilityController = new EligibilityController(
                studentRepository,
                transcriptRepository,
                eligibilityRuleRepository,
                transcriptAnalysisService,
                eligibilityService,
                pdfExportService
        );

        courseMappingController = new CourseMappingController(
                courseMappingRepository,
                courseMappingService
        );

        professorController = new ProfessorController(
                erasmusProgramRepository,
                programApplicationRepository,
                studentRepository,
                studentEvaluationRepository,
                messageService,
                studentEvaluationService,
                participationService,
                programValidationService,
                applicationDecisionService,
                emailService
        );
    }

    public void seedSampleData() {
        if (!InMemoryDatabase.students.isEmpty()) {
            return;
        }

        Student student = new Student(
                1,
                "Γιώργος",
                "Παπαδόπουλος",
                "ΑΒ123456",
                "Πάτρα",
                "2610000000",
                "student@example.com",
                "student",
                "1234",
                "",
                true,
                "1050000",
                "01010101010",
                "Μηχανικών Η/Υ και Πληροφορικής",
                StudyLevel.UNDERGRADUATE,
                "student@upatras.gr"
        );
        student.addLanguage("English");
        student.addLanguage("Greek");
        studentRepository.save(student);

        Professor professor = new Professor(
                2,
                "Νίκος",
                "Καθηγητής",
                "ΑΑ111111",
                "Πάτρα",
                "2610000001",
                "professor@example.com",
                "professor",
                "1234",
                "",
                true,
                "Μηχανικών Η/Υ και Πληροφορικής"
        );
        professorRepository.save(professor);

        Course homeCourse = new Course(
                1,
                "CEID101",
                "Αντικειμενοστρεφής Προγραμματισμός",
                6,
                "Βασικές αρχές OOP",
                "Μηχανικών Η/Υ και Πληροφορικής",
                "0-10"
        );

        Course hostCourse = new Course(
                2,
                "CS201",
                "Object Oriented Programming",
                6,
                "OOP course",
                "Computer Science",
                "0-100"
        );

        courseRepository.save(homeCourse);
        courseRepository.save(hostCourse);

        CourseMapping mapping = new CourseMapping(1, homeCourse, hostCourse, "Αντίστοιχα μαθήματα OOP");
        courseMappingRepository.save(mapping);

        ErasmusProgram program = new ErasmusProgram(
                1,
                "University of Barcelona",
                "Computer Science",
                "Spain",
                6,
                "Spring 2026",
                new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 120),
                5,
                "English B2, min 120 ECTS",
                "Πρόγραμμα Erasmus στο τμήμα Computer Science.",
                StudyLevel.UNDERGRADUATE,
                700.0,
                450.0,
                50.0
        );
        program.addRequiredLanguage("English");
        program.addCourse(hostCourse);
        erasmusProgramRepository.save(program);

        Accommodation accommodation = new Accommodation();
        accommodation.setAccommodationId(1);
        accommodation.setTitle("Student Residence Barcelona");
        accommodation.setType("Εστία");
        accommodation.setArea("Barcelona Center");
        accommodation.setMonthlyCost(400.0);
        accommodation.setRentalPeriod("Spring 2026");
        accommodation.setPaymentMethod("Δόσεις");
        accommodation.setDistanceFromUniversity(2.5);
        accommodation.setCohabitation(true);
        accommodation.setHostInfo("University Housing Office");
        accommodation.setAvailabilityStatus("AVAILABLE");
        accommodationRepository.save(accommodation);

        EligibilityRule rule = new EligibilityRule(
                1,
                120,
                5,
                StudyLevel.UNDERGRADUATE,
                "Απαιτείται καλή ακαδημαϊκή επίδοση."
        );
        rule.addRequiredLanguage("English");
        eligibilityRuleRepository.save(rule);

        ProgramApplication application = new ProgramApplication(
                1,
                student.getUserId(),
                program.getProgramId(),
                "Θέλω να συμμετάσχω στο πρόγραμμα Erasmus."
        );
        programApplicationRepository.save(application);
    }

    public AuthController getAuthController() {
        return authController;
    }

    public ProgramController getProgramController() {
        return programController;
    }

    public HousingController getHousingController() {
        return housingController;
    }

    public EligibilityController getEligibilityController() {
        return eligibilityController;
    }

    public CourseMappingController getCourseMappingController() {
        return courseMappingController;
    }

    public ProfessorController getProfessorController() {
        return professorController;
    }
}
