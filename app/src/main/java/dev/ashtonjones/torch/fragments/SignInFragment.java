package dev.ashtonjones.torch.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentSignInBinding;
import dev.ashtonjones.torch.datamodels.User;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    private static final int RC_SIGN_IN = 123;
    private static final String LOG_TAG = SignInFragment.class.getSimpleName();

    private FragmentSignInBinding binding;

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();



    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setClickListeners();
    }

    private void setClickListeners() {

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    private void signIn() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Start the FirebaseUI Sign in Activity
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers).setIsSmartLockEnabled(false)
                        .build(),
                RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //
        if (requestCode == RC_SIGN_IN) {

            // Handle the SignInResponse
            handleSignInResponse(resultCode, data);

        }
    }

    private void handleSignInResponse(int resultCode, @Nullable Intent data) {

        // Get the Authentication response
        IdpResponse response = IdpResponse.fromResultIntent(data);

        // Successfully signed in: Go to SignedIn Activity
        if (resultCode == RESULT_OK) {

            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            Log.i(LOG_TAG, "Successfully signed in user: " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

//            Toast.makeText(getContext(), "Successfully signed in user: " + firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();


            if (response.isNewUser()) {

                Toast.makeText(getContext(), "Welcome, " + firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();

                // Create a new User in the database
                createNewUser();

                Navigation.findNavController(getView()).navigate(R.id.welcome_nav_graph);


            } else {

                Navigation.findNavController(getView()).navigate(R.id.home_fragment_dest);


            }


        } else {
            // Sign in failed
            if (response == null) {
                // User pressed back button
                Toast.makeText(getContext(), "Cancelled Sign In", Toast.LENGTH_SHORT).show();
                return;
            }

            if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                Toast.makeText(getContext(), "No internet Connection", Toast.LENGTH_SHORT).show();
                return;
            }

            if (response.getError().getErrorCode() == ErrorCodes.ERROR_USER_DISABLED) {
                Toast.makeText(getContext(), "Account is disabled by administrator", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "Unknown error", Toast.LENGTH_SHORT).show();

            Log.e(LOG_TAG, "Sign-in error: ", response.getError());
        }
    }

    /**
     * Create a new User in the users collection in the database, using the FirebaseUser id as the uID
     */

    private void createNewUser() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        User newUser = new User(firebaseUser.getDisplayName(), firebaseUser.getUid());

        firebaseFirestore.collection("users").document(firebaseUser.getUid()).set(newUser);


    }
}
