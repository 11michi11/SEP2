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


	private String value;

	SpecialType(String value) {
		this.value = value;
	}

	public void doAction() {
	}
}
