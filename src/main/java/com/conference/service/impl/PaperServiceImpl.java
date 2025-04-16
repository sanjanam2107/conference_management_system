package com.conference.service.impl;

import com.conference.model.*;
import com.conference.model.enums.PaperState;
import com.conference.repository.ConferenceRepository;
import com.conference.repository.PaperRepository;
import com.conference.repository.UserRepository;
import com.conference.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService {
    
    private final PaperRepository paperRepository;
    private final UserRepository userRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    @Transactional
    public Paper submitPaper(Paper paper, String username, Long conferenceId) {
        User author = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        Conference conference = conferenceRepository.findById(conferenceId)
            .orElseThrow(() -> new RuntimeException("Conference not found"));
            
        paper.setConference(conference);
        paper.setState(PaperState.SUBMITTED);
        paper.setSubmissionDate(new Date());
        paper.setVersion(1);
        
        Paper savedPaper = paperRepository.save(paper);
        
        Author authorEntity = new Author();
        authorEntity.setUser(author);
        authorEntity.setPaper(savedPaper);
        authorEntity.setCorrespondingAuthor(true);
        savedPaper.getAuthors().add(authorEntity);
        
        return paperRepository.save(savedPaper);
    }

    @Override
    public Paper getPaperById(Long id) {
        return paperRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paper not found"));
    }

    @Override
    public List<Paper> getPapersByAuthor(String username) {
        return paperRepository.findByAuthors_User_Username(username);
    }

    @Override
    public List<Paper> getPapersByReviewer(String username) {
        return paperRepository.findByReviewers_User_Username(username);
    }

    @Override
    public List<Paper> getUnassignedPapers() {
        return paperRepository.findByStateAndReviewersEmpty(PaperState.SUBMITTED);
    }

    @Override
    @Transactional
    public void assignReviewer(Long paperId, Long reviewerId) {
        Paper paper = getPaperById(paperId);
        User reviewer = userRepository.findById(reviewerId)
            .orElseThrow(() -> new RuntimeException("Reviewer not found"));
            
        Reviewer reviewerEntity = new Reviewer();
        reviewerEntity.setUser(reviewer);
        reviewerEntity.setPaper(paper);
        
        paper.getReviewers().add(reviewerEntity);
        paper.setState(PaperState.UNDER_REVIEW);
        
        paperRepository.save(paper);
    }

    @Override
    @Transactional
    public void submitReview(Long paperId, String reviewerUsername, String comments, int score) {
        Paper paper = getPaperById(paperId);
        User reviewer = userRepository.findByUsername(reviewerUsername)
            .orElseThrow(() -> new RuntimeException("Reviewer not found"));

        Review review = new Review();
        review.setPaper(paper);
        review.setReviewer(reviewer);
        review.setComments(comments);
        review.setScore(score);
        review.setSubmissionDate(new Date());

        paper.getReviews().add(review);
        
        if (paper.getReviews().size() == paper.getReviewers().size()) {
            paper.setState(PaperState.REVIEWED);
        }
        
        paperRepository.save(paper);
    }
}