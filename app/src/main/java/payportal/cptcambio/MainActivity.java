package payportal.cptcambio;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private SliderAdapter sliderAdapter;
    private Button mNextButton;
    private Button mBackButton;
    private int mCurrentPage;


    @Override
    public void onBackPressed() {
        if (mCurrentPage > 0){
            mBackButton.performClick();
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Deseja sair?")
                    .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onBackPressed();
                            System.exit(1);
                        }
                    })
                    .setNegativeButton("Voltar", null)
                    .show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mPrefs = getSharedPreferences("application", 0);
        boolean show = mPrefs.getBoolean("slideShow", false);
        if (show){
            Intent intent = new Intent(MainActivity.this, LandPageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_main);
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mNextButton = (Button) findViewById(R.id.nextButton);
        mBackButton = (Button) findViewById(R.id.backButton);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentPage == (mDots.length - 1)) {
                    Intent intent = new Intent(MainActivity.this, LandPageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    SharedPreferences mPrefs = getSharedPreferences("application", 0);
                    SharedPreferences.Editor mEditor = mPrefs.edit();
                    mEditor.putBoolean("slideShow",true);
                    mEditor.commit();
                    finish();
                } else {
                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                }


            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

    }

    public void addDotsIndicator(int position){
      mDots = new TextView[3];
      mDotLayout.removeAllViews();
      for (int i = 0; i < mDots.length; i++){
          mDots[i] = new TextView(this);
          mDots[i].setText(Html.fromHtml(getString(R.string.dotHtml)));
          mDots[i].setTextSize(35);

          mDotLayout.addView(mDots[i]);
      }
      if(mDots.length > 0 ){
          mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
      }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;
            if (position == 0){
                mNextButton.setEnabled(true);
                mBackButton.setEnabled(false);
                mBackButton.setVisibility(View.INVISIBLE);

                mNextButton.setText("Próximo");
                mBackButton.setText("");
            } else if (position == (mDots.length - 1) ){
                mNextButton.setEnabled(true);
                mBackButton.setEnabled(true);
                mBackButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Fechar");
                mBackButton.setText("Voltar");

            } else{
                mNextButton.setEnabled(true);
                mBackButton.setEnabled(true);
                mBackButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Próximo");
                mBackButton.setText("Voltar");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
