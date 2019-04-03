package com.example.nevin.secit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QR_Scanner extends Fragment {
    ImageView imageView;
    Button button,addbutton;
    TextView textView ;

    EditText editText;
    String QrValue ;
    Boolean qrHidden = true;
    public final static int QRcodeWidth = 300 ;
    Bitmap bitmap ,imageBitmap;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_qr__scanner, container, false);

        addbutton=(Button)rootView.findViewById(R.id.addButton);
        button=(Button)rootView.findViewById(R.id.qrbutton);
        imageView=(ImageView)rootView.findViewById(R.id.qrimage);
        textView = (TextView)rootView.findViewById(R.id.EncryptedCode);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QrValue = "123456789123";
                qrHidden = (qrHidden)?false:true;
                imageView.setVisibility((qrHidden)?View.VISIBLE:View.GONE);
                if(!QrValue.isEmpty()) {
                    try
                    {
                        bitmap = TextToImageEncode(QrValue);
                        imageView.setImageBitmap(bitmap);
                        imageView.setDrawingCacheEnabled(true);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Enter Something to Generate QR", Toast.LENGTH_SHORT).show();
                }

            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final AlertDialog.Builder alertbuilder = new AlertDialog.Builder(getContext());
                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                alertbuilder.setView(R.layout.alertdialog_edittext);
                alertbuilder.setTitle("AADHAAR_NUMBER");
//                alertbuilder.setMessage("Enter Aadhaar_Number");
                alertbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QrValue = "123456789123";

                        if(!QrValue.isEmpty()) {
                            try
                            {   QrValue = editText.getText().toString();
                                String binary = "";
                                for (int i = 0; i < QrValue.length(); i++) {
                                    String x = "";
                                    int temp = 0;
                                    int base2 = 1;
                                    int j = Integer.parseInt("" + QrValue.charAt(i));
                                    while (j > 0) {
                                        int a = j % 2;
                                        temp = temp + (a * base2);
                                        base2 = base2 * 10;
                                        j = j / 2;
                                    }
                                    x = String.format("%04d", temp);
                                    binary = binary + x;
                                }
                                String decrypted = "";
                                long base = 1;
                                long temp1 = 0,a;
                                for (int i = 0; i < binary.length(); i+=12) {
                                    long n = Long.parseLong(binary.substring(i,i+12));
                                    base = 1;
                                    temp1 = 0;
                                    while(n>0){
                                        a = n%10;
                                        temp1 = temp1 + (a*base);
                                        base = base * 2;
                                        n = n/10;
                                    }
                                    String tempStr = String.format("%04d",temp1);
                                    decrypted = decrypted +""+tempStr;

                                }
                                System.out.println(decrypted);
                                QrValue = decrypted;
                                textView.setText(QrValue);
                                bitmap = TextToImageEncode(QrValue);
                                imageView.setImageBitmap(bitmap);
                                imageView.setDrawingCacheEnabled(true);

                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            Toast.makeText(getContext(), "Enter Something to Generate QR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertbuilder.show();
            }
        });

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v)
            {
                imageBitmap=imageView.getDrawingCache();
                String root= Environment.getExternalStorageDirectory()+"/QR_CODE/img.jpg";
                new File(Environment.getExternalStorageDirectory()+"/QR_CODE/").mkdirs();
                File cache=new File(root);
                System.out.println("bow "+imageBitmap);
                System.out.println("CACHE "+cache.exists());
                try {
                    cache.createNewFile();
                    System.out.println("CACHE "+cache.exists());
                    System.out.println("Cache "+cache.getPath());

                    FileOutputStream fos=new FileOutputStream(cache);
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100,fos);
                    fos.close();



                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.setType("Image/jpeg");
                i.putExtra(Intent.EXTRA_STREAM, Uri.parse(String.valueOf(cache)));
                startActivity(Intent.createChooser(i, "Share Through..."));
                return false;
            }
        });

        return rootView;
    }

    Bitmap TextToImageEncode(String Value) throws WriterException {

        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? getResources().getColor(R.color.QRcolorB) : getResources().getColor(R.color.QRcolorW);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 300, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

}