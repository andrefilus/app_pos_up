package br.com.renegades.posupml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtPrice;
    EditText inputHability;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputHability = findViewById(R.id.input_hability);
        btnSend = findViewById(R.id.btnSend);
        txtPrice = findViewById(R.id.txtPrice);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHability(inputHability.getText().toString());
            }
        });
    }

    private void sendHability(String hability){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://projetoposml.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AppService service = retrofit.create(AppService.class);
        service.valuePlayer(Float.parseFloat(hability)).enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if(response.body() != null) {
                    Player player = response.body();
                    txtPrice.setText(String.valueOf(player.getPrice()));
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

            }
        });
    }
}
