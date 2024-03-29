package com.skye.srms_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-15
 */
@TableName("fix_info")
public class FixInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "Server Index can not be null")
    private String serverIndex;

    @NotBlank(message = "Sender can not be null")
    private String sender;

    private String fixer;

    @NotBlank(message = "Fix Status can not be null")
    private String fixStatus;

    private LocalDateTime createdDate;

    private LocalDateTime fixDate;

    private String info;

    public FixInfo() {
    }

    public FixInfo(String id, String serverIndex, String sender, String fixer, String fixStatus, LocalDateTime createdDate, LocalDateTime fixDate, String info) {
        this.id = id;
        this.serverIndex = serverIndex;
        this.sender = sender;
        this.fixer = fixer;
        this.fixStatus = fixStatus;
        this.createdDate = createdDate;
        this.fixDate = fixDate;
        this.info = info;
    }


    public FixInfo(String id, String serverIndex, String sender, String fixer, String fixStatus, LocalDateTime createdDate, String info) {
        this.id = id;
        this.serverIndex = serverIndex;
        this.sender = sender;
        this.fixer = fixer;
        this.fixStatus = fixStatus;
        this.createdDate = createdDate;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getServerIndex() {
        return serverIndex;
    }

    public void setServerIndex(String serverIndex) {
        this.serverIndex = serverIndex;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getFixer() {
        return fixer;
    }

    public void setFixer(String fixer) {
        this.fixer = fixer;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getFixDate() {
        return fixDate;
    }

    public void setFixDate(LocalDateTime fixDate) {
        this.fixDate = fixDate;
    }

    public String getFixStatus() {
        return fixStatus;
    }

    public void setFixStatus(String fixStatus) {
        this.fixStatus = fixStatus;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "FixInfo{" +
                "id='" + id + '\'' +
                ", serverIndex='" + serverIndex + '\'' +
                ", sender='" + sender + '\'' +
                ", fixer='" + fixer + '\'' +
                ", fixStatus='" + fixStatus + '\'' +
                ", createdDate=" + createdDate +
                ", fixDate=" + fixDate +
                ", info='" + info + '\'' +
                '}';
    }
}
