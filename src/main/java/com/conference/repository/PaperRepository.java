package com.conference.repository;

import com.conference.model.Paper;
import com.conference.model.enums.PaperState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findByAuthors_User_Username(String username);
    List<Paper> findByState(PaperState state);
}