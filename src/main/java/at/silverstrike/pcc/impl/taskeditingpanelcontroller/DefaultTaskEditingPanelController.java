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

package at.silverstrike.pcc.impl.taskeditingpanelcontroller;

import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanel;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanelFactory;
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelController;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

class DefaultTaskEditingPanelController implements TaskEditingPanelController {
    private transient Injector injector = null;
    private transient Persistence persistence;
    private transient WebGuiBus webGuiBus;
    private TaskEditingPanel panel;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;

        if (aInjector != null) {
            this.persistence = this.injector.getInstance(Persistence.class);
            this.webGuiBus = this.injector.getInstance(WebGuiBus.class);
        }
    }

    @Override
    public void saveTask(final Task aTask) {
        if (aTask != null) {
            this.persistence.updateTask(aTask);
            this.webGuiBus.broadcastTaskEditedMessage(aTask);
        }
    }

    @Override
    public void markTaskAsCompleted(final Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTask(final Task aTask) {

        if (this.persistence.deleteTask(aTask)) {
            this.webGuiBus.broadcastTaskDeletedMessage(aTask);
        } else {
            this.webGuiBus.broadcastTaskDeletionFailureMessage();
        }
    }

    @Override
    public void setData(final Task aTask) {
        this.panel.setData(aTask);
    }

    @Override
    public Panel initGui() {
        final TaskEditingPanelFactory factory = this.injector
                .getInstance(TaskEditingPanelFactory.class);
        this.panel = factory.create();

        panel.setInjector(this.injector);
        panel.initGui();

        return panel.toPanel();
    }

    @Override
    public void taskCreated(final Task aNewTask) {

    }

    @Override
    public void taskCreationFailure() {
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
    public void taskDeletedFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventDeletedFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneDeletedFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void clearPanel() {
        // TODO Auto-generated method stub

    }

}
