package com.conference.service.impl;

import com.conference.model.*;
import com.conference.model.enums.PaperState;
import com.conference.repository.ConferenceRepository;
import com.conference.repository.PaperRepository;
import com.conference.repository.UserRepository;
import com.conference.service.PaperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.HashSet;

@Slf4j
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
        
        Author authorEntity = new Author();
        authorEntity.setUser(author);
        authorEntity.setPaper(paper);
        
        if (paper.getAuthors() == null) {
            paper.setAuthors(new HashSet<>());
        }
        paper.getAuthors().add(authorEntity);
        
        Paper savedPaper = paperRepository.save(paper);
        log.info("Paper submitted successfully: {}", savedPaper.getId());
        return savedPaper;
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
    public List<Paper> getAllSubmittedPapers() {
        List<Paper> papers = paperRepository.findAll(); // Get all papers first
        log.info("Found {} total papers", papers.size());
        List<Paper> submittedPapers = paperRepository.findByState(PaperState.SUBMITTED);
        log.info("Found {} papers in SUBMITTED state", submittedPapers.size());
        return submittedPapers;
    }

    @Override
    public List<Paper> getPapersByReviewer(String username) {
        User reviewer = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Reviewer not found"));
            
        return paperRepository.findByReviews_Reviewer_Username(username);
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

        if (paper.getReviews() == null) {
            paper.setReviews(new HashSet<>());
        }
        paper.getReviews().add(review);
        paper.setState(PaperState.UNDER_REVIEW);
        
        paperRepository.save(paper);
        log.info("Review submitted for paper {}", paperId);
    }
}