package com.jun.znews.ui.news.channel;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jun.znews.R;
import com.jun.znews.bean.Channel;
import com.jun.znews.event.NewChannelEvent;
import com.jun.znews.event.SelectChannelEvent;
import com.jun.znews.ui.adapter.ChannelAdapter;

import org.simple.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChannelDialogFragment extends DialogFragment implements OnChannelListener {
    private List<Channel> mDatas = new ArrayList<>();
    RecyclerView mRecyclerView;
    private ItemTouchHelper mHelper;
    private ImageView miVClose;
    private boolean isUpdate = false;
    private ChannelAdapter mAdapter;
    List<Channel> mSelectedDatas;
    List<Channel> mUnSelectedDatas;
    private String firstAddChannelName = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            //添加动画
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }
        return inflater.inflate(R.layout.dialog_channel, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView =  view.findViewById(R.id.recyclerView);
        miVClose = view.findViewById(R.id.icon_collapse);
        miVClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        processLogic();
    }

    public static ChannelDialogFragment newInstance(List<Channel> selectedDatas, List<Channel> unselectedDatas) {
        ChannelDialogFragment dialogFragment = new ChannelDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataSelected", (Serializable) selectedDatas);
        bundle.putSerializable("dataUnselected", (Serializable) unselectedDatas);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    private void setDataType(List<Channel> datas, int type) {
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setItemtype(type);
        }
    }

    private void processLogic() {
        Channel channel = new Channel();
        channel.setItemtype(Channel.TYPE_MY);
        channel.setChannelName("我的频道");
        mDatas.add(channel);
        //添加特殊的两个
        Bundle bundle = getArguments();
        mSelectedDatas = (List<Channel>) bundle.getSerializable("dataSelected");
        mUnSelectedDatas = (List<Channel>) bundle.getSerializable("dataUnselected");
        setDataType(mSelectedDatas, Channel.TYPE_MY_CHANNEL);
        setDataType(mUnSelectedDatas, Channel.TYPE_OTHER_CHANNEL);
        mDatas.addAll(mSelectedDatas);
        Channel moreChannel = new Channel();
        moreChannel.setItemtype(Channel.TYPE_OTHER);
        moreChannel.setChannelName("频道推荐");
        mDatas.add(moreChannel);
        mDatas.addAll(mUnSelectedDatas);
        //设置监听器
        ItemDragHelperCallBack callback = new ItemDragHelperCallBack(this);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
        //设置和适配器
        mAdapter = new ChannelAdapter(mDatas, helper);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                return itemViewType == Channel.TYPE_MY_CHANNEL || itemViewType == Channel.TYPE_OTHER_CHANNEL ? 1 : 4;
            }
        });
        mAdapter.OnChannelListener(this);

    }



    @Override
    public void onItemMove(int starPos, int endPos) {
        if (starPos < 0 || endPos < 0) return;
        if (mDatas.get(endPos).getChannelName().equals("头条")) return;
        //我的频道之间移动
        onMove(starPos, endPos, false);
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        onMove(starPos, endPos, true);
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        onMove(starPos, endPos, false);
    }

    @Override
    public void onFinish(String selectedChannelName) {
        EventBus.getDefault().post(new SelectChannelEvent(selectedChannelName));
        firstAddChannelName = selectedChannelName ;
        dismiss();
    }

    @Override
    public void onPause() {
        if (isUpdate) {
            EventBus.getDefault().post(new NewChannelEvent(mAdapter.getData(), firstAddChannelName));
        }
        super.onPause();
    }


    private void onMove(int starPos, int endPos, boolean isAdd) {
        isUpdate = true;
        Channel startChannel = mDatas.get(starPos);
        //先删除之前的位置
        mDatas.remove(starPos);
        //添加到现在的位置
        mDatas.add(endPos, startChannel);
        mAdapter.notifyItemMoved(starPos, endPos);
        if (isAdd) {
            if (TextUtils.isEmpty(firstAddChannelName)) {
                firstAddChannelName = startChannel.getChannelName();
            }
        } else {
            if (startChannel.getChannelName().equals(firstAddChannelName)) {
                firstAddChannelName = "";
            }
        }
    }


}
