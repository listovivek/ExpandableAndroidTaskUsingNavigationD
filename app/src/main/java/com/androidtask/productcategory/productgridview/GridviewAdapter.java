package com.androidtask.productcategory.productgridview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidtask.R;
import com.androidtask.productcategory.gsonvalues.GetCategoryDetails;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by VIVEKS on 10/18/2016.
 */
public class GridviewAdapter extends BaseAdapter {
    private Context context;
    private int layoutResourceId;
    private GetCategoryDetails data;
    ImageLoader imageLoader;
    private DisplayImageOptions options;
    ViewHolder holder = null;

    public GridviewAdapter(Context context, int layoutResourceId, GetCategoryDetails data) {

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    @Override
    public int getCount() {
        return data.categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;


        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.proPrice = (TextView) row.findViewById(R.id.price);
           // holder.proName = (TextView) row.findViewById(R.id.pro_name);
           // holder.progressBar = (ProgressBar) row.findViewById(R.id.progress);
            holder.image = (ImageView) row.findViewById(R.id.image);
            holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        Log.d("Price", data.categoryList.get(position).price);
        Log.d("imageURL", data.categoryList.get(position).imageURL);

        holder.proPrice.setText("Price:"+data.categoryList.get(position).price+",    "+data.categoryList.get(position).parentProductName);
       // holder.proName.setText(data.result.get(position).productName);

            imageLoader.displayImage(data.categoryList.get(position).imageURL,
                    holder.image, options, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    //holder.progressBar.setProgress(0);
                   // holder.progressBar.setVisibility(View.VISIBLE);

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    //holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    //holder.progressBar.setVisibility(View.GONE);
                }
            }, new ImageLoadingProgressListener() {
                @Override
                public void onProgressUpdate(String imageUri, View view, int current, int total) {
                   // holder.progressBar.setProgress(Math.round(100.0f * current / total));
                }
            });

       /* ImageItem item = data.get(position);
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(item.getImage());*/
        return row;
    }

    static class ViewHolder {
        TextView proPrice, proName;
        ImageView image;
        //ProgressBar progressBar;
    }
}