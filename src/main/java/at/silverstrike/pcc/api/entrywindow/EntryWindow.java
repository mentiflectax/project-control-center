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

package at.silverstrike.pcc.api.entrywindow;

import javax.servlet.http.HttpServletRequest;

import at.silverstrike.pcc.api.conventions.InitializableGuiComponent;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.PccWindow;

public interface EntryWindow extends ModuleWithInjectableDependencies,
        InitializableGuiComponent, PccWindow {

    void setRequest(final HttpServletRequest aRequest);
}
