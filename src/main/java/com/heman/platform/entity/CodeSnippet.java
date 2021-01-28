package com.heman.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class CodeSnippet {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String code;

    private LocalDateTime created = LocalDateTime.now();

    private int time = 0;

    private int views = 0;

    private int viewsSpent = 0;


//    public CodeSnippet(String code) {
//        this.code = code;
//        createdAt = LocalDateTime.now();
//    }


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonProperty("date")
    public LocalDateTime getCreated() {
        if (created == null) {
            created = LocalDateTime.now();
        }
        return created;
    }


    @JsonProperty("time")
    public long getTimeLeft() {
        if (time > 0) {
            long seconds = created.until(LocalDateTime.now(), ChronoUnit.SECONDS);
            if (seconds < time) {
                return time - seconds;
            }
        }

        return 0;
    }


    @JsonProperty("views")
    public long getViewsLeft() {
        if (views > 0) {
            return views - viewsSpent;
        }

        return 0;
    }


    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return getCreated().format(formatter).toString();
    }


    public void setCreated(LocalDateTime created) {
        this.created = created;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    @JsonIgnore
    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public int getTime() {
        return time;
    }


    public void setTime(int time) {
        this.time = time;
    }


    public int getViews() {
        return views;
    }


    public void setViews(int views) {
        this.views = views;
    }


    @JsonIgnore
    public int getViewsSpent() {
        return viewsSpent;
    }


    public boolean hasRestriction() {
        return time > 0 || views > 0;
    }


    public void setViewsSpent(int viewsSpent) {
        this.viewsSpent = viewsSpent;
    }


    public boolean canShow() {
        if (time == 0 && views == 0) {
            return true;
        }

        boolean isActive = true;

        if (time > 0 && getTimeLeft() == 0) {
            isActive = false;
        }

        if (views > 0 && getViewsLeft() == 0) {
            isActive = false;
        } else {
            viewsSpent++;
        }

        return isActive;
    }

    @Override
    public String toString() {
        return "CodeSnippet{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", created=" + created +
                ", time=" + time +
                ", views=" + views +
                ", viewsSpent=" + viewsSpent +
                '}';
    }
}
