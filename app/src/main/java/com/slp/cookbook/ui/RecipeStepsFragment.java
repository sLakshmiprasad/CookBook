package com.slp.cookbook.ui;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slp.cookbook.Adapter.RecipeStepAdapter;
import com.slp.cookbook.R;
import com.slp.cookbook.data.Steps;
import com.slp.cookbook.utils.CookBookConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepsFragment extends Fragment implements RecipeStepAdapter.OnItemClickListener, CookBookConstants {

    private List<Steps> recipeSteps;
    private RecyclerView recipeStepsRV;
    private OnClickListener clickListener;

    public RecipeStepsFragment() {

    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            clickListener = (OnClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Must implement onClickListner");
        }
    }


    public void setRecipeSteps(List<Steps> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        if(savedInstanceState != null){
            recipeSteps = savedInstanceState.getParcelableArrayList(STEPS);
        }
        if (null != recipeSteps && recipeSteps.size() > 0) {
            recipeStepsRV = rootView.findViewById(R.id.recipe_steps_rv);
            recipeStepsRV.setAdapter(new RecipeStepAdapter(recipeSteps,this));
            recipeStepsRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recipeStepsRV.setHasFixedSize(true);

        }
        return rootView;
    }

    @Override
    public void onClick(int position) {
        clickListener.onClick(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STEPS, (ArrayList<? extends Parcelable>) recipeSteps);
    }
}
