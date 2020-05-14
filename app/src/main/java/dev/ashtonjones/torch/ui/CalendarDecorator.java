package dev.ashtonjones.torch.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.threeten.bp.LocalDate;

import dev.ashtonjones.torch.R;

public class CalendarDecorator implements DayViewDecorator {

    private CalendarDay date;


    public CalendarDecorator() {

        date = CalendarDay.today();

    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new RelativeSizeSpan(1.4f));    }

    public void setDate(LocalDate date) {
        this.date = CalendarDay.from(date);
    }
}
