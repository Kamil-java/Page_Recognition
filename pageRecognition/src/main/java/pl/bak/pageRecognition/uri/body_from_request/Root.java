package pl.bak.pageRecognition.uri.body_from_request;

import java.util.List;

public class Root {
    private Domain domain;
    private boolean success;

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

