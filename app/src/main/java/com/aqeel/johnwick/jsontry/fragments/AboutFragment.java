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
                .addGooglePlayStoreLink("7399966994559253451")
                .addGitHubLink("techhuntdevelopers")
                .addFacebookLink("techhuntdevelopers")
                .addTwitterLink("user")
                .addInstagramLink("techhunt_developers")
                .addGooglePlusLink("user")
                .addYoutubeChannelLink("user")
                .addDribbbleLink("user")
                .addLinkedInLink("user")
                .addEmailLink("aqeelzafar19@gmail.com")
                .addWhatsappLink("Ch Aqeel Zafar", "+923013471258")
                .addSkypeLink("user")
                .addGoogleLink("user")
                .addAndroidLink("user")
                .addWebsiteLink("site")
                .addFiveStarsAction()
                .addMoreFromMeAction("TechHunt Developers")
                .setVersionNameAsAppSubTitle()
                .addShareAction("Wallpaper App")
                .addUpdateAction()
                .setActionsColumnsCount(2)
                .addFeedbackAction("techhunt00@gmail.com")
                .addIntroduceAction((Intent) null)
                .addHelpAction((Intent) null)
                .addChangeLogAction((Intent) null)
                .addRemoveAdsAction((Intent) null)
                .addDonateAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        about.addView(view);

        return v;
    }



}
