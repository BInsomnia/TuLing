package com.example.administrator.tuling;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
/**
 * 聊天信息适配器
 */
public class ChatMessageAdapter extends BaseAdapter {

    private List<ChatMessage> list;//数据源

    // 通过构造方法将数据源与数据适配器关联起来
    public ChatMessageAdapter(List<ChatMessage> list) {
        this.list = list;
    }

    //ListView需要显示的数据数量
    @Override
    public int getCount() {
        return list.isEmpty() ? 0 : list.size();  //三目运算符，如果集合里面不存在则为0，存在list.size()
    }

    //指定的索引对应的数据项
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //指定的索引对应的数据项ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = list.get(position);
        // 如果是接收消息：0，发送消息：1
        if (chatMessage.getType() == ChatMessage.Type.INCOUNT) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    //获取每一行显示的内容
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewHolder viewHolder = null;
            // 通过ItemType加载不同的布局
            if (getItemViewType(position) == 0) {//如果接受消息，则加载左边的布局
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_left, null);
                viewHolder = new ViewHolder();
                //对viewHolder的属性进行赋值
                viewHolder.chat_time = (TextView) convertView.findViewById(R.id.chat_left_time);
                viewHolder.chat_message = (TextView) convertView.findViewById(R.id.chat_left_message);
            } else {//如果发送消息，则加载右边的布局
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_right, null);
                viewHolder = new ViewHolder();
                //对viewHolder的属性进行赋值
                viewHolder.chat_time = (TextView) convertView.findViewById(R.id.chat_right_time);
                viewHolder.chat_message = (TextView) convertView.findViewById(R.id.chat_right_message);
            }
            convertView.setTag(viewHolder);//通过setTag将convertView与viewHolder关联
        }
        // 设置数据
        ViewHolder vh = (ViewHolder) convertView.getTag();//如果缓存池中有对应的view缓存，则直接通过getTag取出viewHolder
        // 取出bean对象
        ChatMessage chatMessage = list.get(position);
        // 设置控件的数据
        vh.chat_time.setText(DateUtils.dateToString(chatMessage.getData()));
        vh.chat_message.setText(chatMessage.getMessage());
        return convertView;
    }

    /**
     * 内部类：只寻找一次控件，优化内存 ViewHolder用于缓存控件，三个属性分别对应item布局文件的三个控件
     */
    private class ViewHolder {
        private TextView chat_time, chat_message;
    }
}
