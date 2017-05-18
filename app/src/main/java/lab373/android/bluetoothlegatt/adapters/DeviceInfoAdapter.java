//package lab373.android.bluetoothlegatt.adapters;
//
//import android.content.Context;
//import android.support.v4.widget.TextViewCompat;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import lab373.android.bluetoothlegatt.R;
//
///**
// * Created by ianzhang on 5/17/17.
// */
//
//public class DeviceInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    String TAG = "DeviceInfoAdapter";
//    Context mContext;
//    List<String> items;
//
//    public DeviceInfoAdapter(Context mContext) {
//        this.mContext = mContext;
//        items = buildItems();
//    }
//
//    public class DeviceInfoViewHolder extends RecyclerView.ViewHolder {
//        TextView infoLabel;
//
//        public DeviceInfoViewHolder(View itemView) {
//            super(itemView);
//            infoLabel = (TextView)itemView.findViewById(R.id.device_info_recyclerView);
//        }
//
//    }
//
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int ViewType) {
//
//    }
//
////    private void configureViewHolder(RecyclerView.ViewHolder viewHolder, String item, int position) {
////
////    }
//
//    private List<String> buildItems() {
//        List<String> items = new ArrayList<>();
//        items.add(mContext.getResources().getString(R.string.mouse_action));
//        items.add(mContext.getResources().getString(R.string.zoom_in));
//        items.add(mContext.getResources().getString(R.string.zoom_out));
//        return items;
//    }
//
//    private void configureViewHolder(RecyclerView.ViewHolder holder, final int position) {
//
//    }
//}
