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

package at.silverstrike.pcc.impl.milestoneeditingpanelcontroller;

import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanelFactory;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelController;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.impl.webguibus.WebGuiBusListenerAdapter;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

class DefaultMilestoneEditingPanelController extends WebGuiBusListenerAdapter implements
        MilestoneEditingPanelController {
    private Injector injector = null;

    @Override
    public void dependEditButtonClicked() {
        final DependenciesEditingPanelFactory factory =
                this.injector
                        .getInstance(DependenciesEditingPanelFactory.class);
        final DependenciesEditingPanel panel = factory.create();
        panel.setInjector(injector);
        panel.initGui();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public Panel initGui() {
        // TODO Auto-generated method stub
        return null;
    }

 
    @Override
    public void taskEdited(final Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventCreated(final Event aNewEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventCreationFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneCreated(final Milestone aMilestone) {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneCreationFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setData(final Milestone aMilestone) {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskDeleted(final Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventDeleted(final Event aNewEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneDeleted(final Milestone aMilestone) {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskDeletionFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventDeletionFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneDeletionFailure() {
        // TODO Auto-generated method stub

    }

}