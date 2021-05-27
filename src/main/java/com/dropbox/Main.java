/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

/**
 *
 * @author Rogerio Xonchim Alves Correa
 */
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.users.FullAccount;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    private static final String ACCESS_TOKEN = "x6zR7TtKf28AAAAAAAAAAWQIyvdZpoEcA8GqyDI0iegEo8jIo0BsYetB6GJXTdYN";

    public static void main(String args[]) throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
//        Upload to Dropbox
        try (InputStream in = new FileInputStream("C:\\Users\\Rogerio\\Pictures\\Rogerio Voti.jpeg")) {
            FileMetadata metadata = client.files().uploadBuilder("/Cupom/1234.jpeg").uploadAndFinish(in);
            System.out.println(metadata);
        }


        try {
//            SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings("/teste/imagens/vBoleto2.log");
            SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings("/Cupom/1234.jpeg");
            System.out.println("Link: "+sharedLinkMetadata.getUrl());
        } catch (CreateSharedLinkWithSettingsErrorException ex) {
            System.out.println(ex);
        } catch (DbxException ex) {
            System.out.println(ex);
        }
    }
}
