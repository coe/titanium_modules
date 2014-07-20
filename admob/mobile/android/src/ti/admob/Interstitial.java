/**
 * Copyright (c) 2011 by Studio Classics. All Rights Reserved.
 * Author: Brian Kurzius
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.admob;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;

public class Interstitial extends TiUIView implements AdListener {
	private static final String LCAT = "InterstitialAdView";
	AdView adView;
	int prop_top;
	int prop_left;
	int prop_right;
	String prop_color_bg;
	String prop_color_bg_top;
	String prop_color_border;
	String prop_color_text;
	String prop_color_link;
	String prop_color_url;
	
	private InterstitialAd interstitial;

	public Interstitial(final TiViewProxy proxy) {
		super(proxy);
		Log.d(LCAT, "Creating an InterstitialAdView ad view");
		// get the publisher id that was set in the module
		Log.d(LCAT, "AdmobModule.PUBLISHER_ID: " + AdmobModule.PUBLISHER_ID);
	}

	private void createAdView() {
		Log.d(LCAT, "Creating an InterstitialAdView ad view:"+AdmobModule.PUBLISHER_ID);

		InterstitialAd interstitial = new InterstitialAd(proxy.getActivity(),
				"ca-app-pub-6296232780229186/5200009157");

		AdRequest adRequest = new AdRequest();

		interstitial.loadAd(adRequest);

		// Ad Listener
		interstitial.setAdListener(this);

	}
	
	
	public void callAd(){
		createAdView();
	}

	@Override
	public void processProperties(KrollDict d) {
		super.processProperties(d);
		Log.d(LCAT, "process properties");
		if (d.containsKey("publisherId")) {
			Log.d(LCAT, "has publisherId: " + d.getString("publisherId"));
			AdmobModule.PUBLISHER_ID = d.getString("publisherId");
		}

		// now create the adView
		this.createAdView();
	}

	@Override
	public void onReceiveAd(Ad ad) {
		Log.d(LCAT, "Received ad");
		if (ad == interstitial) {
			interstitial.show();
		}
	}

	// destroy the ad when done
	public void destroy() {
		adView.destroy();
	}


	public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode e) {
		Log.d(LCAT, "onFailedToReceiveAd(): " + e);
		proxy.fireEvent(AdmobModule.AD_NOT_RECEIVED, new KrollDict());
	}

	// not used
	public void onLeaveApplication(Ad ad) {
		Log.d(LCAT, "onLeaveApplication()");
	}

	public void onPresentScreen(Ad ad) {
		Log.d(LCAT, "onPresentScreen()");
	}

	public void onDismissScreen(Ad ad) {
		Log.d(LCAT, "onDismissScreen()");
	}

}