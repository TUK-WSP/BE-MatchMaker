package org.wsp.matchmaker.domain.user.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wsp.matchmaker.domain.user.entity.Hobby;
import org.wsp.matchmaker.domain.user.entity.Report;

public interface ReportRepository  extends JpaRepository<Report, UUID> {
}

