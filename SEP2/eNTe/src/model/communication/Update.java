package model.communication;

import java.io.Serializable;

public class Update implements Serializable {

    private WelcomingData data;

    public Update(WelcomingData data) {
        this.data = data;
    }

    public WelcomingData getData() {
        return data;
    }
}
