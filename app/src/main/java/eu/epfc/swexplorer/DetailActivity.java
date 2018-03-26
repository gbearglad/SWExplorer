package eu.epfc.swexplorer;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;



public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Planet selectedPlanet = (Planet) intent.getSerializableExtra("selPlanet");
        TextView nameDetail = findViewById(R.id.planetNameDetail);
        TextView rotationValue = findViewById(R.id.planetRotationValue);
        TextView orbitalValue = findViewById(R.id.planetOrbitalValue);
        TextView diameterValue = findViewById(R.id.planetDiameterValue);
        TextView climateValue = findViewById(R.id.planetClimateValue);
        ImageView planetImage = findViewById(R.id.planetImageDetail);
        nameDetail.setText(selectedPlanet.getName());
        rotationValue.setText(Double.toString(selectedPlanet.getRotationPeriod()));
        orbitalValue.setText(Double.toString(selectedPlanet.getOrbitalPeriod()));
        diameterValue.setText(Double.toString(selectedPlanet.getDiameter()));
        climateValue.setText(selectedPlanet.getClimate());
        String planetImageNames = selectedPlanet.getName().toLowerCase();
        Resources resources = getResources();
        int resourceId = resources.getIdentifier(planetImageNames, "drawable",getPackageName());
        if (resourceId == 0) {
            resourceId = resources.getIdentifier("generic_planet","drawable",getPackageName());
        }
        planetImage.setImageResource(resourceId);

    }
}
