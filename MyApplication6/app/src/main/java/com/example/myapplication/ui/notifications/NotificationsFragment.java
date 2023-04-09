package com.example.myapplication.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.PWActivity;
import com.example.myapplication.PWsettingActivity;
import com.example.myapplication.R;

public class NotificationsFragment extends Fragment {

    TextView textView;
    private NotificationsViewModel notificationsViewModel;
    private Button PWsetting;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        PWsetting = root.findViewById(R.id.passwordsetting);
        PWsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), PWsettingActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
