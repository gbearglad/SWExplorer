package eu.epfc.swexplorer;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by 0504gicarlson on 12/03/2018.
 */

public class SWPlanetsAdapter extends RecyclerView.Adapter<SWPlanetsAdapter.PlanetViewHolder> {

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    final private ListItemClickListener listItemClickListener;

    public SWPlanetsAdapter(ListItemClickListener listItemClickListener){

        this.listItemClickListener = listItemClickListener;
    }

    class PlanetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private PlanetViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: CALLED");
            int clickedPosition = this.getAdapterPosition();
            SWPlanetsAdapter.this.listItemClickListener.onListItemClick(clickedPosition);


        }
    }

    private List<Planet> planetDatas;

    public void setPlanetDatas(List<Planet> planetDatas) {
        this.planetDatas = planetDatas;
        this.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {

        if (planetDatas == null) {
            return 0;
        } else {
            return planetDatas.size();
        }

    }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View planetView = layoutInflater.inflate(R.layout.item_planet, parent, false);
        return new PlanetViewHolder(planetView);
    }

    @Override
    public void onBindViewHolder(PlanetViewHolder holder, int position) {
        Planet planet = planetDatas.get(position);
        ViewGroup itemViewGroup = (ViewGroup) holder.itemView;
        TextView planetNameTextView = itemViewGroup.findViewById(R.id.text_planet_name);
        TextView planetDiameterView = itemViewGroup.findViewById(R.id.text_planet_diameter);
        TextView planetTerrainView = itemViewGroup.findViewById(R.id.text_planet_terrain);
        ImageView populationOne = itemViewGroup.findViewById(R.id.population_logo_one);
        ImageView populationTwo = itemViewGroup.findViewById(R.id.population_logo_two);
        ImageView populationThree = itemViewGroup.findViewById(R.id.population_logo_three);
        planetNameTextView.setText(planet.getName());
        planetDiameterView.setText("Diameter: " + Double.toString(planet.getDiameter()));
        planetTerrainView.setText("Terrain: " + planet.getTerrain());
        if (planet.getPopulation() == 0) {
            populationOne.setVisibility(View.INVISIBLE);
            populationTwo.setVisibility(View.INVISIBLE);
            populationThree.setVisibility(View.INVISIBLE);

        } else if (planet.getPopulation() <= 100_000_000) {
            populationOne.setVisibility(View.VISIBLE);
            populationTwo.setVisibility(View.INVISIBLE);
            populationThree.setVisibility(View.INVISIBLE);

        } else if (planet.getPopulation() <= 1000_000_000) {
            populationOne.setVisibility(View.VISIBLE);
            populationTwo.setVisibility(View.VISIBLE);
            populationThree.setVisibility(View.INVISIBLE);

        } else {
            populationOne.setVisibility(View.VISIBLE);
            populationTwo.setVisibility(View.VISIBLE);
            populationThree.setVisibility(View.VISIBLE);

        }
    }


}


