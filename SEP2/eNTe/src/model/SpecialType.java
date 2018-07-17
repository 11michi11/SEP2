package model;

import client.controller.ClientController;

import java.io.Serializable;

public enum SpecialType implements Serializable {

	NORMAL("normal"),
	IMPORTANT("important"){
		@Override
		public void doAction(){
			ClientController controller = ClientController.getInstance();
			controller.sendImportantPostEmail();
		}
	},
	PARENTAL("parental"){
		@Override
		public void doAction(){
			ClientController controller = ClientController.getInstance();
			controller.sendParentalPostEmail();
		}
	};

	SpecialType(String value) {
		this.value = value;
	}

	private String value;

	public void doAction() {
	}

	public String getValue() {
		return value;
	}
}
