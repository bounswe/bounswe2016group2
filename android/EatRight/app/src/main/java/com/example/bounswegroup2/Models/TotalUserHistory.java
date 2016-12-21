package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yigitozgumus on 12/19/16.
 */
public class TotalUserHistory {
    @SerializedName("total")
    @Expose
    private Total total;

    /**
     * Gets total.
     *
     * @return the total
     */
    public Total getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(Total total) {
        this.total = total;
    }
}
