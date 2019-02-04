package com.aqeel.johnwick.jsontry.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.aqeel.johnwick.jsontry.R;
import com.techhuntdevelopers.library.builder.AboutBuilder;
import com.techhuntdevelopers.library.views.AboutView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class AboutFragment extends Fragment {
    private FrameLayout about;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.about_fragment, container, false);



        about = v.findViewById(R.id.about);

        AboutBuilder builder = AboutBuilder.with(getContext())
                .setBackgroundColor(R.color.colorPrimaryDark)
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .setPhoto(R.drawable.profilepicwall)
                .setCover(R.mipmap.profile_cover)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("Ch Aqeel Zafar")
                .setSubTitle("Android Appilication Developer")
                .setLinksColumnsCount(4)
                .setBrief("Develop Innovatives")
                .addGooglePlayStoreLink("com.aqeel.johnwick.forcestest")
                .addGitHubLink("ChAqeelZafar")
                .addFacebookLink("ch.aqeel.zafar")
                .addTwitterLink("Aqeelay")
                .addInstagramLink("chaudhary.here")
                .addGooglePlusLink("118446581222229710827")
                .addEmailLink("aqeelzafar195@gmail.com")
                .addWhatsappLink("Ch Aqeel Zafar", "+923013471258")
                .addFiveStarsAction("com.aqeel.johnwick.forcestest")
                .addMoreFromMeAction("Ch Aqeel")
                .setVersionNameAsAppSubTitle()
                .addShareAction("Wallpaper App")
                .addUpdateAction("com.aqeel.johnwick.forcestest")
                .setActionsColumnsCount(2)
                .addFeedbackAction("aqeelzafar195@gmail.com")
                .addRemoveAdsAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        about.addView(view);

        return v;
    }



}
