package com.ackbox.a2.model;

import android.content.Context;

/**
 * Interface establishing the contract for displayable objects.
 * 
 * @author trein
 * 
 */
public interface Displayable {

    /**
     * Title for displayable object.
     * 
     * @param context Current application context.
     * @return Title assigned to current object.
     */
    String getTitle(Context context);

    /**
     * Detail for displayable object.
     * 
     * @param context Current application context
     * @return Detail assigned to current object.
     */
    String getDetail(Context context);

}
