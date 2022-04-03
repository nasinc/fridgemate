package mycompanyname.foodtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * Created by Nicholas on 9/12/2018.
 */

public class RecipeView extends AppCompatActivity{
    private WebView webView;
    Bundle received;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipeview);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        received = getIntent().getExtras();

        String site = received.getString("website");
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(site);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;

    }
}
/**public class WebViewActivity extends Activity {

private WebView webView;

public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.webview);

    webView = (WebView) findViewById(R.id.webView1);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.loadUrl(
            "http://egy-tech-droid.blogspot.com.eg/search/label/%D8%AA%D8%B7%D8%A8%D9%8A%D9%82%D8%A7%D8%AA%20%D8%AD%D8%B5%D8%B1%D9%8A%D8%A9");

}*/