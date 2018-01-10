package com.androidtask.productcategory.gsonvalues;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VIVEKS on 10/18/2016.
 */
public class GetCategoryDetails {

    @SerializedName("result")
    public List<ParentCategory> categoryList;

}
