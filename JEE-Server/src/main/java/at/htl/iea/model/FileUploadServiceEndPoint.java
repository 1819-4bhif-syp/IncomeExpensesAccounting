package at.htl.iea.model;

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
        System.out.println(csvFile);
        fo.write(csvFile.getData());
        fo.close();
        System.out.println("file saved");
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
}