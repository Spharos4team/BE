package com.spharos.ssgpoint.term.application;

import java.util.Map;

public interface TermService {
    void saveTerm(String UUID ,Map<String,Boolean> agreements);
}
