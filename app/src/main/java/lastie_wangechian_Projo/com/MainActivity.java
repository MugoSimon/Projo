package lastie_wangechian_Projo.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editPhoneNumber;
    private Button getCodeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editPhoneNumber = findViewById(R.id.editTextPhone);
        getCodeBtn = findViewById(R.id.getCodeBtn);

        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String number = editPhoneNumber.getText().toString().trim();

                if(number.isEmpty() || number.length() < 9 ){
                    editPhoneNumber.setText("A valid phone number is required");
                    editPhoneNumber.requestFocus();
                    return;

                }
                String phoneNumber = "+" + code + number;
                Intent intent = new Intent(MainActivity.this, CodeActivity.class);
                intent.putExtra("phonenumber", phoneNumber);
                startActivity(intent);


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
