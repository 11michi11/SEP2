package client.view;

import javafx.scene.control.Alert;

public class Contact {

	public static void ContactMessage() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(
				"Contact email: daria.rybka.koch@gmail.com" +
				"\n" +
				"Phone number: 602 261 561");

		alert.showAndWait();
	}
}
