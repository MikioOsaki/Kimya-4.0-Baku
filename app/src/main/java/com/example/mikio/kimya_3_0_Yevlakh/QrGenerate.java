package com.example.mikio.kimya_3_0_Yevlakh;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class QrGenerate extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerate);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                //App läuft normal weiter.
            }
            else {
                requestPermission();
            }
        }

        String qrCodeName = getIntent().getExtras().getString("substanceName");
        String qrCodeData = getIntent().getExtras().getString("substanceID");
        TextView myText = (TextView) findViewById(R.id.qrInput);
        myText.setText("Sie wollen einen Qr-Code für den Stoff\n"+qrCodeName+" erstellen.\nDer Qr-Code wird die Stoff ID "+qrCodeData+" enthalten.");

    }

    @Override
    public void onBackPressed() {
        Intent iG = new Intent(this, QrGenSucheActivity.class);
        startActivity(iG);
        finish();
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(QrGenerate.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(QrGenerate.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(QrGenerate.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(QrGenerate.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }
    public void qrGenerator(View v){
        try {
            //setting size of qr code
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallestDimension = width < height ? width : height;

            //substanceCas wird als Inhalt des QR Codes übegeben
            String qrCodeData = getIntent().getExtras().getString("substanceID");
            //String qrCodeData = context.;
            //setting parameters for qr code
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap =new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            createQRCode(qrCodeData, charset, hintMap, smallestDimension, smallestDimension);

        } catch (Exception ex) {
            Log.e("QrGenerate",ex.getMessage());
        }
    }

    public  void createQRCode(String qrCodeData, String charset, Map hintMap, int qrCodeheight, int qrCodewidth){

        try {
            //generating qr code in bitmatrix type
            BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset), BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
            //converting bitmatrix to bitmap
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int[] pixels = new int[width * height];
            // All are 0, or black, by default
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = matrix.get(x, y) ? BLACK : WHITE;
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            //setting bitmap to image view
            ImageView myImage = (ImageView) findViewById(R.id.imageView1);
            myImage.setImageBitmap(bitmap);


            //String path = Environment.getExternalStorageDirectory().toString();
            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut;
            String qrCodeName = getIntent().getExtras().getString("substanceName");
            File file = new File(path, qrCodeName+".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
            fOut = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush(); // Not really required
            fOut.close(); // do not forget to close the stream

            MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
        }
        catch (Exception er){
            Log.e("QrGenerate",er.getMessage());
        }

    }
}