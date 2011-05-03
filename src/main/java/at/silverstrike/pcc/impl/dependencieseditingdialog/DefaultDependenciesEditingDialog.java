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

package at.silverstrike.pcc.impl.dependencieseditingdialog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.gui.ModalDialogResult;

import com.google.inject.Injector;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.dependencieseditingdialog.DependenciesEditingDialog;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;

/**
 * @author DP118M
 * 
 */
final class DefaultDependenciesEditingDialog implements
        DependenciesEditingDialog, ClickListener, ValueChangeListener {
    private static final long serialVersionUID = 1L;
    private static final String OK_BUTTON = "047.001";
    private static final String CANCEL_BUTTON = "047.002";
    private static final String ADD_DEPENDENCY_BUTTON = "047.003";
    private static final String DELETE_BUTTON = "047.004";

    private Set<SchedulingObject> existingDependencies;
    private List<SchedulingObject> availableDependencies;
    private Window parentWindow;
    private ModalDialogResult dialogResult;
    private Set<SchedulingObject> selectedDependencies;
    private Window dialog;
    private Table dependenciesTable;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultDependenciesEditingDialog.class);
    private ComboBox newDependencyComboBox;
    private Button addDependencyButton;
    private Button deleteButton;
    private SchedulingObject schedulingObject;
    private transient Persistence persistence;
    private transient WebGuiBus webGuiBus;

    public ModalDialogResult getDialogResult() {
        return dialogResult;
    }

    public Set<SchedulingObject> getSelectedDependencies() {
        return selectedDependencies;
    }

    public void setExistingDependencies(
            Set<SchedulingObject> existingDependencies) {
        this.existingDependencies = existingDependencies;
    }

    public void setParentWindow(final Window aParentWindow) {
        this.parentWindow = aParentWindow;
    }

    public void
            setAvailableDependencies(
                    final List<SchedulingObject> aAvailableDependencies) {
        this.availableDependencies = aAvailableDependencies;
    }

    @Override
    public void initGui() {
        this.dialogResult = ModalDialogResult.UNDEFINED;

        this.dialog = new Window(TM.get("dependencieseditingdialog.1-title"));
        this.dialog.setModal(true);
        this.dialog.setWidth("500px");
        this.dialog.setHeight("400px");

        final Label message =
                new Label(TM.get("dependencieseditingdialog.4-top-lettering"));
        this.dialog.getContent().addComponent(message);

        final HorizontalLayout tableLayout = getTableLayout();

        this.dialog.getContent().addComponent(tableLayout);

        final HorizontalLayout addDependencyPanel = getAddDependencyPanel();
        this.dialog.getContent().addComponent(addDependencyPanel);

        final HorizontalLayout buttonPanel = getButtonPanel();
        this.dialog.getContent().addComponent(buttonPanel);
    }

    private HorizontalLayout getTableLayout() {
        final HorizontalLayout tableLayout = new HorizontalLayout();
        dependenciesTable = new Table();

        dependenciesTable.setWidth("80%");
        dependenciesTable.setHeight("170px");
        dependenciesTable.addContainerProperty(
                TM.get("dependencieseditingdialog.7-table-no"), Long.class,
                null);
        dependenciesTable.addContainerProperty(
                TM.get("dependencieseditingdialog.8-table-project"),
                String.class, null);
        dependenciesTable.addContainerProperty(
                TM.get("dependencieseditingdialog.9-table-name"), String.class,
                null);
        dependenciesTable.addListener(new TableListener(this));
        dependenciesTable.setSelectable(true);
        dependenciesTable.setMultiSelect(false);
        dependenciesTable.setImmediate(true);

        addDependencyData(dependenciesTable);

        deleteButton =
                new Button(TM.get("dependencieseditingdialog.6-deleteButton"));
        deleteButton.setDebugId(DELETE_BUTTON);
        deleteButton.setEnabled(false);

        tableLayout.addComponent(dependenciesTable);
        tableLayout.addComponent(deleteButton);
        return tableLayout;
    }

    private void addDependencyData(final Table aTable) {
        if (this.existingDependencies == null) {
            return;
        }
        for (final SchedulingObject curExistingDependency : this.existingDependencies) {
            addDependencyItem(aTable, curExistingDependency);
        }
    }

    private void addDependencyItem(final Table aTable,
            final SchedulingObject aCurExistingDependency) {
        final String projectName;

        if (aCurExistingDependency.getParent() != null) {
            projectName = aCurExistingDependency.getParent().getName();
        } else {
            projectName = "";
        }

        aTable.addItem(
                new Object[] { aCurExistingDependency.getId(), projectName,
                        aCurExistingDependency.getName() },
                aCurExistingDependency);
    }

    private HorizontalLayout getAddDependencyPanel() {
        final HorizontalLayout addDependencyPanel = new HorizontalLayout();
        newDependencyComboBox = new ComboBox();
        addDependencyButton = new Button(
                TM.get("dependencieseditingdialog.5-addDependencyButton"));
        addDependencyButton.setDebugId(ADD_DEPENDENCY_BUTTON);
        addDependencyButton.addListener((ClickListener) this);

        newDependencyComboBox.addContainerProperty("caption", String.class,
                null);
        newDependencyComboBox.setItemCaptionPropertyId("caption");

        for (final SchedulingObject curAvailableDependency : this.availableDependencies) {
            final Item item =
                    newDependencyComboBox.addItem(curAvailableDependency);

            item.getItemProperty("caption").setValue(StringUtils.abbreviate(
                    curAvailableDependency.getName(), 50));
        }
        newDependencyComboBox.setNullSelectionAllowed(false);
        newDependencyComboBox.setNewItemsAllowed(false);
        newDependencyComboBox.setImmediate(true);
        newDependencyComboBox.setMultiSelect(false);
        newDependencyComboBox.addListener((ValueChangeListener) this);

        addDependencyPanel.addComponent(newDependencyComboBox);
        addDependencyPanel.addComponent(addDependencyButton);

        return addDependencyPanel;
    }

    private HorizontalLayout getButtonPanel() {
        final HorizontalLayout buttonPanel = new HorizontalLayout();

        final Button okButton =
                new Button(TM.get("dependencieseditingdialog.2-ok-button"),
                        this);
        okButton.setDebugId(OK_BUTTON);
        buttonPanel.addComponent(okButton);

        final Button cancelButton =
                new Button(TM.get("dependencieseditingdialog.3-cancel-button"),
                        this);
        cancelButton.setDebugId(CANCEL_BUTTON);
        buttonPanel.addComponent(cancelButton);

        buttonPanel.addComponent(okButton);
        buttonPanel.addComponent(cancelButton);
        return buttonPanel;
    }

    @Override
    public Window toWindow() {
        return this.dialog;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        final String debugId = aEvent.getButton().getDebugId();
        LOGGER.debug("buttonClick, debugId: {}", debugId);

        if (OK_BUTTON.equals(debugId)) {
            this.dialogResult = ModalDialogResult.CLOSED_WITH_OK;
            closeDialog();

            this.schedulingObject.setPredecessors(this.selectedDependencies);
            this.persistence.updateSchedulingObject(this.schedulingObject);
            
            LOGGER.debug("DefaultDependenciesEditingDialog.buttonClick.OK_BUTTON");
            webGuiBus.broadcastTaskDependenciesChangedMessage(this.schedulingObject);
            
            
        } else if (CANCEL_BUTTON.equals(debugId)) {
            this.dialogResult = ModalDialogResult.CLOSED_WITH_CANCEL;
            closeDialog();
        } else if (ADD_DEPENDENCY_BUTTON.equals(debugId)) {
            final SchedulingObject selectedObject =
                    (SchedulingObject) newDependencyComboBox.getValue();

            addDependencyItem(this.dependenciesTable, selectedObject);
            newDependencyComboBox.removeItem(selectedObject);
        } else if (DELETE_BUTTON.equals(debugId)) {
            final SchedulingObject selectedObject =
                    (SchedulingObject) this.dependenciesTable.getValue();

            this.dependenciesTable.removeItem(selectedObject);
            newDependencyComboBox.addItem(selectedObject);
        }
    }

    private void closeDialog() {
        this.selectedDependencies = new HashSet<SchedulingObject>();

        for (final Object curItemId : this.dependenciesTable.getItemIds()) {
            final SchedulingObject curObject = (SchedulingObject) curItemId;

            this.selectedDependencies.add(curObject);
        }

        this.parentWindow.removeWindow(this.dialog);
    }

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            persistence = aInjector.getInstance(Persistence.class);
            webGuiBus = aInjector.getInstance(WebGuiBus.class);
        }
    }

    @Override
    public void valueChange(final ValueChangeEvent aEvent) {
        this.addDependencyButton.setEnabled(this.newDependencyComboBox
                .getValue() != null);
    }

    void tableSelectionChanged() {
        this.deleteButton.setEnabled(this.dependenciesTable.getValue() != null);
    }

    @Override
    public void setSchedulingObject(final SchedulingObject aObject) {
        this.schedulingObject = aObject;
    }
}