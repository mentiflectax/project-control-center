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

package at.silverstrike.pcc.api.usersettingspanelcontroller;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.GuiController;

import com.vaadin.ui.Panel;

/**
 * @author DP118M
 * 
 */
public interface UserSettingsPanelController extends
        ModuleWithInjectableDependencies, GuiController<Panel> {

    void saveGoogleAccessData(final String aUsername, final String aPassword);

    void fetchData(final String aUsername, final String aPassword);

    void writeDataToGoogle(final String aUsername, final String aPassword);

}
