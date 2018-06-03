package lwjgui;

import static org.lwjgl.glfw.GLFW.glfwGetFramebufferSize;
import static org.lwjgl.glfw.GLFW.glfwGetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.nanovg.NanoVGGL3;

import lwjgui.collections.ObservableList;
import lwjgui.scene.Node;
import lwjgui.scene.Parent;
import lwjgui.scene.Scene;

public class Context {
	private long windowHandle;
	private long nvgContext;
	
	private int windowWidth = 1;
	private int windowHeight = 1;
	private int screenPixelRatio = 1;
	
	private Node selected = null;
	private Node hovered = null;
	
	private double mouseX;
	private double mouseY;
	protected boolean focused;
	
	public Context( long window ) {
		windowHandle = window;
		
        int flags = NanoVGGL3.NVG_STENCIL_STROKES | NanoVGGL3.NVG_ANTIALIAS;
        nvgContext = NanoVGGL3.nvgCreate(flags);
	}
	
	public boolean isFocused() {
		return focused;
	}
	
	protected void updateContext() {
		int[] windowWidthArr = {0}, windowHeightArr = {0};
		int[] frameBufferWidthArr = {0}, frameBufferHeightArr = {0};
		int[] xposArr = {0}, yposArr = {0};
		glfwGetWindowSize(windowHandle, windowWidthArr, windowHeightArr);
		glfwGetFramebufferSize(windowHandle, frameBufferWidthArr, frameBufferHeightArr);
		glfwGetWindowPos(windowHandle, xposArr, yposArr);
		
		if ( windowWidthArr[0] == 0 ) {
			return;
		}

		windowWidth = windowWidthArr[0];
		windowHeight = windowHeightArr[0];
		screenPixelRatio = frameBufferWidthArr[0]/windowWidthArr[0];
		
		double[] mousePosX = {0},mousePosY = {0};
		GLFW.glfwGetCursorPos(windowHandle, mousePosX, mousePosY);
		mouseX = mousePosX[0];
		mouseY = mousePosY[0];
		
		LWJGUIWindow window = LWJGUI.getWindowFromContext(windowHandle);
		Scene scene = window.getScene();
		hovered = calculateHover(null, scene);
	}
	
	protected Node calculateHover(Node parent, Node root) {
		// Use scene as an entry point into nodes
		if ( parent == null && root instanceof Scene ) {
			root = ((Scene)root).getRoot();
		}
		
		// Ignore if unclickable
		if ( root.isMouseTransparent() ) {
			return parent;
		}
		
		// If mouse is out of our bounds, we're not clickable
		if ( mouseX < root.getAbsoluteX() || mouseX > root.getAbsoluteX() + root.getWidth() )
			return parent;
		if ( mouseY < root.getAbsoluteY() || mouseY > root.getAbsoluteY() + root.getHeight() )
			return parent;
		
		// Check children
		if ( root instanceof Parent ) {
			ObservableList<Node> children = ((Parent)root).getChildren();
			for (int i = 0; i < children.size(); i++) {
				Node ret = calculateHover( root, children.get(i));
				if ( ret != null && ! ret.equals(root)) {
					return ret;
				}
			}
		}
		
		return root;
	}

	public int getWidth() {
		return windowWidth;
	}
	
	public int getHeight() {
		return windowHeight;
	}
	
	public int getPixelRatio() {
		return screenPixelRatio;
	}
	
	public long getNVG() {
		return nvgContext;
	}

	public long getWindowHandle() {
		return windowHandle;
	}

	public boolean isSelected(Node node) {
		if ( selected == null ) {
			return false;
		}
		return node.equals(selected);
	}
	
	public boolean isHovered(Node node) {
		if ( hovered == null )
			return false;
		return node.equals(hovered);
	}
	
	public void setSelected(Node node) {
		this.selected = node;
	}

	public double getMouseX() {
		return mouseX;
	}
	
	public double getMouseY() {
		return mouseY;
	}

	public Node getHovered() {
		return hovered;
	}
}
