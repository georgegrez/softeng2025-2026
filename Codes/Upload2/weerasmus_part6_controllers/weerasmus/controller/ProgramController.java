package weerasmus.controller;

import java.util.ArrayList;
import weerasmus.dto.ErasmusProgramDTO;
import weerasmus.dto.ProgramBasicInfoDTO;
import weerasmus.dto.ProgramSearchCriteria;
import weerasmus.dto.ProgramSearchResultDTO;
import weerasmus.dto.ProgramTimeInfoDTO;
import weerasmus.dto.ValidationResult;
import weerasmus.model.Course;
import weerasmus.model.ErasmusProgram;
import weerasmus.repository.CourseRepository;
import weerasmus.repository.EligibilityRuleRepository;
import weerasmus.repository.ErasmusProgramRepository;
import weerasmus.service.ProgramSearchService;
import weerasmus.service.ProgramValidationService;
import weerasmus.ui.CreateProgramFrame;
import weerasmus.ui.ManageProgramsFrame;
import weerasmus.ui.ProgramDetailsFrame;
import weerasmus.ui.ProgramSearchFrame;

public class ProgramController {
    private final ErasmusProgramRepository erasmusProgramRepository;
    private final CourseRepository courseRepository;
    private final EligibilityRuleRepository eligibilityRuleRepository;
    private final ProgramSearchService programSearchService;
    private final ProgramValidationService programValidationService;

    public ProgramController(ErasmusProgramRepository erasmusProgramRepository,
                             CourseRepository courseRepository,
                             EligibilityRuleRepository eligibilityRuleRepository,
                             ProgramSearchService programSearchService,
                             ProgramValidationService programValidationService) {
        this.erasmusProgramRepository = erasmusProgramRepository;
        this.courseRepository = courseRepository;
        this.eligibilityRuleRepository = eligibilityRuleRepository;
        this.programSearchService = programSearchService;
        this.programValidationService = programValidationService;
    }

    public void openProgramSearchFrame(int studentId) {
        ProgramSearchFrame frame = new ProgramSearchFrame(this, studentId);
        frame.showFrame(studentId);
    }

    public ProgramSearchResultDTO searchPrograms(int studentId, String text, ProgramSearchCriteria filters) {
        ProgramSearchCriteria criteria = programSearchService.buildSearchCriteria(studentId, text, filters);
        return programSearchService.searchByCriteria(criteria);
    }

    public void openProgramDetailsFrame(int programId) {
        ErasmusProgram program = erasmusProgramRepository.findById(programId);
        if (program != null) {
            ProgramDetailsFrame frame = new ProgramDetailsFrame(this);
            frame.showFrame(toDTO(program));
        }
    }

    public void openCreateProgramFrame(int professorId) {
        CreateProgramFrame frame = new CreateProgramFrame(this, professorId);
        frame.showFrame(professorId);
    }

    public ValidationResult validateBasicProgramInfo(ProgramBasicInfoDTO basicInfo) {
        return programValidationService.validateBasicInfo(basicInfo);
    }

    public ValidationResult validateProgramTimeInfo(ProgramTimeInfoDTO timeInfo) {
        return programValidationService.validateTimeInfo(timeInfo);
    }

    public ValidationResult createProgram(int professorId, ErasmusProgramDTO programData) {
        ValidationResult result = programValidationService.validateProgramData(programData);
        if (!result.isValid()) {
            return result;
        }

        ErasmusProgram program = fromDTO(programData);
        erasmusProgramRepository.save(program);

        ArrayList<Course> courses = programData.getAvailableCourses();
        if (courses != null && !courses.isEmpty()) {
            courseRepository.saveAll(courses);
        }

        ManageProgramsFrame frame = new ManageProgramsFrame(null, professorId);
        frame.showFrame(professorId);

        return ValidationResult.valid();
    }

    private ErasmusProgram fromDTO(ErasmusProgramDTO dto) {
        ErasmusProgram program = new ErasmusProgram();
        program.setProgramId(dto.getProgramId());
        program.setUniversityName(dto.getUniversityName());
        program.setDepartment(dto.getDepartment());
        program.setCountry(dto.getCountry());
        program.setDuration(dto.getDuration());
        program.setPeriod(dto.getPeriod());
        program.setApplicationDeadline(dto.getApplicationDeadline());
        program.setAvailablePositions(dto.getAvailablePositions());
        program.setRequirements(dto.getRequirements());
        program.setUniversityDescription(dto.getUniversityDescription());
        program.setStudyLevel(dto.getStudyLevel());
        program.setEstimatedLivingCost(dto.getEstimatedLivingCost());
        program.setEstimatedHousingCost(dto.getEstimatedHousingCost());
        program.setEstimatedTransportCost(dto.getEstimatedTransportCost());
        program.setAvailableCourses(dto.getAvailableCourses());
        return program;
    }

    private ErasmusProgramDTO toDTO(ErasmusProgram program) {
        ErasmusProgramDTO dto = new ErasmusProgramDTO();
        dto.setProgramId(program.getProgramId());
        dto.setUniversityName(program.getUniversityName());
        dto.setDepartment(program.getDepartment());
        dto.setCountry(program.getCountry());
        dto.setDuration(program.getDuration());
        dto.setPeriod(program.getPeriod());
        dto.setApplicationDeadline(program.getApplicationDeadline());
        dto.setAvailablePositions(program.getAvailablePositions());
        dto.setRequirements(program.getRequirements());
        dto.setUniversityDescription(program.getUniversityDescription());
        dto.setStudyLevel(program.getStudyLevel());
        dto.setEstimatedLivingCost(program.getEstimatedLivingCost());
        dto.setEstimatedHousingCost(program.getEstimatedHousingCost());
        dto.setEstimatedTransportCost(program.getEstimatedTransportCost());
        dto.setAvailableCourses(program.getAvailableCourses());
        return dto;
    }
}
