package bdjobscareers.bd.com.bdjobscareers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BdJobsCareers extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProgressBar proBar;
    private WebView bdJobsCareers;
    public static String FACEBOOK_URL = "https://web.facebook.com/bdjobscareers/";
    public static String FACEBOOK_PAGE_ID = "1491104977572990";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd_jobs_careers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#0000d6"));
        }


        //Floting action button

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isNetworkConnected()) {

                    Intent i = new Intent(Intent.ACTION_VIEW);

                    i.setData(Uri.parse("market://details?id=alljobscircularbd.bd.com.alljobscircularbd"));
                    startActivity(i);

                } else {
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });


        //Check internet

        if (isNetworkConnected()) {

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

        // Webview
        bdJobsCareers = (WebView) findViewById(R.id.web1);
        WebSettings webSettings = bdJobsCareers.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Improve wevView performance

        bdJobsCareers.clearCache(true);
        bdJobsCareers.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        bdJobsCareers.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        bdJobsCareers.getSettings().setAppCacheEnabled(false);
        bdJobsCareers.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        bdJobsCareers.setInitialScale(1);
        // Configure related browser settings
       // bdJobsCareers.getSettings().setLoadsImagesAutomatically(true);
        // Enable responsive layout
        //bdJobsCareers.getSettings().setUseWideViewPort(true);
// Zoom out if the content width is greater than the width of the viewport
      //  bdJobsCareers.getSettings().setLoadWithOverviewMode(true);
        //bdJobsCareers.getSettings().setSupportZoom(true);
        bdJobsCareers.getSettings().setDisplayZoomControls(false);
        bdJobsCareers.getSettings().setBuiltInZoomControls(true);
        // chakrirkhobor.setVerticalScrollBarEnabled(false);
        bdJobsCareers.setHorizontalScrollBarEnabled(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);

        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);


        bdJobsCareers.loadUrl("https://www.bdjobscareers.com/");
        //bdJobsCareers.loadUrl("https://scheduler-hcir-int-us1.sec3ure.com/Scheduler?HCIRID=977272&SSOID=977272&token=87ae4b83a2e47c31d480c5749253653a");
        bdJobsCareers.setWebViewClient(new mywebClient());

        proBar = (ProgressBar) findViewById(R.id.progressBar1);


//        // OneSignal Initialization
//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();
    }


    //For webview progress bar loading

    public class mywebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            proBar.setVisibility(View.GONE);
            //setTitle(view.getTitle());

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            proBar.setVisibility(View.VISIBLE);
            //setTitle("Loading.....");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    //End webview progress bar


    //For internet connection

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    //End internet connection


    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }

        if (bdJobsCareers.canGoBack()) {
            bdJobsCareers.goBack();
        } else {
            super.onBackPressed();
        }
    }

    //For menu view

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bd_jobs_careers, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_facebook) {

            if (isNetworkConnected()) {
                Intent facebookIntent = openFacebook(this);
                startActivity(facebookIntent);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }

        if (id == R.id.action_ratings) {

            if (isNetworkConnected()) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("market://details?id=bdjobscareers.bd.com.bdjobscareers"));
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }


        if (id == R.id.update) {

            if (isNetworkConnected()) {
                Intent devAccount = new Intent(Intent.ACTION_VIEW);
                devAccount.setData(Uri.parse("market://details?id=bdjobscareers.bd.com.bdjobscareers"));
                startActivity(devAccount);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }


        if (id == R.id.home) {

            if (isNetworkConnected()) {
                bdJobsCareers.loadUrl("https://www.bdjobscareers.com/");

            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    //End menu view

    //Open facebook page

    public static Intent openFacebook(Context context) {


        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;

            boolean activated = packageManager.getApplicationInfo("com.facebook.katana", 0).enabled;
            if (activated) {
                if ((versionCode >= 3002850)) {

                    return new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://facewebmodal/f?href=" + FACEBOOK_URL));

                } else {
                    return new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://page/" + FACEBOOK_PAGE_ID));

                }
            } else {
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse(FACEBOOK_URL));
            }
        } catch (PackageManager.NameNotFoundException e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(FACEBOOK_URL));
        }
    }

    //End opne facebook page

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.govtJobs) {
            // Handle the camera action
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/government-job/");
        } else if (id == R.id.privateJobs) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/private-job/");

        } else if (id == R.id.allBdJobs) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/bd-jobs/");

        } else if (id == R.id.bdJobsToday) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/bd-jobs-today/");

        } else if (id == R.id.weeklyJobs) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/weekly-jobs-circular/");

        } else if (id == R.id.admitCard) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/admit-card-download/");

        } else if (id == R.id.teleTalk) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/teletalk-application/");

        } else if (id == R.id.suggestions) {
            bdJobsCareers.loadUrl(" https://www.bdjobscareers.com/all-suggestions/");

        } else if (id == R.id.bankJObs) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/bank-job/");

        } else if (id == R.id.allResult) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/public-result/");

        } else if (id == R.id.partTimeJobs) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/part-time-jobs-in-dhaka/");

        } else if (id == R.id.newPaperJobs) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/newspaper-jobs-circular-bangladesh/");

        } else if (id == R.id.careerTips) {
            bdJobsCareers.loadUrl("https://www.bdjobscareers.com/career-tips/");

        } else if (id == R.id.bdcircular) {
            if (isNetworkConnected()) {

                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("market://details?id=alljobscircularbd.bd.com.alljobscircularbd"));
                startActivity(i);

            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
