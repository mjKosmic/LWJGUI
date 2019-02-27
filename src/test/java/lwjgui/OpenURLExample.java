package lwjgui;

import java.io.IOException;

import lwjgui.geometry.Pos;
import lwjgui.scene.Scene;
import lwjgui.scene.Window;
import lwjgui.scene.control.Button;
import lwjgui.scene.layout.StackPane;

public class OpenURLExample extends LWJGUIApplication {
	public static final int WIDTH   = 320;
	public static final int HEIGHT  = 240;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(String[] args, Window window) {
		// Add background pane
		StackPane pane = new StackPane();
		pane.setAlignment(Pos.CENTER);
		
		// Create the button for the box
		Button button = new Button("The Best LWJGL3 UI Solution (Click Me!)");
		button.setOnAction( (event)-> {
			try {
				LWJGUIUtil.openURLInBrowser("https://github.com/orange451/LWJGUI");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		pane.getChildren().add(button);
		
		// Set the scene
		window.setScene(new Scene(pane, WIDTH, HEIGHT));
		window.show();
	}

	@Override
	public void run() {}

	@Override
	public String getProgramName() {
		return "Open URL Example";
	}
}