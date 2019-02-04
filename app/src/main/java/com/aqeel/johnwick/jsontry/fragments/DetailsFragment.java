package com.aqeel.johnwick.jsontry.fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqeel.johnwick.jsontry.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class DetailsFragment extends Fragment {
    ImageView imageView1;
    String imgDetails, urlImgBlur="";
    Bitmap bmp;
    byte[] bitmapArr;
TextView imgDetailText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.details_fragment, container, false);
    imageView1 = v.findViewById(R.id.details_img_blur);
        imgDetailText = v.findViewById(R.id.details_text_details);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            imgDetails = getArguments().getString("imgDetails");
            urlImgBlur = getArguments().getString("urlImgBlur");
            byte[] bitmapArr = getArguments().getByteArray("bitmapArr");
            bmp = BitmapFactory.decodeByteArray(bitmapArr,0,bitmapArr.length);
            imageView1.setImageBitmap(bmp);

        }


        imgDetailText.setText(imgDetails);

        makeBlur();


        return v;
    }
    void makeBlur(){
        int radiusArr[] = new int[]{25, 23, 21, 19, 17};
        BitmapDrawable drawable = (BitmapDrawable) imageView1.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Bitmap blurred = blurRenderScript(bitmap, radiusArr[2]);//second parametre is radius
        imageView1.setImageBitmap(blurred);
    }
    @SuppressLint("NewApi")
    private Bitmap blurRenderScript(Bitmap smallBitmap, int radius) {

        try {
            smallBitmap = RGB565toARGB888(smallBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Bitmap bitmap = Bitmap.createBitmap(
                smallBitmap.getWidth(), smallBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(getContext());

        Allocation blurInput = Allocation.createFromBitmap(renderScript, smallBitmap);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(radius); // radius must be 0 < r <= 25
        blur.forEach(blurOutput);

        blurOutput.copyTo(bitmap);
        renderScript.destroy();

        return bitmap;

    }

    private Bitmap RGB565toARGB888(Bitmap img) throws Exception {
        int numPixels = img.getWidth() * img.getHeight();
        int[] pixels = new int[numPixels];

        //Get JPEG pixels.  Each int is the color values for one pixel.
        img.getPixels(pixels, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());

        //Create a Bitmap of the appropriate format.
        Bitmap result = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);

        //Set RGB pixels.
        result.setPixels(pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight());
        return result;
    }



}
