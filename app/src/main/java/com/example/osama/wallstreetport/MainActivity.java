package com.example.osama.wallstreetport;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private LooseChange LC;
    private LemonadeShop LS;
    private SmallBusiness SB;
    private ChainStore CS;
    private Stock S;
    private HedgeFund HF;

    private volatile double cash;
    private Earnings cashPerDay;

    private Button LCButton;
    private Button LSButton;
    private Button SBButton;
    private Button CSButton;
    private Button sButton;
    private Button HFButton;

    private TextView cashView;
    private TextView cashPerDayView;

    private final DecimalFormat df = new DecimalFormat("0.00");

    private Handler handler;

    private Runnable runnable;

    private final int frames = 30;

    private final int full = 255;
    private final int part = 204;
    MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.i_fall_apart);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ring.setLooping(true);
        ring.start();

        LC = new LooseChange();
        LS = new LemonadeShop();
        SB = new SmallBusiness();
        CS = new ChainStore();
        S = new Stock();
        HF = new HedgeFund();

        cash = 1;
        cashPerDay = new Earnings();

        LCButton = (Button) findViewById(R.id.LooseChange);
        LSButton = (Button) findViewById(R.id.LemonadeStand);
        SBButton = (Button) findViewById(R.id.SmallBusiness);
        CSButton = (Button) findViewById(R.id.ChainStore);
        sButton  = (Button) findViewById(R.id.Stock);
        HFButton = (Button) findViewById(R.id.HedgeFund);

        cashView = (TextView) findViewById(R.id.cashView);
        cashPerDayView = (TextView) findViewById(R.id.cashPerDayView);

        cashView.setText(getString(R.string.cash, df.format(cash)));
        cashPerDayView.setText(getString(R.string.cashPerDay, df.format(cashPerDay.getMoneyPerSec())));
        LCButton.setText(getString(R.string.button_price, df.format(LC.getPrice())));
        LSButton.setText(getString(R.string.button_price, df.format(LS.getPrice())));
        SBButton.setText(getString(R.string.button_price, df.format(SB.getPrice())));
        CSButton.setText(getString(R.string.button_price, df.format(CS.getPrice())));
        sButton.setText(getString(R.string.button_price, df.format(S.getPrice())));
        HFButton.setText(getString(R.string.button_price, df.format(HF.getPrice())));


        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                cash += (cashPerDay.getMoneyPerSec()/frames);
                cashView.setText(getString(R.string.cash, df.format(cash)));
                cashPerDayView.setText(getString(R.string.cashPerDay, df.format(cashPerDay.getMoneyPerSec())));
                LCButton.setText(getString(R.string.button_price, df.format(LC.getPrice())));
                LSButton.setText(getString(R.string.button_price, df.format(LS.getPrice())));
                SBButton.setText(getString(R.string.button_price, df.format(SB.getPrice())));
                CSButton.setText(getString(R.string.button_price, df.format(CS.getPrice())));
                sButton.setText(getString(R.string.button_price, df.format(S.getPrice())));
                HFButton.setText(getString(R.string.button_price, df.format(HF.getPrice())));

                if(LC.getPrice() > cash){
                    LCButton.setBackgroundColor(Color.rgb(full, part, part));
                }
                else{
                    LCButton.setBackgroundColor(Color.rgb(part, full, part));
                }
                if(LS.getPrice() > cash){
                    LSButton.setBackgroundColor(Color.rgb(full, part, part));
                }
                else{
                    LSButton.setBackgroundColor(Color.rgb(part, full, part));
                }
                if(SB.getPrice() > cash){
                    SBButton.setBackgroundColor(Color.rgb(full, part, part));
                }
                else{
                    SBButton.setBackgroundColor(Color.rgb(part, full, part));
                }
                if(CS.getPrice() > cash){
                    CSButton.setBackgroundColor(Color.rgb(full, part, part));
                }
                else{
                    CSButton.setBackgroundColor(Color.rgb(part, full, part));
                }
                if(S.getPrice() > cash){
                    sButton.setBackgroundColor(Color.rgb(full, part, part));
                }
                else{
                    sButton.setBackgroundColor(Color.rgb(part, full, part));
                }
                if(HF.getPrice() > cash){
                    HFButton.setBackgroundColor(Color.rgb(full, part, part));
                }
                else{
                    HFButton.setBackgroundColor(Color.rgb(part, full, part));
                }

                handler.postDelayed(this, 1000/frames);
            }
        };

        handler.postDelayed(runnable, 1000);

        LCButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                double price = LC.getPrice();
                if(cash < price){

                }
                else{
                    cash -= price;
                    LC.getItems();
                    LC.changePrice();
                    cashPerDay.setMoneyPerSec();
                    cashView.setText(getString(R.string.cash, df.format(cash)));
                    cashPerDayView.setText(getString(R.string.cashPerDay, df.format(cashPerDay.getMoneyPerSec())));
                    LCButton.setText(getString(R.string.button_price, df.format(LC.getPrice())));
                }
            }
        });

        LSButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View V){
                double price = LS.getPrice();
                if(cash < price){

                }
                else{
                    cash -= price;
                    LS.getItems();
                    LS.changePrice();
                    cashPerDay.setMoneyPerSec();
                    cashView.setText(df.format(cash));
                    cashPerDayView.setText(df.format(cashPerDay.getMoneyPerSec()));
                }
            }
        });

        SBButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double price = SB.getPrice();
                if(cash < price){

                }
                else{
                    cash -= price;
                    SB.getItems();
                    SB.changePrice();
                    cashPerDay.setMoneyPerSec();
                    cashView.setText(df.format(cash));
                    cashPerDayView.setText(df.format(cashPerDay.getMoneyPerSec()));
                }
            }
        });

        CSButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double price = CS.getPrice();
                if(cash < price){

                }
                else{
                    cash -= price;
                    CS.getItems();
                    CS.changePrice();
                    cashPerDay.setMoneyPerSec();
                    cashView.setText(df.format(cash));
                    cashPerDayView.setText(df.format(cashPerDay.getMoneyPerSec()));
                }
            }
        });

        sButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double price = S.getPrice();
                if(cash < price){

                }
                else{
                    cash -= price;
                    S.getItems();
                    S.changePrice();
                    cashPerDay.setMoneyPerSec();
                    cashView.setText(df.format(cash));
                    cashPerDayView.setText(df.format(cashPerDay.getMoneyPerSec()));
                }
            }
        });

        HFButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double price = HF.getPrice();
                if(cash < price){

                }
                else{
                    cash -= price;
                    HF.getItems();
                    HF.changePrice();
                    cashPerDay.setMoneyPerSec();
                    cashView.setText(df.format(cash));
                    cashPerDayView.setText(df.format(cashPerDay.getMoneyPerSec()));
                }
            }
        });


    }

    public class Earnings {
        private double moneyPerSec = 0;

        private double factor = 1.05;

        public double getMoneyPerSec() {
            return moneyPerSec;
        }

        public void setMoneyPerSec() {
            if (LC.getNumOwned() > 0) {
                moneyPerSec += 0.1 * (Math.pow(factor, LC.getNumOwned()));
            }
            if (LS.getNumOwned() > 0) {
                moneyPerSec += (Math.pow(factor, LS.getNumOwned()));
            }
            if (SB.getNumOwned() > 0) {
                moneyPerSec += 10 * (Math.pow(factor, SB.getNumOwned()));
            }
            if (CS.getNumOwned() > 0) {
                moneyPerSec += 100 * (Math.pow(factor, CS.getNumOwned()));
            }
            if (S.getNumOwned() > 0) {
                moneyPerSec += 1000 * (Math.pow(factor, S.getNumOwned()));
            }
            if (HF.getNumOwned() > 0) {
                moneyPerSec += 10000 * (Math.pow(factor, HF.getNumOwned()));
            }
            moneyPerSec = Math.round(moneyPerSec * 100) / 100.0;
        }
    }

    public void Buy(){
        double price = LC.getPrice();
        if(cash < price){

        }
        else{
            cash -= price;
            LC.getItems();
            LC.changePrice();
            cashPerDay.setMoneyPerSec();
            cashView.setText(df.format(cash));
            cashPerDayView.setText(df.format(cashPerDay.getMoneyPerSec()));
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (this.isFinishing()){
            ring.stop();
        }
    }
}
