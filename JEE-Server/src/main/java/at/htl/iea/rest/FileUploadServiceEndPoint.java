package at.htl.iea.rest;

import at.htl.iea.model.CsvFile;
import com.opencsv.CSVReader;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

@Path("files")
public class FileUploadServiceEndPoint {

    @GET
    public Response hello() {
        return Response.status(Response.Status.OK).entity("Connected to project IEA ...").build();
    }

    @POST
    @Path("uploadcsv")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadBinary(@MultipartForm CsvFile csvFile) throws IOException {
        BufferedReader fileReader = null;
        String line = "";

        System.out.println("===========================================================================================");
        File tmpFile = File.createTempFile("tmpcsv", ".csv");
        FileOutputStream fo = new FileOutputStream(tmpFile.getAbsolutePath());
        fo.write(csvFile.getData());
        fo.close();
        int cnt = 0;
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(tmpFile),"UTF-16"),'\t');
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cnt + ":        " + cell + "\t");
                }
                System.out.println();
                cnt++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        tmpFile.delete();

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("uploadtext")
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response uploadText(String content) {
        System.out.println(content);
        return Response.ok("content recveived").build();
    }
}