package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class Encoding {
//    private String encoding;
//    private String decoding;
    Base64.Encoder encoder = null;
    Base64.Decoder decoder = null;
    Bitmap origin;

    public Encoding (Bitmap picture){
        this.origin = picture;
    }
    //type 0 : galllery, type 1: community
    public void EncodingImage (String email, int type, String content) throws JSONException {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encoder = Base64.getEncoder();
        }
        BitmapFactory.Options bo = new BitmapFactory.Options();

        // Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = this.origin;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap image = Bitmap.createScaledBitmap(bitmap, 100, 100, true);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encodedBytes = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedBytes = encoder.encodeToString(byteArray);
        }
        String url = null;
        PostServer postServer = null;
        JSONObject jsonObject = new JSONObject();
        if (type == 0) { // gallery
            jsonObject.put("image", encodedBytes);
            jsonObject.put("email", email);
            url = "http://192.249.19.243:0380/api/user/new-image";
            postServer = new PostServer(url, jsonObject);
            postServer.execute();
        }
        else if (type == 1) { // community
            jsonObject.put("image", encodedBytes);
            jsonObject.put("content", content);
            jsonObject.put("email", email);
            url = "http://192.249.19.243:0380/api/user/new-community";
            postServer = new PostServer(url, jsonObject);
            postServer.execute();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bitmap DecodingImage(String image) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            decoder = Base64.getDecoder();
        }
        byte[] bytes = decoder.decode(image);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Bitmap bitmap = BitmapFactory.decodeStream(bais);
        Bitmap bm2 = Bitmap.createScaledBitmap(bitmap, 800, 800, true);
        return bm2;
    }
}

/*public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("$###$@@#@", "sdf");

        setContentView(R.layout.activity_main);
        Log.d("$###$@@#@", "sdf");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //사진
                    Drawable drawable = getResources().getDrawable(R.drawable.aa);
                    ///
                    Base64.Encoder encoder = null;
                    Base64.Decoder decoder = null;

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        encoder = Base64.getEncoder();
                        decoder = Base64.getDecoder();

                    }
                    BitmapFactory.Options bo = new BitmapFactory.Options();
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    Log.d("$###$@@#@", "sdf");

                    Bitmap image = Bitmap.createScaledBitmap(bitmap, 100, 100, true);


                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String encodedBytes = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        Log.d("$###$@@#@", "sdfafsddsdsfdsfsdf");

                        encodedBytes = encoder.encodeToString(byteArray);
                    }
                    URL url = new URL("http://192.249.19.243:0380/api/user/new-image");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");

                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("data", encodedBytes);
                    jsonObject.put("name", "trial");
                    Log.d("$###$@@#@", jsonObject.toString());
                    OutputStream outputStream = connection.getOutputStream();
                    //OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
                    Log.d("TAG", jsonObject.toString());
                    outputStream.write(jsonObject.toString().getBytes("UTF-8"));
                    Log.d("TAG", "OK");
                    outputStream.flush();

                    String result;

                    InputStreamReader tmp = new InputStreamReader(connection.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuilder builder = new StringBuilder();
                    String str;
                    while ((str = reader.readLine()) != null) {
                        builder.append(str + "\n");
                    }
                    result = builder.toString();
                    Log.d("TAG", result);

                    URL url1 = new URL("http://192.249.19.243:0380/api/user/image");
                    HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                    Log.d("TAG1", "OK");

                    connection1.setRequestMethod("GET");
                    connection1.setRequestProperty("User-Agent", "Mozilla/5.0");
                    Log.d("TAG2", "OK");


                    //Charset charset = Charset.forName("UTF-8");
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection1.getInputStream()));                    Log.d("TAG", "OK");
                    Log.d("TAG3", "OK");

                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    Log.d("RESULT", response.toString());
                    Log.d("RESULT", encodedBytes);
                    JSONObject json = null;
                    json = new JSONObject(response.toString());




                    if (json.get("data").equals(encodedBytes)) {
                        Log.d("RESULT", "OK");
                        byte[] bytes = decoder.decode(json.getString("data"));
                        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                        Bitmap bm = BitmapFactory.decodeStream(bais);
                        Bitmap bm2 = Bitmap.createScaledBitmap(bitmap, 800, 800, true);

                        ImageView imageView = (ImageView) findViewById(R.id.image1);
                        imageView.setImageBitmap(bm2);


                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}*/
