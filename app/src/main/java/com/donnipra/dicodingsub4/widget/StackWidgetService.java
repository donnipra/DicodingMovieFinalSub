package com.donnipra.dicodingsub4.widget;

/*
 * Created by donni.
 * Last modified 6/13/18 4:58 AM.
 */

import android.content.Intent;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
