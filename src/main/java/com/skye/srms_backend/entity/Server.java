package com.skye.srms_backend.entity;

import com.baomidou.mybatisplus.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Skye_Zhao
 * @since 2023-04-14
 */
@TableName("server")
public class Server implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String serverIndex;

//    @NotNull
    private Boolean isWorking;

    private String descriptions;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String fixId;

    private LocalDateTime createdDate;

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

    public Boolean getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(Boolean isWorking) {
        this.isWorking = isWorking;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getFixId() {
        return fixId;
    }

    public void setFixId(String fixId) {
        this.fixId = fixId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Server{" +
            "id = " + id +
            ", serverIndex = " + serverIndex +
            ", isWorking = " + isWorking +
            ", descriptions = " + descriptions +
            ", fixId = " + fixId +
            ", createdDate = " + createdDate +
        "}";
    }
}
