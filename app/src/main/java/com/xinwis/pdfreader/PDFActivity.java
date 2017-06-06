package com.xinwis.pdfreader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import static java.lang.String.format;


public class PDFActivity extends Activity implements OnPageChangeListener {
    public static final String ABOUT_FILE = "about.pdf";
    public static final String SAMPLE_FILE = "sample.pdf";



    PDFView pdfView;
    String pdfName = ABOUT_FILE;

    int pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdfView= (PDFView) findViewById(R.id.pdfView);
        display(pdfName,true);
    }



    private void display(String assetFileName, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;
        setTitle(pdfName = assetFileName);

        pdfView.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .load();

    }



    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(format("%s %s / %s", pdfName, page, pageCount));
        if (page==pageCount){
            Toast.makeText(PDFActivity.this,"1秒后跳转下一文件",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    display(SAMPLE_FILE,true);
                }
            },1000);

        }
    }



    private boolean displaying(String fileName) {
        return fileName.equals(pdfName);
    }
}
