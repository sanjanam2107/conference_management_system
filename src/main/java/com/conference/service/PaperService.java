package com.conference.service;

import com.conference.model.Paper;
import java.util.List;

public interface PaperService {
    Paper submitPaper(Paper paper, String username, Long conferenceId);
    List<Paper> getPapersByAuthor(String username);
    List<Paper> getAllSubmittedPapers();
    Paper getPaperById(Long id);
    void submitReview(Long paperId, String reviewerUsername, String comments, int score);
    List<Paper> getPapersByReviewer(String username);
    List<Paper> getPapersAssignedToReviewer(String username);
    Paper savePaper(Paper paper);
}