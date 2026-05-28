package weerasmus.service;

import java.util.ArrayList;
import weerasmus.dto.ProgramSearchCriteria;
import weerasmus.dto.ProgramSearchResultDTO;
import weerasmus.model.ErasmusProgram;
import weerasmus.repository.ErasmusProgramRepository;

public class ProgramSearchService {
    private ErasmusProgramRepository erasmusProgramRepository;

    public ProgramSearchService(ErasmusProgramRepository erasmusProgramRepository) {
        this.erasmusProgramRepository = erasmusProgramRepository;
    }

    public ProgramSearchCriteria buildSearchCriteria(int studentId, String text, ProgramSearchCriteria filters) {
        if (filters == null) {
            filters = new ProgramSearchCriteria();
        }
        filters.setStudentId(studentId);
        filters.setText(text);
        return filters;
    }

    public ProgramSearchResultDTO searchByCriteria(ProgramSearchCriteria criteria) {
        ArrayList<ErasmusProgram> results = erasmusProgramRepository.findAvailableByCriteria(criteria);
        String message = results.isEmpty() ? "Δεν βρέθηκαν προγράμματα." : "Βρέθηκαν διαθέσιμα προγράμματα.";
        return new ProgramSearchResultDTO(results, message);
    }
}
