package com.example.cheonyujung.accidentofworld;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheonyujung.accidentofworld.data.UserAuthority;
import com.example.cheonyujung.accidentofworld.data.struct.User;
import com.example.cheonyujung.accidentofworld.fragment.BoardCountryActivity;
import com.example.cheonyujung.accidentofworld.fragment.ListActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


/**
 * Created by cheonyujung on 2016. 5. 19..
 */



public class Base extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    public DrawerLayout drawer;
    public SignInButton drawerLoginBtn;
    public Button drawerLogoutBtn;
    public LinearLayout logoutLayout;
    public View drawerWorldMap_btn;
    public View drawerWorldList_btn;
    public View drawerBoard_btn;
    public RelativeLayout actionbar;
    public SearchView searchview;
    public GoogleApiClient mGoogleApiClient;
    public Toolbar toolbar;
    private ProgressDialog mProgressDialog;
    public static User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(Base.this /* FragmentActivity */, Base.this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = (DrawerLayout) findViewById(R.id.main_activity);
        drawerWorldMap_btn = findViewById(R.id.worldMapButton);
        drawerWorldList_btn = findViewById(R.id.CountryListButton);
        drawerBoard_btn = findViewById(R.id.BoardButton);
        drawerLoginBtn = (SignInButton) findViewById(R.id.loginBtn);
        drawerLogoutBtn = (Button) findViewById(R.id.sign_out_button);

        BtnListener listener = new BtnListener();
        logoutLayout = (LinearLayout) findViewById(R.id.out);

        drawerWorldMap_btn.setOnClickListener(listener);
        drawerWorldList_btn.setOnClickListener(listener);
        drawerBoard_btn.setOnClickListener(listener);
        drawerLoginBtn.setOnClickListener(listener);
        drawerLogoutBtn.setOnClickListener(listener);

    }

    private void signIn(GoogleApiClient mGoogleApiClient) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, 9001);
        System.out.println("!");
    }
    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d("test", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 9001) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            System.out.println(resultCode + "!!");
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("test", "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            logoutLayout.setVisibility(View.VISIBLE);
            drawerLoginBtn.setVisibility(View.GONE);
            String email = acct.getEmail();
            String name = acct.getDisplayName();
            user = new User();
            user.setId(name);
            user.setEmail(email);
            user.setAuthority(UserAuthority.USER);
            Toast.makeText(getApplicationContext(), "로그인 되었습니다", Toast.LENGTH_SHORT);
            Log.d("test",((LinearLayout)findViewById(R.id.out)).getVisibility()+"");
            //updateUI(true);
        } else {
            Log.d("test","logout");
            signOut();
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        user = null;
                        Log.d("test","logout");
                        logoutLayout.setVisibility(View.GONE);
                        drawerLoginBtn.setVisibility(View.VISIBLE);
                    }
                });
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("loading");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_layout, menu);

        return true;
    }

    public void setTitle(String title){
        TextView Title = (TextView)findViewById(R.id.toolbar_title);
        Title.setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.drawer_btn:
                drawer.openDrawer(Gravity.RIGHT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

        @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("onConnecttionFailed", "onConnectionFailed:" + connectionResult);
    }

    private class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            drawer.closeDrawer(Gravity.RIGHT);
            switch(view.getId()) {
                case R.id.loginBtn:
                    signIn(mGoogleApiClient);
                    break;
                case R.id.sign_out_button:
                    signOut();
                    break;
                case R.id.worldMapButton:
                    startActivity(new Intent(Base.this, WorldMap.class));
                    finish();
                    break;
                case R.id.CountryListButton:
                    startActivity(new Intent(Base.this, ListActivity.class));
                    finish();
                    break;
                case R.id.BoardButton:
                    startActivity(new Intent(Base.this, BoardCountryActivity.class));
                    finish();
                    break;
            }

        }
    }

}