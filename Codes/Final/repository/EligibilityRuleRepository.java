package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.EligibilityRule;
import weerasmus.model.StudyLevel;

public class EligibilityRuleRepository {

    public ArrayList<EligibilityRule> findAll() {
        return new ArrayList<>(InMemoryDatabase.eligibilityRules);
    }

    public EligibilityRule findById(int ruleId) {
        for (EligibilityRule rule : InMemoryDatabase.eligibilityRules) {
            if (rule.getRuleId() == ruleId) {
                return rule;
            }
        }
        return null;
    }

    public ArrayList<EligibilityRule> findByDepartmentAndStudyLevel(String department, StudyLevel studyLevel) {
        ArrayList<EligibilityRule> results = new ArrayList<>();

        for (EligibilityRule rule : InMemoryDatabase.eligibilityRules) {
            if (rule.getRequiredStudyLevel() == null || rule.getRequiredStudyLevel() == studyLevel) {
                results.add(rule);
            }
        }

        return results;
    }

    public EligibilityRule save(EligibilityRule rule) {
        if (rule == null) {
            return null;
        }

        if (rule.getRuleId() == 0) {
            rule.setRuleId(InMemoryDatabase.generateEligibilityRuleId());
            InMemoryDatabase.eligibilityRules.add(rule);
            return rule;
        }

        EligibilityRule existing = findById(rule.getRuleId());
        if (existing == null) {
            InMemoryDatabase.eligibilityRules.add(rule);
        }

        return rule;
    }

    public ArrayList<EligibilityRule> saveAll(ArrayList<EligibilityRule> rules) {
        ArrayList<EligibilityRule> saved = new ArrayList<>();

        if (rules == null) {
            return saved;
        }

        for (EligibilityRule rule : rules) {
            saved.add(save(rule));
        }

        return saved;
    }
}
