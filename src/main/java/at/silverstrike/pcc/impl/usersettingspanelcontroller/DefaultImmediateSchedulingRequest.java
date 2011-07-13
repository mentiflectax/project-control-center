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

package at.silverstrike.pcc.impl.usersettingspanelcontroller;

import co.altruix.pcc.api.cdm.ImmediateSchedulingRequest;

/**
 * @author DP118M
 *
 */
class DefaultImmediateSchedulingRequest implements ImmediateSchedulingRequest {
    private static final long serialVersionUID = 1L;
    private Long userId;

    public DefaultImmediateSchedulingRequest(final Long aUserId) {
        this.userId = aUserId;
    }
    
    public Long getUserId() {
        return userId;
    }
}