package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentSetTorchBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetTorchFragment extends Fragment {

    private FragmentSetTorchBinding binding;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public SetTorchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSetTorchBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpClickListeners();
    }

    private void setUpClickListeners() {

        binding.lightYourTorchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // TODO: Save the torch text to the database
                if(binding.textInputEditTextTorchSetTorch.getText() != null && binding.textInputEditTextTorchSetTorch.getText().length() > 0 ) {

                    String torchText = binding.textInputEditTextTorchSetTorch.getText().toString();

                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid());

                    documentReference.update("torchMessage", torchText);

                    // Change the drawable from unlit torch to lit torch
                    binding.unlitTorchSetTorch.setImageResource(R.drawable.app_icon_torch);

                    binding.setYourTorchTextView.setText("Your torch is lit!");


                    // Navigate to the next destination after 2 seconds

                    Handler delayHandler = new Handler();

                    delayHandler.postDelayed(() -> {

                        Navigation.findNavController(getView()).navigate(R.id.home_fragment_dest);

                    }, 2000);


                }

                else {

                    Toast.makeText(getContext(), "You need to set your torch before you can light it!", Toast.LENGTH_SHORT).show();

                }



            }
        });

    }
}
