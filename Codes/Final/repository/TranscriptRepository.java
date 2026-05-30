package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.Transcript;

public class TranscriptRepository {

    public ArrayList<Transcript> findAll() {
        return new ArrayList<>(InMemoryDatabase.transcripts);
    }

    public Transcript findById(int transcriptId) {
        for (Transcript transcript : InMemoryDatabase.transcripts) {
            if (transcript.getTranscriptId() == transcriptId) {
                return transcript;
            }
        }
        return null;
    }

    public Transcript findByStudent(int studentId) {
        for (Transcript transcript : InMemoryDatabase.transcripts) {
            if (transcript.getStudentId() == studentId) {
                return transcript;
            }
        }
        return null;
    }

    public Transcript save(Transcript transcript) {
        if (transcript == null) {
            return null;
        }

        if (transcript.getTranscriptId() == 0) {
            transcript.setTranscriptId(InMemoryDatabase.generateTranscriptId());
            InMemoryDatabase.transcripts.add(transcript);
            return transcript;
        }

        Transcript existing = findById(transcript.getTranscriptId());
        if (existing == null) {
            InMemoryDatabase.transcripts.add(transcript);
        }

        return transcript;
    }
}
