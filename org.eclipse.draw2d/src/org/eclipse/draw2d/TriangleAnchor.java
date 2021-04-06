/*******************************************************************************
 * Copyright (c) 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.draw2d;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class TriangleAnchor extends ChopboxAnchor {

	protected TriangleAnchor() {
	}

	public TriangleAnchor(IFigure owner) {
		super(owner);
	}

	public Point getLocation(Point reference) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBox());
		r.translate(-1, -1);
		r.resize(1, 1);
		getOwner().translateToAbsolute(r);

		Point ref = r.getCenter().negate().translate(reference);
		
		if (ref.x == 0)
			return new Point(reference.x, (ref.y > 0) ? r.bottom() : r.y);
		if (ref.y == 0)
			return new Point((ref.x > 0) ? r.right() : r.x, reference.y);


		int size;
		size = Math.min(r.height, r.width / 2);
		r.y += (r.height - size) / 2;
		size = Math.max(size, 1); // Size cannot be negative

		Point head, p2, p3;
		int d1, d2, d3;
		head = new Point(r.x + r.width / 2, r.y);
		p2 = new Point(head.x - size, head.y + size);
		p3 = new Point(head.x + size, head.y + size);

		Point p;
		if (Math.abs(reference.x - p2.x) + Math.abs(reference.y - p2.y) > Math
				.abs(reference.x - p3.x) + Math.abs(reference.y - p3.y)) {
			p = p3;
		} else {
			p = p2;
		}
		d1 = distance(reference, head);
		d2 = distance(reference, p2);
		d3 = distance(reference, p3);

		p = head;
		if (d1 < d2 && d1 < d3) {
			p = head;
		}
		if (d2 < d1 && d2 < d3) {
			p = p2;
		}
		if (d3 < d1 && d3 < d2) {
			p = p3;
		}

		return p;
	}

	private int distance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	public boolean equals(Object obj) {
		if (obj instanceof TriangleAnchor) {
			TriangleAnchor other = (TriangleAnchor) obj;
			return other.getOwner() == getOwner()
					&& other.getBox().equals(getBox());
		}
		return false;
	}

	public int hashCode() {
		if (getOwner() != null)
			return getOwner().hashCode();
		else
			return super.hashCode();
	}
}
