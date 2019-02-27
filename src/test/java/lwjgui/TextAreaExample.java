package lwjgui;

import java.io.IOException;

import lwjgui.geometry.Insets;
import lwjgui.scene.Scene;
import lwjgui.scene.Window;
import lwjgui.scene.control.Button;
import lwjgui.scene.control.text_input.PasswordField;
import lwjgui.scene.control.text_input.TextArea;
import lwjgui.scene.control.text_input.TextField;
import lwjgui.scene.layout.BorderPane;
import lwjgui.scene.layout.HBox;

public class TextAreaExample extends LWJGUIApplication {
	public static final int WIDTH   = 320;
	public static final int HEIGHT  = 240;

	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@Override
	public void start(String[] args, Window window) {
		// Create background pane
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(8,8,8,8));
		
		HBox hbox = new HBox();
		hbox.setSpacing(8);
		pane.setTop(hbox);
		
		// Create an Input Field
		TextField f = new TextField();
		f.setPrompt("Text Field");
		hbox.getChildren().add(f);
		
		// Password field
		PasswordField p = new PasswordField();
		p.setPrompt("Password Field");
		hbox.getChildren().add(p);
		
		// Create a Text Area
		TextArea t = new TextArea();
		t.setPrefWidth(250);
		t.setWordWrap(true);
		pane.setCenter(t);
		
		// Clear text button
		Button b = new Button("Clear Text");
		b.setPrefWidth(250);
		b.setOnAction((event)->{
			t.clear();
		});
		pane.setBottom(b);
		
		// Set the scene
		window.setScene(new Scene(pane, WIDTH, HEIGHT));
		window.show();
	}

	@Override
	public void run() {
		//
	}

	@Override
	public String getProgramName() {
		return "Text Area Example";
	}
}