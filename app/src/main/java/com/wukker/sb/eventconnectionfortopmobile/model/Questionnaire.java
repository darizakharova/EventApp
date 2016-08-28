package com.wukker.sb.eventconnectionfortopmobile.model;

import java.io.Serializable;

/**
 * Created by sb on 12.11.15.
 */
public class Questionnaire implements Serializable {
    private long userId;
    private long conferenceId;
    private int organizationalScore;
    private int registrationProcess;
    private int organizerWorkScore;
    private int themesRelevanceScore;
    private long bestModeratorEventId;
    private long bestModeratorId;
    private long bestSpeakerEventId;
    private long bestSpeakerId;
    private long bestEventId;
    private String wishes;
    private String comment;

    public Questionnaire(long userId, long conferenceId) {
        this.userId = userId;
        this.conferenceId = conferenceId;
        this.organizationalScore = 0;
        this.registrationProcess = 0;
        this.organizerWorkScore = 0;
        this.themesRelevanceScore = 0;
        this.bestModeratorEventId = 0;
        this.bestModeratorId = 0;
        this.bestSpeakerEventId = 0;
        this.bestSpeakerId = 0;
        this.bestEventId = 0;
        this.wishes = null;
        this.comment = null;
    }

    public Questionnaire(long userId, long conferenceId, int organizationalScore,
                         int registrationProcess, int organizerWorkScore,
                         int themesRelevanceScore,
                         long bestModeratorEventId, long bestModeratorId, long bestSpeakerEventId,
                         long bestSpeakerId, long bestEventId, String wishes, String comment) {
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

    public void setOrganizationalScore(int organizationalScore) {
        this.organizationalScore = organizationalScore;
    }

    public void setRegitrationProcess(int regitrationProcess) {
        this.registrationProcess = regitrationProcess;
    }

    public void setOrganizerWorkScore(int organizerWorkScore) {
        this.organizerWorkScore = organizerWorkScore;
    }

    public void setThemesRelevanceScore(int themesRelevanceScore) {
        this.themesRelevanceScore = themesRelevanceScore;
    }

    public void setBestModeratorEventId(long bestModeratorEventId) {
        this.bestModeratorEventId = bestModeratorEventId;
    }

    public void setBestModeratorId(long bestModeratorId) {
        this.bestModeratorId = bestModeratorId;
    }

    public void setBestSpeakerEventId(long bestSpeakerEventId) {
        this.bestSpeakerEventId = bestSpeakerEventId;
    }

    public void setBestSpeakerId(long bestSpeakerId) {
        this.bestSpeakerId = bestSpeakerId;
    }

    public void setBestEventId(long bestEventId) {
        this.bestEventId = bestEventId;
    }

    public void setWishes(String wishes) {
        this.wishes = wishes;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getUserId() {
        return userId;
    }
}
