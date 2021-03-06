package com.spring.auth.events.domain;

import com.spring.auth.session.domain.Session;
import org.springframework.context.ApplicationEvent;

/**
 * @author diegotobalina created on 24/06/2020
 */
public class SessionCreatedEvent extends ApplicationEvent {
    public SessionCreatedEvent(Session session) {
        super(session);
    }

    @Override
    public Session getSource() {
        return (Session) this.source;
    }
}
