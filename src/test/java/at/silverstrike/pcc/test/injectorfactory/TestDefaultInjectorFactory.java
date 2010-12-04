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

package at.silverstrike.pcc.test.injectorfactory;

import junit.framework.Assert;

import org.junit.Test;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParserFactory;
import at.silverstrike.pcc.api.version.PccVersionReader;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;

public class TestDefaultInjectorFactory {
    @Test
    public void test01()
    {
        final InjectorFactory injectorFactory = new DefaultInjectorFactory();
        final Injector injector = injectorFactory.createInjector();
        
        Assert.assertNotNull(injector);
        
        try
        {
            injector.getInstance(DebugIdRegistry.class);
            injector.getInstance(Tj3DeadlinesFileParserFactory.class);
            injector.getInstance(PccVersionReader.class);
        }
        catch (final ConfigurationException exception)
        {
            Assert.fail(exception.getMessage());
        }
    }
}
