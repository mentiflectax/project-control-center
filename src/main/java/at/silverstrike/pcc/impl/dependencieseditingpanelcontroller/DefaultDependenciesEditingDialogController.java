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

package at.silverstrike.pcc.impl.dependencieseditingpanelcontroller;

import java.util.List;
import java.util.Set;

import com.google.inject.Injector;
import com.vaadin.ui.Window;

import at.silverstrike.pcc.api.conventions.ModalDialogResult;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.dependencieseditingdialog.DependenciesEditingDialog;
import at.silverstrike.pcc.api.dependencieseditingdialog.DependenciesEditingDialogFactory;
import at.silverstrike.pcc.api.dependencieseditingdialogcontroller.DependenciesEditingDialogController;
import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 * 
 */
final class DefaultDependenciesEditingDialogController implements
        DependenciesEditingDialogController {
    private Set<SchedulingObject> newDependencies;
    private ModalDialogResult dialogResult;
    private SchedulingObject schedulingObject;
    private Injector injector;
    private Window parentWindow;
    
    @Override
    public void run() throws PccException {
        this.dialogResult = ModalDialogResult.UNDEFINED;
        final DependenciesEditingDialogFactory factory =
                this.injector
                        .getInstance(DependenciesEditingDialogFactory.class);

        final DependenciesEditingDialog dialog = factory.create();
        
        dialog.setExistingDependencies(this.schedulingObject.getPredecessors());
        dialog.setAvailableDependencies(getAvailableDependencies());
        dialog.setParentWindow(this.parentWindow);

        this.parentWindow.addWindow(dialog.toWindow());
        
        this.dialogResult = dialog.getDialogResult();
        
        if (ModalDialogResult.CLOSED_WITH_OK.equals(this.dialogResult))
        {
            this.newDependencies = dialog.getSelectedDependencies();
        }
    }

    private List<SchedulingObject> getAvailableDependencies() {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void setSchedulingObject(final SchedulingObject aObject) {
        this.schedulingObject = aObject;
    }

    @Override
    public ModalDialogResult getDialogResult() {
        return dialogResult;
    }

    @Override
    public Set<SchedulingObject> getNewDependencies() {
        return this.newDependencies;
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void setParentWindow(final Window aWindow) {
        this.parentWindow = aWindow;
    }
}
