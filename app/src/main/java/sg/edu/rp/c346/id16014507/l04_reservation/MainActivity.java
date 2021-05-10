package sg.edu.rp.c346.id16014507.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etTelephone;
    EditText etSize;
    CheckBox cb;
    DatePicker dp;
    TimePicker tp;
    Button btnReserve;
    Button btnReset;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextTelephone);
        etSize = findViewById(R.id.editTextSize);
        cb = findViewById(R.id.checkBoxSmoking);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);
        tvDisplay = findViewById(R.id.textViewDisplay);


        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String telephone = etTelephone.getText().toString();
                String size = etSize.getText().toString();
                int day = dp.getDayOfMonth();
                int month = dp.getMonth() + 1;
                int year = dp.getYear();
                int hour = tp.getCurrentHour();
                int minute = tp.getCurrentMinute();

                if(etName.getText().toString().trim().length() != 0 ||
                etTelephone.getText().toString().length() != 0 ||
                etSize.getText().toString().length() != 0){
                    int isize = Integer.parseInt(size);
                    if(isize >= 1 && isize <= 5){
                        if(cb.isChecked()){
                            tvDisplay.setText("Name: " + name + "\nTelephone: " + telephone +
                                    "\nSize: " + size + "\nSmoking: Yes" +
                                    "\nDate: " + day + "/" + month + "/" + year + "\nTime: " + hour + minute);
                        }
                        else{
                            tvDisplay.setText("Name: " + name + "\nTelephone: " + telephone +
                                    "\nSize: " + size + "\nSmoking: No" +
                                    "\nDate: " + day + "/" + month + "/" + year + "\nTime: " + hour + minute);
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Enter size between 1 - 5",
                                Toast.LENGTH_LONG).show();
                        etSize.setText("");
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Enter your appointment",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 8 && hourOfDay > 21){
                    tp.setCurrentHour(10);
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etTelephone.setText("");
                etSize.setText("");
                cb.setChecked(false);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5, 1);
            }
        });




    }
}