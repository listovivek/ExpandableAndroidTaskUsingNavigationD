package com.androidtask.productcategory.gsonvalues;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VIVEKS on 10/18/2016.
 */
public class ParentCategory {

    @SerializedName("title")
    public String parentProductName;

    @SerializedName("entity_id")
    public String parentCategoryID;

    @SerializedName("price")
    public String price;

    @SerializedName("ProductCode")
    public String productcode;

    @SerializedName("product_image_url")
    public String imageURL;

    @SerializedName("subcategory")
    public List<ChildCategory> subCategoryList;

}
