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

package at.silverstrike.pcc.api.invitationgui;

import ru.altruix.commons.api.gui.ExternallyControlledGuiComponent;
import ru.altruix.commons.api.gui.InitializableGuiComponent;
import ru.altruix.commons.api.vaadin.AbstractedWindow;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;

import com.vaadin.ui.Window;

/**
 * @author DP118M
 * 
 */
public interface InvitationRequestWindowStep1 extends
        InitializableGuiComponent, AbstractedWindow,
        ExternallyControlledGuiComponent<InvitationGuiController, Window> {

}