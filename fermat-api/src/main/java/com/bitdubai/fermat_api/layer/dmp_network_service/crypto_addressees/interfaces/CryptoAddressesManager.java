package com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.interfaces;

import com.bitdubai.fermat_api.layer.all_definition.enums.ReferenceWallet;
import com.bitdubai.fermat_api.layer.all_definition.money.CryptoAddress;
import com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.enums.AddressExchangeState;
import com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.exceptions.CantAcceptAddressExchangeException;
import com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.exceptions.CantGetCryptoAddessException;
import com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.exceptions.CantGetCurrentStateException;
import com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.exceptions.CantGetPendingContactRequestsListException;
import com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.exceptions.CantRegisterCompatibleListException;
import com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.exceptions.CantRejectAddressExchangeException;
import com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.exceptions.CantStartAddressExchangeException;

import java.util.List;
import java.util.UUID;

/**
 * The interface <code>com.bitdubai.fermat_api.layer.dmp_network_service.crypto_addressees.interfaces.CryptoAddressesManager</code>
 * provide the methods to manage the exchange of crypto addresses between wallets.
 */
public interface CryptoAddressesManager {

    /**
     * The method <code>exchangeAddressesAndAddContact</code> send a request of exchange of addresses to the intra
     * user to add as contact
     *
     * @param walletPublicKey                    The public key of the wallet sending the request
     * @param referenceWallet                    The type of reference wallet the wallet sending the request is associated to
     * @param cryptoAddressSent                  The crypto address sent by the sender of the request
     * @param intraUserToContactPublicKey        The public key of the intra user to ask for an addresses exchange
     * @param intraUserAskingAddressPublicKey    The public key of the intra user sending the request
     * @param intraUserAskingAddressName         The name of the intra user sending the request
     * @param intraUserAskingAddressProfileImage The profile image of the intra user sending the request
     * @throws CantStartAddressExchangeException
     */
    public void exchangeAddressesAndAddContact(String walletPublicKey,
                                               ReferenceWallet referenceWallet,
                                               CryptoAddress cryptoAddressSent,
                                               String intraUserToContactPublicKey,
                                               String intraUserAskingAddressPublicKey,
                                               String intraUserAskingAddressName,
                                               byte[] intraUserAskingAddressProfileImage) throws CantStartAddressExchangeException;



    /**
     * The method <code>exchangeAddresses</code> send a request of exchange of addresses to the intra
     * user to add as contact
     *
     * @param walletPublicKey                 The public key of the wallet sending the request
     * @param referenceWallet                 The type of reference wallet the wallet sending the request is associated to
     * @param cryptoAddressSent               The crypto address sent by the sender of the request
     * @param intraUserToContactPublicKey     The public key of the intra user to ask for an addresses exchange
     * @param intraUserAskingAddressPublicKey The public key of the intra user sending the request
     * @throws CantStartAddressExchangeException
     */
    public void exchangeAddresses(String walletPublicKey,
                                  ReferenceWallet referenceWallet,
                                  CryptoAddress cryptoAddressSent,
                                  String intraUserToContactPublicKey,
                                  String intraUserAskingAddressPublicKey) throws CantStartAddressExchangeException;


    /**
     * The method <code>acceptAddressExchange</code> send an address accepting a contact request
     *
     * @param exchangeId                            The identifier of the exchange generated by the sender
     * @param walletAcceptingTheRequestPublicKey    The public key of the wallet answering the request
     * @param referenceWallet                       The type of reference wallet the wallet answering the request is associated to
     * @param cryptoAddressSent                     The crypto address sent by the intra user accepting the request
     * @param intraUserAcceptingTheRequestPublicKey The public key of the intra user accepting the exchange
     * @param intraUserToInformAcceptancePublicKey  The public key of the intra user that sent the request
     * @throws CantAcceptAddressExchangeException
     */
    public void acceptAddressExchange(UUID exchangeId,
                                      String walletAcceptingTheRequestPublicKey,
                                      ReferenceWallet referenceWallet,
                                      CryptoAddress cryptoAddressSent,
                                      String intraUserAcceptingTheRequestPublicKey,
                                      String intraUserToInformAcceptancePublicKey) throws CantAcceptAddressExchangeException;

    /**
     * The method <code>rejectAddressExchange</code> send a rejection message to the sender of a contact request
     *
     * @param exchangeId                           The identifier of the exchange generated by the sender
     * @param walletThatAskedTheExchangePublicKey  The public key of the wallet that sent the request
     * @param intraUserThatSentTheRequestPublicKey The public key of the intra user that sent the request
     * @param intraUserRejectingTheRequest         The public key of the intra user rejecting the request
     * @throws CantRejectAddressExchangeException
     */
    public void rejectAddressExchange(UUID exchangeId,
                                      String walletThatAskedTheExchangePublicKey,
                                      String intraUserThatSentTheRequestPublicKey,
                                      String intraUserRejectingTheRequest) throws CantRejectAddressExchangeException;

    /**
     * The method <code>setCompatibleWallets</code> registers in the plugin database the information
     * of a the compatible wallets for a particular request.
     *
     * @param requestId         The identifier of the request.
     * @param compatibleWallets The list of compatible wallets of the request identified with the parameter requsetId
     * @throws CantRegisterCompatibleListException
     */
    public void setCompatibleWallets(UUID requestId, List<RequestHandlerWallet> compatibleWallets) throws CantRegisterCompatibleListException;

    /**
     * The method <code>getPendingRequests</code> return the list of requests
     *
     * @param intraUserLoggedInPublicKey The public key of the intra user asking for the pending requests directed to him
     * @return a list a request that can be handled by the intra user
     * @throws CantGetPendingContactRequestsListException
     */
    public List<PendingContactRequest> getPendingRequests(String intraUserLoggedInPublicKey) throws CantGetPendingContactRequestsListException;

    /**
     * The method <code>getCurrentExchangeState</code> returns the state of a crypto addresses exchange
     * request
     *
     * @param walletPublicKey                 The public key of the wallet sending the request
     * @param intraUserAskingAddressPublicKey The public key of the intra user we used to send the request
     * @param intraUserToContactPublicKey     The public key of the contact we sent a request
     * @return the state of the address exchange
     * @throws CantGetCurrentStateException
     */
    public AddressExchangeState getCurrentExchangeState(String walletPublicKey, String intraUserAskingAddressPublicKey, String intraUserToContactPublicKey) throws CantGetCurrentStateException;

    /**
     * The method <code>getReceivedAddress</code> is used to consult the crypto address received in
     * an exchange
     *
     * @param walletPublicKey                 The public key of the wallet sending the request
     * @param intraUserAskingAddressPublicKey The public key of the intra user we used to send the request
     * @param intraUserToContactPublicKey     The public key of the contact we sent a request
     * @return the crypto address received in the exchange
     * @throws CantGetCryptoAddessException
     */
    public CryptoAddress getReceivedAddress(String walletPublicKey, String intraUserAskingAddressPublicKey, String intraUserToContactPublicKey) throws CantGetCryptoAddessException;
}
