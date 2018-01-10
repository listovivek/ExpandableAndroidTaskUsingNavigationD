package com.androidtask.productcategory.gsonvalues;

import com.google.gson.annotations.SerializedName;

/**
 * Created by viveks on 10/20/2016.
 */
public class ChildCategory {

    @SerializedName("title")
    public String childProductName;

    @SerializedName("entity_id")
    public String childCategoryID;
}
