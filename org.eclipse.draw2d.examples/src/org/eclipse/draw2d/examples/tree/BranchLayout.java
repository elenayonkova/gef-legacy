package org.eclipse.draw2d.examples.tree;

import java.util.List;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.Graphics;

/**
 * 
 * @author hudsonr
 * Created on Apr 22, 2003
 */
public abstract class BranchLayout extends AbstractLayout {

final TreeBranch branch;
int[] cachedContourLeft;
int[] cachedContourRight;
int depth = -1;

public boolean horizontal = true;
int[] preferredRowHeights;
int rowHeight;

public BranchLayout(TreeBranch branch) {
	this.branch = branch;
}

abstract void calculateDepth();

public int[] getContourLeft() {
	if (cachedContourLeft == null)
		updateContours();
	return cachedContourLeft;
}

public int[] getContourRight() {
	if (cachedContourRight == null)
		updateContours();
	return cachedContourRight;
}

public int getDepth() {
	if (depth == -1)
		calculateDepth();
	return depth;
}

public int[] getPreferredRowHeights() {
	if (preferredRowHeights == null)
		updateRowHeights();
	return preferredRowHeights;
}

List getSubtrees() {
	return branch.getContentsPane().getChildren();
}

int getMajorSpacing() {
	return branch.getRoot().getMajorSpacing();
}

public void invalidate() {
	preferredRowHeights = null;
	cachedContourLeft = null;
	cachedContourRight = null;
	depth = -1;
	super.invalidate();
}

public boolean isHorizontal() {
	return horizontal;
}

abstract void paintLines(Graphics g);

public void setHorizontal(boolean value) {
	horizontal = value;
}

void setRowHeights(int heights[], int offset) {
	if (rowHeight != heights[offset]) {
		rowHeight = heights[offset];
		branch.revalidate();
	}
}

abstract void updateContours();

abstract void updateRowHeights();

}
