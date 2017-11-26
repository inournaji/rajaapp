package com.rajateck.wael.raja.controllers.inAppViews.rajaList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.controllers.inAppViews.rajaList.holders.AccessoryViewHolder;
import com.rajateck.wael.raja.controllers.inAppViews.rajaList.holders.MobileViewHolder;
import com.rajateck.wael.raja.enums.FragmentTags;
import com.rajateck.wael.raja.models.AccessoryItem;
import com.rajateck.wael.raja.models.AndroidApplication;
import com.rajateck.wael.raja.models.ApplicationItem;
import com.rajateck.wael.raja.models.HardWare;
import com.rajateck.wael.raja.models.Mobile;
import com.rajateck.wael.raja.models.OfferItem;

import java.io.File;
import java.util.ArrayList;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.SlideBottom;

/**
 * Created by wael on 3/3/17.
 */
public class RajaListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    BaseDownloadTask baseDownloadTask;
    private Context context;
    private ArrayList<Object> objectArrayList;
    private ArrayList<Mobile> mobiles;
    private ArrayList<AccessoryItem> accessoryItems;
    private ArrayList<OfferItem> OffersItems;
    private ArrayList<HardWare> hardWareItems;
    private ArrayList<Object> searchItems;
    private ArrayList<ApplicationItem> applicationInstalled;
    private ArrayList<AndroidApplication> applicationsBackend;
    private Activity activity;
    private FragmentTags fragmentTag;

    public RajaListAdapter(Context context, ArrayList<Object> objectArrayList, Activity activity, FragmentTags fragmentTag) {
        this.context = context;
        this.objectArrayList = objectArrayList;
        this.activity = activity;
        this.fragmentTag = fragmentTag;
    }

    public RajaListAdapter(Context context, ArrayList<Mobile> objectArrayList, Activity activity) {
        this.context = context;
        this.mobiles = objectArrayList;
        this.activity = activity;
        this.fragmentTag = FragmentTags.MobileFragment;
    }


    public RajaListAdapter(Context context, ArrayList<AccessoryItem> objectArrayList, Activity activity, Boolean check) {
        this.context = context;
        this.activity = activity;
        this.accessoryItems = objectArrayList;
        this.fragmentTag = FragmentTags.ExtensionsFragment;
    }

    public RajaListAdapter(Context context, Activity activity, ArrayList<Object> objectArrayList) {
        this.context = context;
        this.activity = activity;
        this.searchItems = objectArrayList;
        System.out.println("RajaListAdapter.RajaListAdapter");
        this.fragmentTag = FragmentTags.SearchFragment;
    }


    public RajaListAdapter(FragmentActivity activity, ArrayList<ApplicationItem> applicationItemList, ArrayList<AndroidApplication> applications, FragmentActivity activity1, FragmentTags selectedFragmentTag) {
        this.context = activity;
        this.activity = activity;
        System.out.println("RajaListAdapter.RajaListAdapter : here the mobiles");
        this.applicationInstalled = applicationItemList;
        this.applicationsBackend = applications;
        if (applicationInstalled == null) {
            this.applicationInstalled = new ArrayList<>();
        }
        if (applicationsBackend == null) {
            applicationsBackend = new ArrayList<>();
        }
        this.fragmentTag = FragmentTags.ApplicationFragment;
    }

    public RajaListAdapter(FragmentActivity activity, ArrayList<HardWare> hardWareItems, FragmentActivity activity1, boolean b, boolean b1) {
        this.context = activity;
        this.activity = activity;
        this.hardWareItems = hardWareItems;
        this.fragmentTag = FragmentTags.WarrantyFragment;
    }


    public RajaListAdapter(FragmentActivity activity, ArrayList<OfferItem> offersList, FragmentActivity activity1) {
        this.context = activity;
        this.activity = activity;
        this.OffersItems = offersList;
        this.fragmentTag = FragmentTags.OffersFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (fragmentTag.equals(FragmentTags.ApplicationFragment)) {
            View view = inflater.inflate(R.layout.recycler_list_app_item, parent, false);
            viewHolder = new RagaListItem(view);
        } else if (fragmentTag.equals(FragmentTags.OffersFragment)) {
            View view = inflater.inflate(R.layout.recycler_list_offers_items, parent, false);
            viewHolder = new OffersViewHolder(view);
        } else if (fragmentTag.equals(FragmentTags.MobileFragment)) {
            View view = inflater.inflate(R.layout.recycler_list_mobile_layout, parent, false);
            viewHolder = new MobileViewHolder(view);
        } else if (fragmentTag.equals(FragmentTags.ExtensionsFragment)) {
            View view = inflater.inflate(R.layout.recycler_list_mobile_layout, parent, false);
            viewHolder = new AccessoryViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.recycler_list_item, parent, false);
            viewHolder = new RagaListItem(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (fragmentTag.equals(FragmentTags.MobileFragment)) {

            ((MobileViewHolder) holder).onbind(mobiles.get(position), context);

        } else if (fragmentTag.equals(FragmentTags.WarrantyFragment)) {
            Glide.with(context)
                    .load(hardWareItems.get(position).getImage())
                    .placeholder(R.drawable.news_placeholder)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            if (((RagaListItem) holder).getIcon_holder() != null) {
                                ((RagaListItem) holder).getIcon_holder().setVisibility(View.GONE);
                            }
                            return false;
                        }
                    })
                    .fitCenter()
                    .into(((RagaListItem) holder).getIcon());
            ((RagaListItem) holder).getItemName().setText(hardWareItems.get(position).getTitle());
            ((RagaListItem) holder).getOldPricel().setText(hardWareItems.get(position).getPrice());
        } else if (fragmentTag.equals(FragmentTags.SearchFragment)) {

            if (searchItems.get(position) instanceof Mobile) {

                System.out.println("RajaListAdapter.onBindViewHolder: here is the data Mobile.");
                Glide.with(context)
                        .load(((Mobile) searchItems.get(position)).getImage().get(0))
                        .placeholder(R.drawable.news_placeholder)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                if (((RagaListItem) holder).getIcon_holder() != null) {
                                    ((RagaListItem) holder).getIcon_holder().setVisibility(View.GONE);
                                }
                                return false;
                            }
                        })
                        .fitCenter()
                        .into(((RagaListItem) holder).getIcon());
                ((RagaListItem) holder).getItemName().setText(((Mobile) searchItems.get(position)).getTitle());
                ((RagaListItem) holder).getOldPricel().setText(((Mobile) searchItems.get(position)).getPrice());
            } else if (searchItems.get(position) instanceof AccessoryItem) {
                System.out.println("RajaListAdapter.onBindViewHolder: here is the data Accessory.");


                Glide.with(context)
                        .load(((AccessoryItem) searchItems.get(position)).getImage().get(0))
                        .placeholder(R.drawable.news_placeholder)

                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                if (((RagaListItem) holder).getIcon_holder() != null) {
                                    ((RagaListItem) holder).getIcon_holder().setVisibility(View.GONE);
                                }
                                return false;
                            }
                        })
                        .into(((RagaListItem) holder).getIcon());
                ((RagaListItem) holder).getItemName().setText(((AccessoryItem) searchItems.get(position)).getTitle());
                ((RagaListItem) holder).getOldPricel().setText(((AccessoryItem) searchItems.get(position)).getPrice());
            }


        } else if (fragmentTag.equals(FragmentTags.ExtensionsFragment)) {
            try {
                System.out.println("RajaListAdapter.onBindViewHolder : here to load the accessory url : " + accessoryItems.get(position).getImage().get(0));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            ((AccessoryViewHolder) holder).onbind(accessoryItems.get(position), context);

        } else if (fragmentTag.equals(FragmentTags.OffersFragment)) {

            ((OffersViewHolder) holder).onBind(context, OffersItems.get(position));

        } else {
            if (fragmentTag.equals(FragmentTags.ApplicationFragment)) {

                holder.setIsRecyclable(false);

                if (position < applicationsBackend.size()) {
                    ((RagaListItem) holder).getItemName().setText(applicationsBackend.get(position).getTitle());
                    Glide.with(context)
                            .load(applicationsBackend.get(position).getImage())
                            .placeholder(R.drawable.news_placeholder)
                            .fitCenter()
                            .listener(new RequestListener<String, GlideDrawable>() {
                                @Override
                                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    if (((RagaListItem) holder).getIcon_holder() != null) {
                                        ((RagaListItem) holder).getIcon_holder().setVisibility(View.GONE);
                                    }
                                    return false;
                                }
                            })
                            .into(((RagaListItem) holder).getIcon());
                    ((RagaListItem) holder).getOldPricel().setText("");
                    ((RagaListItem) holder).getOldPricel().setTextColor(Color.GREEN);
                    ((RagaListItem) holder).getDownload().setVisibility(View.VISIBLE);

                    String path = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmpdir1" + File.separator + applicationsBackend.get(position).getTitle() + ".apk.temp";
                    String path1 = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmpdir1" + File.separator + applicationsBackend.get(position).getTitle() + ".apk";

                    try {
                        System.out.println("RajaListAdapter.onBindViewHolder : the path is = " + path);
                        File file = new File(path);
                        File file1 = new File(path1);

                        if (file.exists()) {
                            ((RagaListItem) holder).getDownload().setText("Resume");
                            ((RagaListItem) holder).getDownload().setTextColor(Color.parseColor("#ff0000"));

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ((RagaListItem) holder).getDownload().setBackground(context.getDrawable(R.drawable.rounded_white_resume_background));
                            }
                            System.out.println("RajaListAdapter.onBindViewHolder : it's exist. ");
                        } else if (file1.exists()) {
                            ((RagaListItem) holder).getDownload().setText("install");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ((RagaListItem) holder).getDownload().setBackground(context.getDrawable(R.drawable.rounded_white_install_background));
                            }
                            System.out.println("RajaListAdapter.onBindViewHolder : it's exist. ");
                        } else {
                            ((RagaListItem) holder).getDownload().setText("Download");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ((RagaListItem) holder).getDownload().setBackground(context.getDrawable(R.drawable.rounded_white_install_background));
                            }
                            System.out.println("RajaListAdapter.onBindViewHolder : it's not exist. ");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    try {
                        ((RagaListItem) holder).getVersion().setText("Version: " + applicationsBackend.get(position).getVersion());
                        ((RagaListItem) holder).getSize().setText("Size: " + applicationsBackend.get(position).getApk_Size());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    ((RagaListItem) holder).getStop_downloading().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (baseDownloadTask != null) {
                                try {
                                    ((RagaListItem) holder).getStop_downloading().setVisibility(View.GONE);
                                    ((RagaListItem) holder).getDownload().setVisibility(View.VISIBLE);
                                    ((RagaListItem) holder).getApp_progress().setVisibility(View.GONE);
                                    baseDownloadTask.pause();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }

                        }
                    });


                    ((RagaListItem) holder).getDownload().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("RajaListAdapter.onClick : here to install the app .");
                            System.out.println("RajaListAdapter.onClick : here to start downloading");

                            baseDownloadTask = downloadfile(applicationsBackend.get(position), holder);
                            baseDownloadTask.start();
                        }

                        private BaseDownloadTask downloadfile(final AndroidApplication androidApplication, final RecyclerView.ViewHolder holder) {
                            ((RagaListItem) holder).getStop_downloading().setVisibility(View.VISIBLE);
                            String path = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmpdir1" + File.separator + androidApplication.getTitle() + ".apk";
                            String url = androidApplication.getApk();
                            return FileDownloader.getImpl().create(url)
                                    .setPath(path, false)
                                    .setCallbackProgressTimes(300)
                                    .setMinIntervalUpdateSpeed(400)
                                    .setTag(androidApplication.getNid())
                                    .setListener(new FileDownloadSampleListener() {

                                        @Override
                                        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                            super.pending(task, soFarBytes, totalBytes);
                                            ((RagaListItem) holder).getDownload().setVisibility(View.VISIBLE);
                                            ((RagaListItem) holder).getDownload().setText(R.string.pendding);
                                            ((RagaListItem) holder).getApp_progress().setVisibility(View.GONE);

                                            System.out.println("RajaListAdapter.pending : it's pendding.");
                                        }

                                        @Override
                                        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                            super.progress(task, soFarBytes, totalBytes);
                                            ((RagaListItem) holder).getDownload().setVisibility(View.GONE);
                                            ((RagaListItem) holder).getDownload().setText(context.getString(R.string.resume));
                                            ((RagaListItem) holder).getApp_progress().setVisibility(View.VISIBLE);

                                            System.out.println("RajaListAdapter.progress : total = " + totalBytes);
                                            System.out.println("RajaListAdapter.progress : soFarBytes = " + soFarBytes);
                                            Double value = (((double) soFarBytes / (double) totalBytes) * 100);
                                            System.out.println("RajaListAdapter.progress : value = " + value.intValue());
                                            ((RagaListItem) holder).getApp_progress().setProgress(value.intValue());

                                        }

                                        @Override
                                        protected void error(BaseDownloadTask task, Throwable e) {
                                            super.error(task, e);
                                            System.out.println("RajaListAdapter.error : error happened :" + e.toString());
                                            e.printStackTrace();
                                            ((RagaListItem) holder).getDownload().setVisibility(View.VISIBLE);
                                            ((RagaListItem) holder).getDownload().setText(R.string.Download);
                                            ((RagaListItem) holder).getStop_downloading().setVisibility(View.GONE);
                                            ((RagaListItem) holder).getApp_progress().setVisibility(View.GONE);
                                        }

                                        @Override
                                        protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                                            super.connected(task, etag, isContinue, soFarBytes, totalBytes);
                                            System.out.println("RajaListAdapter.connected : " + task.getFilename());
                                        }

                                        @Override
                                        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                            super.paused(task, soFarBytes, totalBytes);
                                            System.out.println("RajaListAdapter.paused :" + task.getSpeed());
                                        }

                                        @Override
                                        protected void completed(BaseDownloadTask task) {
                                            super.completed(task);
                                            System.out.println("RajaListAdapter.completed");
                                            ((RagaListItem) holder).getApp_progress().setVisibility(View.VISIBLE);
                                            ((RagaListItem) holder).getApp_progress().setVisibility(View.GONE);

                                            ((RagaListItem) holder).getDownload().setVisibility(View.VISIBLE);
                                            ((RagaListItem) holder).getApp_progress().setVisibility(View.GONE);
                                            ((RagaListItem) holder).getStop_downloading().setVisibility(View.GONE);
                                            Intent intent = new Intent(Intent.ACTION_VIEW);
                                            intent.setDataAndType(Uri.fromFile(new File(task.getTargetFilePath())), "application/vnd.android.package-archive");
                                            try {
                                                activity.startActivity(intent);
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                                Toast.makeText(context, "install it from file explorer", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        protected void warn(BaseDownloadTask task) {
                                            super.warn(task);
                                            System.out.println("RajaListAdapter.warn");
                                        }
                                    });
                        }
                    });

                } else {


                    ((RagaListItem) holder).getItemName().setText(applicationInstalled.get(position - applicationsBackend.size()).getTitle());
                    ((RagaListItem) holder).getIcon().setBackgroundDrawable(applicationInstalled.get(position - applicationsBackend.size()).getAppIcon());
                    ((RagaListItem) holder).getOldPricel().setText("installed");
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mobiles != null) {
            System.out.println("RajaListAdapter.getItemCount : here 1");
            return mobiles.size();
        } else if (accessoryItems != null) {
            System.out.println("RajaListAdapter.getItemCount : here 2");
            return accessoryItems.size();
        } else if (applicationInstalled != null && applicationsBackend != null) {
            System.out.println("RajaListAdapter.getItemCount : here 3");
            return applicationsBackend.size() + applicationInstalled.size();
        } else if (searchItems != null) {
            System.out.println("RajaListAdapter.getItemCount : here 4");
            return searchItems.size();
        } else if (hardWareItems != null) {
            System.out.println("RajaListAdapter.getItemCount : here 7");
            return hardWareItems.size();
        } else if (OffersItems != null) {
            System.out.println("RajaListAdapter.getItemCount : here 9");
            return OffersItems.size();
        } else {
            System.out.println("RajaListAdapter.getItemCount : here 5");
            return objectArrayList.size();
        }
    }


    public void showAlertDialog(String title, String message) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(context);
        if (title == null) {
            title = "Sorry!";
        }
        dialogBuilder
                .withTitle(title)
                .withMessage(message)
                .withTitleColor("#000000")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessageColor("#666666")                              //def  | withMessageColor(int resid)
                .withDialogColor("#ffffff")                               //def  | withDialogColor(int resid)
                .withDuration(400)                                          //def
                .withEffect(SlideBottom)                                         //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .show();
    }
}
