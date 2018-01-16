package com.example.cesar.pruebacolor;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import org.opencv.android.JavaCameraView;

import java.io.FileOutputStream;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.widget.Button;

/**
 * Created by Maca on 26-12-2016.
 */
public class MainActivity extends JavaCameraView implements PictureCallback {

    private static final String TAG = "opencv";
    private String mPictureFileName;


    public MainActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void takePicture(final String fileName) {
        Log.i(TAG, "Taking picture");
        this.mPictureFileName = fileName;
        // Postview y jpeg se envían en los mismos búferes si la cola no está vacía al realizar una captura.
        // Limpiar buffers para evitar que mCamera.takePicture quede bloqueado debido a un problema de
        // memoriamCamera.setPreviewCallback(null);

        // PictureCallback es implementado por la actividad actual
        mCamera.takePicture(null, null, this);
    }
    // Una vez tomada la fotografía, con esta función se guarda en la memoría del telefono
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.i(TAG, "Saving a bitmap to file");
        // Si la vista previa de la cámara se detuvo automáticamente. Empiece de nuevo.
        mCamera.startPreview();
        mCamera.setPreviewCallback(this);
        // Escribe la imagen en un archivo (en formato jpeg)
        try {
            FileOutputStream fos = new FileOutputStream(mPictureFileName);

            fos.write(data);
            fos.close();
        } catch (java.io.IOException e) {
            Log.e("PictureDemo", "Exception in photoCallback", e);
        }
    }
}

