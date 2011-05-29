/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.test.model;

import java.util.Date;
import java.util.List;

import at.silverstrike.pcc.api.model.SchedulingObjectValidationError;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.model.UserData;

class MockControlProcess extends MockSchedulingObject implements Task {

    private Double bestCaseEffort;
    private Double averageCaseEffort;
    private Double worstCaseEffort;
    private List<ResourceAllocation> resourceAllocations;
    private Date averageEstimatedEndDateTime;
    private Date bestEstimatedEndDateTime;
    private Date worstEstimatedEndDateTime;
    private SchedulingObjectValidationError validationError;
    private UserData user;

    public UserData getUserData() {
            return user;
    }

    public void setUserData(UserData user) {
            this.user = user;
    }
    public Double getBestCaseEffort() {
        return this.bestCaseEffort;
    }

    public void setBestCaseEffort(final Double aEffortInHours) {
        this.bestCaseEffort = aEffortInHours;
    }

    public double getAverageCaseEffort() {
        return this.averageCaseEffort;
    }

    public Double getWorstCaseEffort() {
        return this.worstCaseEffort;
    }

    public void setWorstCaseEffort(final Double aEffortInHours) {
        this.worstCaseEffort = aEffortInHours;

    }

    public void setResourceAllocations(
            final List<ResourceAllocation> aResourceAllocations) {
        this.resourceAllocations = aResourceAllocations;
    }

    public List<ResourceAllocation> getResourceAllocations() {
        return this.resourceAllocations;
    }

    public Date getAverageEstimatedEndDateTime() {
        return this.averageEstimatedEndDateTime;
    }

    public void setAverageEstimatedEndDateTime(final Date aDate) {
        this.averageEstimatedEndDateTime = aDate;
    }

    public Date getBestEstimatedEndDateTime() {
        return this.bestEstimatedEndDateTime;
    }

    public void setBestEstimatedEndDateTime(final Date aDate) {
        this.bestEstimatedEndDateTime = aDate;
    }

    public Date getWorstEstimatedEndDateTime() {
        return this.worstEstimatedEndDateTime;
    }

    public void setWorstEstimatedEndDateTime(final Date aDate) {
        this.worstEstimatedEndDateTime = aDate;
    }

    public SchedulingObjectValidationError getValidationError() {
        return validationError;
    }

    public void setValidationError(SchedulingObjectValidationError validationError) {
        this.validationError = validationError;
    }

}
