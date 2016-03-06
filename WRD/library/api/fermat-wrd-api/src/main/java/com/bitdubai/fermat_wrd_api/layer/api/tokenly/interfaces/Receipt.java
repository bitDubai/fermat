package com.bitdubai.fermat_wrd_api.layer.api.tokenly.interfaces;

import java.sql.Date;

/**
 * Created by Manuel Perez (darkpriestrelative@gmail.com) on 05/03/16.
 */
public interface Receipt {

    /**
     * This method returns the receipt quantity in.
     * @return
     */
    double getQuantityIn();

    /**
     * This method returns the receipt asset in.
     * @return
     */
    String getAssteIn();

    /**
     * This method returns the receipt quantity out.
     * @return
     */
    double getQuantityOut();

    /**
     * This method returns the receipt asset out.
     * @return
     */
    String getAssetOut();

    /**
     * This method returns the receipt type.
     * @return
     */
    String getType();

    /**
     * This method returns the receipt destination.
     * @return
     */
    String getDestination();

    /**
     * This method returns the receipt transaction in.
     * @return
     */
    String getTxIn();

    /**
     * This method returns the receipt transaction out.
     * @return
     */
    String getTxOut();

    /**
     * This method returns the receipt confirmations.
     * @return
     */
    int getConfirmations();

    /**
     * This method returns the receipt confirmations out.
     * @return
     */
    int getConfirmationsOut();

    long getTimestamp();

    /**
     * This method returns the receipt completed date
     * @return
     */
    Date getCompletedAt();

}
