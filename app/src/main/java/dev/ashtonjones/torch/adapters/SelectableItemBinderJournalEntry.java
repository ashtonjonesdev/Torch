package dev.ashtonjones.torch.adapters;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.datamodels.JournalEntry;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class SelectableItemBinderJournalEntry extends ItemBinder<JournalEntry, SelectableItemBinderJournalEntry.ViewHolder> {


    @Override
    public SelectableItemBinderJournalEntry.ViewHolder createViewHolder(ViewGroup parent) {
        return new SelectableItemBinderJournalEntry.ViewHolder(inflate(parent, R.layout.journal_entry_item));
    }

    @Override
    public void bindViewHolder(SelectableItemBinderJournalEntry.ViewHolder holder, JournalEntry item) {

        switch (item.getLevelOfAlignment()) {

            case 1:
//                holder.constraintLayout.setBackgroundColor(Color.rgb(244, 175, 50));
                holder.emojiImageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_48dp);
                break;
            case 2:
//                holder.constraintLayout.setBackgroundColor(Color.rgb(245, 189, 54));
                holder.emojiImageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_48dp);
                break;
            case 3:
//                holder.constraintLayout.setBackgroundColor(Color.rgb(247, 198, 67));
                holder.emojiImageView.setImageResource(R.drawable.ic_sentiment_neutral_black_48dp);
                break;
            case 4:
//                holder.constraintLayout.setBackgroundColor(Color.rgb(248, 209, 94));
                holder.emojiImageView.setImageResource(R.drawable.ic_sentiment_satisfied_black_48dp);
                break;
            case 5:
//                holder.constraintLayout.setBackgroundColor(Color.rgb(250, 221, 138));
                holder.emojiImageView.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_48dp);
                break;


        }

        if(item.isActionsAligned()) {

            holder.checkMarkImageView.setImageResource(R.drawable.ic_check_green_24dp);

        }

        else {

            holder.checkMarkImageView.setImageResource(R.drawable.ic_clear_red_24dp);

        }

        holder.titleTextView.setText(item.getJournalMessage());

        holder.dateTextView.setText(item.getDate());

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof JournalEntry;
    }

    // TODO: Issue with this class not being static?
    public class ViewHolder extends ItemViewHolder<JournalEntry> {

        // Reference variables for the ViewHolder that represent the Views in the cards and the String for the message

        private MaterialTextView titleTextView;

        private ConstraintLayout constraintLayout;

        private String journalMessage;

        private MaterialCardView materialCardView;

        private ImageView emojiImageView;

        private ImageView checkMarkImageView;

        private MaterialTextView dateTextView;


        public ViewHolder(View itemView) {

            super(itemView);

            materialCardView = itemView.findViewById(R.id.journal_entry_card_view);

            titleTextView = itemView.findViewById(R.id.journal_entry_card_view_title_text_view);

            emojiImageView = itemView.findViewById(R.id.emoji_image_view_journal_entry_item);

            constraintLayout = itemView.findViewById(R.id.constraint_layout_journal_entry_item);

            checkMarkImageView = itemView.findViewById(R.id.check_mark_image_view_journal_entry_item);

            dateTextView = itemView.findViewById(R.id.journal_entry_date_text_view);


            // Select/Deselect the card on click
            itemView.setOnClickListener(view -> {

//                itemAdapterPosition = getAdapterPosition();

                toggleItemSelection();

            });

        }


    }
}
