package com.master.board.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<Date> {
    @Override
    public Optional<Date> getCurrentAuditor() {
        return Optional.of(new Date());
    }
}
