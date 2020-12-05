package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

//ERRORS TO CHECK:
//Error on putting numPrime class inside public start causes error
//Finding a way to put prime formula inside stage without causing errors

public class SPrime extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);

	}

	// Check if numbers are prime
	// public static String numPrime(int num) {
	// check if is number less than 2
	// will return no
	// if (num < 2) {
	// return "No";
	// }
	// i is equal to 2
	// int i = 2;
	// while 2 is less than 2
	// if num and i are equal to 0 return no
	// while (i < num) {
	// if (num % i == 0) {
	// return "No";
	// }
	// i++;
	// }

	// return "Yes";

	// }

	@Override
	public void start(Stage primaryStage) {
		// Text area for displaying contents
		TextArea ta = new TextArea();

		// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("Server"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		new Thread(() -> {

			try {
				ServerSocket ss = new ServerSocket(8000);
				Platform.runLater(() -> ta.appendText("Server started at " + new Date() + '\n'));

				// establishes connection
				Socket s = ss.accept();

				DataInputStream inputFromClient = new DataInputStream(s.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(s.getOutputStream());

				while (true) {

					int num = inputFromClient.readInt();
					boolean flag = false;

					// check if is number less than 2
					// will return no
					if (num < 2) {
					}
					// i is equal to 2
					int i = 2;
					// while 2 is less than 2
					// if num and i are equal to 0 return no
					while (i < num) {
						if (num % i == 0) {
							flag = true;
						}
						i++;
					}

					outputToClient.writeBoolean(flag);

					if (!flag)
						System.out.println(num + " is a prime number.");
					else
						System.out.println(num + " is not a prime number.");

					// num = inputToClient.readInt();

					ta.appendText("Prime number received from client is: " + num + '\n');

				}
				// outputToClient.writeBoolean(flag);
				// From previous server example use as reference

				// Receive radius from the client
				// double radius = inputTOClient.readDouble();

				// Compute area
				// double area = radius * radius * Math.PI;

				// Send area back to the client
				// dout.writeDouble(area);

				// ta.appendText("Radius received from client: " + radius + '\n');
				// ta.appendText("Area is: " + area + '\n');

				// CALLING FUNCTION AND
				// WRITING RETURN VALUE TO CLIENT

			} catch (Exception e) {
				System.out.println(e);
			}

		}).start();

	}

}
