package at.htl.iea.model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("upload")
public class FileUploadServiceEndPoint {

    @GET
    public String getResource() {
        return "test";
    }
}
