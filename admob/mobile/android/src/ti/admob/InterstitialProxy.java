/**
 * Copyright (c) 2011 by Studio Classics. All Rights Reserved.
 * Author: Brian Kurzius
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.admob;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiContext.OnLifecycleEvent;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;

@Kroll.proxy(creatableInModule = AdmobModule.class)
public class InterstitialProxy extends TiViewProxy implements OnLifecycleEvent {
	private Interstitial adMob;
	private static final String LCAT = "InterstitialAdViewProxy";

	public InterstitialProxy() {
		super();
		Log.d(LCAT, "InterstitialProxy");
		createView(null);
	}

	@Override
	protected KrollDict getLangConversionTable() {
		KrollDict table = new KrollDict();
		table.put("title", "titleid");
		return table;
	}

	@Override
	public TiUIView createView(Activity activity) {
		Log.d(LCAT, "Creating an InterstitialAdView ad view");
		adMob = new Interstitial(this);
		return adMob;
	}

	@Override
	public void onDestroy(Activity activity) {
		adMob.destroy();
	}

	@Override
	public void onPause(Activity activity) {
	}

	@Override
	public void onResume(Activity activity) {
	}

	@Override
	public void onStart(Activity activity) {
	}

	@Override
	public void onStop(Activity activity) {
	}
	
	
	@Kroll.method 
	public void callAd(){
		adMob.callAd();
	}

}
