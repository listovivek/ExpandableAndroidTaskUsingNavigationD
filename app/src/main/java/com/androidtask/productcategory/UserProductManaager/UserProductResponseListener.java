package com.androidtask.productcategory.UserProductManaager;


import com.androidtask.productcategory.gsonvalues.GetMainCategory;
import com.androidtask.productcategory.gsonvalues.GetCategoryDetails;

/**
 * Created by VIVEKS on 10/18/2016.
 */
public interface UserProductResponseListener {

  public void onSuccessSubCategory(GetCategoryDetails userD, String subCategroy);

   public void onError(String s);

   public void onSuccessMainCategory(GetMainCategory userD);
}
