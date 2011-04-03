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

package at.silverstrike.pcc.impl.centraleditingpanelcontroller;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanel;
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanelFactory;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanel;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanelFactory;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanel;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanelFactory;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;

class DefaultCentralEditingPanelController implements
        CentralEditingPanelController {
    private Injector injector = null;
    private Persistence persistence;
    private WebGuiBus webGuiBus;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.injector = aInjector;
            this.persistence = this.injector.getInstance(Persistence.class);
            this.webGuiBus = this.injector.getInstance(WebGuiBus.class);
        }
    }

    @Override
    public void increasePriorityButtonClicked() {
        // TODO Auto-generated method stub

    }

    @Override
    public void decreasePriorityButtonClicked() {
        // TODO Auto-generated method stub

    }

    @Override
    public Panel getTaskPanel() {
        final TaskEditingPanelFactory factory =
                this.injector.getInstance(TaskEditingPanelFactory.class);
        final TaskEditingPanel panel = factory.create();
        panel.setInjector(this.injector);
        panel.initGui();

        return panel.toPanel();
    }

    @Override
    public Panel getMeetingPanel() {
        final EventEditingPanelFactory factory =
                this.injector.getInstance(EventEditingPanelFactory.class);
        final EventEditingPanel panel = factory.create();
        panel.setInjector(this.injector);
        panel.initGui();
        return panel.toPanel();
    }

    @Override
    public Panel getMilestonePanel() {
        final MilestoneEditingPanelFactory factory =
                this.injector.getInstance(MilestoneEditingPanelFactory.class);
        final MilestoneEditingPanel panel = factory.create();
        panel.setInjector(this.injector);
        panel.initGui();
        return panel.toPanel();
    }

    @Override
    public void newTaskButtonClicked() {
        // TODO Auto-generated method stub

    }

    @Override
    public void newMeetingButtonClicked() {
        // TODO Auto-generated method stub

    }

    @Override
    public void newMilestoneButtonClicked() {
        // TODO Auto-generated method stub

    }

    @Override
    public void createMilestone(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree) {
    }

    @Override
    public void createTask(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree) {
        final String taskName =
                TM.get("centraleditingpanelcontroller.1-new-task-name");
        final Task newTask = this.persistence.createSubTask(
                taskName,
                aProjectIdCurrentlySelectedInTree);

        if (newTask != null) {
            this.webGuiBus.broadcastTaskCreatedMessage(newTask);
        } else {
            this.webGuiBus.broadcastTaskCreationFailureMessage();
        }
    }

    @Override
    public void createMeeting(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree) {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskCreated(final Task aNewTask) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void taskCreationFailure() {
        // TODO Auto-generated method stub
        
    }
}
