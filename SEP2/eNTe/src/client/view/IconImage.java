package client.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconImage {

	private static ImageView annIcon; //announcement icon
	private static ImageView disIcon; //discussion icon
	private static ImageView homIcon; //homework icon
	private static ImageView impIcon; //important icon
	private static ImageView parIcon; //parental icon
	private static Image imgAnn = new Image("/client/view/fxml/annIcon.png");
	private static Image imgDis = new Image("/client/view/fxml/discussionIcon.png");
	private static Image imgHom = new Image("/client/view/fxml/homeworkIcon.png");
	private static Image imgPar = new Image("/client/view/fxml/pIcon.png");
	private static Image imgImp= new Image("/client/view/fxml/importantIcon.png");

	public static ImageView getAnnIcon() {
		annIcon = new ImageView(imgAnn);
		annIcon.setFitHeight(30);
		annIcon.setFitWidth(30);
		return annIcon;
	}
	public static ImageView getDisIcon() {
		disIcon = new ImageView(imgDis);
		disIcon.setFitHeight(35);
		disIcon.setFitWidth(35);
		return disIcon;
	}
	public static ImageView getHomIcon() {
		homIcon = new ImageView(imgHom);
		homIcon.setFitHeight(30);
		homIcon.setFitWidth(30);
		return homIcon;
	}
	public static ImageView getImpIcon() {
		impIcon = new ImageView(imgImp);
		impIcon.setFitHeight(30);
		impIcon.setFitWidth(30);
		return impIcon;
	}
	public static ImageView getParIcon() {
		parIcon = new ImageView(imgPar);
		parIcon.setFitHeight(15);
		parIcon.setFitWidth(15);
		return parIcon;
	}

}
