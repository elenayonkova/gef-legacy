package org.eclipse.draw2d;

import org.eclipse.draw2d.geometry.*;

/**
 * @author hudsonr
 * @since 2.1
 */
public class ScalableFreeformLayeredPane extends FreeformLayeredPane {

private double zoom = 3.0;

/**
 * @see org.eclipse.draw2d.Figure#getClientArea()
 */
public Rectangle getClientArea(Rectangle rect) {
	super.getClientArea(rect);
	rect.width /= zoom;
	rect.height /= zoom;
	return rect;
}

protected void paintClientArea(Graphics graphics) {
	if (getChildren().isEmpty())
		return;

	ScaledGraphics g = new ScaledGraphics(graphics);

	boolean optimizeClip = getBorder() == null || getBorder().isOpaque();
	if (!optimizeClip)
		g.clipRect(getBounds().getCropped(getInsets()));

	g.scale(zoom);
	g.pushState();
	paintChildren(g);
	g.dispose();
	graphics.restoreState();
}

/**
 * @see org.eclipse.draw2d.Figure#translateToParent(Translatable)
 */
public void translateToParent(Translatable t) {
	t.performScale(zoom);
}

/**
 * @see org.eclipse.draw2d.Figure#translateFromParent(Translatable)
 */
public void translateFromParent(Translatable t) {
	t.performScale(1 / zoom);
}

/**
 * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
 */
protected final boolean useLocalCoordinates() {
	return false;
}


}
