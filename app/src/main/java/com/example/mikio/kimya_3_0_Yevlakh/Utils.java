
package com.example.mikio.kimya_3_0_Yevlakh;


        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

public class Utils {


    public static void openBrowser(Context context, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

    public static String getSpinnerSelection(Spinner spinner) {

        if (spinner.getSelectedItem().toString().length() == 0) {
            return "kein Element ausgewählt";
        } else return spinner.getSelectedItem().toString();
    }
}