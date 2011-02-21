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
package at.silverstrike.pcc.api.conventions;

public class PccException extends Exception {
	private static final long serialVersionUID = 1L;

	public PccException(final String aMessage) {
		super(aMessage);
	}

	public PccException(final Throwable aThrowable) {
		super(aThrowable);
	}
}
