/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.dependencieseditingwindow;

import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultDependenciesEditingPanelFactory implements
        DependenciesEditingPanelFactory {

    @Override
    public final DependenciesEditingPanel create() {
        return new DefaultDependenciesEditingPanel();
    }

}