package View.Viewables;

import View.ShowRequestType;

public abstract class Viewable {
    private ShowRequestType requestType;

    public void setRequestType(ShowRequestType requestType) {
        this.requestType = requestType;
    }

    public ShowRequestType getRequestType() {
        return requestType;
    }
}
