package com.example.osama.wallstreetport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private LooseChange LC;
    private LemonadeShop LS;
    private SmallBusiness SB;
    private ChainStore CS;
    private Stock S;
    private HedgeFund HF;

    private double cash;
    private Earnings cashPerDay;

    private Button LCButton;
    private Button LSButton;
    private Button SBButton;
    private Button CSButton;
    private Button sButton;
    private Button HFButton;

    private TextView cashView;
    private TextView cashPerDayView;

    private Timer timer;

    private final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LC = new LooseChange();
        LS = new LemonadeShop();
        SB = new SmallBusiness();
        CS = new ChainStore();
        S = new Stock();
        HF = new HedgeFund();

        cash = 10;
        cashPerDay = new Earnings();

        LCButton = (Button) findViewById(R.id.LooseChange);
        LSButton = (Button) findViewById(R.id.LemonadeStand);
        SBButton = (Button) findViewById(R.id.SmallBusiness);
        CSButton = (Button) findViewById(R.id.ChainStore);
        sButton  = (Button) findViewById(R.id.Stock);
        HFButton = (Button) findViewById(R.id.HedgeFund);

        cashView = (TextView) findViewById(R.id.cashView);
        cashPerDayView = (TextView) findViewById(R.id.cashPerDayView);

        cashView.setText(Double.toString(cash));
        cashPerDayView.setText(Double.toString(cashPerDay.getMoneyPerSec()));

//        timer = new Timer();

//        timer.scheduleAtFixedRate(task, 0, 1000 / 30);

        LCButton.setOnClickListener(new View.OnClickListener() {
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
                    cashView.setText(Double.toString(cash));
                    cashPerDayView.setText(Double.toString(cashPerDay.getMoneyPerSec()));
                }
            }
        });

    }



    public class Earnings {
        double moneyPerSec;

        public double getMoneyPerSec() {
            return moneyPerSec;
        }

        public void setMoneyPerSec() {
            moneyPerSec = 0;
            if (LS.getNumOwned() > 0) {
                moneyPerSec += (Math.pow(1.15, LS.getNumOwned()));
            }
            if (SB.getNumOwned() > 0) {
                moneyPerSec += 10 * (Math.pow(1.15, SB.getNumOwned()));
            }
            if (CS.getNumOwned() > 0) {
                moneyPerSec += 100 * (Math.pow(1.15, CS.getNumOwned()));
            }
            if (S.getNumOwned() > 0) {
                moneyPerSec += 1000 * (Math.pow(1.15, S.getNumOwned()));
            }
            if (HF.getNumOwned() > 0) {
                moneyPerSec += 10000 * (Math.pow(1.15, HF.getNumOwned()));
            }
            moneyPerSec = Math.round(moneyPerSec * 100) / 100.0;
        }
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            cash += (cashPerDay.getMoneyPerSec() / 30);
            cash = ((int) (cash * 100)) / 100.0;
            cashView.setText("Cash: $" + String.format(df.format(cash)));
            cashPerDayView.setText("$" + df.format(cashPerDay.getMoneyPerSec()) + "/day");
        }
    };

/*    public void start()
    {
        timer.scheduleAtFixedRate(task, 0, 1000 / 30);
    }
*/
}
