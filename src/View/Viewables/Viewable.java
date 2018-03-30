package View.Viewables;

import View.ProgramRequestType;

public class Viewable {
    private ProgramRequestType requestType;

    public Viewable(){
    }

    public Viewable(ProgramRequestType requestType) {
        this.requestType = requestType;
    }

    public void setRequestType(ProgramRequestType requestType) {
        this.requestType = requestType;
    }

    public ProgramRequestType getRequestType() {
        return requestType;
    }
}
