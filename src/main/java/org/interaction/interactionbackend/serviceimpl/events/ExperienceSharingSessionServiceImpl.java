package org.interaction.interactionbackend.serviceimpl.events;

import org.springframework.stereotype.Service;

@Service
public class ExperienceSharingSessionServiceImpl extends SimpleEventServiceImpl {
    @Override
    public Integer getEventId() {
        return 4;
    }
}
