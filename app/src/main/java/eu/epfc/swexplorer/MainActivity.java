package eu.epfc.swexplorer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SWPlanetsAdapter.ListItemClickListener{
    private ArrayList<Planet> swPlanets = new ArrayList<Planet>();
    private String loadJSONFromAsset(){
        String json = null;
        try {
            Context sweContext = getApplicationContext();
            InputStream is = sweContext.getAssets().open("planets.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonString = loadJSONFromAsset();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonPlanets = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonPlanets.length();++i){
                JSONObject jsonPlanet = jsonPlanets.getJSONObject(i);
                Planet planet = new Planet();
                planet.setName(jsonPlanet.getString("name"));
                planet.setClimate(jsonPlanet.getString("climate"));
                planet.setTerrain(jsonPlanet.getString("terrain"));
                if (jsonPlanet.getString("diameter").contentEquals("unknown")){
                    planet.setDiameter(0);
                }
                else {
                    planet.setDiameter(Double.parseDouble(jsonPlanet.getString("diameter")));
                }
                if (jsonPlanet.getString("orbital_period").contentEquals("unknown")){
                    planet.setOrbitalPeriod(0);
                } else {
                    planet.setOrbitalPeriod(Integer.parseInt(jsonPlanet.getString("orbital_period")));
                }
                if(jsonPlanet.getString("rotation_period").contentEquals("unknown")){
                    planet.setRotationPeriod(0);
                }
                else{planet.setRotationPeriod(Integer.parseInt(jsonPlanet.getString("rotation_period")));}
                if (jsonPlanet.getString("population").contentEquals("unknown")){
                    planet.setPopulation(0);

                }else{
                    planet.setPopulation(Long.parseLong(jsonPlanet.getString("population")));
                }

                swPlanets.add(planet);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SWPlanetsAdapter starwarsAdapter = new SWPlanetsAdapter(this);
        starwarsAdapter.setPlanetDatas(swPlanets);
        RecyclerView planetRecycler = findViewById(R.id.planetRecycler);
        planetRecycler.setAdapter(starwarsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        planetRecycler.setLayoutManager(linearLayoutManager);





        }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Planet selectedPlanet = swPlanets.get(clickedItemIndex);
        Intent detailIntent = new Intent(this,DetailActivity.class);
        detailIntent.putExtra("selPlanet",selectedPlanet);
        startActivity(detailIntent);

    }
}

