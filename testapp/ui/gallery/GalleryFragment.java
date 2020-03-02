package com.example.visma.testapp.ui.gallery;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.visma.testapp.R;
import com.example.visma.testapp.yeetActivity;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    //private Button Food;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        Button Food = (Button) view.findViewById(R.id.food);
        Food.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getActivity(), yeetActivity.class);
                startActivity(in);
            }
        });

        return view;


//        galleryViewModel =
//                ViewModelProviders.of(this).get(GalleryViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//       return root;
    }
}