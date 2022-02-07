package se.jensen.caw21;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.fasterxml.jackson.databind.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;

    public class APIClient {
        private String apiAddress;
        HttpURLConnection connection;

        public APIClient(String apiAddress) {

            this.apiAddress = apiAddress;
        }

        public ArrayList<String> getStringArray(String target) {
            JsonObject jObject = new JsonObject();
            ArrayList<String> myArrayOfStrings = new ArrayList<>();
            return myArrayOfStrings;
        }

        public boolean addclientblog(Clientblog newclientblogs) {
            String target = "/blog/create";
            boolean success = false;
            try {
                System.out.println(apiAddress+ target);
                URL url = new URL(apiAddress + target);

                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setDoOutput(true);


                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = newclientblogs.toJson().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int status = connection.getResponseCode();
                if (status < 300) {
                    success = true;
                }
                else {
                    System.out.println("Status code : "+ status);

                }
            }
            catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                connection.disconnect();
            }

            return success;
        }

        public Clientblog[] getclientblogs() {
            Clientblog[] clientblogs = null;
            String target = "/blog/list";
            BufferedReader reader;
            String line;
            StringBuilder responseContent = new StringBuilder();

            try {
                System.out.println(apiAddress+ target);
                URL url = new URL(apiAddress + target);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("accept", "application/json");

                int status = connection.getResponseCode();
                System.out.println("status code : " +status);

                if (status >= 300) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                }

                String jsonStr = responseContent.toString();
                ObjectMapper mapper = new ObjectMapper();
                clientblogs = mapper.readValue(jsonStr, Clientblog[].class);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                connection.disconnect();
            }

            return clientblogs;
        }


        public Clientblog getclientblogbyid(int id) {
            Clientblog clientblogs = null;
            System.out.println("id");
            String target = "/blog/view/" + id;
            BufferedReader reader;
            String line;
            StringBuilder responseContent = new StringBuilder();

            try {
                System.out.println(apiAddress+ target);
                URL url = new URL(apiAddress + target);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("accept", "application/json");
                connection.setRequestProperty("Content-Type","application/json;utf-8");
                int status = connection.getResponseCode();

                if (status >= 300) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                }
                String jsonStr = responseContent.toString();
                ObjectMapper mapper = new ObjectMapper();
                clientblogs = mapper.readValue(jsonStr, Clientblog.class);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                connection.disconnect();
            }

            return clientblogs;
        }

        public boolean  editblogbyid(Clientblog updateblogbyid, int blogid) {
            System.out.println("id");
            String target = "/blog/update/" + blogid;
            boolean success = false;

            try {
                //System.out.println(apiAddress+ target);
                URL url = new URL(apiAddress + target);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/json;utf-8");
                connection.setDoOutput(true);
                try(OutputStream os = connection.getOutputStream()){
                    if(connection.getOutputStream()!=null){
                        byte[] input = updateblogbyid.toJson().getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);

                    }
                }
                int status = connection.getResponseCode();


                if (status<300){
                    success=true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception: " + e);
            } finally {
                connection.disconnect();
            }

            return success;
        }


        public boolean deleteblogbyid(int id) {
            String target = "/blog/delete/" + id;
           // String target = "/blog/clear"; // http://127.0.0.1:8080/api/v1/blog/clear
            boolean success = false;

            try {
                System.out.println(apiAddress+ target);
                URL url = new URL(apiAddress + target);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");

                int status = connection.getResponseCode();

                if (status < 300) {
                    success = true;
                }
                else {
                    System.out.println("Status code : "+ status);
                }

                //System.out.println(responseContent.toString());
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                connection.disconnect();
            }

            return success;
        }

    }













