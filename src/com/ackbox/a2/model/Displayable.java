package com.ackbox.a2.model;

import android.content.Context;

/**
 * Interface stablishing the contract for displayable objects.
 * 
 * @author trein
 * 
 */
public interface Displayable {

    String getTitle(Context context);

    String getDetail(Context context);

}
