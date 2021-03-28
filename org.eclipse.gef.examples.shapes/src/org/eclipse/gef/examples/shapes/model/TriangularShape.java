/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.gef.examples.shapes.model;

import org.eclipse.swt.graphics.Image;

/**
 * An triangular shape.
 * 
 * @author Elena Yonkova
 */

public class TriangularShape extends Shape {
	
	/** A 16x16 pictogram of an elliptical shape. */
	private static final Image TRIANGLE_ICON = createImage("icons/triangle16.gif");

	private static final long serialVersionUID = 1;

	public Image getIcon() {
		return TRIANGLE_ICON;
	}

	public String toString() {
		return "Triangle " + hashCode();
	}
	
}
