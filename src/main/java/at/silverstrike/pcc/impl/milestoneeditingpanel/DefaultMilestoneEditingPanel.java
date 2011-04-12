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

package at.silverstrike.pcc.impl.milestoneeditingpanel;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import at.silverstrike.pcc.api.conventions.FunctionalBlock;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanel;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelController;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.gui.dialogs.OptionDialog;
import eu.livotov.tpt.i18n.TM;

class DefaultMilestoneEditingPanel extends Panel implements
		MilestoneEditingPanel, ClickListener {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DefaultMilestoneEditingPanel.class);
	private static final long serialVersionUID = 1L;

	public static final Object PROJECT_PROPERTY_NAME = "name";

	private static final int PROCESS_NAME_TEXT_FIELD_ROWS = 5;

	private static final String[] TEST_COLUMN_NAMES = new String[] { "�",
			"Project", "Name" };
	private static final List<String[]> TEST_TABLE_DATA = Arrays.asList(
			new String[] { "1.1", "Project 1", "Task 1" }, new String[] {
					"2.1", "Project 4", "Task 5" });
	private static final String SAVE_MILESTONE_BUTTON = "030.001";
	private static final String DELETE_MILESTONE_BUTTON = "030.002";
	private static final String DEPENDENCIES_BUTTON = "030.XYZ";
	private transient Milestone milestone;

	private transient Injector injector;
	private transient MilestoneEditingPanelController controller;
	private transient DebugIdRegistry debugIdRegistry;
	private TextField milestoneNameTextField;

	@Override
	public void setInjector(final Injector aInjector) {
		if (aInjector != null) {
			injector = aInjector;
			this.controller = this.injector
					.getInstance(MilestoneEditingPanelController.class);
			this.controller.setInjector(this.injector);
			this.debugIdRegistry = this.injector
					.getInstance(DebugIdRegistry.class);
		}
	}

	private Button createDependEditButton() {
		final Button dependEditButton = new Button(
				TM.get("milestoneeditingpanel.5-button-edit"));
		dependEditButton.addListener(new DependenciesButtonClickListener(
				controller));
		return dependEditButton;
	}

	private Table createTestTable() {
		final TestTableCreator creator =

		this.injector.getInstance(TestTableCreator.class);
		creator.setColumnNames(TEST_COLUMN_NAMES);
		creator.setData(TEST_TABLE_DATA);
		try {
			creator.run();
		} catch (final PccException exception) {
			LOGGER.error(ErrorCodes.M_001_TEST_TABLE_CREATION, exception);
		}
		final Table table = creator.getTable();
		return table;
	}

	/*
	 * Shows a notification when a button is clicked.
	 */
	public void buttonClick(final ClickEvent aMilestone) {
        final String debugId = aMilestone.getButton().getDebugId();

        LOGGER.debug(
                "at.silverstrike.pcc.impl.milestoneeditingpanel.DefaultMilestoneEditingPanel.buttonClick(ClickEvent), debugId: {}",
                debugId);

        if (SAVE_MILESTONE_BUTTON.equals(debugId)) {
            final OptionDialog dialog =
                    new OptionDialog(TPTApplication.getCurrentApplication());

            dialog.showConfirmationDialog("Milestone", "Debug ID: " + debugId
                    + ", this.milestone: " + this.milestone, null);

            if (this.milestone != null) {
                this.milestone.setName((String) this.milestoneNameTextField.getValue());
                this.controller.saveMilestone(this.milestone);
            }
        } else if (DELETE_MILESTONE_BUTTON.equals(debugId)) {
            controller.deleteMilestone(this.milestone);
        } else if (DEPENDENCIES_BUTTON.equals(debugId)) {
            //letUserEnterDependencies();
        }
	}

	@Override
	public Panel toPanel() {
		return this;
	}

	@Override
	public void initGui() {
		final Panel verticalLayoutRight = new Panel();

		final Label taskLabel = new Label(
				TM.get("milestoneeditingpanel.1-label-milestone"));
		taskLabel.setContentMode(Label.CONTENT_TEXT);
		verticalLayoutRight.addComponent(taskLabel);

		final HorizontalLayout buttonsTaskLayout = new HorizontalLayout();
		buttonsTaskLayout.setSpacing(true);

		final Button saveButton = new Button(
				TM.get("milestoneeditingpanel.2-button-save"));
		saveButton.setDebugId(this.debugIdRegistry.getDebugId(
				FunctionalBlock.milestoneeditingpanel, "1-button-save"));
		saveButton.addListener(this); // react to clicks
		buttonsTaskLayout.addComponent(saveButton);

		final Button deleteButton = new Button(
				TM.get("milestoneeditingpanel.3-button-delete"));
		deleteButton.setDebugId(this.debugIdRegistry.getDebugId(
				FunctionalBlock.milestoneeditingpanel, "2-button-delete"));
		deleteButton.addListener(this); // react to clicks
		buttonsTaskLayout.addComponent(deleteButton);

		verticalLayoutRight.addComponent(buttonsTaskLayout);

		milestoneNameTextField = new TextField();
		milestoneNameTextField.setWidth("100%");
		milestoneNameTextField.setRows(PROCESS_NAME_TEXT_FIELD_ROWS);
		verticalLayoutRight.addComponent(milestoneNameTextField);

		final HorizontalLayout dependLayout = new HorizontalLayout();
		dependLayout.setSpacing(true);

		final Label dependLabel = new Label(
				TM.get("milestoneeditingpanel.4-label-dependencies"));
		dependLayout.addComponent(dependLabel);

		final Button dependEditButton = createDependEditButton();
		dependLayout.addComponent(dependEditButton);

		verticalLayoutRight.addComponent(dependLayout);

		final Table table = createTestTable();
		verticalLayoutRight.addComponent(table);
		this.addComponent(verticalLayoutRight);
	}

	@Override
	public void setMilestone(Milestone aMilestone) {
        this.milestone = aMilestone;
        if (this.milestone != null) {
        	milestoneNameTextField.setValue(this.milestone.getName());
        } else {
        	milestoneNameTextField.setValue("");
        }
		
	}
}