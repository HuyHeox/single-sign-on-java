package com.ks.sso.service;

import com.ks.sso.exception.ClientIdAndRedirectUrlNotMatch;
import com.ks.sso.exception.ClientIdNotFoundException;
import com.ks.sso.model.Client;
import com.ks.sso.model.ClientBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    public static List<Client> clients = new ArrayList<Client>();

    static {
        Client client = new Client("clientid", "clientsecret", "http://localhost:8081/");
        clients.add(client);
        Client client1 = new ClientBuilder()
                .setClientId("")
                .setClientSecret("")
                .setRedirectUrl("")
                .build();
    }

    public Client SearchByClientId(String clientId) {
        for (Client client : clients) {
            if (client.getClientId().equals(clientId)) return client;
        }
        return null;
    }

    public void ValidateClientIdAndRedirectUrl(String clientid, String redirectUrl) {
        Client client = SearchByClientId(clientid);
        if (client == null) throw new ClientIdNotFoundException("clientid is not found");
        if (client != null) {
            if (client.getRedirectUrl().equals(redirectUrl)) throw new ClientIdAndRedirectUrlNotMatch();
        }
    }

}
