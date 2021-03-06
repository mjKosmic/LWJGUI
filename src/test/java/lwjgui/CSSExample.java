package lwjgui;

import lwjgui.geometry.Orientation;
import lwjgui.geometry.Pos;
import lwjgui.paint.Color;
import lwjgui.scene.Scene;
import lwjgui.scene.Window;
import lwjgui.scene.control.Label;
import lwjgui.scene.layout.FlowPane;
import lwjgui.scene.layout.StackPane;

public class CSSExample extends LWJGUIApplication {
	public static final int WIDTH   = 320;
	public static final int HEIGHT  = 240;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(String[] args, Window window) {
		// Create a simple root pane
		StackPane pane = new StackPane();
		
		FlowPane flow = new FlowPane();
		flow.setOrientation(Orientation.HORIZONTAL);
		flow.setAlignment(Pos.CENTER);
		flow.setFillToParentWidth(true);
		flow.setFillToParentHeight(true);
		pane.getChildren().add(flow);

		createButton(flow, "Click Me!");
		createButton(flow, "Success!", "button-success");
		
		// Apply some style!
		pane.setStylesheet(""
				+ "flowpane {"
				+ "		gap: 6px;"
				+ "}"
				+ ""
				+ ""
				+ ".bootstrap-button {"
				+ "		background-color: #007bff;"
				+ "		box-shadow: 0px 0px 0px 0px #007bff80;"
				+ "		border-style: solid;"
				+ "		border-radius: 4px;"
				+ "		border-color: #0865cc;"
				+ "		border-width: 1px;"
				+ "		padding: 6px 16px;"
				+ "		transition: box-shadow 75ms, background-color 0.1s, border-color 0.1s;"
				+ "}"
				+ ""
				+ ".bootstrap-button:hover {"
				+ "		background-color: #0976ea;"
				+ "}"
				+ ""
				+ ".bootstrap-button:focus {"
				+ "		box-shadow: 0px 0px 0px 4px #007bff80;"
				+ "		border-color: #007bff;"
				+ "}"
				+ ""
				+ ".bootstrap-button:active {"
				+ "		background-color: #0d6fd8;"
				+ "}"
				+ ""
				+ ""
				+ ".button-success {"
				+ "		background-color: #28a745;"
				+ "		border-color: #218838;"
				+ "		box-shadow: 0px 0px 0px 0px #28a74580;"
				+ "}"
				+ ""
				+ ".button-success:hover {"
				+ "		background-color: #218838;"
				+ "		border-color: #218838;"
				+ "}"
				+ ""
				+ ".button-success:focus {"
				+ "		box-shadow: 0px 0px 0px 4px #28a74580;"
				+ "		border-color: #1e7e34;"
				+ "}"
				+ ""
				+ ".button-success:active {"
				+ "		background-color: #1e7e34;"
				+ "}");
		
		// Create a new scene
		window.setScene(new Scene(pane, WIDTH, HEIGHT));
		
		// Make window visible
		window.show();
	}

	private void createButton(FlowPane parent, String name, String... classes) {
		// Create pane to be styled! :)
		StackPane styledPane = new StackPane() {
			{
				this.setOnMousePressedInternal((event)->{
					this.cached_context.setSelected(this); // Force context selection when it's clicked DOWN, not on release.
					// This is just so the :focus pseudoclass looks nicer. Otherwise you can potentially see 2 buttons
					// selected at one time.
					
					// Alternatively you can use the custom :select pseudoclass for a similar effect.
					// :select Pseudo class is not valid CSS, but it's implemented in LWJGUI's CSS.
				});
			}
		};
		styledPane.getClassList().add("bootstrap-button");
		for (int i = 0; i < classes.length; i++) {
			styledPane.getClassList().add(classes[i]);
		}
		parent.getItems().add(styledPane);
		
		Label label = new Label(name);
		label.setTextFill(Color.WHITE);
		label.setMouseTransparent(true);
		styledPane.getChildren().add(label);
	}
}