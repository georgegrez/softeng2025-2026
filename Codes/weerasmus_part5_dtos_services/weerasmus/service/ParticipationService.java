package weerasmus.service;

import weerasmus.dto.ParticipationResultDTO;
import weerasmus.repository.StudentRepository;

public class ParticipationService {
    private StudentRepository studentRepository;

    public ParticipationService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ParticipationResultDTO suspendParticipation(int studentId, int programId) {
        studentRepository.updateParticipationStatus(studentId, programId, "SUSPENDED");
        return new ParticipationResultDTO(true, "Η συμμετοχή του φοιτητή ανεστάλη επιτυχώς.");
    }
}
