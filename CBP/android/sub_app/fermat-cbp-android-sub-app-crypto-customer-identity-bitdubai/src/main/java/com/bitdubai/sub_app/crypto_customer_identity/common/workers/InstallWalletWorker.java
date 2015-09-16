package com.bitdubai.sub_app.crypto_customer_identity.common.workers;

import android.app.Activity;

import com.bitdubai.desktop.wallet_manager.common.models.WalletStoreListItem;
import com.bitdubai.fermat_android_api.layer.definition.wallet.interfaces.SubAppsSession;
import com.bitdubai.fermat_android_api.ui.interfaces.FermatWorkerCallBack;
import com.bitdubai.fermat_android_api.ui.util.FermatWorker;
import com.bitdubai.fermat_api.layer.all_definition.enums.WalletCategory;
import com.bitdubai.fermat_api.layer.all_definition.util.Version;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.interfaces.WalletStoreModuleManager;

import java.util.UUID;

import static com.bitdubai.desktop.wallet_manager.session.WalletStoreSubAppSession.BASIC_DATA;
import static com.bitdubai.desktop.wallet_manager.session.WalletStoreSubAppSession.LANGUAGE_ID;
import static com.bitdubai.desktop.wallet_manager.session.WalletStoreSubAppSession.SKIN_ID;
import static com.bitdubai.desktop.wallet_manager.session.WalletStoreSubAppSession.WALLET_VERSION;

/**
 * Created by nelson on 31/08/15.
 */
public class InstallWalletWorker extends FermatWorker {
    private final SubAppsSession session;
    private final WalletStoreModuleManager moduleManager;

    public InstallWalletWorker(Activity context, FermatWorkerCallBack callBack, WalletStoreModuleManager moduleManager, SubAppsSession session) {
        super(context, callBack);
        this.moduleManager = moduleManager;
        this.session = session;
    }

    @Override
    protected Object doInBackground() throws Exception {
        UUID languageId = (UUID) session.getData(LANGUAGE_ID);
        UUID skinId = (UUID) session.getData(SKIN_ID);
        Version version = (Version) session.getData(WALLET_VERSION);
        WalletStoreListItem catalogItem = (WalletStoreListItem) session.getData(BASIC_DATA);
        WalletCategory category = catalogItem.getCategory();
        UUID catalogueId = catalogItem.getId();

        moduleManager.installWallet(category, skinId, languageId, catalogueId, version);
        return true;
    }
}
