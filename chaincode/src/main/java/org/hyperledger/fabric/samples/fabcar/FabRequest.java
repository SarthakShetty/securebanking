
package org.hyperledger.fabric.samples.fabcar;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import com.owlike.genson.Genson;

/**
 * Java implementation of the Fabric Car Contract described in the Writing Your
 * First Application tutorial
 */
@Contract(
        name = "FabRequest",
        info = @Info(
                title = "FabRequest contract",
                description = "The hyperlegendary request contract",
                version = "0.0.1-SNAPSHOT",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "f.carr@example.com",
                        name = "F Carr",
                        url = "https://hyperledger.example.com")))
@Default
public final class FabRequest implements ContractInterface {

    private final Genson genson = new Genson();

    private enum FabRequestErrors {
        REQUEST_NOT_FOUND,
        REQUEST_ALREADY_EXISTS
    }

    /**
     * Retrieves a car with the specified key from the ledger.
     *
     * @param ctx the transaction context
     * @param key the key
     * @return the Car found on the ledger if there was one
     */
    @Transaction()
    public Request queryRequest(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        String requestState = stub.getStringState(key);

        if (requestState.isEmpty()) {
            String errorMessage = String.format("Request %s does not exist", key);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, FabRequestErrors.REQUEST_NOT_FOUND.toString());
        }

        Request request = genson.deserialize(requestState, Request.class);

        return request;
    }

    
    /**
     * Creates a new car on the ledger.
     *
     * @param ctx the transaction context
     * @param key the key for the new car
     * @param make the make of the new car
     * @param model the model of the new car
     * @param color the color of the new car
     * @param owner the owner of the new car
     * @return the created Car
     */
    @Transaction()
    public Request createRequest(final Context ctx, final String key, final int req_id, final int cust_id,
            final int first_acc_num, final int second_acc_num, final int is_critical, final String approved_by,
            final char status, final String type, final Timestamp transaction_date, final Double amount) {
        ChaincodeStub stub = ctx.getStub();

        String requestState = stub.getStringState(key);
        if (!requestState.isEmpty()) {
            String errorMessage = String.format("Car %s already exists", key);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, FabRequestErrors.REQUEST_ALREADY_EXISTS.toString());
        }

        Request request = new Request(req_id, cust_id, first_acc_num, second_acc_num, is_critical, approved_by, 
                                    status, type, transaction_date, amount);
        requestState = genson.serialize(request);
        stub.putStringState(key, requestState);

        return request;
    }

}
