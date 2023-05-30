
import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;


public class MultipartBodyPublisher {
    private final Map<String, Object>data;
    private final String boundary;
    MultipartBodyPublisher(Map<String, Object>data, String boundary){
        this.boundary= boundary;
        this.data= data;
    }
    public  HttpRequest.BodyPublisher build() throws  ApiException {

        List<byte[]> byteArrays = new ArrayList<>();
        byte[] boundarySeparator = getBoundarySeparator();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry != null && entry.getKey() != null && entry.getValue() != null) {
                byteArrays.add(boundarySeparator);
                if (entry.getValue() instanceof File) {
                    processFileEntry(byteArrays, entry);
                } else if (FileUtils.isListOfFiles(entry.getValue())) {
                    processListOfFilesEntry(byteArrays, entry);
                } else {
                    processKeyValueEntry(byteArrays, entry);
                }
            }
        }
        byteArrays.add(getBoundaryEnd());
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }
    public  HttpRequest.BodyPublisher buildUsingApache() throws  ApiException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof String) {
                builder.addPart(key, new StringBody((String) value, ContentType.TEXT_PLAIN));
            } else if (value instanceof byte[]) {
                builder.addBinaryBody(key, (byte[]) value, ContentType.APPLICATION_OCTET_STREAM, key);
            } else {
                throw new IllegalArgumentException("Unsupported data type for key: " + key);
            }
        }

        return null;
    }


    private static void processFileEntry(List<byte[]> byteArrays, Map.Entry<String, Object> entry) throws ApiException{
        File file = (File) entry.getValue();
        Path path = file.toPath();
        String mimeType;
        try {
            mimeType = Files.probeContentType(path);
            byte[] fileBytes = Files.readAllBytes(path);
            byteArrays.add(getFileHeader(entry.getKey(), path.getFileName().toString(), mimeType));
            byteArrays.add(fileBytes);
            byteArrays.add(getLineBreak());
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }
    private  void processListOfFilesEntry(List<byte[]> byteArrays, Map.Entry<String, Object> entry) throws ApiException {
        List<File> files = (List<File>) entry.getValue();
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            Path path = file.toPath();
            String mimeType ;
            try {
                mimeType = Files.probeContentType(path);
                byte[] fileBytes = Files.readAllBytes(path);
                byteArrays.add(getFileHeader(entry.getKey(), path.getFileName().toString(), mimeType));
                byteArrays.add(fileBytes);
                byteArrays.add(getLineBreak());
            } catch (IOException e) {
                throw new ApiException(e);
            }
            if (i + 1 < files.size()) {
                byteArrays.add(getBoundarySeparator());
            }
        }
    }

    private  void processKeyValueEntry(List<byte[]> byteArrays, Map.Entry<String, Object> entry) {
        byteArrays.add(("\"" + entry.getKey() + "\"\r\n\r\n" + entry.getValue().toString() + "\r\n").getBytes(StandardCharsets.UTF_8));
    }


    private  byte[] getBoundarySeparator() {
        return  ("--" + boundary + "\r\nContent-Disposition: form-data; name=").getBytes(StandardCharsets.UTF_8);
    }

    private  byte[] getBoundaryEnd() {
        return("--" + boundary + "--").getBytes(StandardCharsets.UTF_8);
    }

    private static byte[] getFileHeader(String fieldName, String fileName, String mimeType) {
        return ("\"" + fieldName + "\"; filename=\"" + fileName + "\"\r\nContent-Type: " + mimeType + "\r\n\r\n")
                .getBytes(StandardCharsets.UTF_8);
    }

    private  static byte[] getLineBreak() {
        return "\r\n".getBytes(StandardCharsets.UTF_8);
    }


}
