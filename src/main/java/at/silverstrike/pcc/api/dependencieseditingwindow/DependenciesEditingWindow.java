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

package at.silverstrike.pcc.api.dependencieseditingwindow;

import at.silverstrike.pcc.api.conventions.InitializableGuiComponent;
import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 * 
 */
public interface DependenciesEditingWindow extends InitializableGuiComponent {
    
    /**
     * Задаёт расчётный объект, зависимости которого отображаются в окне при старте.
     */
    void setSchedulingObject(final SchedulingObject aObject);
    
    
}
