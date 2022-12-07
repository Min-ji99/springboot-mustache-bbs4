package com.mustache.bbs4.repository;

import com.mustache.bbs4.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
