package com.wukker.sb.eventconnectionfortopmobile.model;

import java.io.Serializable;

/**
 * Created by sb on 12.11.15.
 */
public class Questionnaire implements Serializable {
    Long userId;
    Long conferenceId;
    Integer organizationalScore;
    Integer registrationProcess;
    Integer organizerWorkScore;
    Integer themesRelevanceScore;
    Long bestModeratorEventId;
    Long bestModeratorId;
    Long bestSpeakerEventId;
    Long bestSpeakerId;
    Long bestEventId;
    String wishes;
    String comment;

    public Questionnaire(Long userId, Long conferenceId) {
        this.userId = userId;
        this.conferenceId = conferenceId;
        this.organizationalScore = null;
        this.registrationProcess = null;
        this.organizerWorkScore = null;
        this.themesRelevanceScore = null;
        this.bestModeratorEventId = null;
        this.bestModeratorId = null;
        this.bestSpeakerEventId = null;
        this.bestSpeakerId = null;
        this.bestEventId = null;
        this.wishes = null;
        this.comment = null;
    }

    public Questionnaire(Long userId, Long conferenceId, Integer organizationalScore, Integer registrationProcess, Integer organizerWorkScore, Integer themesRelevanceScore, Long bestModeratorEventId, Long bestModeratorId, Long bestSpeakerEventId, Long bestSpeakerId, Long bestEventId, String wishes, String comment) {
        this.userId = userId;
        this.conferenceId = conferenceId;
        this.organizationalScore = organizationalScore;
        this.registrationProcess = registrationProcess;
        this.organizerWorkScore = organizerWorkScore;
        this.themesRelevanceScore = themesRelevanceScore;
        this.bestModeratorEventId = bestModeratorEventId;
        this.bestModeratorId = bestModeratorId;
        this.bestSpeakerEventId = bestSpeakerEventId;
        this.bestSpeakerId = bestSpeakerId;
        this.bestEventId = bestEventId;
        this.wishes = wishes;
        this.comment = comment;
    }

    public void setOrganizationalScore(Integer organizationalScore) {
        this.organizationalScore = organizationalScore;
    }

    public void setRegitrationProcess(Integer regitrationProcess) {
        this.registrationProcess = regitrationProcess;
    }

    public void setOrganizerWorkScore(Integer organizerWorkScore) {
        this.organizerWorkScore = organizerWorkScore;
    }

    public void setThemesRelevanceScore(Integer themesRelevanceScore) {
        this.themesRelevanceScore = themesRelevanceScore;
    }

    public void setBestModeratorEventId(Long bestModeratorEventId) {
        this.bestModeratorEventId = bestModeratorEventId;
    }

    public void setBestModeratorId(Long bestModeratorId) {
        this.bestModeratorId = bestModeratorId;
    }

    public void setBestSpeakerEventId(Long bestSpeakerEventId) {
        this.bestSpeakerEventId = bestSpeakerEventId;
    }

    public void setBestSpeakerId(Long bestSpeakerId) {
        this.bestSpeakerId = bestSpeakerId;
    }

    public void setBestEventId(Long bestEventId) {
        this.bestEventId = bestEventId;
    }

    public void setWishes(String wishes) {
        this.wishes = wishes;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }
}
