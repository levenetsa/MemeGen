package gkl.memegen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class step2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        Uri uri = (Uri)getIntent().getParcelableExtra("Image");
        String str = uri.getPath();
        InputStream imageStream = null;
        try {
            imageStream = getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);

        TextView tv = new TextView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(yourSelectedImage.getHeight(),yourSelectedImage.getWidth());
        tv.setLayoutParams(layoutParams);
        tv.setText("It's our first meme");
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(Color.TRANSPARENT);

        Bitmap testB;

        testB = yourSelectedImage;//Bitmap.createBitmap(yourSelectedImage.getHeight(), yourSelectedImage.getWidth(), Bitmap.Config.ARGB_8888);

        Bitmap mutableBitmap = testB.copy(Bitmap.Config.ARGB_8888, true);
        Canvas c = new Canvas(mutableBitmap);
        tv.layout(0, 0, yourSelectedImage.getHeight()*4, yourSelectedImage.getWidth()*4);
        tv.draw(c);




        Bitmap tempBitmap = Bitmap.createBitmap(testB.getWidth()*4, testB.getHeight()*4, Bitmap.Config.RGB_565);

        Canvas tempCanvas = new Canvas(tempBitmap);

        tempCanvas.drawBitmap(testB, 0, 0, null);
        Bitmap scaled = Bitmap.createScaledBitmap(testB,testB.getWidth()*4, testB.getHeight()*4, true);
        tempCanvas.drawBitmap(scaled, 0, 0, null);
        tempCanvas.scale(4,4);
        tv.draw(tempCanvas);

        imageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));


        //imageView.setImageBitmap(yourSelectedImage);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.step2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.newMeme) {
            //   Intent intent = new Intent(this, step_1.class);
            //  startActivity(intent);

        } else if (id == R.id.yourMemes) {

        } else if (id == R.id.tools) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
