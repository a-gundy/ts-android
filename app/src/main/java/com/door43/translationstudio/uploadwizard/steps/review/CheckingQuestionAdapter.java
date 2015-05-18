package com.door43.translationstudio.uploadwizard.steps.review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.door43.translationstudio.R;
import com.door43.translationstudio.projects.CheckingQuestion;

import java.util.List;

/**
 * Created by joel on 5/16/2015.
 */
public class CheckingQuestionAdapter extends BaseAdapter {
    private final Context mContext;
    private List<CheckingQuestion> mQuestions;

    public CheckingQuestionAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        if(mQuestions != null) {
            return mQuestions.size();
        } else {
            return 0;
        }
    }

    @Override
    public CheckingQuestion getItem(int position) {
        return mQuestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.fragment_upload_review_item, null);
            holder.questionText = (TextView)v.findViewById(R.id.questionTextView);
            holder.answerText = (TextView)v.findViewById(R.id.answerTextView);
            holder.imageView = (ImageView)v.findViewById(R.id.imageView);
            v.setTag(holder);
        } else {
            holder = (ViewHolder)v.getTag();
        }

        holder.questionText.setText(getItem(position).question);
        holder.answerText.setText(getItem(position).answer);
        if(getItem(position).isViewed()) {
            holder.imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_check_small));
            holder.answerText.setVisibility(View.VISIBLE);
        } else {
            holder.imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_check_small_disabled));
            holder.answerText.setVisibility(View.GONE);
        }

        return v;
    }

    /**
     * Changes the data in the adapter
     * @param questions
     */
    public void changeDataset(List<CheckingQuestion> questions) {
        mQuestions = questions;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        public TextView questionText;
        public TextView answerText;
        public ImageView imageView;
    }
}
