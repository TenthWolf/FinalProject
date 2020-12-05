package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//ERRORS TO CHECK
//Client gets stops operating the problem could be the prime formula
public class CPrime extends Application {

	// IO streams
	DataOutputStream toServer = null;
	DataInputStream fromServer = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);
		/*
		 * Scanner scan = new Scanner(System.in);
		 * 
		 * try { Socket s = new Socket("localhost", 8000); DataInputStream dis = new
		 * DataInputStream(s.getInputStream()); DataOutputStream dout = new
		 * DataOutputStream(s.getOutputStream());
		 * 
		 * // TAKING USER INPUT int num = scan.nextInt(); // WRITING INTEGER TO SERVER
		 * dout.writeInt(num); String ans = dis.readUTF();
		 * System.out.println("\nNumber " + num + " Is Prime Number: " + ans);
		 * dout.flush(); dout.close(); s.close(); } catch (Exception e) {
		 * System.out.println(e); } scan.close(); }
		 */
	}

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Panel p to hold the label and text field
		BorderPane paneForTextField = new BorderPane();
		paneForTextField.setPadding(new Insets(5, 5, 5, 5));
		paneForTextField.setStyle("-fx-border-color: green");
		paneForTextField.setLeft(new Label("Enter a prime number: "));

		TextField tf = new TextField();
		tf.setAlignment(Pos.BOTTOM_RIGHT);
		paneForTextField.setCenter(tf);

		BorderPane mainPane = new BorderPane();
		// Text area to display contents
		TextArea ta = new TextArea();
		mainPane.setCenter(new ScrollPane(ta));
		mainPane.setTop(paneForTextField);

		// Create a scene and place it in the stage
		Scene scene = new Scene(mainPane, 450, 200);
		primaryStage.setTitle("Client"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		tf.setOnAction(e -> {
			try {
				// Get the radius from the text field
				// double radius = Double.parseDouble(tf.getText().trim());
				int num = Integer.parseInt(tf.getText().trim());

				// Send the radius to the server
				// toServer.writeDouble(radius);
				// toServer.flush();

				toServer.writeInt(num);
				toServer.flush();

				// Get flag from server
				boolean flag = fromServer.readBoolean();

				if (!flag) {
					ta.appendText("The number is prime \n");
				} else {
					ta.appendText("The number is not prime \n");
				}

				// flag = fromServer.readBoolean();

				// Display to the text area
				ta.appendText("The Prime number is " + num + "\n");
				// ta.appendText("The number " + flag + "is not prime");
			} catch (IOException ex) {
				System.err.println(ex);
			}
		});

		try {
			// Create a socket to connect to the server
			Socket socket = new Socket("localhost", 8000);

			// Create an input stream to receive data from the server
			fromServer = new DataInputStream(socket.getInputStream());

			// Create an output stream to send data to the server
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			ta.appendText(ex.toString() + '\n');
		}

	}

}
