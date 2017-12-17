package com.devx.demo.mongodocs;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Rule {
    private Integer ruleID;
    private String packetFrom;
    private String packetTo;
    private String packetPort;
    private String ruleAction;
    private Date timestamp;

    public Rule(Integer ruleID, String packetFrom, String packetTo, String packetPort, String ruleAction, Date timestamp) {
        this.ruleID = ruleID;
        this.packetFrom = packetFrom;
        this.packetTo = packetTo;
        this.packetPort = packetPort;
        this.ruleAction = ruleAction;
        this.timestamp=timestamp;
    }

    public Integer getRuleID() {
        return ruleID;
    }

    public void setRuleID(Integer ruleID) {
        this.ruleID = ruleID;
    }

    public String getPacketFrom() {
        return packetFrom;
    }

    public void setPacketFrom(String packetFrom) {
        this.packetFrom = packetFrom;
    }

    public String getPacketTo() {
        return packetTo;
    }

    public void setPacketTo(String packetTo) {
        this.packetTo = packetTo;
    }

    public String getPacketPort() {
        return packetPort;
    }

    public void setPacketPort(String packetPort) {
        this.packetPort = packetPort;
    }

    public String getRuleAction() {
        return ruleAction;
    }

    public void setRuleAction(String ruleAction) {
        this.ruleAction = ruleAction;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
